package openCage.tetris;

import java.util.ArrayList;
import java.util.List;

public class Some {

	static private List< Stone > old = new ArrayList<Stone>();
	static private List< Stone > next = new ArrayList<Stone>();
	
	public static void main( String argv[] ) {
		Stone one = new Stone();
		
		System.out.println(  one );
		System.out.println(  one.add( 0, 1, 0 ));
		System.out.println(  one.add( 0, 0, 1 ));
		
		Stone tt = one.add( 0, 0, 1 );
		tt.turn();
		System.out.println(  tt );
		System.out.println(  one.add( 0, 0, 1 ).equals( one.add( 0, 1, 0 ) ));
		
		
		old.add( new Stone() );

		System.out.println( 2 );
		System.out.println( 2 );
		System.out.println( 2 );
		nextSize();
		System.out.println( 3 );
		System.out.println( 3 );
		System.out.println( 3 );
		nextSize();
		System.out.println( 4 );
		System.out.println( 4 );
		System.out.println( 4 );
		nextSize();
		System.out.println( 5 );
		System.out.println( 5 );
		System.out.println( 5 );
		nextSize();
		System.out.println( 6 );
		System.out.println( 6 );
		System.out.println( 6 );
		nextSize();
		System.out.println( 7 );
		System.out.println( 7 );
		System.out.println( 7 );
		nextSize();
		System.out.println( 8 );
		System.out.println( 8 );
		System.out.println( 8 );
		nextSize();
		
	}

	private static void nextSize() {
		for ( int idx = 0; idx < old.size(); ++idx ) {
			
			Stone model = old.get(idx);
			
			assert( model != null );
			try {
				System.out.println( "next model " + model.size() + "\n" + model  );
			} catch ( Exception exp ) {
				model.size();
			}
			
			for ( int s = 0; s < model.size(); ++s ) {
				
				addif(model, s, 0, 1 );
				addif(model, s, 1, 0 );
				addif(model, s, 0, -1 );
				addif(model, s, -1, 0 );
			}
		}
		
		System.out.println( "Number of stones with size " + next.get(0).size() + " : " + next.size() );
		
		old = next;
		next = new ArrayList<Stone>();
	}

	private static void addif(Stone model, int s, int x, int y ) {
		Stone stone = model.add( s, x, y );
		
		if ( stone == null ) {
			return;
		}
		
		 if ( stone.size() <= model.size()) {
			 int foo = 0;
			 model.add( s, x, y);
		 }
		
		if ( !next.contains( stone )) {
			next.add( stone );
			System.out.print( stone );
//						System.out.println( "yes " + idx + "" + s + " 1 0"  );
		} else {
//						System.out.println( "no" );						
		}
	}

}
