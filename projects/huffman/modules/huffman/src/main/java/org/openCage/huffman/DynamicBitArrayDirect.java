package org.openCage.huffman;

import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;
import org.openCage.lang.iterators.Count;

import java.util.ArrayList;
import java.util.List;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class DynamicBitArrayDirect implements BitField {

    private List<Byte> bytes = new ArrayList<Byte>();
    private int last = 0;

    public DynamicBitArrayDirect() {
        bytes.add( (byte)0);
    }

    int internalGetLast() {
        return last;
    }

    @Override
    public BitField append( boolean bit ) {
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

    @Override
    public byte[] toByteArray() {
        byte[] arr = new byte[ bytes.size() ];
        int  i = 0;
        for ( Byte by : bytes ) {
            arr[i] = by;
            i++;
        }

        return arr;
    }

    @Override
    public String toString() {
        String ret = "";
        for ( Count<Byte> by : Count.count(bytes) ) {
            int to = 8;
            if ( by.isLast() ) {
                to = last;
            }

            for ( int i = 0; i < to; i++ ) {
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
//        String ret = "DBA: ";
//        for ( Count<Byte> by : Count.count(bytes) ) {
//            int to = 8;
//            if ( by.isLast() ) {
//                to = last;
//            }
//
//            for ( int i = 0; i < to; i++ ) {
//                byte pattern = (byte) (1 << i);
//                if ( (by.obj() & pattern) == pattern ) {
//                    ret += "1";
//                } else {
//                    ret += "0";
//                }
//            }
//            ret += "|";
//        }
//
//        return ret;
    }

    @Override
    public BitField append( BitField other ) {

        String todo = other.toString();
        for ( int i = 0; i < todo.length(); ++i ) {
            append( todo.charAt(i) == '1' );
        }

        return this;
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

        BitField ret = new DynamicBitArrayDirect();

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
    public BitField clone() {
        DynamicBitArrayDirect dba = new DynamicBitArrayDirect();
        dba.bytes = new ArrayList<Byte>( bytes );
        dba.last = last;

        return dba;
    }

    @Override
    public Byte getByteModulo( int idx ) {
        return bytes.get( idx % bytes.size() );
    }

    @Override
    public int size() {
        return ((bytes.size() - 1) * 8) + last;
    }

    public static BitField toDba( int num ) {
        if ( num < 0 ) {
            throw new IllegalArgumentException();
        }

        BitField ret = new DynamicBitArrayDirect();

        int max = 0;
        int val = 1;
        while ( val <= num ) {
            val *= 2;
            max++;
        }

        for ( int i = 0; i < max; ++i ) {
            byte pattern = (byte) (1 << i);
            ret.append( (num & pattern) == pattern );
        }

        return ret;
    }

    public static BitField toDba( int i, int size ) {
        BitField ret = toDba( i );

        while ( ret.size() < size ) {
            ret.append( false );
        }

        return ret;
    }

    @Override
    public int getInt(int size) {
        int ret = 0;
        int val = 1;

        String str = toString();

        for ( int i = 0; i < Math.min( size, str.length()); i++ ) {
            if ( str.charAt( i) == '1') {
                ret += val;
            }

            val *= 2;
        }

        return ret;

    }

    @Override
    public int getInt(int from, int size) {
        int ret = 0;
        int val = 1;

        String str = toString();

        for ( int i = from; i < Math.min( from+size, str.length()); i++ ) {
            if ( str.charAt( i) == '1') {
                ret += val;
            }

            val *= 2;
        }

        return ret;
    }

    @Override
    public BitField getSlice( int from, int size ) {
        BitField ret = new DynamicBitArrayDirect();

        for ( int i = from; i < from + size; ++i ) {
            ret.append( get(i));
        }

        return ret;
    }

    @Override
    public BitField trimTo(int len) {
        throw new Error( "not impl" );
    }

    @Override
    public boolean get(int idx) {
        return (bytes.get(idx / 8).byteValue() & ((byte) (1 << (idx % 8)))) != 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DynamicBitArrayDirect that = (DynamicBitArrayDirect) o;

        if (last != that.last) return false;
        if (bytes != null ? !bytes.equals(that.bytes) : that.bytes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bytes != null ? bytes.hashCode() : 0;
        result = 31 * result + last;
        return result;
    }

    public static BitField valueOf( byte[] src ) {
        DynamicBitArrayDirect ret = new DynamicBitArrayDirect();
        ret.bytes.clear();

        for ( byte by : src ) {
            ret.bytes.add( by );
        }

        // full byte
        ret.last = 7;

        return ret;
    }

    public static BitField valueOf( String str ) {
        DynamicBitArrayDirect ret = new DynamicBitArrayDirect();
        //ret.bytes.clear();

        for ( int i = 0; i < str.length(); i++ ) {
            ret.append( str.charAt(i) == '1');
        }

        return ret;
    }
}
