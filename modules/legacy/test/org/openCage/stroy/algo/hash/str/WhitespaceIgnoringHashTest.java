package org.openCage.stroy.algo.hash.str;

import junit.framework.TestCase;

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

public class WhitespaceIgnoringHashTest extends TestCase {

    public void testCompareToStandard() {

        String one = "12343567";

        assertEquals( one.hashCode(), new WhitespaceIgnoringHash().getHash( one ));
    }

    public void testIgnoreWhiteSpaces() {

        String one = "1 2 3 4 5    6";
        String two = "  1 2 3 4 5 6\r";
        String three = "      1    2 34 5 6\r";
        String four = "\t\t\t      1    2 34 5 6   \r";

        assertEquals( new WhitespaceIgnoringHash().getHash( one ), new WhitespaceIgnoringHash().getHash( two ));
        assertEquals( new WhitespaceIgnoringHash().getHash( one ), new WhitespaceIgnoringHash().getHash( three ));
        assertEquals( new WhitespaceIgnoringHash().getHash( one ), new WhitespaceIgnoringHash().getHash( four ));
    }

    public void testClash() {
        // assume classes Abc and Abcde

        assertEquals( new WhitespaceIgnoringHash().getHash( "Abc def = "),
                      new WhitespaceIgnoringHash().getHash( "Abcde f = "));
    }
}
