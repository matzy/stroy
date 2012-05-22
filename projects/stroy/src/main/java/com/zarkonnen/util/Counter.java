package com.zarkonnen.util;

import java.util.Collection;

/**
 * Used for keeping track of where you are in a collection/array being iterated over. Use by
 * initialising with the collection/array before the loop and embedding a switch on the next() Mode
 * value into the loop.
 *
 * LICENCE: This code is licenced under a BSD licence. Feel free to alter and redistribute.
 *
 * @author David Stark, http://www.zarkonnen.com
 * @version 1.0 (2007-07-11)
 */

// example:
//String kittenDesc = "I have no kittens.";
//Counter kc = new Counter(kittens);
//for (Kitten k : kittens) switch(kc.next()) {
//	case one:   kittenDesc =  "My kitten is called " + k.getName() + "."; break;
//	case first: kittenDesc =  "My kittens are called " + k.getName();     break;
//	case item:  kittenDesc += ", " + k.getName();                         break;
//	case last:  kittenDesc += " and " + k.getName() + ".";                break;
//}

public class Counter {
	/**
	 * An enumeration of modes identifying where in the collection/array we are.
	 */
	public enum Where {
		/** The only element of an array/collection of size 1. */ one,
		/** The first element. */                                 first,
		/** The last element of the array/collection. */          last,
		/** Any other element somewhere in the middle. */         item
	}

	private int size;
	private int nextIndex = 0;

	/**
	 * @param c A collection to keep track of. If its size changes between now and the
	 * iteration, strange things will happen.
	 */
	public Counter(Collection c) {
		size = c.size();
	}

	/**
	 * @param a An array to keep track of. If its size changes between now and the iteration,
	 * strange things will happen.
	 */
	public Counter(Object[] a) {
		size = a.length;
	}

	/**
	 * @return A Where enum value for where in the array/collection we now are:
	 * <ul>
	 * <li><strong>one</strong> at the only element of an array/collection of size 1</li>
	 * <li><strong>first</strong> at the first element</li>
	 * <li><strong>last</strong> at the last element</li>
	 * <li><strong>item</strong> at any other element</li>
	 * </ul>
	 */
	public Where next() {
		return size == 1         ? Where.one   :
		       nextIndex++ == 0  ? Where.first :
		       nextIndex == size ? Where.last  :
		                           Where.item  ;
	}

	/**
	 * @return Which index of the array/collection we're currently at.
	 */
	public int index() {
		return nextIndex == 0 ? 0 : nextIndex - 1;
	}
}
