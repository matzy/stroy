package org.openCage.util.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
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
