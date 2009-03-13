package org.openCage.utils;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
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
public class MathTest {

    @Test
    public void testXor() {
        assertTrue( (byte)101 != Math.xor( (byte)101, (byte)50 ) );
        assertEquals( (byte)101, Math.xor( Math.xor( (byte)101, (byte)50 ), (byte)50));
        assertEquals( (byte)0xFE, Math.xor( Math.xor( (byte)0xFE, (byte)50 ), (byte)50));

        for ( int i = -127; i < 128; ++i ) {
            for ( int j = -127; j < 128; ++j ) {
                if ( j != 0 ) {
                    assertTrue( "" + i + " xor " + j, (byte)i != Math.xor( (byte)i, (byte)j ) );
                    assertEquals( (byte)i, Math.xor( Math.xor( (byte)i, (byte)j ), (byte)j));
                }
            }
        }
    }
}
