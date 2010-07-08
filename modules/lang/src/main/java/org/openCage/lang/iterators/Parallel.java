package org.openCage.lang.iterators;

import org.openCage.lang.structure.T2;

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
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
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
