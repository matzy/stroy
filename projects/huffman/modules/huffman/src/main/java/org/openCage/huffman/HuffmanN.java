package org.openCage.huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 06.04.11
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class HuffmanN {

    private final BitField source;

    public HuffmanN( BitField src ) {
        this.source = src;
    }

    public Map<BitField, BitField> getCode( int size ) {

        Map<BitField, Integer> count = count(size);

        PriorityQueue<HNodeN> pq = fillPriorityQue(count);

        buildHuffmanTree(pq);

        Map<BitField, BitField> codes = new HashMap<BitField, BitField>();
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

    private PriorityQueue<HNodeN> fillPriorityQue(Map<BitField, Integer> count) {
        PriorityQueue<HNodeN> pq = new PriorityQueue<HNodeN>();

        for ( Map.Entry<BitField,Integer> pair : count.entrySet() ) {
            pq.add( new HNodeN( pair.getKey(), pair.getValue().intValue()));
        }
        return pq;
    }

    private Map<BitField, Integer> count(int size) {
        Map<BitField, Integer> count = new HashMap<BitField, Integer>();

        for ( int i = 0; i < source.size(); i += size ) {
            incr( count, source.getSlice( i, size ));
        }
        return count;
    }

    private static void incr( Map<BitField, Integer> count, BitField key ) {
        if ( !count.containsKey( key )) {
            count.put( key, 1 );
            return;
        }

        count.put( key, count.get(key) + 1);
    }

    private void computeCodes( HNodeN node, BitField prefix, Map<BitField, BitField> codes ) {
        if ( node.isLeaf() ) {
            //System.out.println( "" + node + " -> "  + prefix );
            codes.put( node.getCh(), prefix );
            return;
        }

        computeCodes( node.getLeft(), prefix.clone().append( false  ), codes );
        computeCodes( node.getRight(), prefix.clone().append( true  ), codes );
    }

    public static void printCodes( Map<BitField, BitField>  codes ) {
        PriorityQueue<Map.Entry<BitField, BitField>> out = new PriorityQueue<Map.Entry<BitField, BitField>>( codes.size(),
                new Comparator<Map.Entry<BitField, BitField>>() {
                    @Override public int compare(Map.Entry<BitField, BitField> a, Map.Entry<BitField, BitField> b) {
                        return a.getKey().compareTo(b.getKey());
                    }
                }
                );

        out.addAll( codes.entrySet());


        while( out.size() > 0 ) {
            Map.Entry<BitField, BitField> pair = out.poll();

            System.out.println( pair.getKey().toString8() + " -> " + pair.getValue().toString());
        }
    }


}
