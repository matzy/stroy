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
    public void testCatch0() {
        CatchAll.call( new F0<Integer>() {
            @Override public Integer call() {
                throw new Error();
            }
        });
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
