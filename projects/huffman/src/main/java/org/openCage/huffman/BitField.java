package org.openCage.huffman;

public interface BitField extends Comparable<BitField> {

    BitField append( boolean bit );
    BitField append( BitField other );

    BitField clonePlusOne();

    byte[] toByteArray();

    String toString();

    String toString8();

    BitField clone();


    int size();

    boolean get(int idx);
    int     getInt(int size);
    int     getInt(int from, int size);

    Byte getByteModulo( int idx );

    BitField getSlice( int from, int size );

    BitField trimTo( int len );

}
