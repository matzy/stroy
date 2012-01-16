package org.openCage.lishp;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

public class SymbolTest {

    @Test
    public void testEqual() {
        assertEquals( Symbol.get("foo"), Symbol.get("foo"));
    }

    @Test
    public void testNotEqual() {
        assertNotSame(Symbol.get("fouu"), Symbol.get("foo"));
    }

    @Test
    public void testEqualSpcial() {
        assertEquals( Symbol.get(":foo"), Symbol.get(":foo"));
    }
    
    @Test
    public void testSpecial() {
        assertTrue( Symbol.get(":duh").isSpecial());
    }
    
    @Test
    public void testGlobalOfSpecial() {
        Symbol.get(":foo").setGlobal( null );
    }

    @Test( expected = LishpException.class )
    public void testGlobalOfNotSpecial() {
        Symbol.get("foo").setGlobal( null );
    }


    // no check yet
//    @Test
//    public void testCharset() {
//
//    }

}
