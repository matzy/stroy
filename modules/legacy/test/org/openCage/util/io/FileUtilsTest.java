package org.openCage.util.io;

import junit.framework.TestCase;
import com.muchsoft.util.Sys;


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

public class FileUtilsTest extends TestCase {

    public void testNormalize() {

        if ( Sys.isWindows() ) {
            assertEquals( "a:/b", FileUtils.normalizePath( "a:b" ));
            assertEquals( "\\\\a/b", FileUtils.normalizePath( "\\\\a\\b" ));
        } else {
            assertEquals( "/a/b", FileUtils.normalizePath( "a:b" ));
            assertEquals( "/a/b", FileUtils.normalizePath( "\\\\a\\b" ));                        
        }
    }

    public void testDriveLetterTest() {
        assertTrue( FileUtils.isDriveLetterPath( "C:\\a\\b" ));
        assertFalse( FileUtils.isDriveLetterPath( "C\\a\\b" ));
        assertFalse( FileUtils.isDriveLetterPath( "ab:\\a\\b" ));
    }

    public void testNormalizePathElement() {
        assertEquals( "a", FileUtils.normalizePathElement( "/a"));
        assertEquals( "a", FileUtils.normalizePathElement( "a\\"));
        assertEquals( "a", FileUtils.normalizePathElement( "/a\\"));
    }
}
