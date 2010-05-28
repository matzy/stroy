//package org.openCage.property;
//
//import org.junit.Test;
//
//import static junit.framework.Assert.assertEquals;
//
//public class NonPersiPropstoreTest {
//
//    @Test
//    public void testSetGet() {
//        PropStore store = new NonPersistingPropStore();
//        Property<String> prop = PersistentProp.get( store, "key", "default", "description");
//
////        store.setProperty( "key", prop );
//
//        assertEquals( prop, store.get("key"));
//    }
//
//
////    @Test( expected = IllegalArgumentException.class )
////    public void testSetTwice() {
////        PropStore store = new NonPersistingPropStore();
////        Property<String> prop = PersistentProp.get( store, "key", "default", "description");
////        Property<String> other = PersistentProp.get( store, "key", "other", "description");
////
////        store.setProperty( "key", prop );
////        store.setProperty( "key", other );
////    }
////
//
//
//    public static class ThisPropertyProvider extends PropertyProvider<String> {
//        public ThisPropertyProvider() {
//            super(new NonPersistingPropStore(), "key", "dflt", "description");
//        }
//    }
//
//    public static class OtherPropertyProviderA extends PropertyProvider<String> {
//        public OtherPropertyProviderA() {
//            super(new NonPersistingPropStore(), "key", "dflt", "description");
//        }
//    }
//
//    public static class OtherPropertyProviderB extends PropertyProvider<String> {
//        public OtherPropertyProviderB() {
//            super(new NonPersistingPropStore(), "key", "dflt", "description");
//        }
//    }
//
//
//    @Test
//    public void testStd(){
//        Property<String> prop = new ThisPropertyProvider().get();
//
//        assertEquals( "dflt", prop.get() );
//    }
//
//    @Test()
//    public void test2ProvidersOther(){
//        new OtherPropertyProviderA();
//        new OtherPropertyProviderB();
//    }
//
//    @Test( expected = IllegalStateException.class )
//    public void test2Providers(){
//        new ThisPropertyProvider();
//        new ThisPropertyProvider();
//    }
//
//
//
//    public static class SingletonPropertyProvider extends PropertyProvider<String> {
//
//        public static SingletonPropertyProvider the = new SingletonPropertyProvider();
//
//        private SingletonPropertyProvider() {
//            super(new NonPersistingPropStore(), "key", "dflt", "description");
//        }
//    }
//
//    @Test
//    public void testProvidersOk(){
//        Property<String> prop = SingletonPropertyProvider.the.get();
//        Property<String> prop2 = SingletonPropertyProvider.the.get();
//
//        assertEquals( prop, prop2 );
//    }
//
//
//    @Test
//    public void testGetAndModi() {
//        Property<String> prop = SingletonPropertyProvider.the.get();
//        Property<String> prop2 = SingletonPropertyProvider.the.get();
//
//        assertEquals( "dflt", prop2.get());
//
//        prop.set( "foo");
//
//        assertEquals( "foo", prop2.get());
//
//    }
//
//}
