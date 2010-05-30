package org.openCage.io;

import org.openCage.io.fspath.FSPath;
import org.openCage.lang.errors.Unchecked;

import java.io.File;
import java.io.IOException;
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

public final class FileUtils {

    private FileUtils() {};

    public static void ensurePath( FSPath path ) {
        path.toFile().getParentFile().mkdirs();
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
    

}
