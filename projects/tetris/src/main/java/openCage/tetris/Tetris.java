package openCage.tetris;

import javax.swing.JFrame;

import openCage.chessLayout.ChessColumn;
import openCage.chessLayout.ChessLayout;

public class Tetris extends JFrame{

	public Tetris() {
		init();
	}
	
	private void init() {
		openCage.chessLayout.ChessLayout chess = new openCage.chessLayout.ChessLayout();
		
		openCage.chessLayout.ChessColumn maincol = chess.addColumnFill();
		chess.addColumn(10);
	}

    public static void main(String[] args) {
        new Tetris();
    }
}
