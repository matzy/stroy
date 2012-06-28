package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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



public class BFStackTest {


    @Test
    public void testIsEmpty() {
        BFStack<String> stack = new BFStack<String>();

        assertTrue( stack.isEmpty() );

        stack.push( "foo");
        assertFalse( stack.isEmpty() );
    }

    @Test
    public void testClear() {
        BFStack<String> stack = new BFStack<String>();
        stack.push( "foo");
        stack.push( "foo");
        stack.push( "foo");
        assertFalse( stack.isEmpty() );

        stack.clear();
        assertTrue( stack.isEmpty() );
        assertFalse( stack.hasBackward() );
        assertFalse( stack.hasForward() );        
    }

    @Test( expected = IllegalStateException.class )
    public void testEmptyGetCurrent() {
        new BFStack<String>().getCurrent();
    }

    @Test
    public void testHasBackWardBackward() {

        BFStack<String> stack = new BFStack<String>();
        stack.push( "foo");
        stack.push( "dug");

        assertTrue( stack.hasBackward());
        stack.backward(); // no execption
    }

    @Test( expected = IllegalStateException.class )
    public void testNotHasBackWardBackward() {

        BFStack<String> stack = new BFStack<String>();
        stack.push( "foo");

        assertFalse( stack.hasBackward());
        stack.backward(); // throws
    }

    @Test( expected = IllegalStateException.class )
    public void testNotHasForwardForward() {

        BFStack<String> stack = new BFStack<String>();
        stack.push( "foo");

        assertFalse( stack.hasForward());
        stack.forward(); // throws
    }

    @Test
    public void testPush() {
        BFStack<String>  bf = new BFStack<String>();

        bf.push( "foo");
        assertEquals( "foo", bf.getCurrent() );
    }

    @Test
    public void testBackward() {
        BFStack<String>  bf = new BFStack<String>();

        bf.push( "foo");
        bf.push( "duh");

        assertEquals( "foo", bf.backward() );
        assertEquals( "foo", bf.getCurrent() );

    }

    @Test
    public void testBackwardForwardDeep() {
        BFStack<String>  bf = new BFStack<String>();

        bf.push( "A");
        bf.push( "B");
        bf.push( "C");
        bf.push( "D");
        bf.push( "E");

        assertTrue( bf.hasBackward());
        assertEquals( "D", bf.backward() );
        assertEquals( "C", bf.backward());
        assertEquals( "B", bf.backward());
        assertEquals( "A", bf.backward());
        assertFalse( bf.hasBackward());


        assertTrue( bf.hasForward());
        assertEquals( "B", bf.forward());
        assertEquals( "C", bf.forward());
        assertEquals( "D", bf.forward());
        assertEquals( "E", bf.forward());
        assertFalse( bf.hasForward());

    }

    @Test
    public void testForward() {
        BFStack<String>  bf = new BFStack<String>();

        bf.push( "foo");
        bf.push( "duh");

        bf.backward();
        assertEquals( "duh", bf.forward() );
        assertEquals( "duh", bf.getCurrent() );
    }

    @Test
    public void testPushWithHasForward() {
        BFStack<String>  bf = new BFStack<String>();

        bf.push( "A");
        bf.push( "B");
        bf.push( "C");

        bf.backward();
        bf.backward();

        assertEquals( "A", bf.getCurrent() );

        bf.push( "new");
        assertEquals( 2, bf.size());
        assertFalse( bf.hasForward());

    }


}
