package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.functions.F1;

import java.util.*;

import static junit.framework.Assert.assertEquals;

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


public class StringsTest {

    @Test
    public void testJoinNonStrings() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "1, 2, 3", Strings.join( ints ).toString());
    }

    @Test
    public void testPrefix() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "pre1, 2, 3", Strings.join( ints ).prefix("pre").toString());

        assertEquals( "", Strings.join( new ArrayList<Integer>()).prefix("pre").toString());
//        assertEquals( "", Strings.join( null).prefix("pre").toString());
    }

    @Test
    public void testSep() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "1-2-3", Strings.join( ints ).separator("-").toString());
    }

    @Test
    public void testTrans() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "2, 4, 6", Strings.join( ints ).trans( new F1<String, Integer>() {
            @Override
            public String call(Integer integer) {
                return new Integer(integer * 2).toString();
            }
        }).toString());

    }

    @Test
    public void testFilter() {
        List<Integer> ints = Arrays.asList( 1, 2, 3);

        assertEquals( "1, 3", Strings.join( ints ).skip( new F1<Boolean, Integer>() {
            @Override
            public Boolean call(Integer integer) {
                return integer.intValue() == 2;
            }
        }).toString());

        assertEquals( "", Strings.join( ints ).skip( new F1<Boolean, Integer>() {
            @Override
            public Boolean call(Integer integer) {
                return integer.intValue() != 4;
            }
        }).postfix("wohahah").toString());

    }

    @Test
    public void testArray() {
        String[] strs = new String[3];
        strs[0] = "a";
        strs[1] = "b";
        strs[2] = "c";

        assertEquals( "a, b, c", Strings.join( strs ).toString() );
    }

    @Test
    public void testSet() {
        Set<String> set = new HashSet<String>();
        set.add("a");
        set.add("b");

        assertEquals( "a, b", Strings.join( set ).order().toString());
    }
}
