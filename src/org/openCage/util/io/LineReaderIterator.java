package org.openCage.util.io;

import org.openCage.util.logging.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

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


public class LineReaderIterator  implements Iterator<String> {
    private BufferedReader reader;
    private String         line;


    public LineReaderIterator( BufferedReader reader ) throws IOException {
        this.reader = reader;
        this.line   = reader.readLine();
    }

    public boolean hasNext() {
        return line != null;
    }

    public String next() {
        String oldLine = line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new IllegalStateException("read not readble anymore" );
        }
        return oldLine;
    }

    public void remove() {
        throw new UnsupportedOperationException( "this iterator has no remove" );
    }

    public void close() {
        if ( reader != null ) {
            try {
                reader.close();
            } catch ( IOException e ) {
                Log.warning( "close threw error " + e  );
            }
            reader = null;
        }
    }

    public static void close( LineReaderIterator it ) {
        if ( it != null ) {
            it.close();
        }
    }

}
