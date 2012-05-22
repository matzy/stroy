package org.openCage.io.fspath;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ElementRulesTest {

    @Test
    public void tesLegal() {
        assertTrue( ElementRules.isLegal( "Aa-.txt"));
        assertTrue( ElementRules.isLegal( "0a1B999Aa_.txt.foo"));
    }

    @Test
    public void testIllegal() {
        assertFalse( ElementRules.isLegal( "" ));
        assertFalse( ElementRules.isLegal( "/a" ));
        assertFalse( ElementRules.isLegal( "aaa/a" ));
        assertFalse( ElementRules.isLegal( "aaa\\a" ));
        assertFalse( ElementRules.isLegal( "$" ));
        assertFalse( ElementRules.isLegal( "#" ));
        assertFalse( ElementRules.isLegal( "@" ));
        assertFalse( ElementRules.isLegal( "|" ));
        assertFalse( ElementRules.isLegal( "%" ));
        assertTrue( ElementRules.isLegal( "My Documents" ));
    }

    @Test( expected = IllegalArgumentException.class )
    public void testList() {
        ElementRules.check( "aa", "%" );
    }

}
