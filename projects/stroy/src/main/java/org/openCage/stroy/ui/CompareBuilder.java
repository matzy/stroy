package org.openCage.stroy.ui;

import org.openCage.kleinod.collection.T2;
import org.openCage.kleinod.io.FileUtils;
import org.openCage.lindwurm.Ignore;
import org.openCage.lindwurm.LindwurmBuilder;
import org.openCage.lindwurm.file.FileLindwurmBuilder;
import org.openCage.lindwurm.jar.JarLindwurmBuilder;
import org.openCage.lindwurm.json.JsonLindwurmBuilder;
import org.openCage.lindwurm.single.SingleLindwurmBuilder;
import org.openCage.lindwurm.xml.XmlLindwurmBuilder;
import org.openCage.lindwurm.zip.ZipLindwurmBuilder;
import org.openCage.stroy.app.Tasks;
import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.graph.matching.LindwurmToTreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.NameOnly;
import org.openCage.stroy.graph.matching.strategy.Reporter;
import org.openCage.stroy.graph.matching.strategy.combined.WatchFull;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.ui.difftree.NWayDiffPaneGenerator;
import org.openCage.stroy.ui.docking.GraphicalDiffMyDoggy;
import org.openCage.stroy.ui.menu.PortableMenuFactory;
import org.openCage.util.logging.Log;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

public class CompareBuilder extends SwingWorker<GraphicalDiffMyDoggy, T2<String,String>> {

    private final Ignore                      ignore;
    private final List<String>                dirs;
    private final ModalProgress               progressDialog = new ModalProgress( null );
    private final WatchFull                   watchFullstrategy;
    private final NWayDiffPaneGenerator       diffPaneGen;
    private PortableMenuFactory menuFactory;


    public CompareBuilder(final PortableMenuFactory menuFactory,
                          final NWayDiffPaneGenerator diffPaneGen,
                          final WatchFull watchFullstrategy,
                          IgnoreCentral ignoreCentral,
                          String... dirs) {

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

        this.ignore = ignoreCentral.getIgnore();
//
        progressDialog.setVisible( true );

    }



    protected GraphicalDiffMyDoggy doInBackground() throws Exception {

        List<TreeMatchingTask> tasks = null;
        try {
            tasks  = buildTasks();
        } catch ( Exception exp ) {
            Log.warning(exp);
            throw exp;
        }

        Reporter reporter = new Reporter() {
            public void detail( String labl, String str) {
                publish( T2.valueOf( labl, str ) );
            }

            public void title( String str ) {
                publish( T2.valueOf(str, (String)null ));
            }
        };

        for ( TreeMatchingTask task : tasks ) {
            new NameOnly().match( task, reporter);
        }

        // build the trees ui in the background
        publish( T2.valueOf( Message.get( "Progress.MainWindowBuilding" ), (String)null ));

        return new GraphicalDiffMyDoggy( menuFactory.get(), diffPaneGen, new Tasks( tasks ) );
    }

    private List<TreeMatchingTask> buildTasks() {
        List<TreeMatchingTask> tasks = new ArrayList<TreeMatchingTask>();

        for ( int idx = 1; idx < dirs.size(); ++idx  ) {

            // TODO localize full message
            publish(  T2.valueOf( Message.get( "Progress.scanning" ), (String)null ));

            if ( tasks.size() == 0 ) {

                TreeMatchingTask task = new LindwurmToTreeMatchingTask().
                        build(
                                getLindwurmBuilder(dirs.get(idx - 1)).
                                        build( ignore, new File(dirs.get(idx - 1))).dir());
                new LindwurmToTreeMatchingTask().
                        build(
                                task,
                                getLindwurmBuilder(dirs.get(idx )).
                                       build( ignore, new File(dirs.get(idx ))).dir());
                tasks.add( task );
//                TreeMatchingTask task = getTaskBuilder(dirs.get(idx - 1)).build(null, ignore, new File(dirs.get(idx - 1)));
//                tasks.add( getTaskBuilder(dirs.get(idx)).build(task, ignore, new File(dirs.get(idx))));
            } else {
                throw new Error("huh");
                //tasks.add( taskBuilder.build( tasks.get(tasks.size() - 1), ignore, new File( dirs.get(idx ))));
            }
        }

        return tasks;
    }


    protected void done() {
        super.done();

        GraphicalDiffMyDoggy gd4 = null;

        try {
            gd4 = get();
        } catch ( InterruptedException e ) {
            // TODO open log window
            Log.log( e );
            progressDialog.dispose();
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "oppsie",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch ( ExecutionException e ) {
            Log.log( e );
            progressDialog.dispose();
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "oppsie",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        if ( gd4 == null ) {
            Log.severe( "Could not build main window" );
            progressDialog.dispose();
            return;
        }

        gd4.setVisible(true);
        gd4.setSelectionRoots();

        progressDialog.dispose();

        new MatchnWatch( gd4.getUIApp(), watchFullstrategy ).execute();
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


    public LindwurmBuilder getLindwurmBuilder(String path) {
        if ( new File(path).isDirectory() ) {
            return new FileLindwurmBuilder();
        } else {
            String ext = FileUtils.getExtension( path );

            if ( ext.equals( "zip" )) {
                return new ZipLindwurmBuilder();
            } else if ( ext.equals( "xml")) {
                return new XmlLindwurmBuilder();
            } else if ( ext.equals( "json")) {
                return new JsonLindwurmBuilder();
            } else if ( ext.equals( "jar")) {
                return new JarLindwurmBuilder();
            }

            return new SingleLindwurmBuilder();

        }


    }

}
