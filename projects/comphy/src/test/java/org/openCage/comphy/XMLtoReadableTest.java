//package org.openCage.comphy;
//
//import org.junit.Test;
//
//import java.io.File;
//import java.net.URL;
//
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertNotNull;
//import static junit.framework.Assert.assertTrue;
//
//public class XMLtoReadableTest {
//
//    @Test
//    public void testMinimal() {
//        XMLtoReadable xmlReader = new XMLtoReadable();
//
//        URL url = getClass().getResource("/org/openCage/comphy/comphy-minimal.xml");
//        assertTrue(new File( url.getFile()).exists());
//
//        Readable readable =  xmlReader.read(url.getFile());
//
//        assertNotNull(readable);
//        assertTrue(readable instanceof RMap );
//    }
//
//    @Test
//    public void testOneString() {
//        XMLtoReadable xmlReader = new XMLtoReadable();
//
//        URL url = getClass().getResource("/org/openCage/comphy/comphy-one.xml");
//        assertTrue(new File( url.getFile()).exists());
//
//        Readable readable =  xmlReader.read(url.getFile());
//
//        assertNotNull(readable);
//        assertTrue(readable instanceof RMap );
//
//        RMap map = (RMap)readable;
//
//        assertTrue(map.containsKey(XMLtoReadable.Key.valueOf("key")));
//        assertEquals( "txt", map.get(XMLtoReadable.Key.valueOf("key")).toString());
//    }
//
//    @Test
//    public void testOneMap() {
//        XMLtoReadable xmlReader = new XMLtoReadable();
//
//        URL url = getClass().getResource("/org/openCage/comphy/comphy-map.xml");
//        assertTrue(new File( url.getFile()).exists());
//
//        Readable readable =  xmlReader.read(url.getFile());
//
//        assertNotNull(readable);
//        assertTrue(readable instanceof RMap );
//
//        RMap map = (RMap)readable;
//
//        assertTrue(map.containsKey( XMLtoReadable.Key.valueOf("things")));
//        Readable rr = map.get(XMLtoReadable.Key.valueOf("things"));
//        assertTrue( rr instanceof RMap );
//
//        RMap things = (RMap)rr;
//
//        assertEquals("txt", things.get(XMLtoReadable.Key.valueOf("one")).toString());
//        assertEquals("2", things.get(XMLtoReadable.Key.valueOf("two")).toString());
//    }
//
//    @Test
//    public void testEmptyList() {
//        XMLtoReadable xmlReader = new XMLtoReadable();
//
//        URL url = getClass().getResource("/org/openCage/comphy/comphy-empty-list.xml");
//        assertTrue(new File( url.getFile()).exists());
//
//        Readable readable =  xmlReader.read(url.getFile());
//
//        assertNotNull(readable);
//        assertTrue(readable instanceof RMap );
//
//        RMap map = (RMap)readable;
//
//        assertTrue(map.containsKey( XMLtoReadable.Key.valueOf("foo")));
//        Readable rr = map.get(XMLtoReadable.Key.valueOf("foo"));
//        assertTrue( rr instanceof RString);
//
//    }
//
//    @Test
//    public void testList() {
//        XMLtoReadable xmlReader = new XMLtoReadable();
//
//        URL url = getClass().getResource("/org/openCage/comphy/comphy-list.xml");
//        Readable readable =  xmlReader.read(url.getFile());
//        RMap map = (RMap)readable;
//
//        assertTrue(map.containsKey( XMLtoReadable.Key.valueOf("list")));
//        RList list = (RList)map.get(XMLtoReadable.Key.valueOf("list"));
//
//        assertEquals( "texta", list.get(0).toString());
//        assertEquals( "textb", list.get(1).toString());
//        assertEquals( "textc", list.get(2).toString());
//    }
//
//    @Test
//    public void testMore() {
//        XMLtoReadable xmlReader = new XMLtoReadable();
//
//        URL url = getClass().getResource("/org/openCage/comphy/comphy-more.xml");
//        assertTrue(new File( url.getFile()).exists());
//
//        Readable readable =  xmlReader.read(url.getFile());
//
//        assertNotNull(readable);
//        assertTrue(readable instanceof RMap );
//        RMap map = (RMap)readable;
//
//        assertTrue(map.containsKey( XMLtoReadable.Key.valueOf("one")));
//        Readable rr = map.get(XMLtoReadable.Key.valueOf("one"));
//        assertTrue( rr instanceof RMap );
//
//        assertTrue(map.containsKey( XMLtoReadable.Key.valueOf("two")));
//        rr = map.get(XMLtoReadable.Key.valueOf("two"));
//        assertTrue( rr instanceof RList );
//
//        assertTrue(map.containsKey( XMLtoReadable.Key.valueOf("three")));
//        rr = map.get(XMLtoReadable.Key.valueOf("three"));
//        assertTrue( rr instanceof RString);
//
//    }
//
//    @Test
//    public void testComplex() {
//        XMLtoReadable xmlReader = new XMLtoReadable();
//
//        Readable readable =  xmlReader.read( getClass().getResource("/org/openCage/comphy/comphy-complex.xml").getFile());
//
//        RMap map = (RMap)readable;
//
//        RMap fileTypes = (RMap)map.get(XMLtoReadable.Key.valueOf("fileTypes"));
//        RMap jpeg      = (RMap)fileTypes.get(XMLtoReadable.Key.valueOf("jpg"));
//
//        assertEquals( "lossy picture codec", jpeg.get(XMLtoReadable.Key.valueOf("description")).toString() );
//    }
//
//    @Test
//    public void testSpecialChars() {
//        XMLtoReadable xmlReader = new XMLtoReadable();
//
//        Readable readable =  xmlReader.read( getClass().getResource("/org/openCage/comphy/comphy-complex1.xml").getFile());
//
//        RMap map = (RMap)readable;
//
//        RMap jpg = (RMap)map.get(XMLtoReadable.Key.valueOf("jpg"));
//
//        assertEquals( "<standard>", jpg.get(XMLtoReadable.Key.valueOf("open")).toString() );
//    }
//
//    @Test
//    public void testEmptyString() {
//        XMLtoReadable xmlReader = new XMLtoReadable();
//
//        Readable readable =  xmlReader.read( getClass().getResource("/org/openCage/comphy/comphy-empty-string.xml").getFile());
//
//        RMap map = (RMap)readable;
//
//        RString str = (RString)map.get(XMLtoReadable.Key.valueOf("empty"));
//
//        assertEquals( "", str.get() );
//    }
//
//}
