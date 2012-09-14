package org.openCage.stroy.graph.matching;

import org.openCage.kleinod.collection.Forall;
import org.openCage.kleinod.lambda.VF1;
import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/22/12
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class LindwurmToTreeMatchingTask {

    public TreeMatchingTask build( LindenDirNode node ) {
        final TreeMatchingTask pool = new TreeMatchingTaskNeutral();

        Forall.forall(node).act( new VF1<LindenNode>() {
            @Override
            public void call(LindenNode treeNode) {
                if ( treeNode.isLeaf() ) {
                    pool.getLeaves().addLeft( treeNode );
                } else {
                    pool.getDirs().addLeft( treeNode.dir() );
                }
            }
        });

        pool.getDirTask().setRoot( node, true );

        return pool;
    }

    public TreeMatchingTask build( final TreeMatchingTask task, LindenDirNode node ) {

        Forall.forall( node ).act( new VF1<LindenNode>() {
            @Override
            public void call(LindenNode treeNode) {
                if ( treeNode.isLeaf() ) {
                    task.getLeaves().addRight( treeNode );
                } else {
                    task.getDirs().addRight( treeNode.dir() );
                }
            }
        });

        task.getDirTask().setRoot( node, false );

        return task;
    }

}
