package org.openCage.stroy.ui.popup;

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
import org.openCage.util.logging.Log;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.List;
import java.util.ArrayList;


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

public class IgnoreUpdateWorker extends SwingWorker<String, T2<Integer, TreeNode<FileContent> >> {


    private final List<TreeMatchingTask<FileContent>> tasks;
    private final Ignore ignore;
    private final ModalProgress progress;
    private final NWayDiffPane diffPane;

    private boolean one = false;
    private List<TreeNode<FileContent>> toDelLeft;
    private List<TreeNode<FileContent>> toDelRight;

    public IgnoreUpdateWorker( final JFrame frame, final NWayDiffPane diffPane, final List<TreeMatchingTask<FileContent>> tasks, final Ignore ignore  ) {
        this.tasks = tasks;
        this.ignore = ignore;
        progress = new ModalProgress( frame );
        progress.setVisible( true );
        this.diffPane = diffPane;
    }

    protected String doInBackground() throws Exception {

        if ( tasks.size() > 1 ) {
            throw new Error("n-way not impl");
        }

        toDelLeft = new ArrayList<TreeNode<FileContent>>();
        toDelRight = new ArrayList<TreeNode<FileContent>>();

        for ( TreeMatchingTask<FileContent> task : tasks ) {

            Log.warning( "TODO order left and right");

            doOneTree( task.getLeftRoot(), 1, false, toDelLeft );
            doOneTree( task.getRightRoot(), 0, false, toDelRight );
        }


        return "done";
    }

    private void doOneTree( TreeNode<FileContent> node, int idx, boolean ignoredAlready, List<TreeNode<FileContent>> toDel) {

        String path = NodeToNode.getStringPath( node );

        if ( ignoredAlready || ignore.match(path )) {
            Log.info( "removing " + node + " from tree" );
            NodeToNode.getStringPath( node );
            ignoredAlready = true;
        }

        if ( !node.isLeaf() ) {
            for ( TreeNode<FileContent> child : ((TreeDirNode<FileContent>)node).getChildren() ) {
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
        for ( TreeNode<FileContent> node : toDelLeft ) {
            tasks.get(0).remove(node);
            node.getParent().removeChild(node);
        }

        for ( TreeNode<FileContent> node : toDelRight ) {
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
    protected void process(List<T2<Integer, TreeNode<FileContent>>> nodes) {
        super.process(nodes);



        for ( T2<Integer, TreeNode<FileContent>> node : nodes ) {

            progress.setText( node.i1.toString() );
            DefaultTreeModel model = ((DefaultTreeModel)diffPane.getTree(node.i0).getModel());

            DefaultMutableTreeNode mutable =  NodeToNode.nodeToNode( diffPane.getRoot( node.i0 ), node.i1 );

//            if ( mutable == null ) {
//
//            }

            model.removeNodeFromParent( mutable );
        }

    }
}
