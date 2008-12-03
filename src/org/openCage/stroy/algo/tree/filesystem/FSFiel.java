package org.openCage.stroy.algo.tree.filesystem;

import org.openCage.stroy.algo.tree.Fiel;
import org.openCage.stroy.algo.tree.IOStateImpl;
import org.openCage.stroy.algo.tree.IOState;
import org.openCage.stroy.algo.fingerprint.FingerPrint;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.util.io.FileUtils;
import org.openCage.util.lang.Lazy;
import org.openCage.util.lang.F0;

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

/**
 * A standard file system file as seen through fiel eyses
 */
public class FSFiel implements Fiel {
    private final File file;
    private String     checkSum;
    private IOState ioState = new IOStateImpl();

    private String     type;
    private FuzzyHash  fuzzy;

    private final Lazy<String> calcChecksum;

    public FSFiel( final File file, final FingerPrint calc ) {
        this.file = file;
        type = FileUtils.getExtension( file );

        final FSFiel fiel = this;

        calcChecksum = new Lazy<String>( new F0<String>() {
            public String call() {
                return calc.getFingerprint( file, ioState );
            }
        } );
    }

    public String getChecksum() {
        return calcChecksum.get();
    }

    public String getType() {
        return type;
    }

    public FuzzyHash getFuzzyHash() {

        throw new UnsupportedOperationException( "impl me" );

//        if ( fuzzy == null ) {
//
//        }
//
//        return fuzzy;
    }

    public long getSize() {
        return file.length();
    }

    public boolean hasReadError() {
        return ioState.isError();
    }
}
