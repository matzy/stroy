package org.openCage.util.prefs;

import junit.framework.TestCase;

import java.util.Arrays;

public class ListSelectionTest extends TestCase {

    public void testIllegalSelectiob() {
        ListSelection<String> ls = new ListSelection<String>( "1", Arrays.asList( "1", "2"));

        try {
            ls.setSelection( "3 ");
            fail( "illegal selection" );
        } catch ( IllegalArgumentException exp ) {
            //expected
        }
    }

    public void testIllegalStartSelection() {

        try {
            ListSelection<String> ls = new ListSelection<String>( "3", Arrays.asList( "1", "2"));
            fail( "illegal selection" );
        } catch ( IllegalArgumentException exp ) {
            //expected
        }
    }

    public void testAlternativeConstr() {
        new ListSelection<String>("1", "1", "2" );
    }
}
