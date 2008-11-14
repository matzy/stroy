package org.openCage.stroy.tree.zip;

import org.openCage.stroy.tree.NoedGenerator;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.Fiel;
import org.openCage.stroy.filter.Ignore;
import org.openCage.util.io.FileUtils;
import org.openCage.util.logging.Log;

import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
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
public class ZipNoedGenerator implements NoedGenerator {

    public Noed build( Ignore ignore, String path ) {

        Noed root = null;
        ZipEntry tt = null;

        Map<String, Noed> noeds = new HashMap<String, Noed>();

        ZipFile zf = null;
        try {
            zf = new ZipFile( path );

            for ( Enumeration<? extends ZipEntry> e = zf.entries(); e.hasMoreElements(); )
            {
                ZipEntry entry = e.nextElement();
                String   elemPath  = FileUtils.normalizePath( entry.getName() );

                String   parentPath = new File( elemPath ).getParent();
                String   name       = new File( elemPath ).getName();

                if ( ignore.match( elemPath )) {
                    // filter
                    continue;
                }

                Noed noed = null;

                if ( parentPath.equals( "/" )) {
                    if ( root != null ) {
                        throw new Error( "strange zip" );
                    }
                    root = NoedImpl.makeDirNoed( name );
                    noed = root;
                } else {
                    Noed parent = noeds.get( parentPath );

                    if ( parent == null ) {
                        // assume filtered
                        continue;
                        //throw new Error( "strange order zip not supported yet" );
                    }

                    if ( entry.isDirectory() ) {
                        noed = NoedImpl.makeDirNoed( name);
                    } else {

                        tt = entry;
                        Fiel fiel = null;
                        fiel = new ZipFiel( path, entry );

                        noed = NoedImpl.makeLeafNoed( name, fiel );
                    }

                    parent.addChild( noed );

                }

                noeds.put( elemPath, noed );
            }
        } catch( IOException e ) {
            Log.warning( "could not open zipfile " + path );
            throw new IllegalArgumentException( e );
        } finally {

            if ( zf != null ) {
                try {
                    zf.close();
                } catch ( IOException e ) {
                    Log.warning( "could not close zipfile " + path );
                }
            }

        }


        return root;
    }
}
