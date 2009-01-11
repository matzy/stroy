package org.openCage.stroy.algo.tree;

import com.google.inject.Inject;
import org.openCage.stroy.algo.tree.singleFile.SingleFile;
import org.openCage.stroy.algo.tree.zip.ZipArchive;
import org.openCage.stroy.algo.tree.filesystem.FileSystem;
import org.openCage.util.io.FileUtils;

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
public class TreeFactoryImpl implements TreeFactory{

    final NoedGenerator singleNoed;
    final NoedGenerator zipNoed;
    final NoedGenerator fsNoed;

    @Inject
    public TreeFactoryImpl(
            @FileSystem final NoedGenerator fsNoed,
            @SingleFile final NoedGenerator singleNoed,
            @ZipArchive final NoedGenerator zipNoed ) {

        this.singleNoed = singleNoed;
        this.zipNoed = zipNoed;
        this.fsNoed = fsNoed;
    }


    public NoedGenerator create( String path, boolean single ) {

        if ( single ) {
            return singleNoed;
        }

        String ext = FileUtils.getExtension( path );

        if ( ext.equals( "zip" )) {
            return zipNoed;
        }

        if ( ext.equals( "" )) {
            return fsNoed;
        }

        throw new IllegalArgumentException( "unknown type:" + path );
    }
}
