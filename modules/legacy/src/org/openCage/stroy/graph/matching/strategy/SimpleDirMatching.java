package org.openCage.stroy.graph.matching.strategy;

import java.util.logging.Logger;
import org.openCage.vfs.protocol.Content;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
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
 * match dirs based on name and same path
 */
public class SimpleDirMatching implements MatchStrategy {

    private static final Logger LOG = Logger.getLogger( SimpleDirMatching.class.getName());

    public void match(TreeMatchingTask treeMatchingTask, Reporter reporter) {
        LOG.fine( "match Simple dirs" );

        if ( !treeMatchingTask.isMatched( treeMatchingTask.getLeftRoot())) {
            treeMatchingTask.getDirs().match( treeMatchingTask.getLeftRoot(),
                                              treeMatchingTask.getRightRoot(), 1.0 );
        }

        matchInChildList(treeMatchingTask, reporter, treeMatchingTask.getDirs().getLeftRoot(), treeMatchingTask.getDirs().getRightRoot());
    }


    public void matchInChildList( TreeMatchingTask treeMatchingTask, Reporter reporter, TreeNode fromDir, TreeNode toParent ) {

        if ( ! treeMatchingTask.isMatched( fromDir )  ) {

            String name = fromDir.getContent().getName();

            if ( toParent == null ) {
                throw new IllegalStateException( "prog error" );
            }

            reporter.detail( Message.get( "testing"), fromDir.toString() );

            for ( TreeNode tgtKid : toParent.getChildren() ) {

                if ( !tgtKid.isLeaf() &&
                     !treeMatchingTask.isMatched( tgtKid ) &&
                     tgtKid.getContent().getName().equals( name )) {

                    treeMatchingTask.getDirs().match( fromDir, (TreeNode)tgtKid, 1.0 );
                    break;
                }
            }
            
        }

        TreeNode newParent = treeMatchingTask.getDirs().getMatch(fromDir);

        if ( newParent == null ) {
            return;
        }

        for ( TreeNode fm : fromDir.getChildren() ) {

            if ( !fm.isLeaf() ) {
                matchInChildList( treeMatchingTask, reporter, (TreeNode)fm, newParent );
            }
        }

    }

}
