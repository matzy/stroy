package org.openCage.util;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class TestResourceHelper {

    public static File getTestFolder( String relpath ) {

//        String path = TestResourceHelper.class.getResource( "TestResourceHelper.class" ).getPath();
//
//        String[] pices = path.split( "/");
//
//        path = path.substring( 0, path.indexOf( "classes" ));
//
//        return new File( path );

        if ( new File( "/Users/spfab/Documents/ppro/testfield/test-resources" ).exists() ) {
            return new File( "/Users/spfab/Documents/ppro/testfield/test-resources/" + relpath );
        } else {
            throw new Error( "TODO: setup" );
        }

    }
}
