package org.openCage.withResource.protocol;

public interface Reader<R,T> {

	public R read( T stream ) throws ReaderException;
}
