package org.openCage.kleinod.collection;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

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



public class TuppleTest {

    @Test
    public void test() {
        T2<String, Integer> p = T2.valueOf("aaa",4);
        assertEquals( "aaa", p.i0);
        assertEquals( new Integer(4), p.i1);
    }

    @Test
    public void testEquals() {
        assertEquals( T2.valueOf(1,"foo"), new T2<Integer,String>(1,"foo"));
        assertEquals( T2.valueOf(1,"foo"), T2.valueOf(1,"foo"));
        assertNotSame( T2.valueOf(1,"foo"), T2.valueOf(2,"foo"));
    }

    @Test
    public void testHash() {
        assertEquals( T2.valueOf(7,"foo").hashCode(), T2.valueOf(7,"foo").hashCode());
        assertNotSame( T2.valueOf(8,"foo").hashCode(), T2.valueOf(7,"foo").hashCode());
    }



}
