package org.openCage.lispaffair;

import org.junit.Test;

import java.text.ParseException;

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

public class LetTest {
    @Test
    public void testLet1() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject( "(let " +
                "     x             5 " +
                " :in" +
                "     x)" );

        assertEquals(5, Eval.eval(obj, new Lispaffair().getEnv()));
    }

    @Test
    public void testLet2() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(let " +
                        "     x             5 " +
                        "     y             7 " +
                " :in" +
                "     x)" );

        assertEquals(5, Eval.eval(obj, new Lispaffair().getEnv()));
    }

    @Test
    public void testLet() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(let (: h1 h2 tl)  '(1 2 3 4 5)" +
                        "     x     5 " +
                        " :in" +
                        "     h1 " +
                        "     h2)" );

        assertEquals(2, Eval.eval(obj, new Lispaffair().getEnv()));
    }

    @Test
    public void testLetPair() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(let (: (key val) tl)   '((1 2) (3 4) 5)" +
                "     x                  5 " +
                " :in" +
                "     key " +
                "     val)" );

        assertEquals(2, Eval.eval(obj, new Lispaffair().getEnv()));
    }


}
