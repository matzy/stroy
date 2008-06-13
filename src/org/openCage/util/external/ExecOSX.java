package org.openCage.util.external;

import org.openCage.util.logging.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

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

public class ExecOSX implements Exec {

    public void view( String path ) {
        open( path );
    }

    public void edit( String path ) {
        open( path );
    }

    public void diff( String path1, String path2 ) {

    }

    public void browse( String path ) {

    }

    private void open( String path ) {
        try {

            String[] cmd = new String[2];
            cmd[0] = "/usr/bin/open";
            cmd[1] = path;

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e1) {
            Log.warning( e1.toString() );
        }


    }
}
