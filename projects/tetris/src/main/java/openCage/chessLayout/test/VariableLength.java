package openCage.chessLayout.test;

import info.clearthought.layout.TableLayoutConstants;

import javax.swing.JButton;
import javax.swing.JFrame;

import openCage.chessLayout.ChessColumn;
import openCage.chessLayout.ChessConstraints;
import openCage.chessLayout.ChessLayout;
import openCage.chessLayout.ChessRow;

public class VariableLength extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new VariableLength().show();
	}
	
	public VariableLength() {
		ChessLayout chess = new ChessLayout();
		ChessRow     buttons   = chess.addRow( TableLayoutConstants.FILL);
		ChessColumn  firstCol  = chess.addColumn( 100 );
		ChessColumn  secondCol = chess.addColumn( 100 );

		getContentPane().setLayout( chess );
		
		getContentPane().add( new JButton( "foo" ), new ChessConstraints( firstCol, buttons, firstCol ));
		getContentPane().add( new JButton( "2222" ), new ChessConstraints( secondCol, buttons));
		
		setSize( 500, 400 );
		setTitle( "ChessLayout test" );
		
	}

}
