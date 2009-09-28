package org.openCage.withResource.protocol;

/**
 * Wrapper for checked exceptions to be used in methods without throws 
 * good for closures and slim code
 * @author stephan
 *
 */
public class Unchecked extends Error {
	
	private static final long serialVersionUID = 1310525450890886497L;

	private Exception source;

	public Unchecked( Exception source ) {
		this.source = source;
	}
	
	public String toString() {
		return "Unchecked Exception, Caused by: " + source;
	}

	public Exception getSource() {
		return source;
	}
}
