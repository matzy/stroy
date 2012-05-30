package org.openCage.stroy.ui;

import com.google.inject.Inject;
import org.openCage.lang.structure.T2;
import org.openCage.lang.structure.Tu;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilder;
import org.openCage.stroy.app.Tasks;
import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.ui.difftree.NWayDiffPaneGenerator;
import org.openCage.stroy.ui.docking.GraphicalDiffMyDoggy;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.Reporter;
import org.openCage.stroy.graph.matching.strategy.NameOnly;
import org.openCage.stroy.graph.matching.strategy.combined.WatchFull;
import org.openCage.stroy.ui.menu.PortableMenuFactory;
import org.openCage.util.logging.Log;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.io.File;

import javax.swing.SwingWorker;

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

public class CompareBuilder extends SwingWorker<GraphicalDiffMyDoggy, T2<String,String>> {

    private final Ignore                      ignore;
    private final List<String>                dirs;
    private final FileTreeMatchingTaskBuilder taskBuilder;
    private final ModalProgress               progressDialog = new ModalProgress( null );
    private final WatchFull                   watchFullstrategy;
    private final NWayDiffPaneGenerator       diffPaneGen;
    private PortableMenuFactory menuFactory;


    public CompareBuilder( final PortableMenuFactory menuFactory,
                           final NWayDiffPaneGenerator diffPaneGen,
                           final WatchFull watchFullstrategy,
                           FileTreeMatchingTaskBuilder taskBuilder,
                           String ... dirs ) {

        this.menuFactory = menuFactory;
        this.watchFullstrategy = watchFullstrategy;
        this.diffPaneGen = diffPaneGen;
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
//
        this.taskBuilder = taskBuilder;
        progressDialog.setVisible( true );

    }



    protected GraphicalDiffMyDoggy doInBackground() throws Exception {

        List<TreeMatchingTask<FileContent>> tasks  = buildTasks();

        Reporter reporter = new Reporter() {
            public void detail( String labl, String str) {
                publish( Tu.c( labl, str ) );
            }

            public void title( String str ) {
                publish( Tu.c(str, (String)null ));
            }
        };

        for ( TreeMatchingTask<FileContent> task : tasks ) {
            new NameOnly<FileContent>().match( task, reporter);
        }

        // build the trees ui in the background
        publish( Tu.c( Message.get( "Progress.MainWindowBuilding" ), (String)null ));

        return new GraphicalDiffMyDoggy( menuFactory.get(), diffPaneGen, new Tasks<FileContent>( tasks ) );
    }

    private List<TreeMatchingTask<FileContent>> buildTasks() {
        List<TreeMatchingTask<FileContent>> tasks = new ArrayList<TreeMatchingTask<FileContent>>();

        for ( int idx = 1; idx < dirs.size(); ++idx  ) {

            publish(  Tu.c( Message.get( "Progress.scanning" ), (String)null ));

            if ( tasks.size() == 0 ) {
                // TODO localize full message
                tasks.add( taskBuilder.build( ignore, new File( dirs.get(idx - 1 )), new File( dirs.get(idx ))));
            } else {
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
            // TODO open log window
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

        new MatchnWatch<FileContent>( gd4.getUIApp(), watchFullstrategy ).execute();
    }


    protected void process( List<T2<String,String>> strings ) {
        super.process( strings );

        for ( T2<String,String> txt : strings ) {
            if ( txt.i1 == null ) {
                progressDialog.setTitle( txt.i0 );
            } else {
                progressDialog.setText( txt.i0,  txt.i1 );
            }
        }
    }
}
