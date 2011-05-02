package org.openCage.huffman;

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

    public Map<BitField, BitField> canonisize( Map<BitField, BitField> code ) {

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

        BitField nextCode = new BitFieldImpl();

        while ( sorted.size() > 0 ) {
            Map.Entry<BitField,BitField> pair = sorted.poll();

            BitField val = pair.getValue();

            nextCode = nextCode.clonePlusOne();

            if ( nextCode.size() < val.size() ) {
                while ( nextCode.size() < val.size() ) {
                    nextCode.append(false);
                }
            }

            ret.put( pair.getKey(), nextCode.clone() );
        }

        return ret;

    }

    public BitField writeCode( Map<BitField, BitField> code ) {

        int size = code.keySet().iterator().next().size(); // size of all keys should be the same

        // write size in 5 bit

        if ( size > 20 ) {
            throw new IllegalArgumentException( "key sizes to large" );
        }

        BitField ret = BitFieldImpl.valueOf( (byte)size, 5 );

        int bitSize = 1;
        while( size > 1 ) {
            bitSize++;
            size /= 2;
        }

        System.out.println(bitSize);

        BitField key = BitFieldImpl.valueOf(false);

        for ( int i = 0; i < (int)Math.pow( 2, size ); i++) {
            if ( !code.containsKey( key )) {
                ret.append( BitFieldImpl.valueOf((byte)0,bitSize));
            } else {
                ret.append( BitFieldImpl.valueOf( (byte)code.get(key).size(),bitSize));
            }

            key = key.clonePlusOne();
        }

        return ret;
    }

}
