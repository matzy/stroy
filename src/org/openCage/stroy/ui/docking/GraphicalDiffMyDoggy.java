package org.openCage.stroy.ui.docking;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowAnchor;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.openCage.stroy.RuntimeModule;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.task.NodeChangeListener;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.app.Tasks;
import org.openCage.stroy.app.UIApp;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.filter.IgnoreChangedListener;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.ui.menu.PortableMenu;
import org.openCage.stroy.ui.difftree.*;
import org.openCage.stroy.ui.util.DMTNMaker;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.util.logging.Log;
import org.openCage.util.ui.TreeUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

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

// TODO cleanup, alot

public class GraphicalDiffMyDoggy<T extends Content> extends JFrame implements IgnoreChangedListener {

    private final MyDoggyToolWindowManager                      toolWindowManager;
    private final java.util.List<TreeMatchingTask<T>>           tasks;
    private final java.util.List<DefaultMutableTreeNode>        dmtRoots;
    private NWayDiffPane                                        diffPane;

    private final UIApp app;

    public GraphicalDiffMyDoggy( final Tasks<T> tasks  ) {
        this.tasks = tasks.getTasks();

        // part of unified taskbar // refactor
        //getRootPane().putClientProperty("apple.awt.brushMetalLook", Boolean.TRUE);

        dmtRoots = DMTNMaker.makeDFTNs( this.tasks );

        fillGhosts( dmtRoots );


        setTitle( Message.get( "Docking.Main.Title" ));
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 400, 200 );

        getContentPane().setLayout( new BorderLayout());

        // TODO
        Injector injector         = Guice.createInjector( new RuntimeModule() );
        NWayDiffPaneGenerator gen = injector.getInstance( NWayDiffPaneGenerator.class );

        diffPane = gen.getDiffPane( this.tasks, dmtRoots );

        final JComponent top = new ShowDiffSummary( this.tasks, dmtRoots );
  //      final ShowCurrentDiff current = new ShowCurrentDiff( tasks );
        final Buttons buttons = new Buttons( this );

    //    center.addNSelectionListener( current );

        toolWindowManager = new MyDoggyToolWindowManager( this );
        getContentPane().add( toolWindowManager, BorderLayout.CENTER );

        toolWindowManager.registerToolWindow( "Summary",   // NON-NLS    // Id
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

        pack();
        toolWindowManager.getContentManager().addContent( "Diff",       // Id // NON-NLS
                                                          Message.get("Docking.diff"),                 // Title
                                                          null,                         // Icon
                                                          diffPane.getPanel() );

        pack();

        // Made all tools available
        for (ToolWindow window : toolWindowManager.getToolWindows()) {
            window.setAvailable(true);
        }


        pack();

        app = new UIApp<T>( diffPane, dmtRoots, tasks );

        // TODO: should be <TreeNode<T>> but it works for Dirs and files
        NodeChangeListener listener = new NodeChangeListener() {
            public void matched(Object left, Object right) {
                final TreeNode ll = (TreeNode)left;
                final TreeNode rr = (TreeNode)right;
                try {
                    SwingUtilities.invokeAndWait( new Runnable() {
                        public void run() {
                            if ( !((TreeNode)ll).isLeaf()) {
                                DefaultMutableTreeNode llm =  NodeToNode.nodeToDMTNode( diffPane.getRoot( 0 ), (TreeNode<T>)ll );
                                fillGhost( llm, 0, dmtRoots  );
                                DefaultMutableTreeNode rrm =  NodeToNode.nodeToDMTNode( diffPane.getRoot( 1 ), (TreeNode<T>)rr );
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
                    final TreeNode<T> ll = (TreeNode)left;
                    final TreeNode<T> rr = (TreeNode)right;
                    SwingUtilities.invokeAndWait( new Runnable() {
                        public void run() {
                            {
                                // need a before
                                DefaultMutableTreeNode mutable =  NodeToNode.nodeToDMTNode( diffPane.getRoot( 0 ), (TreeNode<T>)ll );
                                mutable = NodeToNode.findMatchingNode( diffPane.getRoot( 1 ), TreeUtils.getPath( mutable), tasks.getTasks().get(0));

                                DefaultTreeModel       model   = ((DefaultTreeModel)diffPane.getTree(1).getModel());
                                model.removeNodeFromParent( mutable );
                            }

                            {
                                DefaultMutableTreeNode mutable =  NodeToNode.nodeToDMTNode( diffPane.getRoot( 1 ), (TreeNode<T>)rr );
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

        for ( TreeMatchingTask<T> task : tasks.getTasks() ) {
            task.getFileTask().addNodeChangeListener( listener );
            task.getDirTask().addNodeChangeListener( listener );
        }

        PortableMenu menu = new PortableMenu();
        menu.setFrame( this );
        menu.create();

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
//        // TODO
//        Injector injector = Guice.createInjector( new RuntimeModule() );
//        NWayDiffPaneGenerator gen = injector.getInstance( NWayDiffPaneGenerator.class );
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
