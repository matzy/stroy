package org.openCage.stroy.algo.tree.singleFile;

import junit.framework.TestCase;
import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.NoedUtils;
import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.filter.NullIgnore;

import java.net.URL;
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
 * Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class SingleFileNoedTest extends TestCase {

    private NoedGenerator noedGenerator = new SingleFileGenerator( new NullIgnore());


    public void testCreateSingleFileNoed() {

        URL url = getClass().getResource( "testRoot/a.txt" );
        String path = url.getPath();
        assertTrue( new File(path).exists());

        Noed root = noedGenerator.build( path  );

        assertTrue( root.getParent() == null );

        assertEquals( "testRoot", root.getName());

        assertEquals( 1, root.getChildren().size());

        NoedUtils.print( root );
    }

    public void testGetHash() {
        URL url = getClass().getResource( "testRoot/a.txt" );
        String path = url.getPath();
        assertTrue( new File(path).exists());

        Noed root = noedGenerator.build( path  );

        Noed noed = NoedUtils.getNoed( root, "a.txt" );
        assertNotNull( noed );
        assertEquals( "a.txt", noed.getName());
        assertTrue( noed.isLeaf());

        String hash = noed.getFiel().getChecksum();

        assertNotNull( hash );
    }
}
