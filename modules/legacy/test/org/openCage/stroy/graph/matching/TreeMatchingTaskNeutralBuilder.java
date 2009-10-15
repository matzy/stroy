package org.openCage.stroy.graph.matching;

import org.openCage.stroy.content.ReducedContent;
import org.openCage.vfs.protocol.VNode;
import org.openCage.vfs.protocol.VNode;
import org.openCage.vfs.protocol.VNode;

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

    static public TreeMatchingTask build(
            VNode treeLeft,
            VNode treeRight ) {
        TreeMatchingTask task =  build( build( new TreeMatchingTaskNeutral(), treeLeft, true ),
                                                                      treeRight,false );
        task.getDirs().setRoots( (VNode)treeLeft, (VNode)treeRight);

        return task;
    }

    static private TreeMatchingTask build( TreeMatchingTask task, VNode tree, boolean left ) {

        if ( tree.isLeaf() ) {
            if ( left ) {
                task.getLeaves().addLeft( (VNode) tree );
            } else {
                task.getLeaves().addRight( (VNode) tree );
            }
        } else {
            if ( left ) {
                task.getDirs().addLeft( (VNode) tree );
            } else {
                task.getDirs().addRight( (VNode) tree );
            }

            for ( VNode child : ((VNode) tree).getChildren() ) {
                build( task, child, left );
            }
        }

        return task;
    }
}
