package org.openCage.lang.iterators;

import java.util.Iterator;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/


/**
 * Adds and index and isFirst and isLast to any Iteratable
 * <pre>
 *      {@code
 *           List<String> stringlist = Arrays.asList( "A", "B", "C" );
 *           for(Count<String> str : Count.count( stringlist)) {
 *               System.out.println("idx " + str.idx() + " = " + str.obj() );
 *            }
 *      }
 * </pre>
 * @param <T> Any Type
 * @throws UnsupportedOperationException on remove
 */
public class Count<T> implements Iterable<Count<T>>, Iterator<Count<T>> {

    private  T           obj;
    private  int         idx;

    private final Iterator<T> orig;

    /**
     * Add an index and isLast and isFirst to any element in the iteration
     * @param t
     */
    public Count( final Iterable<T> t) {
        orig = t.iterator();
        idx  = -1;
    }

    public boolean hasNext() {
        return orig.hasNext();
    }

    public Count<T> next() {
        obj = orig.next();
        idx++;
        return this;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Iterator<Count<T>> iterator() {
        return this;
    }

    /**
     * returns true if it is the last element in the iteration
     * @return if it is the last element in the iteration
     */
    public boolean isLast() {
        return !hasNext();
    }

    /**
     * returns true if it is the first element in the iteration
     * @return if it is the first element in the iteration
     */
    public boolean isFirst() {
        return idx == 0;
    }

    /**
     * Factory method to construct Counts
     * @param iter
     * @param <T>
     * @return
     */
    public static <T> Count<T> count( Iterable<T> iter ) {
        return new Count<T>( iter );
    }


    /**
     * returns the wrapped object of the iteration
     * @return wrapped object of the iteration
     */
    public T obj() {
        return obj;
    }

    /**
     * return the index of the current iteration
     * @return the index of the current iteration
     */
    public int idx() {
        return idx;
    }

    @Override
    public String toString() {
        return "Count{" +
                "obj=" + obj +
                ", idx=" + idx +
                '}';
    }


}
