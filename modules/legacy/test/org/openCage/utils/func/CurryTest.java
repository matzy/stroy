package org.openCage.utils.func;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class CurryTest {

    @Test
    public void testOrder() {
        F1<Integer,Integer> f = new F1<Integer, Integer>() {
            public Integer c( Integer integer ) {
                return 2 * integer;
            }
        };

        Integer res1 = f.c( 7 );
        Integer res2 = Curry.curry( f, 7 ).c();

        assertEquals( res1, res2 );
    }

    @Test
    public void testPlus5() {
        F2<Integer,Integer,Integer> plus = new F2<Integer, Integer, Integer>() {
            public Integer c(Integer integer, Integer integer1) {
                return integer + integer1;
            }
        };

        F1<Integer,Integer> plus5 = Curry.curry( plus, 5 );

        assertEquals( new Integer(13), plus5.c(8));
    }
}
