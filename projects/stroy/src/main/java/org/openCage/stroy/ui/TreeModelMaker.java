package org.openCage.stroy.ui;

import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.app.Tasks;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.difftree.UINodeImpl;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 9/14/12
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreeModelMaker {

    public DefaultMutableTreeNode make( Tasks tasks ) {

        return null;
    }

    private static DefaultMutableTreeNode addNodes( TreeMatchingTask task1,
                                                    TreeMatchingTask    task2,
                                                    DefaultMutableTreeNode curTop,
                                                    LindenNode node   ) {

        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode( node.getContent().getName() );

        curDir.setUserObject( new UINodeImpl(  node, task1,  task2 ));

        if (curTop != null) { // should only be null at root
            curTop.add( curDir );
        }


        if ( node.isLeaf() ) {
            return curDir;
        }

        for (LindenNode child : ((LindenDirNode) node).getChildren() ) {

            addNodes( task1, task2,  curDir, child );
        }

        return curDir;
    }

}
