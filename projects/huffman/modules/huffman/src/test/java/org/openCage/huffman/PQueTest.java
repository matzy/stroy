package org.openCage.huffman;

import org.junit.Test;

import java.util.BitSet;
import java.util.PriorityQueue;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 04.04.11
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class PQueTest {


//    @Test
//    // check that equally weighted vals are ordered such that last entry is last
//    public void testPQueOrder() {
//        PQue pq = new PQue();
//
//        pq.add(new HNode('c', null, null, 2));
//        pq.add(new HNode('a', null, null, 1));
//        pq.add(new HNode('b', null, null, 1));
//
//        assertEquals( 'a', pq.peek().character);
//    }

    @Test
    // check that equally weighted vals are ordered such that last entry is last
    public void testPriorityQueOrder() {
        PriorityQueue<HNode> pq = new PriorityQueue<HNode>();

        pq.add(new HNode('c', null, null, 2));
        pq.add(new HNode('a', null, null, 1));
        pq.add(new HNode('b', null, null, 1));

        assertEquals( 'a', pq.peek().character);

    }
}
