package org.openCage.stroy.tree.filesystem;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.openCage.stroy.tree.NoedGen;

import java.net.URISyntaxException;
import java.io.File;

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
public class FsNoedGenSelectorTest {

    @Test
    public void testYesOnDir() throws URISyntaxException {

        String path = FsNoedGenSelectorTest.class.getResource( "/org/openCage/stroy/tree").toURI().getPath();
        assertTrue( new File( path).exists() );

        NoedGen gen = new FsNoedGenSelector().find( path, false );

        assertNotNull( gen );
    }

    @Test
    public void testNoOnFile() throws URISyntaxException {
        String path = FsNoedGenSelectorTest.class.getResource( "/org/openCage/stroy/tree/filesystem/FsNoedGenSelectorTest.class").toURI().getPath();
        assertTrue( new File( path).exists() );

        NoedGen gen = new FsNoedGenSelector().find( path, false );

        assertTrue( gen == null );
    }

    @Test
    public void testNoOnSvn() {
        NoedGen gen = new FsNoedGenSelector().find( "svn://http:/foo", false );

        assertTrue( gen == null );
    }
}
