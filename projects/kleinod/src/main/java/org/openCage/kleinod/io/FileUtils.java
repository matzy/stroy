package org.openCage.kleinod.io;

import org.openCage.kleinod.os.OS;

import java.io.File;
import java.util.regex.Pattern;

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

public class FileUtils {

    static private Pattern startWithDriveLetter = Pattern.compile( "([a-zA-Z]):.*");


    /**
     * @param file a file.
     * @return the extension of the filename.
     */
    static public String getExtension( File file ) {
        return getExtension( file.getName() );
    }

    /**
     * @param name a (file) name.
     * @return the extension of the filename, i.e. the substring after the last '.'.
     */
    static public String getExtension( String name ) {
        int    dotpos = name.lastIndexOf('.');

        if ( dotpos < 0 ) {
            return "";
        }

        return name.substring( dotpos + 1).toLowerCase();
    }

    /**
     * @return the current directory
     */
    static public String getCurrentDir() {
        return System.getProperty("user.dir");
    }

    /**
     * return the current users home directory
     * @return the home directory
     */
    static public String getHomeDir() {
        return System.getProperty("user.home");
    }

    /**
     * Normalizes an absolute file path to use only '/' as seperatros.
     * On windows drive letter plus ':' is used.
     * @param in An absolute path.
     * @return The same path whith '/'
     */
    static public String normalizePath( String in ) {

        boolean isUNC = false;
        // TODO fix me
        boolean isDriveLetter = false;

        if (OS.isWindows() ) {
            if ( in.startsWith( "\\\\") || in.startsWith( "//")) {
                isUNC = true;
            } else if ( startWithDriveLetter.matcher( in ).matches() ) {
                isDriveLetter = true;
            } else {
                throw new IllegalArgumentException( "not an absolute path" );
            }
        }

        String[] parts = in.split( "(/|:|\\\\|;)" );

        StringBuilder norm = new StringBuilder();

        boolean first = true;
        for ( String part : parts ) {
            if ( part.length() > 0 ) {
                if ( first && OS.isWindows() ) {
                    if ( isUNC ) {
                        norm.append( "\\\\").append( part );
                    } else {
                        norm.append( part ).append( ":");
                    }
                    first = false;
                } else {
                    norm.append( '/' /*File.pathSeparator*/ ).append( part );
                }
            }
        }

        return norm.toString();
    }

    public static String normalizePathElement( String name ) {
        return name.replace( "/", "" ).replace( "\\", "");
    }

    public static boolean isDriveLetterPath( String path ) {
        return startWithDriveLetter.matcher( path ).matches();
    }



    private FileUtils() {
        throw new NoSuchMethodError("utility class");
    }

}
