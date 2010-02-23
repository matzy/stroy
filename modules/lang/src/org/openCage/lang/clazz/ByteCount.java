package org.openCage.lang.clazz;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Feb 23, 2010
 * Time: 3:07:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class ByteCount implements Iterable<ByteCount>, Iterator<ByteCount> {
    private int idx = -1;
    private final byte[] array;

    public ByteCount( byte[] array ) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return idx < array.length - 1;
    }

    @Override
    public ByteCount next() {
        idx++;
        return this;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException( "no remove in ArrayCount" );
    }

    @Override
    public Iterator<ByteCount> iterator() {
        return this;
    }

    public Byte obj() {
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


    public static ByteCount count( byte[] arr ) {
        return new ByteCount( arr );
    }

}
