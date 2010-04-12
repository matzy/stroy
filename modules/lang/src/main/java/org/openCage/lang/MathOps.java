package org.openCage.lang;

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

/**
 * Utility class for math operations
 */
public class MathOps {

    /**
     * do not instantiate
     */
    private MathOps() {}

    /**
     * The xor of 2 byte values
     * @param a any byte
     * @param b any byte
     * @return a {@literal ^} b
     */
    public static byte xor( byte a, byte b ) {
        return (byte)((int)a ^ (int)b);
    }

}
