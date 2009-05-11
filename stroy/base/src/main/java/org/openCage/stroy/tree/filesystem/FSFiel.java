package org.openCage.stroy.tree.filesystem;

import org.openCage.stroy.tree.StdFiel;
import org.openCage.stroy.hash.FuzzyHash;
import org.openCage.stroy.mimetype.MimeListImpl;
import org.openCage.lang.*;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

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

/**
 * A standard file system file as seen through fiel eyes
 */
public class FSFiel extends StdFiel {

    private final File file;

    public FSFiel( final File file ) {
        super( new E0<InputStream>() {
                    public InputStream c() throws Exception {
                        return new FileInputStream( file );
                    }},
               new MimeListImpl(null), // mines,
               file.length(),
               new E1<String, InputStream>() {
                   public String c(InputStream inputStream) throws Exception {
                       return null;  //To change body of implemented methods use File | Settings | File Templates.
                   }
               },
               new E1<FuzzyHash, InputStream>() {
                   public FuzzyHash c(InputStream inputStream) throws Exception {
                       return null;  //To change body of implemented methods use File | Settings | File Templates.
                   }
               }
                );
        this.file = file;
    }


    public File findFile() {
        return file;
    }
}
