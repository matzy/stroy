package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.clazz.MRU;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Feb 1, 2010
 * Time: 7:51:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class MRUTest {

    @Test
    public void testAdding() {
        MRU<String> mru = new MRU<String>();

        mru.use( "foo" );
        assertEquals( "foo", mru.getAll().iterator().next() );

        mru.use( "duda" );
        assertEquals( "duda", mru.getAll().iterator().next() );

        mru.use( "foo" );
        assertEquals( "foo", mru.getAll().iterator().next() );
        assertEquals( 2, mru.getAll().size());

        mru.use( "333" );
        assertEquals( "333", mru.getAll().iterator().next() );
        assertEquals( 3, mru.getAll().size());
        mru.setMaxSize( 2 );
        assertEquals( 2, mru.getAll().size());

        mru.use( "444" );
        assertEquals( "444", mru.getAll().iterator().next() );
        assertEquals( 2, mru.getAll().size());
    }
}
