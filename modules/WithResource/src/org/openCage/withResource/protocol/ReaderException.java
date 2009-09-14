package org.openCage.withResource.protocol;

public class ReaderException extends Exception {
	
	private static final long serialVersionUID = -4371505371946069397L;

	private Exception source;

	public ReaderException( Exception source ) {
		this.source = source;
	}
	
	public String toString() {
		return "ReaderException, Caused by: " + source;
	}

	public Exception getSource() {
		return source;
	}
	
	

}
