package org.openCage.io.fspath;

import org.openCage.io.SystemUtils;

import javax.swing.JFileChooser;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

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

public final class FSPathBuilder {

    private FSPathBuilder() {};

    private static JFileChooser fileChooser = new JFileChooser();


    public static FSPath getPath( String str ) {
        if (SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_UNIX ) {
            return new FSPathUnix(str);
        }

        if ( SystemUtils.IS_OS_WINDOWS ) {

            if ( str.startsWith( "\\\\" )) {
                return new FSPathUNC(str);
            }

            return new FSPathWindows(str);
        }

        throw new UnsupportedOperationException("impl me");
    }


    public static FSPath getPath( URL url ) {
        String file = url.getFile();

        if ( file.isEmpty() ) {
            throw new IllegalArgumentException( "not a file: " + url.toString() );
        }

        return getPath( file );
    }


    public static FSPath getPath( File file ) {
        return getPath( file.getAbsolutePath() );
    }


    public static FSPath getHome() {
        return getPath( SystemUtils.getUserHome() );
    }

    public static FSPath getARoot() {
        if (SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_UNIX ) {
            return new FSPathUnix("/");
        }

        return new FSPathWindows("C:");
    }

    public static FSPath getDocuments() {
        if ( SystemUtils.IS_OS_MAC_OSX ) {
            return FSPathBuilder.getHome().add( "Documents" );
        }

        if ( SystemUtils.IS_OS_UNIX ) {
            // follow freedesktop.org xdg base dir spec
            String docs = System.getenv( "XDG_DOCUMENTS_DIR" );
            if ( docs != null ) {
                return getPath( docs );
            }

            return FSPathBuilder.getHome().add( "Documents" );
        }

        if ( SystemUtils.IS_OS_WINDOWS ) {

            // "my documents" in windows (or whatever it is mapped to)
            return getPath( fileChooser.getFileSystemView().getDefaultDirectory());
        }

        throw new Error( "unknown os" );
    }

    public static FSPath getPreferences() {
        if ( SystemUtils.IS_OS_MAC_OSX ) {
            return FSPathBuilder.getHome().add( "Library", "Preferences");
        }

        if ( SystemUtils.IS_OS_UNIX ) {
            // follow freedesktop.org xdg base dir spec
            String conf = System.getenv( "XDG_CONFIG" );
            if ( conf != null ) {
                return getPath( conf );
            }

            return FSPathBuilder.getHome().add( ".config" );
        }

        if ( SystemUtils.IS_OS_WINDOWS ) {
            return getPath( System.getenv("APPDATA"));
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
