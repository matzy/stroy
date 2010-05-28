package org.openCage.lang.tic;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openCage.lang.Lazy;
import org.openCage.lang.Tic;
import org.openCage.lang.functions.F0;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: May 27, 2010
 * Time: 10:09:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class TicTest {

    @BeforeClass
    public static void before() {

        Tic.clear();

        Tic.bind( Mess.class, new F0<Mess>() {
            @Override public Mess call() {
                return new Mess() {
                    @Override
                    public String out() {
                        return "Hello World";
                    }
                };
            }
        });

        Tic.bind( Multi.class, new F0<Multi>() {
            @Override public Multi call() {
                return new HelloWorldM();
            }
        });

        Tic.bindSingleton( Singl.class, new F0<Singl>() {
            @Override
            public Singl call() {
                return new Singleton();
            }
        });
    }

    @Test
    public void testTic() {
        Mess mess = Tic.get( Mess.class );

        assertEquals( "Hello World", mess.out() );
    }


    @Test
    public void testSingle() {
        Singl s1 = Tic.get( Singl.class );
        Singl s2 = Tic.get( Singl.class );

        assertEquals(1, countSingl );
    }

    @Test
    public void testMulti() {
        Multi s1 = Tic.get( Multi.class );
        Multi s2 = Tic.get( Multi.class );

        assertEquals(2, helloWorldCount );
    }

    private static int countSingl = 0;

    private static class Singleton implements Singl {

        public Singleton() {
            countSingl++;
        }
        @Override
        public String huh() {
            return "huh";
        }
    }

    private static int helloWorldCount;
    private static class HelloWorldM implements Multi {

        public HelloWorldM() {
            helloWorldCount++;
        }
    }
}
