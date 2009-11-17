package org.openCage.huffman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Nov 12, 2009
 * Time: 7:22:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class DynamicBitArray {

    private List<Byte> bytes = new ArrayList<Byte>();
    private int last = 0;

    public DynamicBitArray() {
        bytes.add( (byte)0);
    }


    public DynamicBitArray append( boolean bit ) {
        if ( bit ) {
            byte by = bytes.get(bytes.size() -1);
            by |= (byte) (1 << last);
            bytes.set( bytes.size() -1, by );
        }

        last++;

        if ( last == 8 ) {
            bytes.add( (byte)0 );
            last = 0;
        }

        return this;
    }

    public byte[] toByteArray() {
        byte[] arr = new byte[ bytes.size() ];
        int  i = 0;
        for ( Byte by : bytes ) {
            arr[i] = by;
            i++;
        }

        return arr;
    }

    public String toString() {
        String ret = "DBA: ";
        for ( Byte by : bytes ) {
            for ( int i = 0; i < 8; i++ ) {
                byte pattern = (byte) (1 << i);
                if ( (by & pattern) == pattern ) {
                    ret += "1";
                } else {
                    ret += "0";
                }
            }
        }

        return ret;
    }

    public String toString8() {
        String ret = "DBA: ";
        for ( Byte by : bytes ) {
            for ( int i = 0; i < 8; i++ ) {
                byte pattern = (byte) (1 << i);
                if ( (by & pattern) == pattern ) {
                    ret += "1";
                } else {
                    ret += "0";
                }
            }
            ret += "|";
        }

        return ret;
    }

    public DynamicBitArray append( DynamicBitArray other ) {

        String todo = other.toString();
        for ( int i = 0; i < todo.length(); ++i ) {
            append( todo.charAt(i) == '1' );
        }
        
        return this;
    }

    public DynamicBitArray clone() {
        DynamicBitArray dba = new DynamicBitArray();
        dba.bytes = new ArrayList<Byte>( bytes );
        dba.last = last;

        return dba;
    }

    public Byte getByteModulo( int idx ) {
        return bytes.get( idx % bytes.size() );
    }

}
