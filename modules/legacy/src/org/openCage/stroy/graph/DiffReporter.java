package org.openCage.stroy.graph;

import org.openCage.vfs.protocol.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.diff.ContentDiff;

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


public class DiffReporter {

    public static boolean isMoved( TreeMatchingTask task, TreeNode node ) {
        if ( !task.isMatched( node )) {
            return false;
        }

        // root?
        if ( node.getParent() == null ) {
            return false;
        }


        TreeNode    parentMatch = task.getMatch( node.getParent() );
        TreeNode matchParent = task.getMatch( node ).getParent();

        return parentMatch != matchParent;
    }

    public static <T extends Content> boolean isRenamed(TreeMatchingTask task, TreeNode node) {
        if ( !task.isMatched( node )) {
            return false;
        }


        return !node.getContent().getName().equals( task.getMatch( node ).getContent().getName());
    }

    public static <T extends Content> ContentDiff isContentChanged(TreeMatchingTask task, TreeNode node) {
        if ( !task.isMatched( node )) {
            return null;
        }

        if ( !node.isLeaf() ) {
            return task.getDirs().getDifference( node );
        }

        return task.getLeaves().getDifference( node );
    }

    public static <T extends Content> ChangeVector getChangeVector( TreeMatchingTask task, TreeNode node) {
        return null;
    }
}
