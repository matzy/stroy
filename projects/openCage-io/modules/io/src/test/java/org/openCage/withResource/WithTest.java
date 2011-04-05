/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package org.openCage.withResource;

import java.io.*;

import org.junit.Test;
import org.openCage.io.Resource;
import org.openCage.io.With;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.FE1;

/**
*
* @author stephan
*/
public class WithTest {

    //private Injector injector = ;



    private With with = new With();

//    @Before
//    public void setUp() {
//        with = injector.getInstance(With.class);
//    }

    @Test( expected=Unchecked.class)
    public void testNonExisitngFile() {
        with.withInputStream( new File( "idontexist" ), new F1<Void, InputStream>() {

            public Void call(InputStream a) {
                return null;
            }
        } );
    }

    @Test( expected=Unchecked.class )
    public void testExeptionWhileReading() {

        with.withInputStream( new File( getClass().getResource("WithTest.class").getPath() ), new F1<Void, InputStream>() {

            public Void call(InputStream a) {
                throw new IllegalArgumentException();
            }
        } );
    }


//    public void testTT() {
//        with.with(CatchAll.call( new F0<InputStream>() {
//            @Override
//            public InputStream call() throws Exception{
//                return new FileInputStream("dfadff");
//            }
//        }),
//                new F1<Object, InputStream>() {
//                    @Override
//                    public Object call(InputStream closeable) {
//                        return null;  //To change body of implemented methods use File | Settings | File Templates.
//                    }
//                });
//    }
//
//    public void testYY() {
//        with.with( new F0<FileInputStream>() {
//            @Override
//            public FileInputStream call() throws Exception {
//                return new FileInputStream( "gggg" );
//            }
//        }, new F1<Object, FileInputStream>() {
//            @Override
//            public Object call(FileInputStream fileInputStream) {
//                return null;  //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });
//    }

//    public void testAA() {
//        with.with( new FE1<Object, Ref<FileInputStream>>() {
//            @Override public Object call(Ref<FileInputStream> stream ) throws Exception {
//                stream.add(new FileInputStream("fff"));
//                stream.get().read();
//                return null;  //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });
//    }

    public void testBB() {
        with.with( new FE1<Object, Resource>() {
            @Override public Object call(Resource memory) throws Exception {
                InputStream is = memory.add(new FileInputStream("foo"));
                is.read();
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void testII() {
        Integer ii = with.with( new FE1<Integer, Resource>() {
            @Override public Integer call(Resource memory) throws Exception {
                InputStream is = memory.add(new FileInputStream("foo"));
                is.read();
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

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


}
