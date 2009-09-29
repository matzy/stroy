package org.openCage.lang.protocol;

/**
 * Builder Pattern
 * add data to a builder and at last call build to create a new object
 * this replaces a complex constructor + init process
 * @author stephan
 * @param <T>
 */
public interface Builder<T> {
	public T build();
}
