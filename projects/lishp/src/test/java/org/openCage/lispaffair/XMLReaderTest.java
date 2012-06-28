package org.openCage.lispaffair;

import org.junit.Test;
import org.openCage.lishp.Symbol;

import java.text.ParseException;

import static junit.framework.Assert.assertNotNull;

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

public class XMLReaderTest {

//    @Test
//    public void testStd() throws ParseException {
//        Object obj = XMLReader.readTags();
//
//        assertNotNull( obj );
//
//        Lispaffair la = new Lispaffair();
//        Environment env = la.getEnv();
//
//        env.bind(Symbol.get("tags"), obj);
//
////        LispFormat frmt = new LispFormat();
////
////        Object ret = frmt.parseObject( "tags" );
////
////        assertEquals(1, Eval.eval( ret, env ));
//
//        new Repl(env).repl();
//
//    }

//    public static void main(String[] args) throws ParseException {
//        Object obj = XMLReader.readTags();
//
//        assertNotNull( obj );
//
//        Lispaffair la = new Lispaffair();
//        Environment env = la.getEnv();
//
//        env.bind(Symbol.get("tags"), obj);
//        Repl repl = new Repl( env );
//
//        repl.eval( "(set posts (tail (tail (tail (head (head tags))))))" );
//        repl.eval( "(set posts2 (let (: (: x y z pp) nix) tags :in pp))" );
//
//        repl.eval( "(set pp (head (tail posts)))" );
//        repl.eval( "(set tags (fct (x) (tags-rec-3 (tail x))))");
//        repl.eval( "(set tags-rec (fct (x) " +
//                   "            ((fct (key val tl)" +
//                   "               (if (= key 'tag) " +
//                   "                 (print ((. string split) val  \" \"))" +
//                   "                 (tags-rec tl))) " +
//                   "             (head (head x)) (head (tail (head x))) (tail x))))" );
//        repl.eval( "(set has (fct (x y) (if (isNil x) () )" );
//
//        repl.eval( "(set tags-rec-2 (fct (x) " +
//                "      (let  (: (key val) tl)   x  " +
//                "         :in" +
//                "               (if (= key 'tag) " +
//                "                 (print ((. string split) val  \" \"))" +
//                "                 (tags-rec-2 tl))) " +
//                "         )))" );
//
//        repl.eval( "(set tags-rec-3 (fct (x) " +
//                "         (find x " +
//                "           (fct (y) (let (key val) y :in (= key 'tag)))" +
//                "           (fct (y) (let (key val) y :in (print ((. string split) val  \" \" ))))" +
//                "))))" );
//
//
//        repl.eval( "((. list for-each) posts tags)" );
////        LispFormat frmt = new LispFormat();
////
////        Object ret = frmt.parseObject( "tags" );
////
////        assertEquals(1, Eval.eval( ret, env ));
//
//        repl.repl();
//
//
//    }
}
