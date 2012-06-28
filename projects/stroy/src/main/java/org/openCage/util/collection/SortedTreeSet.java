package org.openCage.util.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

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

public class SortedTreeSet<T> implements Iterable<T> {

    private class Sorted<U> implements Comparable<Sorted<U>> {

        public  final U             val;
        private final Comparator<U> compa;

        public Sorted( U val, Comparator<U> compa ) {
            this.val   = val;
            this.compa = compa;
        }

        public int compareTo(Sorted<U> other ) {
            return compa.compare( val, other.val );
        }
    }

    public class SortedTreeSetIterator<V> implements Iterator<V> {

        public final Iterator<Sorted<V>> it;

        public SortedTreeSetIterator( Iterator<Sorted<V>> it ) {
            this.it = it;
        }

        public boolean hasNext() {
            return it.hasNext();
        }

        public V next() {
            return it.next().val;
        }

        public void remove() {
            it.remove();
        }
    }


    private final Comparator<T>      comp;
    private final TreeSet<Sorted<T>> tree = new TreeSet<Sorted<T>>();

    public SortedTreeSet( Comparator<T> comp ) {
        this.comp = comp;
    }


    public Iterator<T> iterator() {
        return new SortedTreeSetIterator<T>( tree.iterator() );
    }

    public void add( T t ) {
        tree.add( new Sorted<T>(t,comp));
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }
}
