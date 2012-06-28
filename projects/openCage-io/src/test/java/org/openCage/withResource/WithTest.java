package org.openCage.withResource;

import java.io.*;

import org.junit.Test;
import org.openCage.io.Resource;
import org.openCage.io.With;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.FE1;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ***** END LICENSE BLOCK *****/
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
