package org.openCage.utils.func;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.openCage.util.lang.Ref;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class FuncTest {

    /**
     * Test (more validate assumption) the use of type Void
     */
    @Test
    public void testVoid() {

        final Ref<Integer> res = new Ref<Integer>( 4 );

        F0<Void> f = new F0<Void>() {
            public Void c() {
                res.o = 13;
                return null;
            }
        };

        f.c();

        assertEquals( 13, res.o.intValue());
    }

}
