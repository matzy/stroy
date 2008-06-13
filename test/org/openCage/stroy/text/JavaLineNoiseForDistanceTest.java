package org.openCage.stroy.text;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class JavaLineNoiseForDistanceTest extends TestCase {

    LineNoise lineNoise;


    protected void setUp() throws Exception {
        super.setUp();

        lineNoise = new JavaLineNoiseForDistanceRegex();
    }

    public void testIsLineNoiseDoubleComment() {
        assertTrue( lineNoise.isGrayNoise( "   /** " ));
    }

    public void testIsLineCommentStart() {
        assertTrue( lineNoise.isGrayNoise( "   /* " ));
    }

    public void testisNoiseCommentEnd() {
        assertTrue( lineNoise.isGrayNoise( "   */ " ));
    }

    public void testisNoiseDoubleLineComment() {
        assertTrue( lineNoise.isGrayNoise( "    // foo" ));
    }

    public void testisNoiseImport() {
        assertTrue( lineNoise.isGrayNoise( "  import java.util.*  " ));
    }

    public void testisNoisePackage() {
        assertTrue( lineNoise.isGrayNoise( "    package org.openCage.distance; " ));
    }

    public void testisNoiseOpenBracket() {
        assertTrue( lineNoise.isGrayNoise( "   { " ));
    }

    public void testisNoiseOpenBracketWithCode() {
        assertFalse( lineNoise.isGrayNoise( "   if (duh) {         " ));
    }

    public void testisNoiseClosingBracketWithCode() {
        assertFalse( lineNoise.isGrayNoise( "   duda {}         " ));
    }

    public void testisNoiseWhiteSpace() {
        assertTrue( lineNoise.isGrayNoise( "             " ));
    }

    public void testIsLineCodeIf() {
        assertFalse( lineNoise.isGrayNoise( "   if ( foo )        " ));
    }

    public void testisNoiseSlash() {
        assertFalse( lineNoise.isGrayNoise( "   / duh          " ));
    }

    public void testIsLineClassDecl() {
        assertTrue( lineNoise.isGrayNoise( "   public class        " ));
    }

    public void testIsLineInterfaceDecl() {
        assertTrue( lineNoise.isGrayNoise( "   public interface        " ));
    }

    public void testIsLineAnnotation() {
        assertTrue( lineNoise.isGrayNoise( "   @duda        " ));
    }
}
