package org.openCage.lang.structure;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.F0;
import org.openCage.lang.functions.F1;

import static org.junit.Assert.assertEquals;

/**
 * ** BEGIN LICENSE BLOCK *****
 * New BSD License
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 * <p/>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * * Neither the name of Stephan Pfab nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * <p/>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * **** END LICENSE BLOCK ****
 */

public class LazyTest {

    @Test(expected = Unchecked.class)
    public void testExecption() {
        Lazy<String> lazy = new Lazy<String>(new F0<String>() {
            public String call() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        lazy.get();
    }

    @Test(expected = Unchecked.class)
    public void testGetTwiceExecption() {
        Lazy<String> lazy = new Lazy<String>(new F0<String>() {
            public String call() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        try {
            lazy.get();
        } catch (Error ex) {
        }

        lazy.get();

    }

    @Test
    public void testMultithreading() {
        final Lazy<String> lazy = new Lazy<String>(new F0<String>() {
            public String call() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw Unchecked.wrap(e);
                }
                System.out.println("evaluating");
                return "a big a slow result";
            }
        });

        for (int i = 0; i < 100; ++i) {
            new Thread(new Runnable() {
                public void run() {
                    lazy.get();
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LazyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void testCalledLazy() {

        final Ref<Integer> count = new Ref<Integer>(0);

        Lazy<String> ll = new Lazy<String>(new F0<String>() {
            public String call() {
                count.set( count.get() + 1);
                return "foo";
            }
        });

        assertEquals(0, count.get().intValue());

    }

    public void testCalledOnce() {
        final Ref<Integer> count = new Ref<Integer>(0);

        Lazy<String> ll = new Lazy<String>(new F0<String>() {
            public String call() {
                count.set( count.get() + 1 );
                return "foo";
            }
        });

        ll.get();
        assertEquals(1, count.get().intValue());

        ll.get();
        assertEquals(1, count.get().intValue());

    }

    @Test
    public void testVal() {
        final Ref<Integer> count = new Ref<Integer>(0);

        Lazy<String> ll = new Lazy<String>(new F0<String>() {
            public String call() {
                count.set( count.get() + 1 );
                return "foo";
            }
        });

        assertEquals("foo", ll.get());
    }

    @Test
    public void test1CalledLazy() {

        final Ref<Integer> count = new Ref<Integer>(0);

        Lazy1<String, Integer> ll = new Lazy1<String, Integer>(new F1<String, Integer>() {
            public String call(Integer i) {
                count.set( count.get() + 1 );
                return "" + i;
            }
        });

        assertEquals(0, count.get().intValue());

    }

    @Test
    public void test1CalledOnce() {
        final Ref<Integer> count = new Ref<Integer>(0);

        Lazy1<String, Integer> ll = new Lazy1<String, Integer>(new F1<String, Integer>() {
            public String call(Integer i) {
                count.set( count.get() + 1 );
                return "" + i;
            }
        });

        assertEquals(0, count.get().intValue());

        ll.get(1);
        assertEquals(1, count.get().intValue());

        ll.get(2);
        assertEquals(1, count.get().intValue());

    }

    @Test
    public void test1Val() {
        final Ref<Integer> count = new Ref<Integer>(0);

        Lazy1<String, Integer> ll = new Lazy1<String, Integer>(new F1<String, Integer>() {
            public String call(Integer i) {
                count.set( count.get() + 1 );
                return "" + i;
            }
        });

        assertEquals("123", ll.get(123));
        assertEquals("123", ll.get(456));
    }

    @Test
    public void test1MethodReuse() {
        final Ref<Integer> count = new Ref<Integer>(0);
        final F1<String, Integer> meth = new F1<String, Integer>() {
            public String call(Integer i) {
                count.set( count.get() + 1 );
                return "" + i;
            }
        };


        Lazy1<String, Integer> l1 = new Lazy1<String, Integer>(meth);
        Lazy1<String, Integer> l2 = new Lazy1<String, Integer>(meth);

        assertEquals("123", l1.get(123));
        assertEquals("456", l2.get(456));
    }

    @Test
    public void testNotNull() {
        final Ref<Integer> count = new Ref<Integer>(0);
        F1<String, Integer> meth = new F1<String, Integer>() {
            public String call(Integer i) {
                count.set( count.get() + 1 );
                return "" + i;
            }
        };


        new Lazy1<String, Integer>(meth);

        try {
            new Lazy1<String, Integer>(null);
        } catch (IllegalArgumentException exp) {
            // expected
        }

        if (1 < 2) {
            meth = null;
        }

        try {
            new Lazy1<String, Integer>(meth);
        } catch (IllegalArgumentException exp) {
            // expected
        }

        new Lazy1<String, Integer>(meth);

    }

}
