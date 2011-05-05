package org.openCage.huffman;

import org.openCage.lang.structure.T2;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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

        Map<BitField, BitField> codes = computeCodes( pq.poll(), new BitFieldImpl(), new HashMap<BitField, BitField>() );

        return codes;
    }

    private void buildHuffmanTree(PriorityQueue<HNodeN> pq) {
        while( pq.size() > 1 ) {
            HNodeN left = pq.poll();
            HNodeN right = pq.poll();

            pq.add( new HNodeN( new BitFieldImpl(), left.getWeight() + right.getWeight(), left, right  ));
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

    private Map<BitField, BitField> computeCodes( HNodeN node, BitField prefix, Map<BitField, BitField> codes ) {
        if ( node.isLeaf() ) {
            //System.out.println( "" + node + " -> "  + prefix );
            codes.put( node.getCh(), prefix );
            return codes;
        }

        computeCodes( node.getLeft(), prefix.clone().append( false  ), codes );
        computeCodes( node.getRight(), prefix.clone().append( true  ), codes );
//        computeCodes( node.getLeft(), BitFieldImpl.valueOf( false ).append( prefix ), codes );
//        computeCodes( node.getRight(), BitFieldImpl.valueOf( true ).append( prefix ), codes );

        return codes;
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

    public BitField encode( int keySize ) {
        Map<BitField, BitField> code = getCode( keySize );

        T2<Map<BitField, BitField>,Integer> can = Canonical.canonisize( code );
        code = can.i0;
        int valSize = can.i1;
//
//        for ( BitField val : code.values() ) {
//            valSize = Math.max( valSize,  val.size() );
//        }

        BitField res = Canonical.writeCode(code, (byte) valSize);

        System.out.println( "res with code " + res);

        for( int pos = 0; pos < source.size(); pos += keySize ) {
            BitField key = source.getSlice( pos, keySize );
            res.append( code.get( key ));
        }

        System.out.println( "and content   " +res);

        return res;
    }

    public BitField decode( BitField coded )  {
        T2<Map<BitField,BitField>,Integer> res= Canonical.readCode( coded );
        Map<BitField,BitField> code = res.i0;
        int pos = res.i1;

        HNodeN tree = codeToTree( code );
        BitField ret = new BitFieldImpl();

        HNodeN current = tree;
        for ( int i = pos; i < coded.size(); i++ ) {
            if ( coded.get(i)) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }

            if ( current.getCh().size() > 0 ) {
                // found one
                ret.append( current.getCh() );
                current = tree;
            }
        }

        ret.trimEnd();
        return ret;

    }

    public BitField encode( Map<BitField, BitField> code ) {

        BitField res = new BitFieldImpl();

        int keySize = code.keySet().iterator().next().size();

        for( int pos = 0; pos < source.size(); pos += keySize ) {
            BitField key = source.getSlice( pos, keySize );
            res.append( code.get( key ));
        }

        return res;
    }

    public BitField decode( Map<BitField, BitField> code, BitField src ) {
        HNodeN tree = codeToTree( code );
        BitField ret = new BitFieldImpl();

        HNodeN current = tree;
        for ( int i = 0; i < src.size(); i++ ) {
            if ( src.get(i)) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }

            if ( current.getCh().size() > 0 ) {
                // found one
                ret.append( current.getCh() );
                current = tree;
            }
        }

        ret.trimEnd();
        return ret;
    }

    private HNodeN codeToTree( Map<BitField, BitField> code ) {
        HNodeN tree = new HNodeN( new BitFieldImpl(), -1 );

        for ( Map.Entry<BitField, BitField> pair : code.entrySet() ) {
            BitField val = pair.getValue();

            HNodeN current = tree;

            for ( int i = 0; i < val.size(); i++ ) {
                if( current.getCh().size() > 0 ) {
                    throw new IllegalArgumentException( "can't attach a node to a leaf" );
                }
                if ( val.get(i)) {
                    if ( current.getRight() == null ) {
                        current.setRight(new HNodeN(new BitFieldImpl(), -1));
                    }

                    current = current.getRight();
                } else {
                    if ( current.getLeft() == null ) {
                        current.setLeft(new HNodeN(new BitFieldImpl(), -1));
                    }

                    current = current.getLeft();

                }
            }

            current.setCh( pair.getKey() );
        }

        return tree;

    }


}
