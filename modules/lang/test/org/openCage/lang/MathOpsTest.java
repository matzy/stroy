package org.openCage.lang;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.openCage.lang.clazz.MathOps.xor;

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

public class MathOpsTest {

    @Test
    public void testByteXor() {

        // 2 loops avoiding max values to not create infinity loops

        for ( byte a = Byte.MIN_VALUE; a < Byte.MAX_VALUE -1; ++a ) {
            for ( byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE -1; ++b ) {
                assertEquals( a, xor( xor(a,b), b));
            }
        }

        for ( byte a = Byte.MAX_VALUE; a > Byte.MIN_VALUE + 1; --a ) {
            for ( byte b = Byte.MAX_VALUE; b > Byte.MIN_VALUE + 1; --b ) {
                assertEquals( a, xor( xor(a,b), b));
            }
        }

    }
}
