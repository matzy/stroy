package org.openCage.huffman;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Mar 12, 2010
 * Time: 3:23:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DynamicBitArray {
    DynamicBitArray append( boolean bit );

    byte[] toByteArray();

    String toString();

    String toString8();

    DynamicBitArray append( DynamicBitArray other );

    DynamicBitArray clone();

    Byte getByteModulo( int idx );

    int getBitSize();

    int toInt( int size );

    int toInt( int from, int size );

    DynamicBitArray getSlice( int from, int size );

    boolean bitAt( int idx );
}
