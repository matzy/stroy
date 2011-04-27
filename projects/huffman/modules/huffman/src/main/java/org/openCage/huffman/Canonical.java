package org.openCage.huffman;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MultiHashtable;
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

    public Map<DynamicBitArray, DynamicBitArray> canonisize( Map<DynamicBitArray, DynamicBitArray> code ) {

        PriorityQueue<Map.Entry<DynamicBitArray,DynamicBitArray>> sorted = new PriorityQueue<Map.Entry<DynamicBitArray, DynamicBitArray>>(code.size(), new Comparator<Map.Entry<DynamicBitArray, DynamicBitArray>>() {
            @Override
            public int compare(Map.Entry<DynamicBitArray, DynamicBitArray> a, Map.Entry<DynamicBitArray, DynamicBitArray> b) {
                if ( a.getValue().getBitSize() != b.getValue().getBitSize() ) {
                    return a.getValue().getBitSize() - b.getValue().getBitSize();
                }
                return a.getKey().compareTo(b.getKey());
            }
        });


        sorted.addAll( code.entrySet());

        Map<DynamicBitArray, DynamicBitArray> ret = new HashMap<DynamicBitArray, DynamicBitArray>( code.size());

        while ( sorted.size() > 0 ) {
            Map.Entry<DynamicBitArray,DynamicBitArray> pair = sorted.poll();

            ret.put( pair.getKey(), null );
        }

        return ret;

    }

}
