package org.openCage.stroy.tree.filesystem;

import junit.framework.TestCase;

import java.net.URL;
import java.io.File;

import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.NoedGenerator;
import org.openCage.stroy.tree.NoedUtils;

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
public class FSNoedTest extends TestCase {
    private NoedGenerator fsNoedGenerator = new FSNoedGenerator();

    public void testSimple() {
        URL url = getClass().getResource( "/org/openCage/stroy/tree/filesystem/testRoot" );

        String path = url.getPath();

        assertTrue( new File(path).exists());

        Noed root = fsNoedGenerator.build( null, path  );
        assertTrue( root.getParent() == null );

        assertEquals( "testRoot", root.getName());

        assertEquals( 2, root.getChildren().size());

    }

    public void testGetHash() {
        URL url = getClass().getResource( "/org/openCage/stroy/tree/filesystem/testRoot" );
        String path = url.getPath();
        Noed root = fsNoedGenerator.build( null, path  );

        Noed noed = NoedUtils.getNoed( root, "foo", "b.txt" );
        assertNotNull( noed );
        assertEquals( "b.txt", noed.getName());
        assertTrue( noed.isLeaf());

        String hash = noed.getFiel().getChecksum();

        assertNotNull( hash );

    }

}
