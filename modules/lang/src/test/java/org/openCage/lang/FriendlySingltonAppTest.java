package org.openCage.lang;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openCage.lang.FriendlySingletonApp;
import org.openCage.lang.SingletonApp;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

public class FriendlySingltonAppTest {

    private static File getTmpFile() {
        String dir = System.getProperty("java.io.tmpdir");
        if ( !dir.endsWith( File.pathSeparator )) {
            dir += File.pathSeparator;
        }

        dir += "FriendlySingltonAppTest-tmp1.tmp";

        return new File( dir );
    }

    @Before
    public void setup() {
        File tmp = getTmpFile();

        if ( tmp.exists()) {
            tmp.delete();
        }
    }

    @After
    public void tearDown() {
        File tmp = getTmpFile();

        if ( tmp.exists()) {
            tmp.delete();
        }        
    }

    @Test
    public void testCreateFile() {
        File tmpFile = getTmpFile();
        
        assertNotNull( tmpFile.getParentFile());
        assertFalse( tmpFile.exists());

        SingletonApp single = new FriendlySingletonApp( tmpFile );

        assertTrue( tmpFile.exists());
        assertTrue( single.isMaster() );

        SingletonApp single2 = new FriendlySingletonApp( tmpFile );
        assertTrue( single.isMaster() );
        assertFalse( single2.isMaster() );
    }

}
