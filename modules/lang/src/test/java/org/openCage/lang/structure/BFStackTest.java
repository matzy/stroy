package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 2, 2010
 * Time: 1:33:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BFStackTest {

    @Test( expected = IllegalStateException.class )
    public void testInitial() {
        new BFStack<String>().getCurrent();
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
