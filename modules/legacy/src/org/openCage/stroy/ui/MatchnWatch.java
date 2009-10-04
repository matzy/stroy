package org.openCage.stroy.ui;

import org.openCage.stroy.app.UIApp;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.graph.matching.strategy.Reporter;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.content.Content;
import org.openCage.util.iterator.T2;
import org.jdesktop.swingworker.SwingWorker;

import java.util.List;

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



public class MatchnWatch extends SwingWorker< String, T2<String, String>> {

    private final UIApp          uiApp;
    private final MatchStrategy  strategy;
    private final ModalProgress     progressUI = new ModalProgress( null );

    public MatchnWatch( final UIApp uiApp, final MatchStrategy strategy  ) {
        this.uiApp    = uiApp;
        this.strategy = strategy;

        progressUI.setVisible( true );
    }

    protected String doInBackground() throws Exception {

        Reporter reporter = new Reporter () {
            public void detail( String label, String str) {
                publish( T2.c( label, str ));
            }

            public void title( String str ) {
                publish( T2.c(str, (String)null ));
            }
        };


        for ( TreeMatchingTask task : uiApp.getTasks() ) {
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
