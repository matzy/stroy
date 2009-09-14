package org.openCage.withResource.protocol;

public class Unchecked extends Error {
	
	private static final long serialVersionUID = 1310525450890886497L;

	private Exception source;

	public Unchecked( Exception source ) {
		this.source = source;
	}
	
	public String toString() {
		return "ReaderException, Caused by: " + source;
	}

	public Exception getSource() {
		return source;
	}
	


}
