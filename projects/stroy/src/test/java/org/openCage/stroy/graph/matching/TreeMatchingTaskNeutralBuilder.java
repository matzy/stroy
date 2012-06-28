package org.openCage.stroy.graph.matching;

import org.openCage.stroy.content.ReducedContent;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.graph.node.TreeDirNode;

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
public class TreeMatchingTaskNeutralBuilder {

    static public TreeMatchingTask<ReducedContent> build(
            TreeNode<ReducedContent> treeLeft,
            TreeNode<ReducedContent> treeRight ) {
        TreeMatchingTask<ReducedContent> task =  build( build( new TreeMatchingTaskNeutral<ReducedContent>(), treeLeft, true ),
                                                                      treeRight,false );
        task.getDirs().setRoots( (TreeDirNode<ReducedContent>)treeLeft, (TreeDirNode<ReducedContent>)treeRight);

        return task;
    }

    static private TreeMatchingTask<ReducedContent> build( TreeMatchingTask<ReducedContent> task, TreeNode<ReducedContent> tree, boolean left ) {

        if ( tree.isLeaf() ) {
            if ( left ) {
                task.getLeaves().addLeft( (TreeLeafNode<ReducedContent>) tree );
            } else {
                task.getLeaves().addRight( (TreeLeafNode<ReducedContent>) tree );
            }
        } else {
            if ( left ) {
                task.getDirs().addLeft( (TreeDirNode<ReducedContent>) tree );
            } else {
                task.getDirs().addRight( (TreeDirNode<ReducedContent>) tree );
            }

            for ( TreeNode<ReducedContent> child : ((TreeDirNode<ReducedContent>) tree).getChildren() ) {
                build( task, child, left );
            }
        }

        return task;
    }
}
