package org.openCage.huffman;

import org.openCage.lang.structure.T2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

public class Canonical {

    public static T2<Map<BitField, BitField>, Integer> canonisize( Map<BitField, BitField> code ) {

        PriorityQueue<Map.Entry<BitField,BitField>> sorted = new PriorityQueue<Map.Entry<BitField, BitField>>(code.size(), new Comparator<Map.Entry<BitField, BitField>>() {
            @Override
            public int compare(Map.Entry<BitField, BitField> a, Map.Entry<BitField, BitField> b) {
                if ( a.getValue().size() != b.getValue().size() ) {
                    return a.getValue().size() - b.getValue().size();
                }
                return a.getKey().compareTo(b.getKey());
            }
        });


        sorted.addAll( code.entrySet());

        Map<BitField, BitField> ret = new HashMap<BitField, BitField>( code.size());

        BitField nextCode = BitList.valueOf(false);
        BitField lastCode = null;

        while ( sorted.size() > 0 ) {
            Map.Entry<BitField,BitField> pair = sorted.poll();

            BitField val = pair.getValue();

            if ( nextCode.size() < val.size() ) {
                while ( nextCode.size() < val.size() ) {
                    nextCode = BitList.valueOf(false).append( nextCode );
//                    nextCode.append(false);
                }
            }

            ret.put( pair.getKey(), reverse( nextCode.clone() ));

            lastCode = nextCode;
            nextCode = nextCode.clonePlusOne();

        }

        return T2.valueOf( ret, lastCode.size());

    }

    public static BitField writeCode( Map<BitField, BitField> code, byte depth ) {

        int size = code.keySet().iterator().next().size(); // size of all keys should be the same

        // write size in 5 bit

        if ( size > 20 ) {
            throw new IllegalArgumentException( "key sizes to large" );
        }

        int valSize = highestSetBit( BitList.valueOf((byte) depth)) + 1;
        //System.out.println("header " + size + ", " +valSize);

        BitField ret = BitList.valueOf((byte) size, 5);
        //System.out.println(ret);
        ret.append(BitList.valueOf((byte) valSize, 5));

        //System.out.println( ret );

        BitField key = BitList.valueOf((byte) 0, size);

        for ( int i = 0; i < (int)Math.pow( 2, size ); i++) {
            if ( !code.containsKey( key )) {
                ret.append( BitList.valueOf((byte) 0, valSize));
            } else {
                ret.append( BitList.valueOf((byte) code.get(key).size(), valSize));
            }

            key = key.clonePlusOne();
        }

        return ret;
    }

    public static T2<Map<BitField, BitField>, Integer> readCode( BitField bf ) {
        int keySize = bf.getInt(0,5);
        int valLength = bf.getInt(5,5);

        //System.out.println( "" + keySize + " - " + valLength );

        BitField key = BitList.valueOf((byte) 0, keySize);

        PriorityQueue<T2<BitField,Integer>> sorted = new PriorityQueue<T2<BitField, Integer>>(100, new Comparator<T2<BitField, Integer>>() {
            @Override
            public int compare(T2<BitField, Integer> a, T2<BitField, Integer> b) {
                if ( a.i1 != b.i1 ) {
                    return a.i1 - b.i1;
                }
                return a.i0.compareTo(b.i0);
            }
        });

        // System.out.println( "math pow " +  (int)Math.pow( 2, keySize ));

        for ( int i = 0; i < (int)Math.pow( 2, keySize ); i++) {
            int val = bf.getInt( 10 + i * valLength, valLength );
            if ( val != 0 ) {
//                System.out.println(key.toString8() + " -> " + val);
                sorted.add( T2.valueOf( key,val ));
            }
            key = key.clonePlusOne();
        }

        Map<BitField, BitField> ret = new HashMap<BitField, BitField>( sorted.size() );

        BitField nextCode = BitList.valueOf(false);
        BitField lastCode = null;

        while ( sorted.size() > 0 ) {
            T2<BitField,Integer> pair = sorted.poll();

            if ( nextCode.size() < pair.i1 ) {
                while ( nextCode.size() < pair.i1 ) {
                    nextCode = BitList.valueOf(false).append( nextCode );
//                    nextCode.append(false);
                }
            }

            ret.put( pair.i0, reverse( nextCode.clone() ));

            lastCode = nextCode;
            nextCode = nextCode.clonePlusOne();

        }


        return T2.valueOf( ret, ((int)Math.pow( 2, keySize ) * valLength) + 10);
    }



    public static int highestSetBit( BitField bf ) {
        for ( int i = bf.size() -1; i >= 0; i-- ) {
            if ( bf.get(i)) {
                return i;
            }
        }

        return -1;
    }

    public static BitField reverse( BitField orig ) {
        BitField ret = new BitList();

        for ( int i = orig.size() -1; i >= 0; i-- ) {
            ret.append( orig.get(i));
        }

        return ret;
    }

//    public static boolean equals( Map<BitField, BitField>, )

}
