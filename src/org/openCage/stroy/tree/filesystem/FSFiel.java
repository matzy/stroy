package org.openCage.stroy.tree.filesystem;

import org.openCage.stroy.tree.Fiel;
import org.openCage.util.string.Strings;
import org.openCage.util.logging.Log;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.zip.ZipFile;

import com.twmacinta.util.MD5;
import com.JavaExchange.www.RandomGUID;

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

public class FSFiel implements Fiel {
    private final File file;
    private String checkSum;
    private boolean readError = false;

    public FSFiel( final File file ) {
        this.file = file;
    }

    public String getChecksum() {
        if ( checkSum == null ) {
            try {
                checkSum = Strings.asHex( MD5.getHash( file ));
            } catch ( IOException e ) {
                Log.warning( "read error of file: " + file.getAbsolutePath()  );
                readError = true;
            }
        }

        return checkSum;
    }

    public long getSize() {
        return file.length();
    }

    public boolean hasReadError() {
        return readError;
    }
}
