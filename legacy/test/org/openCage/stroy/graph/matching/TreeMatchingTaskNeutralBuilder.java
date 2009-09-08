package org.openCage.stroy.graph.matching;

import org.openCage.stroy.content.ReducedContent;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.graph.node.TreeDirNode;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
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
