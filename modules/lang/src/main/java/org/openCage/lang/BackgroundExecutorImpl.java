package org.openCage.lang;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openCage.lang.functions.CatchAll;
import org.openCage.lang.functions.F0;
import org.openCage.lang.functions.FV;

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

/**
 * Executes tasks repeatedly (every  10s) and/or at the end of the program
 */
public class BackgroundExecutorImpl implements BackgroundExecutor {

    private static final int WAITING = 10000; // 10s
    private static final Logger LOG = Logger.getLogger(BackgroundExecutorImpl.class.getName());

    public void addPeriodicAndExitTask( final FV task) {
        addExitTask( task );
        addPeriodicTask( task );
    }

    @Override public void addPeriodicTask( final FV task) {
        new Thread() {

            @SuppressWarnings({"OverlyBroadCatchBlock"})
            public void run() {
                //noinspection InfiniteLoopStatement
                while (true) {

                    try {
                        Thread.sleep( WAITING );
                    } catch (InterruptedException e) {
                        // sleep interrupted => next call early
                        LOG.info( "sleep interrupted: " + e );                        
                    }

                    CatchAll.call( task );
                }
            }
        }.start();
    }

    @Override public void addExitTask( final FV task ) {
        Runtime.getRuntime().addShutdownHook(
                new Thread( new Runnable() {
                    @Override public void run() {
                        CatchAll.call( task );
                    }
                }));

    }
}
