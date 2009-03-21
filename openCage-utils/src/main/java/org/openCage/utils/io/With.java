package org.openCage.utils.io;

import org.openCage.lang.E1;

import java.io.*;
import java.util.logging.Logger;
import java.net.URL;
import java.net.URLConnection;

import static org.openCage.utils.unchecked.Unchecked.unchecked;

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
 * utility function to read from an Stream (Url, File) ...
 * so that it is always cleanedup and closed
 */

public class With {

    private static Logger LOG = Logger.getLogger( With.class.getName() );

    /**
     * Runs a method over an InputStream
     * and allways closes the stream on matter what
     * Converts exceptions to ExcpetionsUCs.
     * @param is An InputStream
     * @param f A function accepting an InputStream, returning T
     * @return The result of f
     */
    public static <T> T withIS( InputStream is, E1<T, InputStream> f ) {
        try {
            return  f.c( is );
        } catch (IOException e) {
            throw unchecked( e );
        } catch ( Exception e ) {
            throw unchecked( e );
        } finally {
            if ( is != null ) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOG.warning( "strange 'could not close' in a finally: " + e );
                }
            }
        }
    }

    /**
     * Runs a method over an InputStream from the URL para,
     * and allways closes the stream on matter what
     * Converts exceptions to ExcpetionsUCs.
     * @param url A valid URL
     * @param f  A function accepting an InputStream, returning T.
     * @return The result of f
     */
    public static <T> T withIS( URL url, E1<T, InputStream> f ) {
        URLConnection connection = null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            throw unchecked( e );
        }

        try {
            InputStream is = connection.getInputStream();
            return withIS( is, f );
        } catch (IOException e) {
            throw unchecked( e );
        }
    }

    /**
     * read from file, closed after wards
     * @param file A File.
     * @param f A Function working with that file.
     * @return Return value.
     */
    public static <T> T withIS( File file, E1<T, InputStream> f ) {
        try {
            InputStream is = new FileInputStream( file );
            return withIS( is, f );
        } catch (FileNotFoundException e) {
            throw unchecked( e );
        }
    }

    public static <T> T withOS( OutputStream os, E1<T, OutputStream> f ) {
        try {
            return f.c( os );
        } catch (IOException e) {
            throw unchecked(e );
        } catch (Exception e) {
            throw unchecked(e);
        } finally {
            if ( os != null ) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOG.warning( "strange 'could not close' in a finally: " + e );
                }
            }
        }
    }


    public static <T> T withOS( File file, E1<T, OutputStream> f ) {
        OutputStream os = null;
        try {
            os = new FileOutputStream( file );
        } catch (FileNotFoundException e) {
            throw unchecked( e );
        }

        try {
            return f.c( os );
        } catch (Exception e) {
            throw unchecked( e );
        }
    }





}
