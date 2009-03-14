package org.openCage.stroy.mimetype;


import eu.medsea.mimeutil.MimeUtil;

import java.io.File;
import java.util.logging.Logger;
import java.util.logging.Level;


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

public class FileType {

    public static void main(String[] args) {

       CLTArgs cltArgs = new CLTArgs( args );

        if ( !cltArgs.isOk() ) {
            return;
        }

        swallowWarningMessage();
        
        check( cltArgs, cltArgs.getFile(), "" );


    }

    private static void check(CLTArgs cltArgs, String file, String prefix ) {

        checkFile(cltArgs, file, prefix  );

        if ( cltArgs.isRecursive() && new File(file).isDirectory()) {
            for ( File kid : new File( file ).listFiles()) {
                check( cltArgs, kid.getAbsolutePath(), prefix + "  " );
            }
        }
    }

    private static void checkFile( CLTArgs cltArgs, String file, String prefix ) {

        System.out.println( prefix + "Checking: " + file );

        if ( !cltArgs.contentOnly() ) {
            if ( cltArgs.isVerbose() ) {
                System.out.print( prefix + "  By extension: ");
            }
            

            System.out.println( MimeUtil.getMimeTypes( file ));
        }

        if ( !cltArgs.extensionOnly() ) {

            if ( !new File(file).exists() ) {
                System.err.println( prefix + "  file does not exist"  );
                return;
            }

            if ( cltArgs.isVerbose() ) {
                System.out.print( prefix + "  By content:   ");
            }
            System.out.println( MimeUtil.getMimeTypes( file));
        }
    }

    private static void swallowWarningMessage() {
        // swallow the warning message that this is not UNIX, e.g. no magic at /etc/somesuch
        Logger.getLogger("").setLevel( Level.SEVERE );
        MimeUtil.getMimeTypes( "foo.txt");
        Logger.getLogger("").setLevel( Level.INFO );
    }
}
