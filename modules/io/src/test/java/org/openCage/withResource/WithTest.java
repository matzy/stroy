///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.openCage.withResource;
//
//import com.google.inject.Guice;
//import com.google.inject.Injector;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//
//import org.junit.Test;
//import org.openCage.lang.errors.Unchecked;
//import org.openCage.lang.functions.F1;
//import org.openCage.withResource.protocol.With;
//import org.openCage.withResource.wiring.IoWiring;
//
///**
// *
// * @author stephan
// */
//public class WithTest {
//
//    //private Injector injector = ;
//
//
//
//    private With with = Guice.createInjector(new IoWiring()).getInstance( With.class );
//
////    @Before
////    public void setUp() {
////        with = injector.getInstance(With.class);
////    }
//
//    @Test( expected=Unchecked.class)
//    public void testNonExisitngFile() {
//        with.withInputStream( new File( "idontexist" ), new F1<Void, InputStream>() {
//
//            public Void call(InputStream a) {
//                return null;
//            }
//        } );
//    }
//
//    @Test( expected=Unchecked.class )
//    public void testExeptionWhileReading() {
//
//        with.withInputStream( new File( getClass().getResource("WithTest.class").getPath() ), new F1<Void, InputStream>() {
//
//            public Void call(InputStream a) {
//                throw new IllegalArgumentException();
//            }
//        } );
//    }
//
//    @Test( expected=Unchecked.class )
//    public void testWithExecption() {
//        Injector injector = Guice.createInjector(new IoWiring());
//        With with = injector.getInstance(With.class);
//
//        with.withInputStream( new File( getClass().getResource("WithTest.class").getPath() ), new F1<Void, InputStream>() {
//
//            public Void call(InputStream a) {
//                throw Unchecked.wrap( new IOException());
//            }
//        } );
//    }
//
//    @Test
//    public void testReadSomething() {
//        Injector injector = Guice.createInjector(new IoWiring());
//        With with = injector.getInstance(With.class);
//
//        with.withInputStream( new File( getClass().getResource("WithTest.class").getPath() ), new F1<Void, InputStream>() {
//
//            public Void call(InputStream a) {
//                try {
//                    a.read();
//                } catch (IOException e) {
//                    throw Unchecked.wrap(e);
//                }
//                return null;
//            }
//        } );
//    }
//
//    @Test
//    public void testClosingStreamInRead() {
//        Injector injector = Guice.createInjector(new IoWiring());
//        With with = injector.getInstance(With.class);
//
//        with.withInputStream( new File( getClass().getResource("WithTest.class").getPath() ), new F1<Void, InputStream>() {
//
//            public Void call(InputStream a) {
//                try {
//                    a.close();
//                } catch (IOException e) {
//                    throw Unchecked.wrap(e);
//                }
//                return null;
//            }
//        } );
//
//    }
//
//
//}
