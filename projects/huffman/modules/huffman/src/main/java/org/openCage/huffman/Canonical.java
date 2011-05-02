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

            if ( nextCode.size() < val.size() ) {
                while ( nextCode.size() < val.size() ) {
                    nextCode.append(false);
                }
            } else {
                nextCode = nextCode.clonePlusOne();
            }

            ret.put( pair.getKey(), nextCode );
        }

        return ret;

    }

}
