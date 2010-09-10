package org.openCage.io;

import java.io.*;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openCage.lang.functions.F1;
import org.openCage.lang.errors.Unchecked;

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
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class With { 

    public <T> T withInputStream(File file, F1<T, InputStream> reader) {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            return reader.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

    public <T> T withInputStream( URI file, F1<T, InputStream> reader) {
        InputStream is = null;
        try {

            is = new BufferedInputStream( file.toURL().openStream());
            return reader.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

    public <T> T withReader(File file, F1<T, Reader> reader) {
        Reader is = null;
        try {
            is = new FileReader( file );
            return reader.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

    public <T> T withWriter(File file, F1<T, Writer> writer) {
        Writer is = null;
        try {
            is = new FileWriter( file );
            return writer.call(is);
        } catch (Exception e) {
            throw new Unchecked(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // was closed
                }
            }
        }
    }

//    public FileLineIterable getLineIteratorCloseInFinal(File file) {
//        return new IterableFile( file );
//    }

    public void close( InputStream is ) {
        if ( is != null ) {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(With.class.getName()).log(Level.SEVERE, null, ex);
            }

            is = null;
        }
    }



}
