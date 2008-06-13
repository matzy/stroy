package org.openCage.stroy.ui.util;

import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.stroy.ui.difftree.UINodeImpl;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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

public class DMTNMaker {


    public static <T extends Content> List<DefaultMutableTreeNode> makeDFTNs( List<TreeMatchingTask<T>> tasks ) {

        if ( tasks == null || tasks.size() == 0 ) {
            throw new IllegalArgumentException( "need some tasks" );
        }


        for ( TreeMatchingTask<T> task : tasks ) {
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


    public static <T extends Content> List<DefaultMutableTreeNode> makeDFTNs( TreeMatchingTask<T> task1, TreeMatchingTask<T> task2 ) {

        if ( task2 == null ) {
            return makeDFTNs( Arrays.asList( task1 ));            
        }
        return makeDFTNs( Arrays.asList( task1, task2 ));
    }


    private static <T extends Content>DefaultMutableTreeNode addNodes( TreeMatchingTask<T>    task1,
                                                                       TreeMatchingTask<T>    task2,
                                                                       DefaultMutableTreeNode curTop,
                                                                       TreeNode<T>            node   ) {


        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode( node.getContent().getName() );

        curDir.setUserObject( new UINodeImpl(  node, task1,  task2 ));

        if (curTop != null) { // should only be null at root
            curTop.add( curDir );
        }

        if ( node.isLeaf() ) {
            return curDir;
        }

        for (TreeNode<T> child : ((TreeDirNode<T>) node).getChildren() ) {

            addNodes( task1, task2,  curDir, child );
        }

        return curDir;
    }

}
