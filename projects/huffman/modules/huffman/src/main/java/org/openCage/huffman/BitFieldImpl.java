package org.openCage.huffman;

import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;
import org.openCage.lang.iterators.Count;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: spf
 * Date: 28.04.11
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public class BitFieldImpl implements BitField {

    private List<Byte> bytes = new ArrayList<Byte>();

    // last bit in last byte
    private int last = -1;

    private BitFieldImpl() {
    }

    public static BitFieldImpl valueOf(boolean val) {
        BitFieldImpl ret = new BitFieldImpl();
        ret.last = 0;
        ret.bytes.add((byte) (val ? 1 : 0));

        return ret;
    }

    public static BitField valueOf(String str) {
        BitFieldImpl ret = new BitFieldImpl();

        for (int i = 0; i < str.length(); i++) {
            ret.append(str.charAt(i) == '1');
        }

        return ret;
    }

    public static BitField valueOf( byte[] src ) {
        BitFieldImpl ret = new BitFieldImpl();

        for ( byte by : src ) {
            ret.bytes.add( by );
        }

        // full byte
        ret.last = 7;

        return ret;
    }




    @Override
    public BitField append(boolean bit) {
        if (bit) {
            byte by = bytes.get(bytes.size() - 1);
            by |= (byte) (1 << last);
            bytes.set(bytes.size() - 1, by);
        }

        last++;

        if (last == 8) {
            bytes.add((byte) 0);
            last = 0;
        }

        return this;
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
    public byte[] toByteArray() {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {


        String ret = "";
        for ( Count<Byte> by : Count.count(bytes) ) {
            int to = 8;
            if ( by.isLast() ) {
                to = last + 1;
            }

            for ( int i = to -1; i >= 0; i-- ) {
                byte pattern = (byte) (1 << i);
                if ( (by.obj() & pattern) == pattern ) {
                    ret += "1";
                } else {
                    ret += "0";
                }
            }
        }

        if ( ret.equals("")) {
            return "0";
        }

        return ret;
    }


    @Override
    public String toString8() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BitField clone() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return ((bytes.size() - 1) * 8) + (last + 1);
    }

    @Override
    public boolean get(int idx) {
        return (bytes.get(idx / 8).byteValue() & ((byte) (1 << (7 -(idx % 8))))) != 0;
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
    public Byte getByteModulo(int idx) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BitField getSlice(int from, int size) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int compareTo(BitField other) {
        if ( size() != other.size() ) {
            return size() - other.size();
        }

        for ( int i = 0; i < size(); i++ ) {

            if ( get(i) != other.get(i)) {
                return get(i) ? 1 : -1;
            }
        }

        return 0;
    }

    // for tests

    int internalGetLast() {
        return last;
    }

    public List<Byte> internalGetBytes() {
        return bytes;
    }
}
