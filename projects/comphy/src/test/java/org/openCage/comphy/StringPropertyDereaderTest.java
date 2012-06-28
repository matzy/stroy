//package org.openCage.comphy;
//
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class StringPropertyDereaderTest {
//
//    @Test
//    public void testDeread() {
//        assertEquals("foo", new StringPropertyDereader().fromString(new RString("foo")).get());
//    }
//
//    @Test( expected = IllegalArgumentException.class )
//    public void testDereadFromMap() {
//        RMap map = new RMap();
//        map.put(Key.valueOf("duh"), new RString("bar"));
//        new StringPropertyDereader().fromMap(map);
//    }
//}
