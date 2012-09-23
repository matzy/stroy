package openCage.tetris;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.sun.tools.javac.util.Pair;

public class StoneDisplay extends JPanel {
	
	private static Color neutral = new Color( 237, 237, 237 );
	private static Color alarm   = new Color( 137, 0, 0 );
	private static Color tested    = new Color( 100, 100, 200 );
	private static Color test    = new Color( 0, 0, 200 );
	private static Color baseCol  = new Color( 0, 200, 0 );
	private static Color stoneCol    = new Color( 0, 150, 0 );
	private static Color highlightCol    = new Color( 237, 237, 150 );

	
	private Stone                   stone;
	private Pair<Integer,Integer>   basePoint;
	private Pair<Integer,Integer>   testPoint;
	private Color                   background;
	private boolean                 colors;
	private boolean                 empty;
	private int                     colorCount;
	private boolean                 useMinMax;
	private int                     testIdx;
	private boolean                 highlight;
	
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	public StoneDisplay() {
		super();
		stone   = new Stone();
		basePoint = stone.getPoint(0);
		colors    = false;
		empty = false;
		colorCount = 0;
		useMinMax = false;
		highlight = false;
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(50, 50);
		this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		this.setPreferredSize(new java.awt.Dimension(50,50));
		setBackground(0);
	}
	
	protected void paintComponent( Graphics graphics ) {
		
		super.paintComponent( graphics );

		graphics.clearRect( 0, 0, getWidth(), getHeight());
		
		if ( empty ) {
			return;
		}

		int size = stone.size(); 
		if ( useMinMax ) {
			size = Math.max( stone.getMaxX(), stone.getMaxY()) + 1;
		} 
		
		size = Math.max( 2, size );
		
		for ( int x = 0; x < size; ++x ) {
			for ( int y = 0; y < size; ++y ) {
				drawCell( graphics, x, y );
			}
		}
	}

	private void drawCell( Graphics graphics, int x, int y ) {
		Pair<Integer, Integer > point = new Pair<Integer, Integer>( x, y );
		
		Color color = background;
		
		if ( highlight) {
			color = highlightCol;
		}
		

		if ( stone.contains( point )) {
			
			int idx = stone.getIdx(point);
			
			if ( colors && ( idx == testIdx )) {
				color = baseCol;				
			} else if ( colors && (idx + colorCount >= stone.size() ) && idx < testIdx ) {
				color = tested;
			} else if ( colors && (idx + colorCount >= stone.size() )) {
				color = test;
			} else {			
				color = stoneCol;
			}
		}

		int size = stone.size(); 
		if ( useMinMax ) {
			size = Math.max( stone.getMaxX(), stone.getMaxY()) + 1;
		}
		
		int xsize = getWidth() / size;
		int ysize = getHeight() / size;
		
		graphics.setColor( color );
//		graphics.fillRect( xsize * x, ysize * y, xsize,  ysize );
		graphics.fill3DRect( xsize * x, ysize * y, xsize,  ysize, true );
	}
	
	
	public Stone getStone() {
		return stone;
	}
	
	public void setStone(Stone stone) {
		this.stone = stone;
		testPoint = stone.getPoint( stone.size() - 1 );
		testIdx = 0;
		empty = false;
	}
		
	public void setBaseIdx( int baseIdx ) {
		basePoint = stone.getPoint( baseIdx );
	}
	
	public void result( boolean yes ) {
		if ( yes ) {
			setBackground(1);
		} else {
			setBackground(2);
		}
	}
	
	private void setBackground( int state ) {
		switch (state) {
		case 1:
			background = new Color( 0, 237, 0 );
			break;
		case 2:
			background = alarm;
			break;

		default:
			background = neutral;
			break;
		}
		
		setBackground( background );
	}
	public boolean isColors() {
		return colors;
	}
	public void setColors(boolean colors) {
		this.colors = colors;
	}
	public void setColorCount(int colorCount) {
		this.colorCount = colorCount;
	}
	
	public int getColorCount() {
		return colorCount;
	}
	
	public void setUseMinMax(boolean useMinMax) {
		this.useMinMax = useMinMax;
	}
	public void setTestPoint(Pair<Integer, Integer> testPoint) {
		this.testPoint = testPoint;
	}
	public void setTestIdx(int testIdx) {
		this.testIdx = testIdx;
	}
	
}
