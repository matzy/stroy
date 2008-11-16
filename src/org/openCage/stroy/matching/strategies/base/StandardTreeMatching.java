package org.openCage.stroy.matching.strategies.base;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.graph.matching.strategy.Reporter;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.locale.Message;
import org.openCage.util.logging.Log;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.matching.strategies.Strategy;
import org.openCage.stroy.matching.strategies.TreeStrategy;
import org.openCage.stroy.matching.TreeTask;
import org.openCage.stroy.matching.Task;
import org.openCage.stroy.tree.Noed;
import org.openCage.util.logging.Log;

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

/**
 * match dirs and leaves based on same path
 */

/**
 * A tree matching strategy following the standard logic
 * i.e. just use the name path.
 * not great but fast and a good first step
 */
public class StandardTreeMatching implements TreeStrategy {

    public void match( TreeTask task ) {
        matchInChildList( task, task.getLeftRoot(), task.getRightRoot() );
    }

    public void match( Task<Noed> task ) {
        // would need to search for root
        throw new Error( "not impl" );
    }



    public void matchInChildList( Task<Noed> task,
//                                  Reporter reporter,
                                  Noed leftNode,
                                  Noed toParent ) {

        if ( ! task.isMatched( leftNode )  ) {

            String name = leftNode.getName();

            if ( toParent == null ) {
                throw new IllegalStateException( "prog error" );
            }

            // TODO
            //reporter.detail( Message.get( "testing"), leftNode.toString() );

            for ( Noed tgtKid : toParent.getChildren() ) {

                if ( (leftNode.isLeaf() == tgtKid.isLeaf() ) &&
                     !task.isMatched( tgtKid ) &&
                     tgtKid.getName().equals( name )) {

                    task.match( leftNode, tgtKid );
                    break;
                }
            }

        }

        if ( leftNode.isLeaf() ) {
            return;
        }

        Noed newParent = task.getMatch( leftNode );

        if ( newParent == null ) {
            // no match
            return;
        }

        for ( Noed fm : leftNode.getChildren() ) {
            matchInChildList(task, fm, newParent  );
        }

    }

}

