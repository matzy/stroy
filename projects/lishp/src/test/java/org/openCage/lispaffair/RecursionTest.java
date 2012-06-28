package org.openCage.lispaffair;

import org.junit.Test;

import java.text.ParseException;

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

public class RecursionTest {

    @Test
    public void testRecursion() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject( "(block (set f (fct (x) (if (= x 1) 1 (f (- x 1)))))  (f 2))" );

        assertEquals(1, Eval.eval(obj, new Lispaffair().getEnv()));

    }

    @Test
    public void testMutualRecursion() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject( "(block (set f (fct (x) (if (= x 1) 1 (g (- x 1))))) (set g (fct (x) (if (= x 1) 1 (f (- x 1))))) (f 3))" );

        assertEquals(1, Eval.eval(obj, new Lispaffair().getEnv()));
    }

    @Test
    public void testArgs() throws  ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject( "((fct (x) ((fct (x) #n) 7) x) 5)" );

        assertEquals(5, Eval.eval(obj, new Lispaffair().getEnv()));

    }

}
