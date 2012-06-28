package org.openCage.lispaffair;

import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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

public class FindTest {

    @Test
    public void testSimple() throws ParseException {

        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(find '(1 2 3 7) (fct (x) (= x 3)))" );

        assertTrue((Boolean) Eval.eval(obj, new Lispaffair().getEnv()));
    }
    
    @Test
    public void testSome() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(find '(1 2 3 7) (fct (x) (= x 3)) (fct (x) (* x 3)))" );

        assertEquals(9, ((List) Eval.eval(obj, new Lispaffair().getEnv())).get(1));
        
    }

    @Test
    public void testSomeNot() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(find '(1 2 3 7) (fct (x) (= x 33)) (fct (x) (* x 3)))" );

        assertTrue( !(Boolean)((List) Eval.eval(obj, new Lispaffair().getEnv())).get(0));

    }

    @Test
    public void testTags() throws ParseException {
        LispFormat frmt = new LispFormat();

        Object obj = frmt.parseObject(
                "(find '((foo 5) (du 7) (tags \"123 4\")) " +
                        "(fct (x) (let (key val) x :in (= key 'tags))) " +
                        "(fct (x) (let (key val) x :in val)))" );

        List answ =  ((List) Eval.eval(obj, new Lispaffair().getEnv()));
        
        assertTrue((Boolean) answ.get(0));
        assertEquals( "123 4", answ.get(1));

    }
}
