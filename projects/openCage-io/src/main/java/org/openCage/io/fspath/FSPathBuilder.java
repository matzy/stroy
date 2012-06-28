package org.openCage.io.fspath;

import org.openCage.io.IOUtils;
import org.openCage.lang.OS;

import javax.swing.JFileChooser;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

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


public final class FSPathBuilder {

    private FSPathBuilder() {}

    private static final JFileChooser fileChooser = new JFileChooser();


    public static FSPath getPath( String str ) {

        // TODO shift to paths
        if ( str.endsWith( "..")) {
            throw new UnsupportedOperationException( ".. not suported yet" );
        }

        if ( str.endsWith( ".")) {
            return getPath( str.substring( 0, str.length() - 1 ));
        }

        if ( OS.isUnix() ) {
            return new FSPathUnix(str);
        }

        if ( OS.isWindows() ) {
            if ( str.startsWith( FSPathUNC.UNC_PREFIX )) {
                return new FSPathUNC(str);
            }

            return new FSPathWindows(str);
        }

        throw new UnsupportedOperationException("impl me");
    }

    public static FSPath getPath( URL url ) {
        File file = IOUtils.url2File( url );

        return getPath( file );
    }


    public static FSPath getPath( File file ) {
        return getPath( file.getAbsolutePath() );
    }


    public static FSPath getHome() {
        return getPath( System.getProperty("user.home"));
    }

    public static FSPath getARoot() {
        if (OS.isUnix() ) {
            return new FSPathUnix("/");
        }

        return new FSPathWindows("C:");
    }

    public static FSPath getDocuments() {
        if ( OS.isOSX() ) {
            return FSPathBuilder.getHome().add( "Documents" );
        }

        if ( OS.isUnix() ) {
            // follow freedesktop.org xdg base dir spec
            String docs = System.getenv( "XDG_DOCUMENTS_DIR" );
            if ( docs != null ) {
                return getPath( docs );
            }

            return FSPathBuilder.getHome().add( "Documents" );
        }

        if ( OS.isWindows() ) {

            // "my documents" in windows (or whatever it is mapped to)
            return getPath( fileChooser.getFileSystemView().getDefaultDirectory());
        }

        throw new Error( "unknown os" );
    }

    public static FSPath getPreferences() {
        if ( OS.isOSX() ) {
            return FSPathBuilder.getHome().add( "Library", "Preferences");
        }

        if ( OS.isUnix() ) {
            // follow freedesktop.org xdg base dir spec
            String conf = System.getenv( "XDG_CONFIG" );
            if ( conf != null ) {
                return getPath( conf );
            }

            return FSPathBuilder.getHome().add( ".config" );
        }

        if ( OS.isWindows() ) {
            return getPath( System.getenv("APPDATA")); // TODO does this always work ?
        }

        throw new Error( "unknown os" );
    }

    public static FSPath getTmpFile( String extension ) {

        return getPath( System.getProperty("java.io.tmpdir")).add(
                "" + new Date().getTime() + "-" + UUID.randomUUID().toString() + "." + extension );
    }

    public static FSPath getTmpDir() {

        return getPath( System.getProperty("java.io.tmpdir")).add(
                "" + new Date().getTime() + "-" + UUID.randomUUID().toString());
    }

}
