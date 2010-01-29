package org.openCage.fspath.clazz;

import org.apache.commons.lang.SystemUtils;
import org.openCage.fspath.impl.FSPathUNC;
import org.openCage.fspath.impl.FSPathUnix;
import org.openCage.fspath.impl.FSPathWindows;
import org.openCage.fspath.protocol.FSPath;

import java.io.File;

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

public class FSPathBuilder {

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

//        throw new UnsupportedOperationException("impl me");
//        //return new FSPath( "C:\\");

    }

    public static FSPath getDocuments() {
        if ( SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_UNIX ) {
            return new FSPathUnix( "~/Documents" );
        }

        if ( SystemUtils.IS_OS_WINDOWS ) {
            return getPath(SystemUtils.getUserHome());
        }

        throw new Error( "unknown os" );
    }
}
