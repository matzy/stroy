package org.openCage.stroy.ui;

import org.openCage.lang.structure.Tu;
import org.openCage.stroy.app.UIApp;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.graph.matching.strategy.Reporter;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.content.Content;
import org.openCage.lang.structure.T2;

import javax.swing.*;
import java.util.List;

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



public class MatchnWatch<T extends Content> extends SwingWorker< String, T2<String, String>> {

    private final UIApp<T>          uiApp;
    private final MatchStrategy<T>  strategy;
    private final ModalProgress     progressUI = new ModalProgress( null );

    public MatchnWatch( final UIApp uiApp, final MatchStrategy<T> strategy  ) {
        this.uiApp    = uiApp;
        this.strategy = strategy;

        progressUI.setVisible( true );
    }

    protected String doInBackground() throws Exception {

        Reporter reporter = new Reporter () {
            public void detail( String label, String str) {
                publish( Tu.c(label, str));
            }

            public void title( String str ) {
                publish( Tu.c(str, (String)null ));
            }
        };


        for ( TreeMatchingTask<T> task : uiApp.getTasks() ) {
//            publish( "matching " + task );
            strategy.match( task, reporter);
        }

        return "done";
    }


    protected void process( List<T2<String,String>> strings ) {
        super.process( strings );

        for ( T2<String,String> txt : strings ) {
            progressUI.setText( txt );
        }
    }


    protected void done() {
        super.done();

        progressUI.setText( "", "" );
        progressUI.dispose();
    }
}
