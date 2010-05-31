package org.openCage.lang.iterators;

import org.openCage.lang.structure.T2;

import java.util.Iterator;

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
