package org.openCage.kleinod.io;

import org.openCage.kleinod.type.DuckType;
import org.openCage.kleinod.errors.Unchecked;
import org.openCage.kleinod.io.fspath.FSPath;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

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


public class IOUtils {

    private IOUtils() {}

    /**
     * close any Closeable (e.g. Stream)
     * don't fail if it is already closed or null
     * @param is Any Closeable
     */
    public static void closeQuietly( Closeable is ) {
        if ( is != null ) {
            try {
                is.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * close any Closeable (e.g. Stream)
     * don't fail if it is already closed or null
     * @param is Any Closeable
     */
    public static void closeQuietly( Object is ) {
        if ( is != null ) {
            try {
                DuckType.coerce(is).to( Closeable.class  ).close();
            } catch (IOException e) {
                // ignore
            }
        }
    }




    public static void ensurePath( FSPath path ) {
        ensurePath( path.toFile() );
    }


    public static void ensurePath( File file ) {
        file.getParentFile().mkdirs();
    }

    public static void createNewFile( File file ) {
        ensurePath( file );
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw Unchecked.wrap( e );
        }
    }

    public static File url2File( URL url ) {
        File file;
        try {
          file = new File(url.toURI());
        } catch(URISyntaxException e) {
          file = new File(url.getPath());
        }

        return file;
    }

    public static String getJarLocation( Class classFromJar ) {
        return url2File( classFromJar.getProtectionDomain().getCodeSource().getLocation()).getAbsolutePath();
    }

    public static void copy( InputStream in, FSPath file ) {
        copy( in, file.toFile() );
    }
    public static void copy( File in, File out ) {
        try {
            copy( new FileInputStream( in), out );
        } catch (FileNotFoundException e) {
            throw Unchecked.wrap( e );
        }
    }

    public static void copy( InputStream in, File file ) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream( file );

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (FileNotFoundException e) {
            throw Unchecked.wrap( e );
        } catch (IOException e) {
            throw Unchecked.wrap( e );
        } finally {
            closeQuietly( in);
            closeQuietly( out );
        }

    }

        /**
     * Copy file of source to target/
     * @param source A file path
     * @param target A target path.
     * @throws IOException Rethrow of a io exception.
     */
    public static void copy( String source, String target ) throws IOException {
        File ft = new File( target );
        if ( ft.isDirectory() ) {
            target += File.separatorChar +
                    source.substring( source.lastIndexOf( File.separatorChar ) + 1 );
        }

        new File( target ).getParentFile().mkdirs();

        copy( new FileInputStream( source), new FileOutputStream( target));
    }

    /**
     * helper function for copy
     * @param in input stream
     * @param out output stream
     */
    public static void copy( InputStream in, OutputStream out ) {
        try{
            byte buffer[] = new byte[ 0xffff];
            int  count;

            while ( (count = in.read( buffer)) != -1 ) {
                out.write( buffer, 0, count );
            }
        } catch ( IOException e ) {
            System.err.println( "copy error " + e);

        } finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException e ) {
                }
            }
            if ( out != null ) {
                try {
                    out.close();
                } catch ( IOException e ) {
                }
            }
        }
    }

    // from http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
    public static String toString( InputStream is) {
        try {
            return new java.util.Scanner(is).useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        } finally {
            closeQuietly( is );
        }
    }




}
