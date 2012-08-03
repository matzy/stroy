package org.openCage.utils.io.with;

import org.openCage.util.logging.Log;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;

import static org.openCage.lang.errors.Unchecked.wrap;


/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
            throw wrap(e);
        }

        try {
            InputStream is = connection.getInputStream();
            return withIS( is, f );
        } catch (IOException e) {
            throw wrap( e );
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
            throw wrap( e );
        }
    }

    public static <T> T withIS( InputStream is, InputStreamFunctor<T> f ) {
        try {
            return  f.c( is );
        } catch (IOException e) {
            throw wrap( e );
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
            throw wrap(e );
        } catch (IOException e) {
            throw wrap(e );
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
            throw wrap(e);

        } catch ( Error err ) {
            System.err.println( err );
            throw err;

        } catch (IOException e) {
            throw wrap(e);
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
            throw wrap( e );
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
