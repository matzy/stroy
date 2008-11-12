package org.openCage.stroy.tree.filesystem;

import org.openCage.stroy.tree.NoedGenerator;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.zip.NoedImpl;

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
    public Noed build( String path ) {

        File rootFile = new File( path );

        return build( rootFile );
    }


    private Noed build( File file ) {
        if ( !file.isDirectory()) {
            return NoedImpl.makeLeafNoed( file.getName(), new FSFiel( file ));
        }

        Noed parent = NoedImpl.makeDirNoed( file.getName() );

        for ( File child : file.listFiles() ) {
            parent.addChild( build( child ));
        }

        return parent;
    }
}
