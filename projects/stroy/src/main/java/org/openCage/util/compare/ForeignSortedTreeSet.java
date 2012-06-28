package org.openCage.util.compare;

import java.util.TreeSet;
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

public class ForeignSortedTreeSet<T extends Comparable, S> implements Iterable<S>{


    private class CompPair<U extends Comparable, V> implements Comparable{

        public final U comp;
        public final V val;

        public CompPair( U dist, V val ) {
            this.comp = dist;
            this.val  = val;
        }

        public int compareTo(Object o) {
            return comp.compareTo( ((CompPair<U,V>)o).comp );
        }
    }

    public class ForeignSortedTreeSetIterator<U> implements Iterator<U> {

        private final Iterator<CompPair<?,U>> it;

        public ForeignSortedTreeSetIterator( Iterator<CompPair<?,U>> it ) {
            this.it = it;
        }

        public boolean hasNext() {
            return it.hasNext();
        }

        public U next() {
            return it.next().val;
        }

        public void remove() {
            it.remove();
        }
    }

    private TreeSet< CompPair<T,S> > tree = new TreeSet< CompPair<T,S> >();

    public Iterator<S> iterator() {
        return new ForeignSortedTreeSetIterator<S>( (Iterator)tree.iterator() );
    }


    public void add( T comp, S val ) {
        tree.add( new CompPair(comp, val));
    }


    public boolean isEmpty() {
        return tree.isEmpty();
    }

    

    

}
