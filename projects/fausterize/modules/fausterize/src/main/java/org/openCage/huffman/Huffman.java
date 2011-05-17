package org.openCage.huffman;

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

public class Huffman {

    public DynamicBitArray encode( byte[] array ) {

        PQue pq = buildPriorityQue( array );

        combine( pq );

        DynamicBitArray[] codes = new DynamicBitArray[257];
        computeCodes( pq.pop(), new DynamicBitArrayDirect(), codes );

        DynamicBitArray result = encode( array, codes );


        return result;
    }

    private DynamicBitArray encode(byte[] array, DynamicBitArray[] codes) {

        DynamicBitArray res = new DynamicBitArrayDirect();

        for ( byte by : array ) {
            res.append( codes[by - Byte.MIN_VALUE ]);
        }

        // EOF
        res.append( codes[ 256 ]);

        return res;
    }

    private PQue buildPriorityQue( byte[] arr ) {
        int[] weight = new int[257];

        for ( byte by : arr ) {
            weight[ by - Byte.MIN_VALUE ]++;
        }

        PQue pq = new PQue();

        for ( int i = 0; i < 257; i++ ) {
            if ( i == 256 || weight[i] > 0 ) {
                pq.push( new HNode( i + Byte.MIN_VALUE, null, null, weight[i]));
            }
        }

        return pq;
    }

    private void combine( PQue pque ) {

        while( pque.size() > 1 ) {
            HNode left = pque.pop();
            HNode right = pque.pop();

            pque.push( new HNode( 1001, left, right, left.weight + right.weight ));
        }
    }

    private void computeCodes( HNode node, DynamicBitArray prefix, DynamicBitArray[] codes ) {
        if ( node.left == null && node.right == null ) {
            //System.out.println( "" + node + " -> "  + prefix );
            codes[ node.character - Byte.MIN_VALUE ] = prefix;
            return;
        }

        computeCodes( node.left, prefix.clone().append( false  ), codes );
        computeCodes( node.right, prefix.clone().append( true  ), codes );
    }


    // halfhated attempt to extend the huffman code class to encrypt/decrypt
//    private DynamicBitArray encodeCodes( DynamicBitArray[] codes ) {
//
//        List<Integer> indeces = new ArrayList<Integer>();
//        for ( int i = 0; i < 256; ++i ) {
//            indeces.add( i );
//        }
//
//        Collections.shuffle( indeces );
//        indeces.add( 256 );
//
//        DynamicBitArray res = new DynamicBitArrayDirect();
//        for ( Integer i : indeces ) {
//            if ( codes[i] != null ) {
////                System.out.println("" + i + " " + codes[i].toString8());
////                System.out.println(DynamicBitArray.toDba( i, 9).toString8());
//                res.append( DynamicBitArrayDirect.toDba( i, 9));
//                res.append( DynamicBitArrayDirect.toDba( codes[i].getBitSize(), 5));
//                res.append( codes[i] );
//            }
//        }
//
//        return res;
//    }
//
//    private void decodeCodes( DynamicBitArray dba ) {
//        int idx = dba.toInt( 9 );
//        int len = dba.toInt( 10, 5 );
//        DynamicBitArray code = dba.getSlice( 15, len );
//    }
}
