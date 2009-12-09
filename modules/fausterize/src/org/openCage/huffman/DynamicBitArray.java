package org.openCage.huffman;

import org.openCage.lang.clazz.Count;

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
        String ret = "";
        for ( Count<Byte> by : Count.count(bytes) ) {
            int to = 8;
            if ( by.isLast() ) {
                to = last;
            }

            for ( int i = 0; i < to; i++ ) {
                byte pattern = (byte) (1 << i);
                if ( (by.obj & pattern) == pattern ) {
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

    public String toString8() {
        String ret = "DBA: ";
        for ( Count<Byte> by : Count.count(bytes) ) {
            int to = 8;
            if ( by.isLast() ) {
                to = last;
            }

            for ( int i = 0; i < to; i++ ) {
                byte pattern = (byte) (1 << i);
                if ( (by.obj & pattern) == pattern ) {
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

    public int getBitSize() {
        return ((bytes.size() - 1) * 8) + last;
    }

    public static DynamicBitArray toDba( int num ) {
        if ( num < 0 ) {
            throw new IllegalArgumentException();
        }

        DynamicBitArray ret = new DynamicBitArray();

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

    public static DynamicBitArray toDba( int i, int size ) {
        DynamicBitArray ret = toDba( i );

        while ( ret.getBitSize() < size ) {
            ret.append( false );
        }

        return ret;
    }

    public int toInt( int size ) {
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

    public int toInt( int from, int size ) {
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
}
