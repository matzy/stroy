package org.openCage.stroy.ui.thinning;

import org.jdesktop.swingworker.SwingWorker;
import org.openCage.util.iterator.T2;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeNodeUtils;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.ModalProgress;
import org.openCage.stroy.ui.difftree.NWayDiffPane;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.locale.Message;
import org.openCage.util.logging.Log;

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

/**
 * To be run if ignore is changed.
 * TODO extend to 3way
 */
public class IgnoreUpdateWorker<T extends Content> extends SwingWorker<String, T2<Integer, TreeNode<T> >> {


    private final List<TreeMatchingTask<T>> tasks;
    private final Ignore ignore;
    private final ModalProgress progress;
    private final NWayDiffPane diffPane;

    private boolean one = false;
    private List<TreeNode<T>> toDelLeft;
    private List<TreeNode<T>> toDelRight;

    public IgnoreUpdateWorker( final JFrame frame, final NWayDiffPane diffPane, final List<TreeMatchingTask<T>> tasks, final Ignore ignore  ) {
        this.tasks    = tasks;
        this.ignore   = ignore;
        progress      = new ModalProgress( frame );
        progress.setVisible( true );
        this.diffPane = diffPane;
    }

    protected String doInBackground() throws Exception {

        if ( tasks.size() > 1 ) {
            throw new Error("n-way not impl");
        }

        toDelLeft  = new ArrayList<TreeNode<T>>();
        toDelRight = new ArrayList<TreeNode<T>>();

        for ( TreeMatchingTask<T> task : tasks ) {

            doOneTree( task.getLeftRoot(), 0, false, toDelLeft );
            doOneTree( task.getRightRoot(), 1, false, toDelRight );
        }


        return "done";
    }

    private void doOneTree( TreeNode<T> node, int idx, boolean ignoredAlready, List<TreeNode<T>> toDel) {

        String path = TreeNodeUtils.getStringPath( node );

        if ( ignoredAlready || ignore.match(path )) {
            Log.info( "removing " + node + " from tree" );
            ignoredAlready = true;
        }

        if ( !node.isLeaf() ) {
            for ( TreeNode<T> child : ((TreeDirNode<T>)node).getChildren() ) {
                doOneTree( child, idx, ignoredAlready, toDel );
            }
        }

        if ( ignoredAlready ) {
            this.publish( new T2( idx , node ));
            toDel.add( node );
        }
    }


    // EDT
    protected void done() {
        super.done();

        // must be here after tree stuff is done
        for ( TreeNode<T> node : toDelLeft ) {
            tasks.get(0).remove(node);
            node.getParent().removeChild(node);
        }

        for ( TreeNode<T> node : toDelRight ) {
            tasks.get(0).remove(node);
            node.getParent().removeChild(node);
        }


        TreeNode rr = tasks.get(0).getRightRoot();
        List l2 = TreeNodeUtils.toList( tasks.get(0).getRightRoot());

        diffPane.refresh(); //getSkyView(0).setList( TreeNodeUtils.toList( tasks.get(0).getRightRoot()) );
        //diffPane.getSkyView(1).setList( TreeNodeUtils.toList( tasks.get(0).getLeftRoot()) );


        progress.dispose();


    }

    // EDT
//    protected void process(List<String> strings) {
//        super.process(strings);
//
//        for ( String str : strings ) {
//            progress.setText(str);
//        }
//    }


    // EDT
    protected void process(List<T2<Integer, TreeNode<T>>> nodes) {
        super.process(nodes);



        for ( T2<Integer, TreeNode<T>> node : nodes ) {

            progress.setText( Message.get( "Progress.filtering" ), node.i1.toString() );
            DefaultTreeModel       model   = ((DefaultTreeModel)diffPane.getTree(node.i0).getModel());
            DefaultMutableTreeNode mutable =  NodeToNode.nodeToDMTNode( diffPane.getRoot( node.i0 ), node.i1 );

            model.removeNodeFromParent( mutable );
        }

    }
}
