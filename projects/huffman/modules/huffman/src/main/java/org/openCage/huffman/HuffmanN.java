package org.openCage.huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 06.04.11
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class HuffmanN {

    private final DynamicBitArray source;

    public HuffmanN( DynamicBitArray src ) {
        this.source = src;
    }

    public Map<DynamicBitArray, DynamicBitArray> getCode( int size ) {

        Map<DynamicBitArray, Integer> count = count(size);

        PriorityQueue<HNodeN> pq = fillPriorityQue(count);

        buildHuffmanTree(pq);

        Map<DynamicBitArray, DynamicBitArray> codes = new HashMap<DynamicBitArray, DynamicBitArray>();
        computeCodes( pq.poll(), new DynamicBitArrayDirect(), codes );

        return codes;
    }

    private void buildHuffmanTree(PriorityQueue<HNodeN> pq) {
        while( pq.size() > 1 ) {
            HNodeN left = pq.poll();
            HNodeN right = pq.poll();

            pq.add( new HNodeN( new DynamicBitArrayDirect(), left.getWeight() + right.getWeight(), left, right  ));
        }
    }

    private PriorityQueue<HNodeN> fillPriorityQue(Map<DynamicBitArray, Integer> count) {
        PriorityQueue<HNodeN> pq = new PriorityQueue<HNodeN>();

        for ( Map.Entry<DynamicBitArray,Integer> pair : count.entrySet() ) {
            pq.add( new HNodeN( pair.getKey(), pair.getValue().intValue()));
        }
        return pq;
    }

    private Map<DynamicBitArray, Integer> count(int size) {
        Map<DynamicBitArray, Integer> count = new HashMap<DynamicBitArray, Integer>();

        for ( int i = 0; i < source.getBitSize(); i += size ) {
            incr( count, source.getSlice( i, size ));
        }
        return count;
    }

    private static void incr( Map<DynamicBitArray, Integer> count, DynamicBitArray key ) {
        if ( !count.containsKey( key )) {
            count.put( key, 1 );
            return;
        }

        count.put( key, count.get(key) + 1);
    }

    private void computeCodes( HNodeN node, DynamicBitArray prefix, Map<DynamicBitArray, DynamicBitArray> codes ) {
        if ( node.isLeaf() ) {
            //System.out.println( "" + node + " -> "  + prefix );
            codes.put( node.getCh(), prefix );
            return;
        }

        computeCodes( node.getLeft(), prefix.clone().append( false  ), codes );
        computeCodes( node.getRight(), prefix.clone().append( true  ), codes );
    }

    public static void printCodes( Map<DynamicBitArray, DynamicBitArray>  codes ) {
        PriorityQueue<Map.Entry<DynamicBitArray, DynamicBitArray>> out = new PriorityQueue<Map.Entry<DynamicBitArray, DynamicBitArray>>( codes.size(),
                new Comparator<Map.Entry<DynamicBitArray, DynamicBitArray>>() {
                    @Override
                    public int compare(Map.Entry<DynamicBitArray, DynamicBitArray> a, Map.Entry<DynamicBitArray, DynamicBitArray> b) {
                        return a.getKey().compareTo(b.getKey());
                    }
                }
                );

        out.addAll( codes.entrySet());


        while( out.size() > 0 ) {
            Map.Entry<DynamicBitArray, DynamicBitArray> pair = out.poll();

            System.out.println( pair.getKey().toString8() + " -> " + pair.getValue().toString());
        }
    }


}
