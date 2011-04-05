package org.openCage.lang.functions;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/***** BEGIN LICENSE BLOCK *****
 * New BSD License
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Stephan Pfab nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

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
