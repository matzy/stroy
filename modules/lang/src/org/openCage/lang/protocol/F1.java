package org.openCage.lang.protocol;

/**
 * A generic function with one argument
 * @author stephan
 * @param <R>
 * @param <A>
 */
public interface F1<R,A> {
	public R call( A a);
}
