package org.openCage.huffman;

import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 0
 * 10
 * 76543210
 * 76543210 8
 * 76543210 98
 */
public class BitList implements BitField {

    private List<Byte> bytes = new ArrayList<Byte>();

    // last bit in last byte
    private int last = 7;

    public BitList() {
    }

    public static BitList valueOf(boolean val) {
        BitList ret = new BitList();
        ret.last = 0;
        ret.bytes.add((byte) (val ? 1 : 0));

        return ret;
    }

    public static BitField valueOf(String str) {
        BitList ret = new BitList();

        for (int i = 0; i < str.length(); i++) {
            ret.append(str.charAt(i) == '1');
        }

        return ret;
    }

    public static BitField valueOf( byte[] src ) {
        BitList ret = new BitList();

        for ( byte by : src ) {
            ret.bytes.add( by );
        }

        // full byte
        ret.last = 7;

        return ret;
    }

    public static BitField valueOf( byte by ) {
        BitList ret = new BitList();

        ret.bytes.add( by );

        // full byte
        ret.last = 7;

        return ret;
    }

    public static BitField valueOf( InputStream is ) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        byte[] bytes = new byte[512];

        // Read bytes from the input stream in bytes.length-sized chunks and write
        // them into the output stream
        int readBytes;
        while ((readBytes = is.read(bytes)) > 0) {
            outputStream.write(bytes, 0, readBytes);
        }

        // Convert the contents of the output stream into a byte array
        byte[] byteData = outputStream.toByteArray();


        return valueOf( byteData );

    }

    public static BitField valueOf( int by, int size ) {

        if ( by < 0 ) {
            throw new IllegalArgumentException( "negative numbers are not supported " + by );
        }

        if ( size <= 0 ) {
            throw new IllegalArgumentException( "size needs to be >0 " + size );
        }


        BitList ret = new BitList();

        int mask = 1;

        for ( int pos = 0; pos < size && pos < 32; pos++ ) {
            ret.append( (by & mask) != 0);
            mask <<= 1;
        }

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
        for ( int i = 0; i < other.size(); i++ ) {
            append( other.get(i));
        }

        return this;
    }

    @Override
    public BitField clonePlusOne() {
        BitField ret = new BitList();

        boolean found0 = false;
        for (int i =0; i < size(); i++ ) {
            if( get(i)) {
                ret.append( found0 );
            } else {
                if ( !found0 ) {
                    found0 = true;
                    ret.append( true );
                } else {
                    ret.append( !found0 );
                }
            }
        }

        if ( !found0 ) {
            ret.append(true);
        }

        return ret;

//        int first0 = -1;
//        for ( int i = size() - 1; i >= 0; i-- ) {
//            if ( !get(i) ) {
//                first0 = i;
//                break;
//            }
//        }
//
//        BitField ret = new BitList();
//
//        for ( int i = 0; i < first0; i++ ) {
//            ret.append( get(i));
//        }
//
//        ret.append( true );
//
//        for ( int i = first0 + 1; i < size(); i++ ) {
//            ret.append( false );
//        }
//
//        return ret;
    }

    @Override
    public byte[] toByteArray() {
        byte[] ret  = new byte[bytes.size()];

        for ( int i = 0; i < bytes.size(); i++ ) {
            ret[i] = bytes.get(i);
        }

        return ret;


    }

    @Override
    public String toString() {

        if ( bytes.size() == 0 ) {
            return "-1";
        }

        StringBuilder ret = new StringBuilder();

        for ( int i = 0; i < size(); i++ ) {
            boolean bit = get(i);
            ret.append(bit ? "1" : "0");
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

        return ret.toString();
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
        BitList ret = new BitList();
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
        if ( idx < 0 || idx >= size() ) {
            throw new IllegalArgumentException( "index out of bounds ("+ size() +"): " + idx);
        }
        try {
            return (bytes.get(idx / 8).byteValue() & ((byte) (1 << (idx % 8)))) != 0;
        }catch ( IndexOutOfBoundsException exp ) {
            int yoo = 0;
            return false;
        }
    }

    @Override
    public int getInt(int size) {
        throw new Error( "not impl" );
    }

    @Override
    public int getInt(int from, int size) {
        if ( size > 32 ) {
            throw new IllegalArgumentException( "size too big " + size );
        }

        int ret = 0;
        for ( int i = size-1; i >= 0; i--  ) {
            ret *= 2;
            if ( get( from+i)) {
                ret++;
            }
        }

        return ret;
    }

    @Override
    public Byte getByteModulo(int idx) {
        return bytes.get( idx % bytes.size() );
    }

    @Override
    public BitField getSlice(int from, int size) {
        BitField ret = new BitList();
        for ( int i = from; i < from + size; i++) {
            if ( i >= size() ) {
                ret.append(false);
            } else {
                ret.append( get(i));
            }
        }
        return ret;
    }

    @Override
    public BitField trimTo(int len) {

        if ( len <= 0 ) {
            throw new IllegalArgumentException( "can't be negative " + len );
        }

        if ( size() < len ) {
            return this;
        }

        while ( (bytes.size() - 1)* 8 >= len ) {
            bytes.remove( bytes.size() -1 );
        }

        last = len - ((bytes.size() -1) * 8) - 1;

        return this;
    }


    @Override
    public int compareTo(BitField other) {

        if ( !(other instanceof BitList)) {
            throw new IllegalArgumentException( "different impls of bitfield don't compare" );
        }

        BitList oimpl = (BitList)other;

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

        BitList bitField = (BitList) o;

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
