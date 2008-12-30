package org.openCage.utils.io.with;

import org.openCage.util.lang.V1;
import org.openCage.util.logging.Log;
import org.openCage.utils.lang.Unchecked;
import org.openCage.utils.func.F1;

import java.io.FileReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;

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

public class WithIO {

    public static <T> T withReader( String path, F1<T, Reader> f ) {
        Reader reader = null;
        T      ret    = null;
        try {
            reader = new FileReader( path);
            ret    = f.c( reader );
        } catch( FileNotFoundException e ) {
            throw new Unchecked( e );
        } catch ( Error err ) {
            System.err.println( err );
            throw err;
        } finally {
            if ( reader != null ) {
                try {
                    reader.close();
                } catch( IOException e ) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            return ret;
        }
    }

    public static void withWriter( String path, V1<Writer> v ) {
        Writer writer = null;
        try {
            writer = new FileWriter( path );
            v.call( writer );
        } catch( IOException e ) {
            Log.warning( e );
            throw new Unchecked( e );
        } finally {
            if ( writer != null ) {
                try {
                    writer.close();
                } catch( IOException e ) {}
            }
        }
    }

}
