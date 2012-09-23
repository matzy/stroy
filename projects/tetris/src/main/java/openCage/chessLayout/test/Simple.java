package openCage.chessLayout.test;

import info.clearthought.layout.TableLayoutConstants;

import javax.swing.JButton;
import javax.swing.JFrame;

import openCage.chessLayout.ChessColumn;
import openCage.chessLayout.ChessConstraints;
import openCage.chessLayout.ChessLayout;
import openCage.chessLayout.ChessRow;

public class Simple extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Simple().show();
	}
	
	public Simple() {
		ChessLayout chess = new ChessLayout();
		ChessRow     buttons   = chess.addRow( TableLayoutConstants.FILL);
		ChessColumn  firstCol  = chess.addColumn( 100 );
		ChessColumn  secondCol = chess.addColumn( 100 );

		getContentPane().setLayout( chess );
		
		getContentPane().add( new JButton( "foo" ), new ChessConstraints( firstCol, buttons));
		getContentPane().add( new JButton( "2222" ), new ChessConstraints( secondCol, buttons));
		
	}

}
