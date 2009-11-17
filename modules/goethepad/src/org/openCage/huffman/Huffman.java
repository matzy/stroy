package org.openCage.huffman;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Nov 12, 2009
 * Time: 7:50:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Huffman {

    public DynamicBitArray encode( byte[] array ) {

        PQue pq = buildPriorityQue( array );

        //System.out.println( pq );

        combine( pq );

        //System.out.println("-----");

        //System.out.println( pq );

        DynamicBitArray[] codes = new DynamicBitArray[257];
        computeCodes( pq.pop(), new DynamicBitArray(), codes );


//        for ( int i = 0; i < 257; i++ )  {
//            System.out.println( codes[i]);
//        }

        DynamicBitArray result = encode( array, codes );

        //System.out.println( result.toString8() );


        return result;
    }

    private DynamicBitArray encode(byte[] array, DynamicBitArray[] codes) {

        DynamicBitArray res = new DynamicBitArray();

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
            pq.push( new HNode( i + Byte.MIN_VALUE, null, null, weight[i]));
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
}
