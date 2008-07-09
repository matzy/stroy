package org.openCage.util.prefs;

import junit.framework.TestCase;

import java.util.Arrays;

public class PListSelectionStringTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Preferences.initForTest();
    }

    @Override
    protected void tearDown() throws Exception {
        Preferences.clearAfterTest();
        super.tearDown();
    }

    public void testSimple() {
        PListSelectionString.getOrCreate( "foo", new ListSelection<String>( "2", Arrays.asList( "1", "2")));

        assertEquals( "2", PListSelectionString.get( "foo").get().getSelection() );
    }

    public void testSimple2() {
        PListSelectionString.getOrCreate( "foo", "2", "1", "2");

        assertEquals( "2", PListSelectionString.get( "foo").get().getSelection() );
    }

}
