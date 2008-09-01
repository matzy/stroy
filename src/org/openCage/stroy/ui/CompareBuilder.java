package org.openCage.stroy.ui;

import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilder;
import org.openCage.stroy.app.Tasks;
import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.RuntimeModule;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.ui.docking.GraphicalDiffMyDoggy;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.Reporter;
import org.openCage.stroy.graph.matching.strategy.NameOnly;
import org.openCage.stroy.graph.matching.strategy.combined.WatchFull;
import org.openCage.util.iterator.T2;
import org.openCage.util.logging.Log;
import org.jdesktop.swingworker.SwingWorker;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.io.File;

import com.google.inject.Injector;
import com.google.inject.Guice;

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

public class CompareBuilder extends SwingWorker<GraphicalDiffMyDoggy, T2<String,Boolean>> {

    private final Ignore                      ignore;
    private final List<String>                dirs;
    private final FileTreeMatchingTaskBuilder taskBuilder;
    private final ModalProgress               progressDialog = new ModalProgress( null );


    public CompareBuilder( String ... dirs ) {

        this.dirs = new ArrayList<String>();
        for ( String dir : dirs ) {
            if ( dir != null && dir.length() > 0 ) {
                this.dirs.add( dir );
            }
        }

        if ( this.dirs.size() < 2 ) {
            throw new IllegalArgumentException( "CompareBuilder needs 2 or more dirs" );
        }

        this.ignore = IgnoreCentral.create().getIgnore();

        Injector injector = Guice.createInjector( new RuntimeModule() );

        taskBuilder = injector.getInstance( FileTreeMatchingTaskBuilder.class );
        progressDialog.setVisible( true );

    }



    protected GraphicalDiffMyDoggy doInBackground() throws Exception {

        List<TreeMatchingTask<FileContent>> tasks  = buildTasks();

        Reporter reporter = new Reporter() {
            public void detail(String str) {
                publish( T2.c(str,false) );
            }

            public void title( String str ) {
                publish( T2.c(str, true ));
            }
        };

        for ( TreeMatchingTask<FileContent> task : tasks ) {
            new NameOnly<FileContent>().match( task, reporter);
        }

        // build the trees ui in the background
        publish( T2.c( Message.get( "Progress.MainWindowBuilding" ), true ));

        return new GraphicalDiffMyDoggy( new Tasks<FileContent>( tasks ) );
    }

    private List<TreeMatchingTask<FileContent>> buildTasks() {
        List<TreeMatchingTask<FileContent>> tasks = new ArrayList<TreeMatchingTask<FileContent>>();

        for ( int idx = 1; idx < dirs.size(); ++idx  ) {

            if ( tasks.size() == 0 ) {
                // TODO localize full message
                publish(  T2.c( Message.get( "Progress.reading" ) + "\"" +  dirs.get(idx - 1 ) + "\" and \"" +  dirs.get(idx ) + "\"", true ));
                tasks.add( taskBuilder.build( ignore, new File( dirs.get(idx - 1 )), new File( dirs.get(idx ))));
            } else {
                publish( T2.c( Message.get( "Progress.reading" ) + "\"" + dirs.get(idx ) + "\"", true ));
                tasks.add( taskBuilder.build( ignore, tasks.get(tasks.size() - 1), new File( dirs.get(idx ))));
            }
        }

        return tasks;
    }


    protected void done() {
        super.done();

        GraphicalDiffMyDoggy gd4;

        try {
            gd4 = get();
        } catch ( InterruptedException e ) {
            // open log window
            Log.log( e );
            progressDialog.dispose();
            return;
        } catch ( ExecutionException e ) {
            Log.log( e );
            progressDialog.dispose();
            return;
        }

        if ( gd4 == null ) {
            Log.severe( "Could not build main window" );
            progressDialog.dispose();
            return;
        }

        gd4.setVisible(true);
        gd4.setSelectionRoots();

        progressDialog.dispose();

        Injector injector = Guice.createInjector( new RuntimeModule() );
        WatchFull strategy = injector.getInstance( WatchFull.class );

        new MatchnWatch<FileContent>( gd4.getUIApp(), strategy ).execute();
    }


    protected void process( List<T2<String,Boolean>> strings ) {
        super.process( strings );

        for ( T2<String,Boolean> txt : strings ) {
            progressDialog.setText( txt);
        }
    }
}
