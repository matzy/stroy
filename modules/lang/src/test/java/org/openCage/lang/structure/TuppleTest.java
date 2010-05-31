package org.openCage.lang.structure;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class TuppleTest {

    @Test
    public void test() {
        T2<String, Integer> p = Tu.c("aaa",4);
        assertEquals( "aaa", p.i0);
        assertEquals( new Integer(4), p.i1);
    }

    @Test
    public void testEquals() {
        assertEquals( Tu.c(1,"foo"), new T2<Integer,String>(1,"foo"));
        assertEquals( Tu.c(1,"foo"), T2.valueOf(1,"foo"));
        assertNotSame( Tu.c(1,"foo"), T2.valueOf(2,"foo"));
    }

    @Test
    public void testHash() {
        assertEquals( Tu.c(7,"foo").hashCode(), T2.valueOf(7,"foo").hashCode());
        assertNotSame( Tu.c(8,"foo").hashCode(), T2.valueOf(7,"foo").hashCode());
    }



}
