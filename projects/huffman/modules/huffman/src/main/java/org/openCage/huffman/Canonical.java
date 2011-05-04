package org.openCage.huffman;

import org.openCage.lang.structure.T2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 06.04.11
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class Canonical {

    public T2<Map<BitField, BitField>, Integer> canonisize( Map<BitField, BitField> code ) {

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

        BitField nextCode = BitFieldImpl.valueOf( false );
        BitField lastCode = null;

        while ( sorted.size() > 0 ) {
            Map.Entry<BitField,BitField> pair = sorted.poll();

            BitField val = pair.getValue();

            if ( nextCode.size() < val.size() ) {
                while ( nextCode.size() < val.size() ) {
                    nextCode.append(false);
                }
            }

            ret.put( pair.getKey(), nextCode.clone() );

            lastCode = nextCode;
            nextCode = nextCode.clonePlusOne();

        }

        return T2.valueOf( ret, lastCode.size());

    }

    public BitField writeCode( Map<BitField, BitField> code, byte depth ) {

        int size = code.keySet().iterator().next().size(); // size of all keys should be the same

        // write size in 5 bit

        if ( size > 20 ) {
            throw new IllegalArgumentException( "key sizes to large" );
        }

        int valSize = highestSetBit( BitFieldImpl.valueOf((byte)4));
        System.out.println("-- " + valSize);

        BitField ret = BitFieldImpl.valueOf((byte) size, 5);
        ret.append(BitFieldImpl.valueOf((byte) valSize, 5));

        System.out.println( ret );

        BitField key = BitFieldImpl.valueOf(false);

        for ( int i = 0; i < (int)Math.pow( 2, size ); i++) {
            if ( !code.containsKey( key )) {
                ret.append( BitFieldImpl.valueOf((byte)0,valSize));
            } else {
                ret.append( BitFieldImpl.valueOf( (byte)code.get(key).size(),valSize));
            }

            key = key.clonePlusOne();
        }

        return ret;
    }

    public Map<BitField, BitField> readCode( BitField bf ) {
        int keySize = bf.getInt(0,5);
        int valLength = bf.getInt(5,5);

        System.out.println( "" + keySize + " - " + valLength );

        return null;
    }



    public static int highestSetBit( BitField bf ) {
        for ( int i = bf.size() -1; i >= 0; i-- ) {
            if ( bf.get(i)) {
                return i;
            }
        }

        return -1;
    }

}
