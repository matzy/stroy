//package org.openCage.comphy;
//
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class MapPropertyDereaderTest {
//
//    @Test
//    public void testSimple() {
//        RMap rmap = new RMap();
//        rmap.put(Key.valueOf("key"), new RString("val"));
//
//        MapProperty<StringProperty> prop = deread( new MapPropertyDereader<StringProperty>( new StringPropertyDereader()), rmap );
//        assertNotNull( prop );
//
//        assertEquals( "val", prop.get(Key.valueOf("key")).get() );
//    }
//
//    @Test
//    public void testEmptyMap() {
//        MapProperty<StringProperty> prop = deread(new MapPropertyDereader<StringProperty>(new StringPropertyDereader()), new RString("   "));
//        assertNotNull( prop );
//    }
//
//    @Test
//    public void testComplex() {
//        RMap elem = new RMap();
//        elem.put(Key.valueOf("description"), new RString("a image codex"));
//
//        RMap rmap = new RMap();
//        rmap.put(Key.valueOf("jpg"), elem );
//
//        MapProperty<MapProperty<StringProperty>> prop =
//                deread( new MapPropertyDereader<MapProperty<StringProperty>>(new MapPropertyDereader<StringProperty>(new StringPropertyDereader())),
//                        rmap);
//        assertNotNull(prop);
//        assertEquals( "a image codex", prop.get(Key.valueOf("jpg")).get(Key.valueOf("description")).get());
//    }
//
//
//}
