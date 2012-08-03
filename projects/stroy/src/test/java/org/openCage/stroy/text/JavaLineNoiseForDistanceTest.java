package org.openCage.stroy.text;

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

public class JavaLineNoiseForDistanceTest extends TestCase {

    LineNoise lineNoise;


    protected void setUp() throws Exception {
        super.setUp();

        lineNoise = new JavaLineNoiseForDistanceRegex();
    }

    public void testIsLineNoiseDoubleComment() {
        assertTrue( lineNoise.call( "   /** " ));
    }

    public void testIsLineCommentStart() {
        assertTrue( lineNoise.call( "   /* " ));
    }

    public void testisNoiseCommentEnd() {
        assertTrue( lineNoise.call( "   */ " ));
    }

    public void testisNoiseDoubleLineComment() {
        assertTrue( lineNoise.call( "    // foo" ));
    }

    public void testisNoiseImport() {
        assertTrue( lineNoise.call( "  import java.util.*  " ));
    }

    public void testisNoisePackage() {
        assertTrue( lineNoise.call( "    package org.openCage.distance; " ));
    }

    public void testisNoiseOpenBracket() {
        assertTrue( lineNoise.call( "   { " ));
    }

    public void testisNoiseOpenBracketWithCode() {
        assertFalse( lineNoise.call( "   if (duh) {         " ));
    }

    public void testisNoiseClosingBracketWithCode() {
        assertFalse( lineNoise.call( "   duda {}         " ));
    }

    public void testisNoiseWhiteSpace() {
        assertTrue( lineNoise.call( "             " ));
    }

    public void testIsLineCodeIf() {
        assertFalse( lineNoise.call( "   if ( foo )        " ));
    }

    public void testisNoiseSlash() {
        assertFalse( lineNoise.call( "   / duh          " ));
    }

    public void testIsLineClassDecl() {
        assertTrue( lineNoise.call( "   public class        " ));
    }

    public void testIsLineInterfaceDecl() {
        assertTrue( lineNoise.call( "   public interface        " ));
    }

    public void testIsLineAnnotation() {
        assertTrue( lineNoise.call( "   @duda        " ));
    }
}
