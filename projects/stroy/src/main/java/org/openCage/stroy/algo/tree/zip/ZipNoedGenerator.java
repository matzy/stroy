//package org.openCage.stroy.algo.tree.zip;
//
//import org.openCage.stroy.algo.tree.NoedGenerator;
//import org.openCage.stroy.algo.tree.Noed;
//import org.openCage.stroy.algo.tree.Fiel;
//import org.openCage.stroy.algo.tree.NoedImpl;
//import org.openCage.stroy.filter.Ignore;
//import org.openCage.util.io.FileUtils;
//import org.openCage.util.logging.Log;
//
//import java.util.zip.ZipFile;
//import java.util.zip.ZipEntry;
//import java.util.Enumeration;
//import java.util.Map;
//import java.util.HashMap;
//import java.io.IOException;
//import java.io.File;
//
//import com.google.inject.Inject;
//
///***** BEGIN LICENSE BLOCK *****
// * BSD License (2 clause)
// * Copyright (c) 2006 - 2012, Stephan Pfab
// * All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *     * Redistributions of source code must retain the above copyright
// *       notice, this list of conditions and the following disclaimer.
// *     * Redistributions in binary form must reproduce the above copyright
// *       notice, this list of conditions and the following disclaimer in the
// *       documentation and/or other materials provided with the distribution.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
// * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//***** END LICENSE BLOCK *****/
//public class ZipNoedGenerator implements NoedGenerator {
//
//    private final Ignore ignore;
//
//    @Inject
//    public ZipNoedGenerator( final Ignore ignore ) {
//        this.ignore = ignore;
//
//    }
//
//    public Noed build( String path ) {
//
//        Noed root = null;
//        ZipEntry tt = null;
//
//        Map<String, Noed> noeds = new HashMap<String, Noed>();
//
//        ZipFile zf = null;
//        try {
//            zf = new ZipFile( path );
//
//            for ( Enumeration<? extends ZipEntry> e = zf.entries(); e.hasMoreElements(); )
//            {
//                ZipEntry entry = e.nextElement();
//                String   elemPath  = FileUtils.normalizePath( entry.getName() );
//
//                String   parentPath = new File( elemPath ).getParent();
//                String   name       = new File( elemPath ).getName();
//
//                if ( ignore.match( elemPath )) {
//                    // filter
//                    continue;
//                }
//
//                Noed noed = null;
//
//                // TODO windows ?
//                if ( parentPath.equals( "/" )) {
//                    if ( root != null ) {
//                        throw new Error( "strange zip" );
//                    }
//                    root = NoedImpl.makeDirNoed( name );
//                    noed = root;
//                } else {
//                    Noed parent = noeds.get( parentPath );
//
//                    if ( parent == null ) {
//                        // assume filtered
//                        continue;
//                        //throw new Error( "strange order zip not supported yet" );
//                    }
//
//                    if ( entry.isDirectory() ) {
//                        noed = NoedImpl.makeDirNoed( name);
//                    } else {
//
//                        tt = entry;
//                        Fiel fiel = null;
//                        fiel = new ZipFiel( path, entry, FileUtils.getExtension( name ) );
//
//                        noed = NoedImpl.makeLeafNoed( name, fiel );
//                    }
//
//                    parent.addChild( noed );
//
//                }
//
//                noeds.put( elemPath, noed );
//            }
//        } catch( IOException e ) {
//            Log.warning( "could not open zipfile " + path );
//            throw new IllegalArgumentException( e );
//        } finally {
//
//            if ( zf != null ) {
//                try {
//                    zf.close();
//                } catch ( IOException e ) {
//                    Log.warning( "could not close zipfile " + path );
//                }
//            }
//
//        }
//
//
//        return root;
//    }
//}
