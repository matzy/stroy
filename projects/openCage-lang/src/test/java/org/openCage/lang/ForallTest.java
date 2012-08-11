package org.openCage.lang;

import org.junit.Test;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.F1;
import org.openCage.lang.functions.VF1;
import org.openCage.lang.inc.ReaderMock;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class ForallTest {

    @Test
    public void toIntString() {
        List<Integer> lst = Arrays.asList( 1,2,345,789 );

        String one = Strings.join(Forall.forall( lst ).
          skip(new F1<Boolean, Integer>() {
            @Override
            public Boolean call(Integer integer) {
                return integer.equals(2);
            }
        }).
          trans( new F1<String, Integer>() {
            @Override
            public String call(Integer integer) { return "" + (integer * integer);
            }
        }).skip( new F1<Boolean, String>() {
            @Override
            public Boolean call(String s) {
                return s.length() < 4;
            }
        })).toString();

        assertEquals( "119025, 622521", one);
    }

    @Test( expected = Unchecked.class)
    public void fileNotFound() {
        Forall.lines( new File("/iamntohere/foo"));
    }


    @Test
    public void lines() {
        ReaderMock rm = new ReaderMock(-1);
        Forall.lines( rm ).act(new VF1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        assertTrue(rm.isClosed());
    }

    @Test
    public void err() {

        ReaderMock rm = new ReaderMock(100);
        try {
            Forall.lines( rm ).act(new VF1<String>() {
                @Override
                public void call(String s) {
                    System.out.println(s);
                }
            });
        }catch( Unchecked exp ) {
        }

        assertTrue(rm.isClosed());
    }

    @Test
    public void set() {
        assertEquals( 2, Forall.forall( Arrays.asList( 1,2,3 )).skip( new F1<Boolean, Integer>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 2;
            }
        }).toSet().size());
    }
}
