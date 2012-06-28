package org.openCage.lang.iterators;

import org.openCage.lang.structure.T2;

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
 * run 2 iterations in parallel, combined
 * stop when one of the iterators stops 
 * @param <A>
 * @param <B>
 */
public class Parallel<A,B> implements Iterator<T2<A,B>>, Iterable<T2<A,B>>{
    private final Iterator<A> ia;
    private final Iterator<B> ib;

    public Parallel( Iterable<A> ia, Iterable<B> ib ) {
        this.ia = ia.iterator();
        this.ib = ib.iterator();
    }

    @Override
    public boolean hasNext() {
        return ia.hasNext() && ib.hasNext();
    }

    @Override
    public T2<A, B> next() {
        return T2.valueOf( ia.next(), ib.next() );
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("no remove");
    }

    @Override
    public Iterator<T2<A, B>> iterator() {
        return this;
    }

}
