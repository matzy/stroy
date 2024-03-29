package org.openCage.stroy.ui.docking;

import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowAnchor;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.openCage.lindwurm.Ignore;
import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.task.NodeChangeListener;
import org.openCage.lindwurm.content.Content;
import org.openCage.stroy.app.Tasks;
import org.openCage.stroy.app.UIApp;
import org.openCage.stroy.app.StroyAppInfo;
import org.openCage.stroy.filter.IgnoreChangedListener;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.menu.PortableMenu;
import org.openCage.stroy.ui.difftree.*;
import org.openCage.stroy.ui.util.DMTNMaker;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.util.logging.Log;
import org.openCage.util.ui.TreeUtils;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.BorderLayout;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

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

// TODO cleanup, alot

public class GraphicalDiffMyDoggy<T extends Content> extends JFrame implements IgnoreChangedListener {

    private final MyDoggyToolWindowManager                      toolWindowManager;
    private final java.util.List<TreeMatchingTask>           tasks;
    private final java.util.List<DefaultMutableTreeNode>        dmtRoots;
    private NWayDiffPane                                        diffPane;

    private final UIApp app;
    private PortableMenu portableMenu;

    public GraphicalDiffMyDoggy( PortableMenu portableMenu, NWayDiffPaneGenerator gen, final Tasks tasks  ) {
        this.tasks = tasks.getTasks();
        this.portableMenu = portableMenu;

        // part of unified taskbar // refactor
        //getRootPane().putClientProperty("apple.awt.brushMetalLook", Boolean.TRUE);

        dmtRoots = DMTNMaker.makeDFTNs(this.tasks);

        fillGhosts( dmtRoots );

        setTitle( Message.get( "Docking.Main.Title" ));
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 400, 200 );

        getContentPane().setLayout( new BorderLayout());

        try {

            diffPane = gen.getDiffPane( this.tasks, dmtRoots );
        } catch ( Throwable exp ) {
            exp.fillInStackTrace();
            Log.warning(exp.getMessage());
            Log.log( exp );
        }

        final JComponent top = new ShowDiffSummary( this.tasks, dmtRoots );

        final JComponent text = new TextEditPane();
        //      final ShowCurrentDiff current = new ShowCurrentDiff( tasks );
//        final Buttons buttons = new Buttons( this );

    //    center.addNSelectionListener( current );


        toolWindowManager = new MyDoggyToolWindowManager( this );


        getContentPane().add(toolWindowManager, BorderLayout.CENTER);

