package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BFStackTest {


    @Test
    public void testIsEmpty() {
        BFStack<String> stack = new BFStack<String>();

        assertTrue( stack.isEmpty() );

        stack.push( "foo");
        assertFalse( stack.isEmpty() );
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
