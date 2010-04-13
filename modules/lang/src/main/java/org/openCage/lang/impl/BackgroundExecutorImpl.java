package org.openCage.lang.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.lang.protocol.F0;

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

    public void addPeriodicAndExitTask( final F0<Void> task) {
        addExitTask( task );
        addPeriodicTask( task );
    }

    public void addPeriodicTask( final F0<Void> task) {
        new Thread() {

            @SuppressWarnings({"OverlyBroadCatchBlock"})
            public void run() {
                //noinspection InfiniteLoopStatement
                while (true) {
                    try {
                        Thread.sleep(WAITING);
                        task.call();
                    } catch (Exception exp) {
                        LOG.warning("BackgroundExecutor caught " + exp);
                    }
                }
            }
        }.start();
    }

    public void addExitTask( final F0<Void> task ) {
        Runtime.getRuntime().addShutdownHook(
            new Thread(new Runnable() {

            public void run() {
                try {
                    task.call();
                } catch (Exception ex) {
                    LOG.log(Level.SEVERE, null, ex);
                } catch ( Error err ) {
                    LOG.log(Level.SEVERE, null, err);                    
                }
            }
        }));

    }
}
