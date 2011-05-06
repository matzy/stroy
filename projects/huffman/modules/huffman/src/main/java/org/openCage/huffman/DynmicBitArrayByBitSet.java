package org.openCage.huffman;

import java.util.BitSet;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Mar 12, 2010
 * Time: 3:24:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class DynmicBitArrayByBitSet implements BitField {

    private BitSet bits   = new BitSet();
    private int    length = 0;

    @Override
    public BitField append(boolean bit) {
        bits.set( length, bit );
        length++;
        return this;
    }

    @Override
    public byte[] toByteArray() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return bits.toString();
    }

    @Override
    public String toString8() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BitField append(BitField other) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BitField clonePlusOne() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BitField clone() {
        DynmicBitArrayByBitSet next = new DynmicBitArrayByBitSet();
        next.length = this.length;
        next.bits = (BitSet)bits.clone();
        return next;
    }

    @Override
    public Byte getByteModulo(int idx) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public int getInt(int size) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getInt(int from, int size) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BitField getSlice(int from, int size) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BitField trimTo(int len) {
        throw new Error( "not impl" );
    }


    @Override
    public boolean get(int idx) {
        return bits.get(idx);
    }

    @Override
    public int compareTo(BitField dynamicBitArray) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
