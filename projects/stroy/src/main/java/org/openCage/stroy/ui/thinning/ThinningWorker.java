//package org.openCage.stroy.ui.thinning;
//
//import org.jdesktop.swingworker.SwingWorker;
//import org.openCage.lang.structure.T2;
//import org.openCage.lindwurm.content.FileContent;
//import org.openCage.lindwurm.LindenDirNode;
//import org.openCage.stroy.graph.node.LindenNode;
//import org.openCage.lindwurm.TreeNodes;
//import org.openCage.stroy.graph.matching.TreeMatchingTask;
//import org.openCage.stroy.ui.ModalProgress;
//import org.openCage.stroy.app.UIApp;
//import org.openCage.stroy.ui.util.NodeToNode;
//import org.openCage.lindwurm.content.Content;
//import org.openCage.util.logging.Log;
//import org.openCage.util.lang.Method1;
//
//import javax.swing.*;
//import javax.swing.tree.DefaultMutableTreeNode;
//import javax.swing.tree.DefaultTreeModel;
//import java.util.List;
//import java.util.ArrayList;
//
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//
//public class ThinningWorker<T extends Content> extends SwingWorker<String, T2<Integer, LindenNode<T> >> {
//
//
//    public enum DisplayAndModel {
//        displayOnly,
//        displayAndModel
//    }
//
//    private final Method1<Boolean, LindenNode<T>> doFilter;
//    private final ModalProgress                           progress;
//    private final DisplayAndModel                         displayAndModel;
//    private final UIApp<T>                      uiApp;
//
//    private boolean one = false;
//    private List<LindenNode<T>> toDelLeft;
//    private List<LindenNode<T>> toDelRight;
//
//    public ThinningWorker( final JFrame                                  frame,
//                           final UIApp<T>                      uiApp,
//                           final Method1<Boolean, LindenNode<T>> doFilter,
//                           final DisplayAndModel                         displayAndModel        ) {
//        this.doFilter = doFilter;
//        progress      = new ModalProgress( frame );
//        progress.setVisible( true );
//        this.displayAndModel = displayAndModel;
//        this.uiApp = uiApp;
//    }
//
//    protected String doInBackground() throws Exception {
//
//        if ( uiApp.getTasks().size() > 1 ) {
//            throw new Error("n-way not impl");
//        }
//
//        toDelLeft  = new ArrayList<LindenNode<T>>();
//        toDelRight = new ArrayList<LindenNode<T>>();
//
//        for ( TreeMatchingTask<T> task : uiApp.getTasks() ) {
//
//            doOneTree( task.getLeftRoot(), 0, false, toDelLeft );
//            doOneTree( task.getRightRoot(), 1, false, toDelRight );
//        }
//
//
//        return "done";
//    }
//
//    private void doOneTree( LindenNode<T> node, int idx, boolean ignoredAlready, List<LindenNode<T>> toDel) {
//
//        String path = TreeNodes.getStringPath( node );
//
//        if ( ignoredAlready || doFilter.call( node ) ) {
//            Log.info( "removing " + node + " from tree" );
//            ignoredAlready = true;
//        }
//
//        if ( ignoredAlready ) {
//            this.publish( new T2( idx , node ));
//            toDel.add( node );
//        }
//
//        if ( !node.isLeaf() && !ignoredAlready ) {
//            for ( LindenNode<T> child : ((LindenDirNode<T>)node).getChildren() ) {
//                doOneTree( child, idx, ignoredAlready, toDel );
//            }
//        }
//
//    }
//
//
//    // EDT
//    protected void done() {
//        super.done();
//
////        // must be here after tree stuff is done
////        for ( LindenNode<T> node : toDelLeft ) {
////            tasks.get(0).remove(node);
////            node.getParent().removeChild(node);
////        }
////
////        for ( LindenNode<T> node : toDelRight ) {
////            tasks.get(0).remove(node);
////            node.getParent().removeChild(node);
////        }
//
//
//        LindenNode rr = uiApp.getTasks().get(0).getRightRoot();
//        List l2 = TreeNodes.toList( uiApp.getTasks().get(0).getRightRoot());
//
//        uiApp.getDiffPane().refresh(); //getSkyView(0).setList( TreeNodes.toList( tasks.get(0).getRightRoot()) );
//        //diffPane.getSkyView(1).setList( TreeNodes.toList( tasks.get(0).getLeftRoot()) );
//
//
//        progress.dispose();
//
//
//    }
//
//
//    // EDT
//    protected void process(List<T2<Integer, LindenNode<T>>> nodes) {
//        super.process(nodes);
//
//        for ( T2<Integer, LindenNode<T>> node : nodes ) {
//
//            progress.setText( node.i1.toString() );
//            DefaultTreeModel       model   = ((DefaultTreeModel)uiApp.getDiffPane().getTree(node.i0).getModel());
//            DefaultMutableTreeNode mutable =  NodeToNode.nodeToDMTNode( uiApp.getDiffPane().getRoot( node.i0 ), node.i1 );
//
//            if ( mutable == null ) {
//                int i = 0;
//            }
//
//            model.removeNodeFromParent( mutable );
//        }
//
//    }
//}
