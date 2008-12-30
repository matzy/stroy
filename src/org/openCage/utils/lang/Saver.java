package org.openCage.utils.lang;

import org.openCage.utils.func.F0;
import org.openCage.util.logging.Log;

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

public class Saver implements BackgroundSaver {

    private static final int WAITING = 10000;

    public void addTask( final F0<Void> task ) {

        // make sure we save during shutdown
        Runtime.getRuntime().addShutdownHook(
                new Thread( new Runnable() {
                    public void run() {
                        task.c();
                    }
                }));

        // save once in a while
        new Thread() {
            @SuppressWarnings({"OverlyBroadCatchBlock"})
            public void run() {
                //noinspection InfiniteLoopStatement
                while ( true ) {
                    try {
                        Thread.sleep( WAITING );
                        task.c();
                    } catch ( Exception exp ) {
                        Log.warning( "PrefSaveThread cought " + exp );
                    }
                }
            }
        }.start();
    }
}