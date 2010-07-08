package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is stroy code.
 *
 * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
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
