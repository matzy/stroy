package org.openCage.stroy.graph.matching.strategy;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.util.logging.Log;

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

/**
 * match dirs and leaves based on same path
 */
public class StandardMatching <T extends Content> implements MatchStrategy<T> {

    public void match( TreeMatchingTask<T> treeMatchingTask) {
        Log.fine( "match Simple dirs" );

        if ( !treeMatchingTask.isMatched( treeMatchingTask.getLeftRoot())) {
            treeMatchingTask.getDirs().match( treeMatchingTask.getLeftRoot(),
                                              treeMatchingTask.getRightRoot(), 1.0 );
        }

        matchInChildList(treeMatchingTask, treeMatchingTask.getDirs().getLeftRoot(), treeMatchingTask.getDirs().getRightRoot());
    }


    public void matchInChildList( TreeMatchingTask<T> treeMatchingTask, TreeNode<T> leftNode, TreeNode<T> toParent ) {

        if ( ! treeMatchingTask.isMatched( leftNode )  ) {

            String name = leftNode.getContent().getName();

            if ( toParent == null ) {
                throw new IllegalStateException( "prog error" );
            }

            for ( TreeNode<T> tgtKid : toParent.getChildren() ) {

                if ( (leftNode.isLeaf() == tgtKid.isLeaf() ) &&
                     !treeMatchingTask.isMatched( tgtKid ) &&
                     tgtKid.getContent().getName().equals( name )) {

                    if ( leftNode.isLeaf() ) {
                        double qual = 0.7;
                        if ( leftNode.getContent().getChecksum().equals( tgtKid.getContent().getChecksum() )) {
                            qual = 1.0;
                        }
                        treeMatchingTask.getLeaves().match( (TreeNode<T>)leftNode, (TreeNode<T>)tgtKid, qual );
                    } else {
                        treeMatchingTask.getDirs().match( (TreeNode<T>)leftNode, (TreeNode<T>)tgtKid, 1.0 );
                    }
                    break;
                }
            }

        }

        if ( leftNode.isLeaf() ) {
            return;
        }

        TreeNode<T> newParent = treeMatchingTask.getDirs().getMatch((TreeNode<T>)leftNode);

        if ( newParent == null ) {
            // no match
            return;
        }

        for ( TreeNode<T> fm : ((TreeNode<T>)leftNode).getChildren() ) {
            matchInChildList(treeMatchingTask, fm, newParent );
        }

    }

}

