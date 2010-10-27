package org.openCage.io;

import org.openCage.io.fspath.FSPath;
import org.openCage.lang.errors.Unchecked;

import java.io.*;

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
public class IOUtils {

    public static void closeQuietly( InputStream is ) {
        if ( is != null ) {
            try {
                is.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static void closeQuietly(FileWriter writer) {
        if ( writer != null ) {
            try {
                writer.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    private static void closeQuietly(FileOutputStream out) {
        if ( out != null ) {
            try {
                out.close();
            } catch (IOException e) {
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

    public static String getJarLocation( Class classFromJar ) {
        return classFromJar.getProtectionDomain().getCodeSource().getLocation().getFile();
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
