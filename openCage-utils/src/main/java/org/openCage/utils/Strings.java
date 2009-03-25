package org.openCage.util.string;

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

public class Strings {

    private static final char[] HEX_CHARS = {
            '0', '1', '2', '3',
            '4', '5', '6', '7',
            '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f',};

    /**
     * Turns an array of bytes into a string representing each byte as
     * an unsigned hex number.
     *
     * @param bytes	Array of bytes to convert to hex-string
     * @return	Generated hex string
     */
    public static String toHexString( byte bytes[] ) {

        char buf[] = new char[bytes.length * 2];

        for (int i = 0, x = 0; i < bytes.length; i++) {

            buf[x++] = HEX_CHARS[(bytes[i] >>> 4) & 0xf];
            buf[x++] = HEX_CHARS[bytes[i] & 0xf];
        }

        return new String(buf);
    }

}
