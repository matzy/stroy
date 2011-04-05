package org.openCage.generj;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.openCage.generj.NameExpr.NULL;



public class IdentifierTest {

    @Test
    public void testIdentis() {

        assertTrue( Identifier.isJavaIdentifier( "arg0" ));
        assertTrue( Identifier.isJavaIdentifier( "_F123A" ));
        assertFalse( Identifier.isJavaIdentifier( "null" ));

        assertFalse(Identifier.isJavaIdentifier("while"));
    }


}
