package org.openCage.stroy.ui.util;

import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.difftree.UINodeImpl;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class DMTNMaker {


    public static List<DefaultMutableTreeNode> makeDFTNs( List<TreeMatchingTask> tasks ) {

        if ( tasks == null || tasks.size() == 0 ) {
            throw new IllegalArgumentException( "need some tasks" );
        }

        for ( TreeMatchingTask task : tasks ) {
            if ( task == null ) {
                throw new IllegalArgumentException( "null tasks found" );                
            }
        }

        for ( int idx = 1; idx < tasks.size(); ++idx ) {
            if ( tasks.get(idx-1).getRightRoot() != tasks.get(idx).getLeftRoot() ) {
                throw new IllegalArgumentException( "tasks mismatched" );
            }
        }

        List<DefaultMutableTreeNode> ret = new ArrayList<DefaultMutableTreeNode>();

        ret.add( addNodes( null,  tasks.get(0), null, tasks.get(0).getLeftRoot()));

        for ( int idx = 0; idx < tasks.size(); ++idx ) {

            if ( idx + 1 < tasks.size() ) {
                ret.add( addNodes( tasks.get( idx ), tasks.get( idx + 1), null, tasks.get( idx ).getRightRoot()));
            } else {
                ret.add( addNodes( tasks.get( idx ), null,                null, tasks.get( idx ).getRightRoot()));
            }
        }

        return ret;
    }


    public static List<DefaultMutableTreeNode> makeDFTNs( TreeMatchingTask task1, TreeMatchingTask task2 ) {

        if ( task2 == null ) {
            return makeDFTNs( Arrays.asList( task1 ));            
        }
        return makeDFTNs( Arrays.asList( task1, task2 ));
    }


    private static DefaultMutableTreeNode addNodes( TreeMatchingTask    task1,
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
