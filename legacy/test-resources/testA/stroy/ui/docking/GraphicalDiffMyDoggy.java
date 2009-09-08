package org.openCage.stroy.ui.docking;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowAnchor;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.openCage.stroy.RuntimeModule;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.filter.IgnoreChangedListener;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.popup.IgnoreUpdateWorker;
import org.openCage.stroy.ui.difftree.NWayDiffPane;
import org.openCage.stroy.ui.menu.PortableMenu;
import org.openCage.stroy.ui.difftree.NWayDiffPaneGenerator;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class GraphicalDiffMyDoggy extends JFrame implements IgnoreChangedListener {

    private final MyDoggyToolWindowManager toolWindowManager;
    private final java.util.List<TreeMatchingTask<FileContent>> tasks;
    private NWayDiffPane diffPane;

    public GraphicalDiffMyDoggy( final java.util.List<TreeMatchingTask<FileContent>> tasks,
                                 final java.util.List<DefaultMutableTreeNode> roots,
                                 final PortableMenu menu) {


        this.tasks = tasks;

        if ( tasks.size() < 1 || tasks.size() > 2 ) {
            throw new Error( "not impl" );
        }

        setTitle( "Diff" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 400, 200 );

        getContentPane().setLayout( new BorderLayout());

        // TODO
        Injector injector = Guice.createInjector( new RuntimeModule() );
        NWayDiffPaneGenerator gen = injector.getInstance( NWayDiffPaneGenerator.class );

        diffPane = gen.getDiffPane(tasks, roots );

        // TODO make consistent
        // this selects something but not necessarily the root
        diffPane.getTree(0).setSelectionRow(0);

        final JComponent top = new ShowDiffSummery(tasks, roots );
  //      final ShowCurrentDiff current = new ShowCurrentDiff( tasks );
        final Buttons buttons = new Buttons( this );

    //    center.addNSelectionListener( current );

        toolWindowManager = new MyDoggyToolWindowManager( this );
        getContentPane().add( toolWindowManager, BorderLayout.CENTER );

        toolWindowManager.registerToolWindow( "Summery",       // Id
                                              "Summery",                 // Title
                                              null,                         // Icon
                                              top,    // Component
                                              ToolWindowAnchor.TOP);       // Anchor
//        toolWindowManager.registerToolWindow( "Selection",       // Id
//                                              "Details",                 // Title
//                                              null,                         // Icon
//                                              current,    // Component
//                                              ToolWindowAnchor.BOTTOM);       // Anchor
//          toolWindowManager.registerToolWindow( "Global",       // Id
//                                              "actions",                 // Title
//                                              null,                         // Icon
//                                              buttons,    // Component
//                                              ToolWindowAnchor.TOP);       // Anchor
//        toolWindowManager.registerToolWindow( "Selection",       // Id
//                                              "actions",                 // Title
//                                              null,                         // Icon
//                                              selectionActions,    // Component
//                                              ToolWindowAnchor.BOTTOM);       // Anchor

        pack();
        toolWindowManager.getContentManager().addContent( "Diff",       // Id
                                                          "Diff",                 // Title
                                                          null,                         // Icon
                                                          diffPane.getPanel() );

        pack();

        // Made all tools available
        for (ToolWindow window : toolWindowManager.getToolWindows()) {
            window.setAvailable(true);
        }


        pack();

        menu.setFrame( this );
        menu.create();

        IgnoreCentral.create().addListener( this );
    }

    protected void initToolWindowManager() {
        // Create a new instance of MyDoggyToolWindowManager passing the frame.

        // Register a Tool.
        toolWindowManager.registerToolWindow("Debug",       // Id
                                             "Debug Tool",                 // Title
                                             null,                         // Icon
                                             new JButton("Debug Tool"),    // Component
                                             ToolWindowAnchor.TOP);       // Anchor

        // Made all tools available
        for (ToolWindow window : toolWindowManager.getToolWindows())
            window.setAvailable(true);

        // Add myDoggyToolWindowManager to the frame. MyDoggyToolWindowManager is an extension of a JPanel
    }

    public void setMerging(boolean on) {
        if ( on ) {
            setTitle( "Merge" );
        } else {
            setTitle( "Diff" );
        }
    }

    public void setMatchSelection(boolean on) {

    }

    public void ignoreChanged(Ignore ignore, boolean more) {
        new IgnoreUpdateWorker( this, diffPane, tasks, ignore ).execute();
    }
}
