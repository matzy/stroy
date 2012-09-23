package openCage.tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import openCage.chessLayout.ChessColumn;
import openCage.chessLayout.ChessConstraints;
import openCage.chessLayout.ChessLayout;
import openCage.chessLayout.ChessRow;

public class CalculateStones extends JFrame {

	private StoneDisplay stoneDisplay;
	private StoneDisplay miniDisplay;
	private JButton      nextButton;
	private StoneDisplay stoneFrame;
	
	private List< Stone > oldStones = new ArrayList<Stone>();
	private List< Stone > nextStones = new ArrayList<Stone>();
	private int           modelIdx;
	private int           cellIdx;
	private int           testIdx;           
	private int           turnIdx;
//	private List<StoneDisplay>          nextDisplays;
	private ShowStones    showStones;
	private ShowStones    showOldStones;
	private JLabel        labelOld;
	private JLabel        labelNew;

	private StoneDisplay  zero;
	private StoneDisplay  one;
	private StoneDisplay  two;
	private StoneDisplay  three;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CalculateStones().setVisible( true );
	}
	
	public CalculateStones() {

		oldStones.add( new Stone() );
		modelIdx = 0;
		cellIdx  = 0;
		testIdx  = 0;
		turnIdx  = 0;
		
		stoneDisplay = new StoneDisplay();
		stoneDisplay.setColors(true);
//		miniDisplay = new StoneDisplay();
//		miniDisplay.setColors(true);
		stoneFrame = new StoneDisplay();
		stoneFrame.setColors(true);
		
		nextButton   = new JButton( "next" );
		nextButton.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				next();
			}} );
		
		
		ChessLayout chess = new ChessLayout();
		
		//  xx x
		//  xx  
		//
		
		ChessRow     topRow     = chess.addRowPrefered();
		chess.addRow( 10 );
		ChessRow     displayRow = chess.addRowPrefered();
		ChessRow     frameRow       = chess.addRowPrefered();
		ChessRow     zeroRow       = chess.addRowPrefered();
		ChessRow     oneRow       = chess.addRowPrefered();
		ChessRow     twoRow       = chess.addRowPrefered();
		ChessRow     threeRow       = chess.addRowPrefered();
		ChessRow     bottomRow  = chess.addRowFill();
