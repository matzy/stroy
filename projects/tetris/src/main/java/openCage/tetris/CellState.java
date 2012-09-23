package openCage.tetris;

import java.awt.Color;

public class CellState {
	
	boolean hasStone; 
	boolean immobile;
	Color   col;
	boolean moves;
	
	public CellState() {
		hasStone = false;
		immobile = false;
		moves    = false;
	}

	public boolean isHasStone() {
		return hasStone;
	}

	public void setHasStone(boolean hasStone) {
		this.hasStone = hasStone;
	}

	public boolean isImmobile() {
		return immobile;
	}

	public void setImmobile(boolean immobile) {
		this.immobile = immobile;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public boolean isMoves() {
		return moves;
	}

	public void setMoves(boolean moves) {
		this.moves = moves;
	}
}
