//package org.openCage.util.prefs;
//
//import junit.framework.TestCase;
//
//public class PreferenceStringTest extends TestCase {
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        Preferences.initForTest();
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        Preferences.clearAfterTest();
//        super.tearDown();
//    }
//
//    public void testSimple() {
//        PreferenceString ps = PreferenceString.getOrCreate( "foo", "duda" );
//        assertEquals( "duda", ps.get());
//    }
//
//    public void testAgain() {
//        {
//            PreferenceString ps = PreferenceString.getOrCreate( "foo", "duda" );
//        }
//
//        assertEquals( "duda", PreferenceString.get( "foo" ).get());
//
//        assertEquals( "duda", PreferenceString.getOrCreate( "foo", "argh ").get());
//    }
//
//    public void testCreateTwice() {
//        {
//            PreferenceString.getOrCreate( "foo", "duda" );
//        }
//
//        assertEquals( "duda", PreferenceString.getOrCreate( "foo", "argh ").get());
//    }
//
//    public void testUpdate() {
//        {
//            PreferenceString ps = PreferenceString.getOrCreate( "foo", "duda" );
//            ps.set( "so");
//        }
//
//        assertEquals( "so", PreferenceString.get( "foo" ).get());
//    }
//
//    public void testTwo() {
//        PreferenceString ps = PreferenceString.getOrCreate( "foo", "duda" );
//        PreferenceString ps2 = PreferenceString.get( "foo" );
//
//        ps.set( "so");
//
//        assertEquals( "so", ps2.get());
//    }
//
//    public void testInitalGet() {
//        try {
//            PreferenceString.get( "nop");
//            fail( "not initiated" );
//        } catch ( IllegalStateException exp ) {
//            // expected
//        }
//    }
//}
