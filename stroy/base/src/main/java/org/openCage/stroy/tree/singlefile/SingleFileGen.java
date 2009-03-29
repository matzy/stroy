package org.openCage.stroy.tree.singlefile;

import org.openCage.stroy.tree.NoedGen;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.NoedImpl;
import org.openCage.stroy.tree.filesystem.FSFiel;
import org.openCage.stroy.tree.filter.Ignore;
import org.openCage.stroy.tree.filter.NullIgnore;
import org.jetbrains.annotations.NotNull;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class SingleFileGen implements NoedGen {
    private Ignore ignore = new NullIgnore();

    public Noed build( @NotNull String path ) {

        File file = new File( path );
        FSFiel fiel = new FSFiel( file );

        return NoedImpl.makeLeafNoed(
                file.getName(),
                fiel );
    }

    public void setIgnore( Ignore ignore ) {
        this.ignore = ignore;
    }


    private Noed build( Ignore ignore, final File file ) {

        if ( ignore.match( file.getPath() )) {
            return null;
        }

        return null;
    }
}
