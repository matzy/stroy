package org.openCage.stroy.tree.filesystem;

import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.NoedImpl;
import org.openCage.stroy.filter.Ignore;

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

public class FSNoedGenerator implements NoedGenerator {
    public Noed build( Ignore ignore, String path ) {

        File rootFile = new File( path );

        return build( ignore, rootFile );
    }


    private Noed build( Ignore ignore, File file ) {

        if ( ignore.match( file.getPath() )) {
            return null;
        }

        if ( !file.isDirectory()) {
            return NoedImpl.makeLeafNoed( file.getName(), new FSFiel( file ));
        }

        Noed parent = NoedImpl.makeDirNoed( file.getName() );

        for ( File child : file.listFiles() ) {
            Noed noed = build( ignore, child );
            if ( noed != null ) {
                parent.addChild( noed );
            }
        }

        return parent;
    }
}
