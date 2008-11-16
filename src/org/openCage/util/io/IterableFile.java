package org.openCage.util.io;

import java.io.*;
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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

/* Use:
 *      for ( String line : new IterableFile( "D:/tmp/duda.txt" ) ) {
 *          System.out.println( line );
 *      }
 */

public class IterableFile implements Iterable<String> {

    private BufferedReader reader;

    public IterableFile( File file ) {

        if ( !file.canRead() ) {
            throw new IllegalArgumentException("file " + file.getAbsolutePath() + " is not readable");
        }

        try {
            this.reader = new BufferedReader( new FileReader( file ));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("file " + file.getAbsolutePath() + " not found");
        }
    }

    public IterableFile( String fname ) {
        this( new File(fname));
    }

    public Iterator<String> iterator() {
        try {
            return new LineReaderIterator( reader );
        } catch (IOException e) {
            throw new IllegalStateException( "reader has problem now" );
        }
    }

    public void close() {
        if ( reader != null ) {
            try {
                reader.close();
            } catch( IOException e ) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            reader = null;
        }
    }
}
