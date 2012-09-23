package openCage.tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.sun.tools.javac.util.Pair;

import openCage.chessLayout.ChessColumn;
import openCage.chessLayout.ChessConstraints;
import openCage.chessLayout.ChessLayout;
import openCage.chessLayout.ChessRow;

public class ShowStones extends JPanel {

	private static Color neutral = new Color( 237, 237, 237 );
	private static Color alarm   = new Color( 137, 0, 0 );
	private static Color good    = new Color( 237, 237, 150 );
	private static Color test    = new Color( 0, 50, 200 );
	private static Color baseCol  = new Color( 0, 200, 0 );
	private static Color stoneCol    = new Color( 0, 150, 0 );
	
	//private final List<StoneDisplay> displays;
	private List<Stone>              stones;
	private int                      highLight;
//	private List<ChessRow> rows = new ArrayList<ChessRow>();
//	private 		ChessLayout chess = new ChessLayout();
//	private ChessColumn  col;

	public ShowStones() {
//		displays = new ArrayList<StoneDisplay>();
//		for ( int i = 0; i < 10; ++ i ) {
//			displays.add( new StoneDisplay() );
//			displays.get(i).setEmpty(true);
//		}
//		stones = null;
//		
//		
//		col = chess.addColumnFill();
//
//		for ( int idx = 0; idx < 10; ++idx ) {
//			rows.add( chess.addRowPrefered());
//		}
//		
//		setLayout( chess );
//		
//		for ( int idx = 0; idx < 10; ++idx ) {
//			add( displays.get(idx), new ChessConstraints( col, rows.get(idx)));
//		}
		
		stones = null;
		highLight = -1;
		setSize( 70, 400 );

	}

	public void setStones( List<Stone> stones ) {
		this.stones = stones;

//		while ( stones.size() > displays.size()) {
//			StoneDisplay disp = new StoneDisplay(); 
//			displays.add( disp );			
//			rows.add( chess.addRowPrefered());
//			add( disp, new ChessConstraints( col, rows.get(rows.size()-1)));
//		}
//		
//		
//		for ( int idx = 0; idx < stones.size(); ++idx ) {
//			displays.get(idx).setStone(stones.get(idx));
//			displays.get(idx).repaint();
//		}
//		
//		for ( int idx = stones.size(); idx < displays.size(); ++idx ) {
//			displays.get(idx).setEmpty( true );
//			displays.get(idx).repaint();			
//		}
	}
	
	protected void paintComponent( Graphics graphics ) {
		
		super.paintComponent( graphics );
		
		graphics.clearRect( 0, 0, getWidth(), getHeight());
		
		if ( stones == null || stones.size() == 0 ) {
			return;
		}
		
		int size = cellSize();
		int idx  = 0;
		
		for ( int y = 0; y + size < getHeight(); y += size) {
			for ( int x = 0; x + size < getWidth(); x += size ) {
				if ( idx >= stones.size()) {
					break;
				}
				
				drawStone( graphics, stones.get(idx), x, y, size, idx == highLight );
				idx++;
			}
		}
	}
	
	private void drawStone( Graphics graphics, Stone stone, int x, int y, int size, boolean show ) {
		int stoneSize = Math.max( 2, stone.size());
		for ( int cx = 0; cx < stoneSize; ++cx ) {
			for ( int cy = 0; cy < stoneSize; ++cy ) {
				drawCell( graphics, stone, x ,y, size, cx, cy, show );
			}
		}
	}
	
	private void drawCell( Graphics graphics, Stone stone, int x, int y, int size, int cx, int cy, boolean show ) {
		Pair<Integer, Integer > point = new Pair<Integer, Integer>( cx, cy );
		
		Color color = neutral;
		
		if ( show ) {
			color = good; 
		}
		
		if ( stone.contains( point )) {
			color = stoneCol;
		}
		
		int xsize = size / Math.max( 2, stone.size());;
		int ysize = size / Math.max( 2, stone.size());;
		
		graphics.setColor( color );
		graphics.fill3DRect( x + xsize * cx, y + ysize * cy, xsize,  ysize, true );
	}
	
	
	private int cellSize() {
//		int x = (int)Math.floor( Math.sqrt(  (getWidth() * getHeight()) / count ));
//		
//		return Math.min( Math.min( x, getWidth()), getHeight() );
		
		int size = 1;
		
		while ( true ) {
			if ( Math.floor( (getWidth() -2)/ size) * Math.floor( (getHeight() -2 )/ size) < stones.size() ) {
				return size - 1;
			}
			
			if ( size == 80 ) {
				return size;
			}
			
			size++;
		}
		
//		return 1;
	}

	public void setHighLight(int highLight) {
		this.highLight = highLight;
	}
	
}
