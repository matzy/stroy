package org.openCage.stroy.algo.hash.str;

import junit.framework.TestCase;

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

public class WhitespaceIgnoringHashTest extends TestCase {

    public void testCompareToStandard() {

        String one = "12343567";

        assertEquals( one.hashCode(), new WhitespaceIgnoringHash().call( one ).intValue());
    }

    public void testIgnoreWhiteSpaces() {

        String one = "1 2 3 4 5    6";
        String two = "  1 2 3 4 5 6\r";
        String three = "      1    2 34 5 6\r";
        String four = "\t\t\t      1    2 34 5 6   \r";

        assertEquals( new WhitespaceIgnoringHash().call( one ), new WhitespaceIgnoringHash().call( two ));
        assertEquals( new WhitespaceIgnoringHash().call( one ), new WhitespaceIgnoringHash().call( three ));
        assertEquals( new WhitespaceIgnoringHash().call( one ), new WhitespaceIgnoringHash().call( four ));
    }

    public void testClash() {
        // assume classes Abc and Abcde

        assertEquals( new WhitespaceIgnoringHash().call( "Abc def = "),
                      new WhitespaceIgnoringHash().call( "Abcde f = "));
    }
}
