package org.openCage.lang.clazz;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Feb 23, 2010
 * Time: 2:53:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayCount<T> implements Iterable<ArrayCount<T>>, Iterator<ArrayCount<T>> {

    private int idx = -1;
    private final T[] array;

    public ArrayCount( T[] array ) {
        this.array = array;
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
