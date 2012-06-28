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

public class ArrayCount<T> implements Iterable<ArrayCount<T>>, Iterator<ArrayCount<T>> {

    private int idx = -1;
    private final T[] array;

    public ArrayCount( T[] array ) { // NOSONAR
        this.array = array; // NOSONAR
        // ArrayCount is just a wrapper over the array so it is intential to copy it and not to clone ir
    }

    @Override
    public boolean hasNext() {
        return idx < array.length - 1;
    }

    @Override
    public ArrayCount<T> next() {
        idx++;
        return this;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException( "no remove in ArrayCount" );
    }

    @Override
    public Iterator<ArrayCount<T>> iterator() {
        return this;
    }

    public T obj() {
        return array[idx];
    }

    public int idx() {
        return idx;
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
    

    public static <T> ArrayCount<T> count( T[] arr ) {
        return new ArrayCount<T>( arr );
    }


}
