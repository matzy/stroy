package org.openCage.lang.functions;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CatchAllTest {

    @Test
    public void testCatch() {
        CatchAll.call( new FV() {
            @Override public void call() {
                throw new IllegalArgumentException("");
            }
        });
    }

    @Test
    public void testCatchError() {
        CatchAll.call( new FV() {
            @Override public void call() {
                throw new Error("");
            }
        });
    }

    @Test
    public void testCatch0() {
        CatchAll.call( new F0<Integer>() {
            @Override public Integer call() {
                throw new Error();
            }
        });
    }

    @Test
    public void testCatch0Exception() {
        CatchAll.call( new F0<Integer>() {
            @Override public Integer call() {
                throw new IllegalStateException();
            }
        });
    }

    @Test
    public void testCatch1() {
        CatchAll.call( new F1<Integer,Integer>() {
            @Override public Integer call( Integer a ) {
                throw new Error();
            }
        }, 3);
    }

    @Test
    public void testCatch1Exception() {
        CatchAll.call( new F1<Integer,Integer>() {
            @Override public Integer call( Integer a ) {
                throw new IllegalStateException();
            }
        }, 3);
    }

    @Test
    public void testCatch2() {
        CatchAll.call( new F2<Integer,Integer,Integer>() {
            @Override public Integer call( Integer a, Integer b ) {
                throw new Error();
            }
        }, 3, 4);
    }

    @Test
    public void testCatch2Exception() {
        CatchAll.call( new F2<Integer,Integer,Integer>() {
            @Override public Integer call( Integer a, Integer b ) {
                throw new IllegalStateException();
            }
        }, 3, 4);
    }


    @Test
    public void testCatchNix() {
        assertEquals( new Integer(3),
                CatchAll.call( new F1<Integer,Integer>() {
                    @Override public Integer call( Integer in ) {
                        return in + 2;
                    }
                }, 1));
    }
}
