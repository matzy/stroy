package openCage.tetris;

public class StoneTurns {

	private Loop<Stone> stones;
	
	public StoneTurns() {
		stones = new Loop<Stone>();
	}
	
	public void setStone( Stone stone ) {
		stones.add( stone );
		Stone next = stone.clone();
		next.turn();
		stones.add( next );
		next = stone.clone();
		next.turn();
		stones.add( next );
		next = stone.clone();
		next.turn();
		stones.add( next );
	}
	
	public void advance() {
		stones.advance();
	}
	
	public Stone get() {
		return stones.get();
	}
}
