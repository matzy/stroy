package org.openCage.utils.io.with;

import org.openCage.util.logging.Log;
import static org.openCage.utils.lang.unchecked.Unchecked.unchecked;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;


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
 * so that it is always cleanup, closed
 */
public class WithIO {


    /**
     * 
     * @param url
     * @param f
     * @return
     */
    public static <T> T withIS( URL url, InputStreamFunctor<T> f ) {
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
    public static <T> T withIS( File file, InputStreamFunctor<T> f ) {
        try {
            InputStream is = new FileInputStream( file );
            return withIS( is, f );
        } catch (FileNotFoundException e) {
            throw unchecked( e );
        }
    }

    public static <T> T withIS( InputStream is, InputStreamFunctor<T> f ) {
        try {
            return  f.c( is );
        } catch (IOException e) {
            throw unchecked( e );
        } finally {
            if ( is != null ) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.warning( "strange 'could not close' in a finally: " + e );
                }
            }
        }
    }

    public static <T> T withOS( File file, OutputStreamFunctor<T> f ) {
        OutputStream os = null;

        try {
            os = new FileOutputStream( file );
            return f.c( os );
        } catch (FileNotFoundException e) {
            throw unchecked(e );
        } catch (IOException e) {
            throw unchecked(e );
        } finally {
            if ( os != null ) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

    public static <T> T readFromUrl( String urlstr ) throws MalformedURLException {
        new URL( urlstr );

        return null;
    }


    public static <T> T URLwithReader( String urlstr, ReaderFunctor<T> f ) {
        Reader reader = null;
        T      ret;
        URL url    = null;
        InputStream is = null;

        try {
            url = new URL( urlstr );
            URLConnection connection = url.openConnection();
            is = connection.getInputStream();

            ret = f.c( new InputStreamReader(is ));
            return ret;

        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new Error(e);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new Error(e);
        } finally {
            if ( is != null ) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

    /**
     * Executes a method on a Reader of the file proved by the path
     * It makes sure that the reader is closed afterwards
     * throw unchecked: FileNotFoundExceptionUC
     * @param path A path to an existing file (or FileNotFoundExceptionUC )
     * @param f A function to executed over the Reader
     * @param <T> The return type of the function
     * @return The return of the function f
     */
    public static <T> T withReader( String path, ReaderFunctor<T> f ) {
        Reader reader = null;
        T      ret;
        try {
            reader = new FileReader( path);
            ret    = f.c( reader );
            return ret;
            
        } catch( FileNotFoundException e ) {
            throw unchecked(e);

        } catch ( Error err ) {
            System.err.println( err );
            throw err;

        } catch (IOException e) {
            throw unchecked(e);
        } finally {
            if ( reader != null ) {
                try {
                    reader.close();
                } catch( IOException e ) {
                    Log.warning( "strange 'could not close' in a finally: " + e );
                }
            }

            // no return here otherwise no exception could leave this method
            // see e.g. http://geekexplains.blogspot.com/2008/11/playing-with-try-catch-finally-in-java.html
        }
    }

    public static void withWriter( String path, WriterFunctor v ) {
        Writer writer = null;
        try {
            writer = new FileWriter( path );
            v.c( writer );
        } catch( IOException e ) {
            throw unchecked( e );
        } catch( Error err ) {
            Log.warning( err );
            throw err;
        } finally {
            if ( writer != null ) {
                try {
                    writer.close();
                } catch( IOException e ) {}
            }
        }
    }

}
