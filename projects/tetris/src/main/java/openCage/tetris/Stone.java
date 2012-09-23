package openCage.tetris;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.tools.javac.util.Pair;

public class Stone {
	
//	private Map< Pair< Integer, Integer>, Boolean > blocks;
	private List< Pair<Integer, Integer >> blocks;
	boolean                                dirty;
 	
	public Stone() {
		blocks = new ArrayList<Pair<Integer,Integer>>();
		
		
		blocks.add( new Pair<Integer,Integer>( 0, 0 ));
	}
	

	public boolean canAdd( int idx, int x, int y ) {
		
		if ( idx >= size() ) {
			return false;
		}
		
		//Pair< Integer, Integer > point = getIdx( idx );
		Pair<Integer, Integer> point = blocks.get( idx );
		
		return !blocks.contains( new Pair<Integer,Integer>( point.fst + x, point.snd + y ));
	}

	public boolean canAdd( Pair<Integer,Integer> point ) {
		
		return !blocks.contains( point );
	}
	
	private void addDestr( int idx, int x, int y ) {
		Pair<Integer, Integer> point = blocks.get( idx );
		
		blocks.add( new Pair<Integer,Integer>( point.fst + x, point.snd + y ));
		dirty = true;
		normalize();
	}
	
	private void addDestr( Pair<Integer,Integer> point ) {
		blocks.add( point );
		dirty = true;
		normalize();	
	}
	
	public Stone add( int idx, int x, int y ) {
		
		if ( !canAdd( idx, x, y )) {
			return null;
		}
		
		Stone next = clone();
		next.addDestr( idx, x, y);
		
		return next;
	}

	public Stone add( Pair<Integer,Integer> point ) {
		
		if ( !canAdd( point )) {
			return null;
		}
		
		Stone next = clone();
		next.addDestr( point );
		
		return next;
	}
	
	public Stone delBut( int dels, int but ) {
		Stone ret = clone();
		int oldSize = size();
		int delIdx = dels;
		for ( int idx = dels; idx < oldSize; ++idx ) {
			if ( idx != but ) {
				ret.blocks.remove( delIdx );
			} else {
				delIdx++;
			}
		}
		
		ret.dirty = true;
		ret.normalize();
		
		return ret;
	}

	public Stone clone() {
		Stone next = new Stone();
		
		next.blocks.clear();
		
		for ( Pair< Integer, Integer > point : blocks ) {
			next.blocks.add( new Pair<Integer, Integer>( point.fst, point.snd ) );
		}		
		
		return next;
	}
	
//	public Pair< Integer, Integer > getIdx( int idx ) {
//
//		int count = 0;
//		for ( Pair< Integer, Integer > point : blocks.keySet() ) {
//			if ( count == idx) {
//				return point;
//			}
//			
//			count++;
//		}		
//		
//		throw new IllegalArgumentException( "idx < size" );
//	}
 	
	public int size() {
		return blocks.size();
	}
	
	public void normalize() {
		if ( !dirty ) {
			return;
		}
		
		move( getMinX() * -1, getMinY() * -1 );
		dirty = false;
	}
	
	public void turn() {
		List< Pair< Integer, Integer>> newBlocks = new ArrayList<Pair<Integer,Integer>>();

		for ( Pair< Integer, Integer > point : blocks ) {
			newBlocks.add( new Pair<Integer, Integer>(point.snd, point.fst * -1));
		}		

		blocks = newBlocks;

		dirty = true;

		normalize();
	}
	
	public void move( int x, int y ) {
		List< Pair< Integer, Integer>> newBlocks = new ArrayList<Pair<Integer,Integer>>();

		for ( Pair< Integer, Integer > point : blocks ) {
			newBlocks.add( new Pair<Integer, Integer>(point.fst + x, point.snd + y));
		}

		blocks = newBlocks;

		dirty = true;
	}
	
	public int getMinX() {
		int min = 10000000;
		for ( Pair< Integer, Integer > point : blocks ) {
			min = Math.min( min, point.fst);
		}
		
		return min;
	}
	
	public int getMinY() {
		int min = 10000000;
		for ( Pair< Integer, Integer > point : blocks ) {
			min = Math.min( min, point.snd);
		}
		
		return min;
	}
	
	public int getMaxX() {
		int max = -10000000;
		for ( Pair< Integer, Integer > point : blocks ) {
			max = Math.max( max, point.fst);
		}
		
		return max;
	}
	
	public int getMaxY() {
		int max = -10000000;
		for ( Pair< Integer, Integer > point : blocks ) {
			max = Math.max( max, point.snd);
		}
		
		return max;
	}

	
	public boolean equals( Object obj ) {
		if ( !(obj instanceof Stone)) {
			return false;
		}
		
		normalize();
		
		Stone other = (Stone)obj;
		other.normalize();
		
		return blocksEqual(other);
//		
//		if ( size() != other.size() ) {
//			return false;
//		}
//		
//		if ( blocksEqual(other)) {
//			return true;
//		}
//
//		turn();
//		if ( blocksEqual(other)) {
//			return true;
//		}
//
//		turn();
//		if ( blocksEqual(other)) {
//			return true;
//		}
//
//		turn();
//		if ( blocksEqual(other)) {
//			return true;
//		}
//
//		return false;
	}
	
	private boolean blocksEqual( Stone other ) {
		for ( Pair< Integer, Integer > point : blocks ) {
			if ( !other.blocks.contains(point)) {
				return false;
			}
		}

		return true;
	}
	
	public int hashCode() {
		return size();
	}
	
	public String toString() {
	
		normalize();
		int maxx = getMaxX();
		int maxy = getMaxY();
		
		String out= "Stone size " + size() + "\n";
		
		for ( int y = 0; y <= maxy; ++y ) {
			for ( int x = 0; x <= maxx; ++x ) {
				if ( blocks.contains( new Pair< Integer,Integer >(x,y))) {
					out += "#";
				} else {
					out += " ";					
				}
			}
			out += '\n';
		}
		out += '\n';
			
		return out;
	}
	
	public boolean contains( Pair<Integer, Integer> point ) {
		return blocks.contains( point );
	}
	
	public Pair< Integer, Integer > getPoint( int idx ) {
		return blocks.get( idx );
	}
	
	public int getIdx( Pair< Integer, Integer > point ) {
		return blocks.indexOf( point );
	}
}
