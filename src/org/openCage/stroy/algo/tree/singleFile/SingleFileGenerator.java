package org.openCage.stroy.algo.tree.singleFile;

import org.openCage.stroy.algo.tree.NoedGenerator;
import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.algo.tree.filesystem.FSFiel;
import org.openCage.stroy.algo.tree.filesystem.FileSystem;
import org.openCage.stroy.algo.tree.NoedImpl;
import org.openCage.stroy.algo.fingerprint.FingerPrint;
import org.openCage.stroy.filter.Ignore;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class SingleFileGenerator implements NoedGenerator {

    final Ignore ignore;
    private final FingerPrint checksum;


    @Inject
    public SingleFileGenerator( final Ignore ignore, @FileSystem final FingerPrint checksum  ) {
        this.ignore = ignore;
        this.checksum = checksum;
    }

    public Noed build( String path ) {

        File file = new File( path );

        Noed root = NoedImpl.makeDirNoed( file.getParentFile().getName() );
        root.addChild( NoedImpl.makeLeafNoed( file.getName(), new FSFiel( file, checksum )));

        return root;
    }
}