        toolWindowManager.registerToolWindow( Message.get("Docking.summary"),  // Id
                                              Message.get("Docking.summary"),                 // Title
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

        toolWindowManager.registerToolWindow( "Text", "Edit", null, text, ToolWindowAnchor.BOTTOM );

        pack();
        toolWindowManager.getContentManager().addContent( Message.get("Docking.diff"),       // Id // NON-NLS
                                                          Message.get("Docking.diff"),                 // Title
                                                          null,                         // Icon
                                                          diffPane.getPanel() );

        pack();


        // Made all tools available
        for (ToolWindow window : toolWindowManager.getToolWindows()) {
            window.setAvailable(true);
        }


        pack();

        app = new UIApp( diffPane, dmtRoots, tasks );

        // TODO: should be <LindenNode> but it works for Dirs and files
        NodeChangeListener listener = new NodeChangeListener() {
            public void matched(Object left, Object right) {
                final LindenNode ll = (LindenNode)left;
                final LindenNode rr = (LindenNode)right;
                try {
                    SwingUtilities.invokeAndWait( new Runnable() {
                        public void run() {
                            if ( !((LindenNode)ll).isLeaf()) {
                                DefaultMutableTreeNode llm =  NodeToNode.nodeToDMTNode( diffPane.getRoot( 0 ), (LindenNode)ll );
                                fillGhost( llm, 0, dmtRoots  );
                                DefaultMutableTreeNode rrm =  NodeToNode.nodeToDMTNode( diffPane.getRoot( 1 ), (LindenNode)rr );
                                fillGhost( rrm, 0, dmtRoots  );
                            }

                            // diffPane.elementRefresh();
                            diffPane.refresh();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            public void matchRemoved(Object left, Object right) {
                try {
                    SwingUtilities.invokeAndWait( new Runnable() {
                        public void run() {
                            diffPane.elementRefresh();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            public void diffChanged(Object left, Object right) {
                try {
                    SwingUtilities.invokeAndWait( new Runnable() {
                        public void run() {
                            diffPane.elementRefresh();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            public void removed(Object obj) {
                Log.warning( "GDMD TODO removed" );
            }


            public void beforeMatched(Object left, Object right) {
                try {
                    final LindenNode ll = (LindenNode)left;
                    final LindenNode rr = (LindenNode)right;
                    SwingUtilities.invokeAndWait( new Runnable() {
                        public void run() {
                            {
                                // need a before
                                DefaultMutableTreeNode mutable =  NodeToNode.nodeToDMTNode( diffPane.getRoot( 0 ), (LindenNode)ll );
                                mutable = NodeToNode.findMatchingNode( diffPane.getRoot( 1 ), TreeUtils.getPath( mutable), tasks.getTasks().get(0));

                                DefaultTreeModel       model   = ((DefaultTreeModel)diffPane.getTree(1).getModel());
                                model.removeNodeFromParent( mutable );
                            }

                            {
                                DefaultMutableTreeNode mutable =  NodeToNode.nodeToDMTNode( diffPane.getRoot( 1 ), (LindenNode)rr );
                                mutable = NodeToNode.findMatchingNode( diffPane.getRoot( 0 ), TreeUtils.getPath( mutable), tasks.getTasks().get(0));

                                DefaultTreeModel model         = ((DefaultTreeModel)diffPane.getTree(0).getModel());
                                model.removeNodeFromParent( mutable );
                            }

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        };

        for ( TreeMatchingTask task : tasks.getTasks() ) {
            task.getFileTask().addNodeChangeListener( listener );
            task.getDirTask().addNodeChangeListener( listener );
        }

        portableMenu.setAppInfo( new StroyAppInfo());
        portableMenu.setFrame( this );
        portableMenu.create();

//        IgnoreCentral.getOrCreate().addListener( this );
//
//        Central.diffPane = diffPane;
//        Central.tasks    = this.tasks;
    }

    private void fillGhosts( final List<DefaultMutableTreeNode> roots) {
        for ( DefaultMutableTreeNode node : roots ) {
            fillGhost(node, 0, roots);
        }
    }

    private void fillGhost( DefaultMutableTreeNode node, int idx, List<DefaultMutableTreeNode> roots) {

        UINode ui = ((UINode)node.getUserObject());

        // TODO 3 way: tasks.get(0) is wrong
        // TODO refactor
        if ( ui.isOnlyLeft() ) {
            DefaultMutableTreeNode parent =
                    NodeToNode.findMatchingNode( roots.get(0), TreeUtils.getPath( node.getParent()), tasks.get(0));
            DefaultMutableTreeNode child = new DefaultMutableTreeNode( "//" );
            child.setUserObject( new GhostNode(  ui.get(), tasks.get(0),  null, true, false ));

            if ( diffPane != null ) {
                DefaultTreeModel model         = ((DefaultTreeModel)diffPane.getTree(0).getModel());
                model.insertNodeInto( child, parent, Math.min( idx, parent.getChildCount()));
            } else {
                parent.insert( child, Math.min( idx, parent.getChildCount()) );
            }


        }
        if ( ui.isOnlyRight() ) {
            DefaultMutableTreeNode parent =
                    NodeToNode.findMatchingNode( roots.get(1), TreeUtils.getPath( node.getParent()), tasks.get(0));
            DefaultMutableTreeNode child = new DefaultMutableTreeNode( "//" );
            child.setUserObject( new GhostNode(  ui.get(), null, tasks.get(0),  false, true ));

            // insert ghost possible in the same position as the orginal
            if ( diffPane != null ) {
                DefaultTreeModel model         = ((DefaultTreeModel)diffPane.getTree(1).getModel());
                model.insertNodeInto( child, parent, Math.min( idx, parent.getChildCount()));
            } else {
                parent.insert( child, Math.min( idx, parent.getChildCount()) );
            }


            // TODO order
//            parent.insert( child, 0 );

        }

        for ( int i = 0; i < node.getChildCount(); ++i ) {
            fillGhost( (DefaultMutableTreeNode)node.getChildAt(i), i, roots );
        }
    }

//    public GraphicalDiffMyDoggy( final java.util.List<TreeMatchingTask<FileContent>> tasks,
//                                 final java.util.List<DefaultMutableTreeNode> roots,
//                                 final PortableMenu menu) {
//
//
//        this.tasks = tasks;
//
//        if ( tasks.size() < 1 || tasks.size() > 2 ) {
//            throw new Error( "not impl" );
//        }
//
//        setTitle( "Diff" );
//        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        setSize( 400, 200 );
//
//        getContentPane().setLayout( new BorderLayout());
//
//
//        diffPane = gen.getDiffPane(tasks, roots );
//
//        // TODO make consistent
//        // this selects something but not necessarily the root
//        diffPane.getTree(0).setSelectionRow(0);
//
//        final JComponent top = new ShowDiffSummary(tasks, roots );
//  //      final ShowCurrentDiff current = new ShowCurrentDiff( tasks );
//        final Buttons buttons = new Buttons( this );
//
//    //    center.addNSelectionListener( current );
//
//        toolWindowManager = new MyDoggyToolWindowManager( this );
//        getContentPane().add( toolWindowManager, BorderLayout.CENTER );
//
//        toolWindowManager.registerToolWindow( "Summary",       // Id
//                                              "Summary",                 // Title
//                                              null,                         // Icon
//                                              top,    // Component
//                                              ToolWindowAnchor.TOP);       // Anchor
////        toolWindowManager.registerToolWindow( "Selection",       // Id
////                                              "Details",                 // Title
////                                              null,                         // Icon
////                                              current,    // Component
////                                              ToolWindowAnchor.BOTTOM);       // Anchor
////          toolWindowManager.registerToolWindow( "Global",       // Id
////                                              "actions",                 // Title
////                                              null,                         // Icon
////                                              buttons,    // Component
////                                              ToolWindowAnchor.TOP);       // Anchor
////        toolWindowManager.registerToolWindow( "Selection",       // Id
////                                              "actions",                 // Title
////                                              null,                         // Icon
////                                              selectionActions,    // Component
////                                              ToolWindowAnchor.BOTTOM);       // Anchor
//
//        pack();
//        toolWindowManager.getContentManager().addContent( "Diff",       // Id
//                                                          "Diff",                 // Title
//                                                          null,                         // Icon
//                                                          diffPane.getPanel() );
//
//        pack();
//
//        // Made all tools available
//        for (ToolWindow window : toolWindowManager.getToolWindows()) {
//            window.setAvailable(true);
//        }
//
//
//        pack();
//
//        menu.setFrame( this );
//        menu.getOrCreate();
//
//        IgnoreCentral.getOrCreate().addListener( this );
//
//        Central.diffPane = diffPane;
//        Central.tasks    = tasks;
//    }

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

    // TODO localize +
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
//        new IgnoreUpdateWorker( this, diffPane, tasks, ignore ).execute();
    }


    public UIApp getUIApp() {
        return app;
    }

    public void setSelectionRoots() {
        if ( !isVisible() ) {
            Log.warning( "frame not visible: setSelectionRoots is probably not working correctly" );
        }

        diffPane.getTree( 0).setSelectionPath( NodeToNode.getTreePath( dmtRoots.get(0)));

    }
}