//		ChessRow     nextStonesRow  = chess.addRowPrefered();
		
		
		ChessColumn  nold = chess.addColumnFill();
		ChessColumn  display = chess.addColumnFill();
		chess.addColumn( 10 );
		ChessColumn  state = chess.addColumnPrefered();
		chess.addColumn( 10 );
		ChessColumn  turnLabels = chess.addColumnPrefered();
		ChessColumn  button  = chess.addColumnPrefered();
		chess.addColumn( 10 );
		ChessColumn  resCol  = chess.addColumnPrefered();
		chess.addColumn( 10 );
		ChessColumn  nexts  = chess.addColumnPrefered();
		ChessColumn  n2 = chess.addColumnFill();
		
		List<ChessRow> rows = new ArrayList<ChessRow>();
		for ( int idx = 0; idx < 10; ++idx ) {
			rows.add( chess.addRowPrefered());
		}
		getContentPane().setLayout( chess );
		
		getContentPane().add( nextButton, new ChessConstraints( topRow, button ));
		//getContentPane().add( stateLabel, new ChessConstraints( topRow, state ));
		getContentPane().add( stoneFrame, new ChessConstraints( frameRow, button ));
		getContentPane().add( new JLabel( "0" ), new ChessConstraints( zeroRow, turnLabels ));
		getContentPane().add( new JLabel( "90" ), new ChessConstraints( oneRow, turnLabels ));
		getContentPane().add( new JLabel( "180" ), new ChessConstraints( twoRow, turnLabels ));
		getContentPane().add( new JLabel( "270" ), new ChessConstraints( threeRow, turnLabels ));
		
		

		getContentPane().add( stoneDisplay, new ChessConstraints( displayRow, bottomRow, display ));
		showStones = new ShowStones();
		getContentPane().add( showStones, new ChessConstraints( displayRow, bottomRow, n2 ));
		showOldStones = new ShowStones();
		getContentPane().add( showOldStones, new ChessConstraints( displayRow, bottomRow, nold ));

		labelOld = new JLabel( "Stones with 1 Cell" );
		getContentPane().add( labelOld, new ChessConstraints( topRow, nold ));
		labelNew = new JLabel( "Stones with 2 Cells" );
		getContentPane().add( labelNew, new ChessConstraints( topRow, n2 ));
		
		setSize( 500, 400 );
		setTitle( "Calculate Tetris Stones" );
	}
	
	private void next2() {

		
		
	}
	
	private void next() {
		boolean done =  false;
		
		while( !done ) {
			
			if ( turnIdx >= 4 ) {
				turnIdx = 0;
				testIdx++;
			}
	
			if ( testIdx >= 4 ) {
				testIdx = 0;
				cellIdx++;
			}
			
			if ( cellIdx >= oldStones.get( modelIdx ).size() ) {
				modelIdx++;
				cellIdx = 0;
			}
			
			if ( modelIdx >= oldStones.size() ) {
				oldStones = nextStones ;
				nextStones = new ArrayList<Stone>();
				modelIdx = 0;
				showNexts();
			}
			
			showOldStones.setHighLight( modelIdx );
			
			int x = 0;
			int y = 0;
			
			switch ( testIdx ) {
			case 0:
				x = -1;
				y = 0;
				break;
			case 1:
				x = 0;
				y = -1;
				break;
			case 2:
				x = 1;
				y = 0;
				break;
			case 3:
				x = 0;
				y = 1;
				break;
	
			default:
				break;
			}
			
			System.out.println( oldStones.get(0).size() + " "  + modelIdx + " " + cellIdx + " " + x + " " + y );
						
			done = assign( oldStones.get( modelIdx ), x, y );
			
			turnIdx++;
		}
		
		if ( nextStones.contains( stoneDisplay.getStone() ) ) {
			turnIdx = 4;
		} else {
			if ( turnIdx == 4 ) {
				nextStones.add( stoneDisplay.getStone() );
				showNexts();
			}
		}
		
	}
	
	private void showNexts() {
//		for ( int idx = 0; idx < nextStones.size(); ++idx ) {
//			nextDisplays.get(idx).setStone( nextStones.get(idx));
//			nextDisplays.get(idx).repaint();
//		}
//		
//		for ( int idx = nextStones.size(); idx < 10; ++idx  ) {
//			nextDisplays.get(idx).setEmpty(true);
//			nextDisplays.get(idx).repaint();			
//		}
		labelOld.setText( "Stones with " + oldStones.get(0).size() + " Cells");
		labelNew.setText( "Stones with " + (oldStones.get(0).size() + 1) + " Cells");
		showStones.setStones( nextStones );
		showOldStones.setStones( oldStones );
		showStones.repaint();
		showOldStones.repaint();
		

	}
	
	private boolean assign( Stone base, int x , int y ) {
		
		if ( !base.canAdd( cellIdx, x, y)) {
			return false;
		}
		
		Stone stone = base.add( cellIdx, x, y );
		
		for ( int i = 0; i < turnIdx; ++i ) {
			stone.turn();
		}
						
		stoneDisplay.setStone( stone );
		stoneDisplay.setBaseIdx( cellIdx );
//		miniDisplay.setStone( base.add( cellIdx, x, y) );
//		miniDisplay.setBaseIdx( cellIdx );

		int count = 0;
		Stone frame = base.clone();
		for ( int idx = 0; idx < base.size(); ++idx ) {
			if ( frame.canAdd( idx, 0, 1 )) {
				frame = frame.add(idx, 0, 1);
				count++;
			}
			if ( frame.canAdd( idx, 1, 0 )) {
				frame = frame.add(idx, 1, 0);
				count++;
			}
			if ( frame.canAdd( idx, 0, -1 )) {
				frame = frame.add(idx, 0, -1);
				count++;
			}
			if ( frame.canAdd( idx, -1, 0 )) {
				frame = frame.add(idx, -1, 0);
				count++;
			}
		}
		stoneFrame.setStone( frame );
		stoneFrame.setColorCount( count);
		stoneFrame.setColors( true );
		stoneFrame.setUseMinMax( true );
		
		stoneDisplay.repaint();
//		miniDisplay.repaint();
		stoneFrame.repaint();
		
		
		return true;
	}
}
