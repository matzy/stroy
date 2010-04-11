package org.openCage.lang.impl;

import org.openCage.lang.protocol.SingletonApp;

import java.io.File;
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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class FriendlySingletonApp implements SingletonApp {

    private final boolean isMaster;

    public FriendlySingletonApp( File markerFile ) {

        isMaster = !markerFile.exists();

        if ( isMaster ) {
            try {
                markerFile.getParentFile().mkdirs();
                markerFile.createNewFile();
                //FileExistence.createNewFile( markerFile ); // would need depemdecy to io
                // TODO find a better home
            } catch (IOException e) {
                throw new IllegalStateException( "can't create singletonapp marker file " + markerFile.getAbsolutePath() );
            }
            markerFile.deleteOnExit();
        }
    }

    @Override
    public boolean isMaster() {
        return isMaster;
    }
}
