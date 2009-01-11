package org.openCage.stroy.algo.tree.filesystem;

import junit.framework.TestCase;

import java.net.URL;
import java.io.File;
import java.util.Arrays;

import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.algo.tree.NoedUtils;
import org.openCage.stroy.algo.tree.IOState;
import org.openCage.stroy.algo.fingerprint.FingerPrint;
import org.openCage.stroy.algo.fingerprint.FileFingerPrint;
import org.openCage.stroy.filter.NullIgnore;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.RuntimeModule;
import com.google.inject.Injector;
import com.google.inject.Guice;

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
public class FSNoedTest extends TestCase {
    private NoedGenerator fsNoedGenerator = new FSNoedGenerator( new NullIgnore(), new FileFingerPrint() );

    public void testSimple() {
        URL url = getClass().getResource( "/org/openCage/stroy/algo/tree/filesystem/testRoot" );

        String path = url.getPath();

        assertTrue( new File(path).exists());

        Noed root = fsNoedGenerator.build( path  );
        assertTrue( root.getParent() == null );

        assertEquals( "testRoot", root.getName());

        assertEquals( 2, root.getChildren().size());

    }

    public void testGetHash() {
        URL url = getClass().getResource( "/org/openCage/stroy/algo/tree/filesystem/testRoot" );
        String path = url.getPath();
        Noed root = fsNoedGenerator.build( path  );

        Noed noed = NoedUtils.getNoed( root, "foo", "b.txt" );
        assertNotNull( noed );
        assertEquals( "b.txt", noed.getName());
        assertTrue( noed.isLeaf());

        String hash = noed.getFiel().getFingerprint();

        assertNotNull( hash );
    }

    public void testFilter() {

        {
            // no filter
            URL url = getClass().getResource( "/org/openCage/stroy/algo/tree/filesystem/testRoot" );
            String path = url.getPath();
            Noed root = fsNoedGenerator.build( path  );

            Noed noed = NoedUtils.getNoed( root, "foo", "b.txt" );
        }

        {
            // filter foo
            Injector injector = Guice.createInjector( new RuntimeModule() );
            Ignore ignore = injector.getInstance( Ignore.class );
            ignore.setPatterns( Arrays.asList( ".*/foo" ));

            URL url = getClass().getResource( "/org/openCage/stroy/algo/tree/filesystem/testRoot" );
            String path = url.getPath();
            NoedGenerator fsNoedGenerator2 = new FSNoedGenerator( ignore, new FileFingerPrint() );
            Noed root = fsNoedGenerator2.build( path  );

            try {
                Noed noed = NoedUtils.getNoed( root, "foo", "b.txt" );
                fail( "should be filtered" );
            } catch ( IllegalArgumentException exp ) {
                // expected
            }
        }


    }

    public void testFilterRelPath() {
        Injector injector = Guice.createInjector( new RuntimeModule() );
        Ignore ignore = injector.getInstance( Ignore.class );
        ignore.setPatterns( Arrays.asList( ".*/openCage" ));

        URL url = getClass().getResource( "/org/openCage/stroy/algo/tree/filesystem/testRoot" );
        String path = url.getPath();
        Noed root = fsNoedGenerator.build( path  );

        Noed noed = NoedUtils.getNoed( root, "foo", "b.txt" );
        assertNotNull( noed );

    }
}
