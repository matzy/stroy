package org.openCage.stroy.ui.thinning;

import org.jdesktop.swingworker.SwingWorker;
import org.openCage.util.iterator.T2;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeNodeUtils;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.ModalProgress;
import org.openCage.stroy.app.UIApp;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.content.Content;
import org.openCage.util.logging.Log;
import org.openCage.util.lang.Method1;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.List;
import java.util.ArrayList;


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

public class ThinningWorker<T extends Content> extends SwingWorker<String, T2<Integer, TreeNode<T> >> {


    public enum DisplayAndModel {
        displayOnly,
        displayAndModel
    }

    private final Method1<Boolean, TreeNode<T>> doFilter;
    private final ModalProgress                           progress;
    private final DisplayAndModel                         displayAndModel;
    private final UIApp<T>                      uiApp;

    private boolean one = false;
    private List<TreeNode<T>> toDelLeft;
    private List<TreeNode<T>> toDelRight;

    public ThinningWorker( final JFrame                                  frame,
                           final UIApp<T>                      uiApp,
                           final Method1<Boolean, TreeNode<T>> doFilter,
                           final DisplayAndModel                         displayAndModel        ) {
        this.doFilter = doFilter;
        progress      = new ModalProgress( frame );
        progress.setVisible( true );
        this.displayAndModel = displayAndModel;
        this.uiApp = uiApp;
    }

    protected String doInBackground() throws Exception {

        if ( uiApp.getTasks().size() > 1 ) {
            throw new Error("n-way not impl");
        }

        toDelLeft  = new ArrayList<TreeNode<T>>();
        toDelRight = new ArrayList<TreeNode<T>>();

        for ( TreeMatchingTask<T> task : uiApp.getTasks() ) {

            doOneTree( task.getLeftRoot(), 0, false, toDelLeft );
            doOneTree( task.getRightRoot(), 1, false, toDelRight );
        }


        return "done";
    }

    private void doOneTree( TreeNode<T> node, int idx, boolean ignoredAlready, List<TreeNode<T>> toDel) {

        String path = TreeNodeUtils.getStringPath( node );

        if ( ignoredAlready || doFilter.call( node ) ) {
            Log.info( "removing " + node + " from tree" );
            ignoredAlready = true;
        }

        if ( ignoredAlready ) {
            this.publish( new T2( idx , node ));
            toDel.add( node );
        }

        if ( !node.isLeaf() && !ignoredAlready ) {
            for ( TreeNode<T> child : ((TreeDirNode<T>)node).getChildren() ) {
                doOneTree( child, idx, ignoredAlready, toDel );
            }
        }

    }


    // EDT
    protected void done() {
        super.done();

//        // must be here after tree stuff is done
//        for ( TreeNode<T> node : toDelLeft ) {
//            tasks.get(0).remove(node);
//            node.getParent().removeChild(node);
//        }
//
//        for ( TreeNode<T> node : toDelRight ) {
//            tasks.get(0).remove(node);
//            node.getParent().removeChild(node);
//        }


        TreeNode rr = uiApp.getTasks().get(0).getRightRoot();
        List l2 = TreeNodeUtils.toList( uiApp.getTasks().get(0).getRightRoot());

        uiApp.getDiffPane().refresh(); //getSkyView(0).setList( TreeNodeUtils.toList( tasks.get(0).getRightRoot()) );
        //diffPane.getSkyView(1).setList( TreeNodeUtils.toList( tasks.get(0).getLeftRoot()) );


        progress.dispose();


    }


    // EDT
    protected void process(List<T2<Integer, TreeNode<T>>> nodes) {
        super.process(nodes);

        for ( T2<Integer, TreeNode<T>> node : nodes ) {

            progress.setText( node.i1.toString() );
            DefaultTreeModel       model   = ((DefaultTreeModel)uiApp.getDiffPane().getTree(node.i0).getModel());
            DefaultMutableTreeNode mutable =  NodeToNode.nodeToDMTNode( uiApp.getDiffPane().getRoot( node.i0 ), node.i1 );

            if ( mutable == null ) {
                int i = 0;
            }

            model.removeNodeFromParent( mutable );
        }

    }
}
