package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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



public class BIMapTest {


    @Test
    public void testBasicMap() {
        BiMap<String,Integer> bim = new BiMap<String, Integer>();

        assertEquals(0, bim.size());
        assertNull( bim.get("A"));
        assertFalse( bim.containsKey( "A" ));
        assertFalse( bim.containsValue( 1 ));
        assertTrue( bim.isEmpty());


        bim.put( "A", 1 );

        assertEquals(1, bim.size());
        assertFalse( bim.isEmpty());
        assertTrue( bim.containsKey( "A" ));
        assertTrue( bim.containsValue( 1 ));


        bim.remove("A");
        assertEquals(0, bim.size());
        assertNull( bim.get("A"));
        assertFalse( bim.containsKey( "A" ));
        assertFalse( bim.containsValue( 1 ));
        assertTrue( bim.isEmpty());


        bim.put( "B", 2 );
        bim.put( "C", 3 );
        bim.clear();
        assertEquals(0, bim.size());
        assertNull( bim.get("A"));
        assertFalse( bim.containsKey( "A" ));
        assertFalse( bim.containsValue( 1 ));
        assertTrue( bim.isEmpty());

    }

    @Test
    public void testBi() {
        BiMap<String,Integer> bim = new BiMap<String, Integer>();
        bim.put( "A", 1 );

        assertEquals( new Integer(1), bim.get("A"));
        assertEquals( "A", bim.getReverse(1));
    }




}
