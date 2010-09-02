package org.openCage.lang.functions;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
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
