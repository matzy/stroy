package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OnceTest {

    @Test
    public void testOnce() {
        Once<String> o = new Once<String>( "dflt");

        assertFalse( o.isSet() );

        o.set( "foo");
        assertTrue( o.isSet() );
        assertEquals( "foo", o.get() );

    }

    @Test( expected = IllegalStateException.class )
    public void setAfterGet() {
        Once<String> o = new Once<String>( "dflt");
        o.get();
        o.set("b");
    }

    @Test( expected = IllegalStateException.class )
    public void setTwiceBad() {
        Once<String> o = new Once<String>( "dflt");

        o.set("a");
        o.set("b");
    }

    @Test
    public void setTwiceGood() {
        Once<String> o = new Once<String>( "dflt");

        o.set("a");
        o.set("a");

        assertTrue( o.isSet());
    }

    @Test
    public void setIf() {
        Once<String> to = new Once<String>( "to");
        Once<String> from = new Once<String>( "from");

        to.setIf( from );
        assertFalse( to.isSet() );

        from.set( "yes" );
        to.setIf( from );
        assertTrue( to.isSet() );
        assertEquals( "yes", to.get() );
    }

    @Test
    public void setIfNothingTodo() {
        Once<String> to = new Once<String>( "to");
        Once<String> from = new Once<String>( "from");


        to.set("done");
        to.setIf( from );

        assertEquals( "done", to.get() );
    }

    @Test( expected = IllegalStateException.class ) 
    public void setIfFail() {
        Once<String> to = new Once<String>( "to");
        Once<String> from = new Once<String>( "from");


        to.set("done");
        from.set("also");

        to.setIf( from );
    }


}
