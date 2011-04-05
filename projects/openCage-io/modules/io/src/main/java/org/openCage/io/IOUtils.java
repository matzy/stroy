package org.openCage.io;

import org.openCage.io.fspath.FSPath;
import org.openCage.lang.errors.Unchecked;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/***** BEGIN LICENSE BLOCK *****
 * New BSD License
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Stephan Pfab nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
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
        File file = null;
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


}
