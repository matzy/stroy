package org.openCage.huffman;

import org.junit.Test;

import java.util.BitSet;
import java.util.PriorityQueue;

import static junit.framework.Assert.assertEquals;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

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
