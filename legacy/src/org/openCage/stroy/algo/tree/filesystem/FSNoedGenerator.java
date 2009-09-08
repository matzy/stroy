package org.openCage.stroy.algo.tree.filesystem;

import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.NoedImpl;
import org.openCage.stroy.algo.fingerprint.FingerPrint;
import org.openCage.stroy.filter.Ignore;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import com.google.inject.Inject;

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

public class FSNoedGenerator implements NoedGenerator {

    private final Ignore            ignore;
    private final FingerPrint<File> checksum;

    @Inject
    public FSNoedGenerator( @NotNull             final Ignore            ignore,
                            @NotNull @FileSystem final FingerPrint<File> checksum ) {
        this.ignore   = ignore;
        this.checksum = checksum;
    }

    public Noed build( @NotNull String path ) {

        return build( ignore, new File( path ) );
    }


    private Noed build( Ignore ignore, File file ) {

        if ( ignore.match( file.getPath() )) {
            return null;
        }

        if ( !file.isDirectory()) {
            return NoedImpl.makeLeafNoed( file.getName(), new FSFiel( file, checksum ));
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
