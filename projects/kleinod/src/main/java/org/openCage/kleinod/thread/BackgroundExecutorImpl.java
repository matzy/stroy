package org.openCage.kleinod.thread;

import org.openCage.kleinod.errors.CatchAll;
import org.openCage.kleinod.lambda.F0;

import java.util.logging.Logger;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/



/**
 * Executes tasks repeatedly (every  10s) and/or at the end of the program
 */
public class BackgroundExecutorImpl implements BackgroundExecutor {

    //private static final int WAITING = 10000; // 10s
    private static final Logger LOG = Logger.getLogger(BackgroundExecutorImpl.class.getName());

    private int waiting = 10000;

    @Override
    public void setIntervall(int millisec) {
        synchronized( this ) {
            waiting = millisec;
        }
    }

    @Override
    public void addPeriodicAndExitTask( final F0<BackgroundState> task) {
        addExitTask( task );
        addPeriodicTask( task );
    }

    @Override public void addPeriodicTask( final F0<BackgroundState> task) {
        new Thread() {

            @SuppressWarnings({"OverlyBroadCatchBlock"})
            public void run() {
                //noinspection InfiniteLoopStatement
                while (true) {

                    try {
                        Thread.sleep( waiting );
                    } catch (InterruptedException e) {
                        // sleep interrupted => next call early
                        LOG.info( "sleep interrupted: " + e );                        
                    }

                    if ( BackgroundState.done == CatchAll.call(task) ) {
                        return;
                    }
                }
            }
        }.start();
    }

    @Override public void addExitTask( final F0<BackgroundState> task ) {
        Runtime.getRuntime().addShutdownHook(
                new Thread( new Runnable() {
                    @Override public void run() {
                        if ( BackgroundState.done == CatchAll.call( task )) {
                            return;
                        }
                    }
                }));

    }
}
