package openCage.tetris;

import com.sun.tools.javac.util.Pair;

import java.awt.Graphics;

import javax.swing.JPanel;


public class Falling extends JPanel{
	
	private int width;
	private int height;
	private CellState[][] cells;
	private StoneTurns    stoneTurns;
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Falling() {
		width = 10;
		height = 20;
	}
	
	private int cellSize() {
		return Math.min( getWidth() / width, getHeight() / height );
	}
	
	protected void paintComponent( Graphics graphics ) {
		
		super.paintComponent( graphics );
		
		graphics.clearRect( 0, 0, getWidth(), getHeight());
				
		for ( int y = 0; y < height; ++y )
			for ( int x = 0; x < width; ++x  ) {
				
				drawCell( graphics, x, y );
			}
	}


	private void drawCell( Graphics graphics, int x, int y ) {
	}
	
	private boolean down() {
		Stone stone = stoneTurns.get();
		
		for ( int idx = 0; idx < stone.size(); ++idx ) {
			Pair<Integer, Integer> point = stone.getPoint(idx);
			
			point = new Pair<Integer, Integer>( point.fst, point.snd+1);
			
			if ( stone.contains(point)) {
				
			}
		}
		
		for ( int y = height - 2; y > 0; --y ) {
			for ( int x = 0; x < width; ++x ) {
				CellState state = cells[y][x];
				
				if ( state.isHasStone() && state.isMoves() ) {
					
				}
			}
		}
		
		return true;
	}


}
