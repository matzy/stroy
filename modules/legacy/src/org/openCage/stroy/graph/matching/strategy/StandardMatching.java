package org.openCage.stroy.graph.matching.strategy;

import java.util.logging.Logger;
import org.openCage.vfs.protocol.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.locale.Message;

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

/**
 * match dirs and leaves based on same path
 */
public class StandardMatching  implements MatchStrategy {

    private static Logger LOG = Logger.getLogger(StandardMatching.class.getName());

    public void match( TreeMatchingTask treeMatchingTask, Reporter reporter) {
        LOG.fine( "match Simple dirs" );

        if ( !treeMatchingTask.isMatched( treeMatchingTask.getLeftRoot())) {
            treeMatchingTask.getDirs().match( treeMatchingTask.getLeftRoot(),
                                              treeMatchingTask.getRightRoot(), 1.0 );
        }

        matchInChildList( treeMatchingTask,
                reporter,
                          treeMatchingTask.getDirs().getLeftRoot(),
                          treeMatchingTask.getDirs().getRightRoot());
    }


    public void matchInChildList( TreeMatchingTask treeMatchingTask,
                                  Reporter reporter,
                                  TreeNode         leftNode,
                                  TreeNode      toParent ) {

        if ( ! treeMatchingTask.isMatched( leftNode )  ) {

            String name = leftNode.getContent().getName();

            if ( toParent == null ) {
                throw new IllegalStateException( "prog error" );
            }

            reporter.detail( Message.get( "testing"), leftNode.toString() );

            for ( TreeNode tgtKid : toParent.getChildren() ) {

                if ( (leftNode.isLeaf() == tgtKid.isLeaf() ) &&
                     !treeMatchingTask.isMatched( tgtKid ) &&
                     tgtKid.getContent().getName().equals( name )) {

                    if ( leftNode.isLeaf() ) {
                        double qual = 0.7;
                        if ( leftNode.getContent().getChecksum().equals( tgtKid.getContent().getChecksum() )) {
                            qual = 1.0;
                        }
                        treeMatchingTask.getLeaves().match( (TreeNode)leftNode, (TreeNode)tgtKid, qual );
                    } else {
                        treeMatchingTask.getDirs().match( (TreeNode)leftNode, (TreeNode)tgtKid, 1.0 );
                    }
                    break;
                }
            }

        }

        if ( leftNode.isLeaf() ) {
            return;
        }

        TreeNode newParent = treeMatchingTask.getDirs().getMatch((TreeNode)leftNode);

        if ( newParent == null ) {
            // no match
            return;
        }

        for ( TreeNode fm : ((TreeNode)leftNode).getChildren() ) {
            matchInChildList(treeMatchingTask, reporter, fm, newParent  );
        }

    }

}

