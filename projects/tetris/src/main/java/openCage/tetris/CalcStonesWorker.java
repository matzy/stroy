package openCage.tetris;

public class CalcStonesWorker extends Thread {
	
	private CalculateStonesFrame gui;
	
	public CalcStonesWorker( CalculateStonesFrame gui ) {
		this.gui = gui;
	}
	
	public void run() {
		
		boolean more = gui.next2( true );
		
		while ( more ) {
			more = gui.next2( false );
			
			try {
				sleep( 1000 );
			} catch (InterruptedException e) {
				more = false;
			}
		}
	}

}
