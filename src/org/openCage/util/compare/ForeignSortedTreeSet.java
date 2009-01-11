package org.openCage.util.compare;

import java.util.TreeSet;
import java.util.Iterator;

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
