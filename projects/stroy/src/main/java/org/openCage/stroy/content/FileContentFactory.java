package org.openCage.stroy.content;

import com.google.inject.Inject;
import org.openCage.lang.functions.F1;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.fuzzyHash.FuzzyHashGenerator;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;

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

public class FileContentFactory {

//    private final FuzzyHashGenerator<File> fuzzyHashGen;

    private final F1<FuzzyHash,File> fuzzyHashGenerator;

    @Inject
    public FileContentFactory( final FuzzyHashGenerator<File> fuzzyHashGen ) {
        this.fuzzyHashGenerator = new F1<FuzzyHash, File>() {
            public FuzzyHash call( File file ) {
                return fuzzyHashGen.generate( file );
            }
        };
    }

    public FileContent create( final File file ) {
        return new FileContent( fuzzyHashGenerator, file );
    }
}
