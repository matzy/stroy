package org.openCage.utils.func;

import org.junit.Test;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.F2;

import static org.junit.Assert.assertEquals;

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

public class CurryTest {

    @Test
    public void testOrder() {
        F1<Integer,Integer> f = new F1<Integer, Integer>() {
            public Integer call( Integer integer ) {
                return 2 * integer;
            }
        };

        Integer res1 = f.call(7);
        Integer res2 = Curry.curry( f, 7 ).call();

        assertEquals( res1, res2 );
    }

    @Test
    public void testPlus5() {
        F2<Integer,Integer,Integer> plus = new F2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer1) {
                return integer + integer1;
            }
        };

        F1<Integer,Integer> plus5 = Curry.curry( plus, 5 );

        assertEquals( new Integer(13), plus5.call(8));
    }
}
