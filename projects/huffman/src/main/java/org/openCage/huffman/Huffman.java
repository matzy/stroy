package org.openCage.huffman;

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

public class Huffman {

    public BitField encode( byte[] array ) {

        PriorityQueue<HNode> pq = buildPriorityQue( array );

        combine( pq );

        BitField[] codes = new BitField[257];
        computeCodes( pq.poll(), new DynamicBitArrayDirect(), codes );

        BitField result = encode( array, codes );


        return result;
    }

    private BitField encode(byte[] array, BitField[] codes) {

        BitField res = new DynamicBitArrayDirect();

        for ( byte by : array ) {
            res.append( codes[by - Byte.MIN_VALUE ]);
        }

        // EOF
        res.append( codes[ 256 ]);

        return res;
    }

    private PriorityQueue<HNode> buildPriorityQue( byte[] arr ) {
        int[] weight = new int[257];

        for ( byte by : arr ) {
            weight[ by - Byte.MIN_VALUE ]++;
        }

        PriorityQueue<HNode> pq = new PriorityQueue<HNode>();

        for ( int i = 0; i < 257; i++ ) {
            if ( i == 256 || weight[i] > 0 ) {
                pq.add( new HNode( i + Byte.MIN_VALUE, null, null, weight[i]));
            }
        }

        return pq;
    }

    private void combine( PriorityQueue<HNode> pque ) {

        while( pque.size() > 1 ) {
            HNode left = pque.poll();
            HNode right = pque.poll();

            pque.add( new HNode( 1001, left, right, left.weight + right.weight ));
        }
    }

    private void computeCodes( HNode node, BitField prefix, BitField[] codes ) {
        if ( node.left == null && node.right == null ) {
            //System.out.println( "" + node + " -> "  + prefix );
            codes[ node.character - Byte.MIN_VALUE ] = prefix;
            return;
        }

        computeCodes( node.left, prefix.clone().append( false  ), codes );
        computeCodes( node.right, prefix.clone().append( true  ), codes );
    }


    // halfhated attempt to extend the huffman code class to encrypt/decrypt
//    private BitField encodeCodes( BitField[] codes ) {
//
//        List<Integer> indeces = new ArrayList<Integer>();
//        for ( int i = 0; i < 256; ++i ) {
//            indeces.add( i );
//        }
//
//        Collections.shuffle( indeces );
//        indeces.add( 256 );
//
//        BitField res = new DynamicBitArrayDirect();
//        for ( Integer i : indeces ) {
//            if ( codes[i] != null ) {
////                System.out.println("" + i + " " + codes[i].toString8());
////                System.out.println(BitField.toDba( i, 9).toString8());
//                res.append( DynamicBitArrayDirect.toDba( i, 9));
//                res.append( DynamicBitArrayDirect.toDba( codes[i].size(), 5));
//                res.append( codes[i] );
//            }
//        }
//
//        return res;
//    }
//
//    private void decodeCodes( BitField dba ) {
//        int idx = dba.getInt( 9 );
//        int len = dba.getInt( 10, 5 );
//        BitField code = dba.getSlice( 15, len );
//    }
}
