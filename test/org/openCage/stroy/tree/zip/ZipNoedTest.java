package org.openCage.stroy.tree.zip;

import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.NoedUtils;

import java.util.ResourceBundle;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

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

public class ZipNoedTest extends TestCase {
    private ZipNoedGenerator zipNoedGenerator = new ZipNoedGenerator();

    public void testResourcePath() {
        URL url = getClass().getResource( "/org/openCage/stroy/tree/zip/dir.zip" );

        assertNotNull( "add .zip files to IDEA prefs: compiler resource patterns or build.xml",  url );
    }

    public void testCreateZipNoed() {

        URL url = getClass().getResource( "/org/openCage/stroy/tree/zip/dir.zip" );

        String path = url.getPath();

        assertTrue( new File(path).exists());

        Noed root = zipNoedGenerator.build( path  );

        assertTrue( root.getParent() == null );

        assertEquals( "dir", root.getName());

        assertEquals( 7, root.getChildren().size());

        NoedUtils.print( root );
    }

    public void testGetHash() {
        URL url = getClass().getResource( "/org/openCage/stroy/tree/zip/dir.zip" );
        String path = url.getPath();

        Noed root = zipNoedGenerator.build( path  );

        Noed noed = NoedUtils.getNoed( root, "doubles2", "CompareDirs2.java" );
        assertNotNull( noed );
        assertEquals( "CompareDirs2.java", noed.getName());
        assertTrue( noed.isLeaf());

        String hash = noed.getFiel().getChecksum();

        assertNotNull( hash );

    }
}
