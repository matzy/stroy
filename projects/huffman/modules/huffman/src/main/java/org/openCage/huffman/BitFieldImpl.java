package org.openCage.huffman;

import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;
import org.openCage.lang.iterators.Count;

import java.util.ArrayList;
import java.util.List;

/**
 * 0
 * 10
 * 76543210
 * 76543210 8
 * 76543210 98
 */
public class BitFieldImpl implements BitField {

    private List<Byte> bytes = new ArrayList<Byte>();

    // last bit in last byte
    private int last = 7;

    public BitFieldImpl() {
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
        last++;

        if (last == 8 || last == -1) {
            bytes.add((byte) 0);
            last = 0;
        }

        if (bit) {
            byte by = bytes.get(bytes.size() - 1);
            by |= (byte) (1 << last);
            bytes.set(bytes.size() - 1, by);
        }

        return this;
    }

    @Override
    public BitField append(BitField other) {
        throw new Error( "not impl" );
    }

    @Override
    public BitField clonePlusOne() {
        int first0 = -1;
        for ( int i = size() - 1; i >= 0; i-- ) {
            if ( !get(i) ) {
                first0 = i;
                break;
            }
        }

        BitField ret = new BitFieldImpl();

        for ( int i = 0; i < first0; i++ ) {
            ret.append( get(i));
        }

        ret.append( true );

        for ( int i = first0 + 1; i < size(); i++ ) {
            ret.append( false );
        }

        return ret;
    }

    @Override
    public byte[] toByteArray() {
        throw new Error( "not impl" );
    }

    @Override
    public String toString() {

        if ( bytes.size() == 0 ) {
            return "-1";
        }

        String ret = "";

        for ( int i = size() -1; i >= 0; i-- ) {
            boolean bit = get(i);
            ret += (bit ? "1" : "0");
        }

//        for ( Count<Byte> by : Count.count(bytes) ) {
//            int to = 8;
//            if ( by.isLast() ) {
//                to = last + 1;
//            }
//
//            for ( int i = to -1; i >= 0; i-- ) {
//                byte pattern = (byte) (1 << i);
//                if ( (by.obj() & pattern) == pattern ) {
//                    ret += "1";
//                } else {
//                    ret += "0";
//                }
//            }
//        }
//
//        if ( ret.equals("")) {
//            return "0";
//        }

        return ret;
    }


    @Override
    public String toString8() {
        if ( bytes.size() == 0 ) {
            return "-1";
        }

        return Strings.join( bytes ).prefix("DBA:").separator("|").trans( new F1<String,Byte>() {
            @Override
            public String call(Byte by ) {
                if ( 32 <= by && by < 127 ) {
                    return "'" + (char)(by.byteValue()) + "'";
                } else {
                    return by.toString();
                }
            }
        }).toString();


//        String ret = "";
//
//        boolean first = true;
//        for ( int i = bytes.size() - 1; i >= 0; i--) {
//
//            ret += bytes.get(i) + "|";
//        }
//
////        for ( int i = size() -1; i >= 0; i-- ) {
////            boolean bit = get(i);
////            ret += (bit ? "1" : "0");
////        }
//
//        return ret;
    }

    @Override
    public BitField clone() {
        BitFieldImpl ret = new BitFieldImpl();
        ret.last = last;
        ret.bytes.addAll( bytes );
        return ret;
    }

    @Override
    public int size() {
        return ((bytes.size() - 1) * 8) + (last + 1);
    }

    @Override
    public boolean get(int idx) {
        return (bytes.get(idx / 8).byteValue() & ((byte) (1 << (idx % 8)))) != 0;
    }

    @Override
    public int getInt(int size) {
        throw new Error( "not impl" );
    }

    @Override
    public int getInt(int from, int size) {
        throw new Error( "not impl" );
    }

    @Override
    public Byte getByteModulo(int idx) {
        throw new Error( "not impl" );
    }

    @Override
    public BitField getSlice(int from, int size) {
        BitField ret = new BitFieldImpl();
        for ( int i = from; i < from + size; i++) {
            ret.append( get(i));
        }
        return ret;
    }

    @Override
    public int compareTo(BitField other) {

        if ( !(other instanceof BitFieldImpl )) {
            throw new IllegalArgumentException( "different impls of bitfield don't compare" );
        }

        BitFieldImpl oimpl = (BitFieldImpl)other;

        if ( bytes.size() != oimpl.bytes.size()  ) {
            return bytes.size() - oimpl.bytes.size();
        }

        for ( int i = bytes.size() - 1; i>= 0; i-- ) {
            if ( bytes.get(i) != oimpl.bytes.get(i)) {
                return bytes.get(i) - oimpl.bytes.get(i);
            }
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitFieldImpl bitField = (BitFieldImpl) o;

        if (last != bitField.last) return false;
        if (bytes != null ? !bytes.equals(bitField.bytes) : bitField.bytes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bytes != null ? bytes.hashCode() : 0;
        result = 31 * result + last;
        return result;
    }

    // for tests

    int internalGetLast() {
        return last;
    }

    public List<Byte> internalGetBytes() {
        return bytes;
    }
}
