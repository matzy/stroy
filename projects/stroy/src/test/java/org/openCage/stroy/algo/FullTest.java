//package org.openCage.stroy.algo;
//
//import junit.framework.TestCase;
//
//import java.io.File;
//
//import org.openCage.stroy.algo.tree.Noed;
//import org.openCage.stroy.algo.tree.TreeFactory;
//import org.openCage.stroy.algo.matching.TreeTaskFactory;
//import org.openCage.stroy.algo.matching.TreeTask;
//import org.openCage.stroy.algo.matching.Tasks;
//import org.openCage.stroy.algo.matching.strategies.TreeStrategy;
//import com.google.inject.Injector;
//import com.google.inject.Guice;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//public class FullTest extends TestCase {
//
//
//    public void testZipAndFS() {
//
//        String path = getClass().getResource( ".").getPath();
//        System.out.println( path );
//
//        String pathZip = path.substring( 0, path.length() - "classes/stroy/test/stroy/org/openCage/stroy/algo/".length() )
//                + "stroy/test-resources/testA/stroy.zip";
//        String pathFs = path.substring( 0, path.length() - "classes/stroy/test/stroy/org/openCage/stroy/algo/".length() )
//                + "stroy/test-resources/testA/stroy";
//
//        System.out.println( pathZip );
//        assertTrue(  new File(pathZip).exists());
//
//        Injector        ij  = Guice.createInjector( new FullTestModule() );
//        TreeFactory     tf  = ij.getInstance( TreeFactory.class );
//        TreeTaskFactory ttf = ij.getInstance( TreeTaskFactory.class );
//        TreeStrategy    st  = ij.getInstance( TreeStrategy.class );
//
//        Noed zipRoot = tf.create( pathZip, false ).build( pathZip );
//        assertNotNull( zipRoot );
//
//        Noed fsRoot = tf.create( pathFs, false ).build( pathFs );
//        assertNotNull( fsRoot );
//
//        TreeTask tt = ttf.create( zipRoot, fsRoot );
//        assertNotNull( tt );
//
//        int leftUnmatched = tt.getLeft( Tasks.isUnmatched ).size();
//
//        st.match( tt );
//        int leftUnmatchedAfter = tt.getLeft( Tasks.isUnmatched ).size();
//
//        assertTrue( leftUnmatched > leftUnmatchedAfter );
//
//        for ( Noed noed : tt.getRight( Tasks.isUnmatched )) {
//            if ( noed.isLeaf() ) {
//                noed.getFiel().getFingerprint();
//                break;
//            }
//        }
//
//
//
//
//    }
//
//
//
//}
