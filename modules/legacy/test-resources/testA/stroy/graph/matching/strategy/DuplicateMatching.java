package org.openCage.stroy.graph.matching.strategy;

import org.openCage.stroy.TreeLeafDistance;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.array.MatchBestConnections2;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.SameContent;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.util.logging.Log;

import java.util.List;

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

public class DuplicateMatching<T extends Content> implements MatchStrategy<T> {
    public void match(TreeMatchingTask<T> treeMatchingTask) {

        Log.fine( "duplicate matching" );

        for (SameContent<T> sh : treeMatchingTask.getDuplicates() ) {
            match(treeMatchingTask, sh.getSources(), sh.getTargets() );
        }
    }


    // TODO: refactor with other 

    private void match( final TreeMatchingTask<T> matchingTask, List<TreeNode<T>> src, List<TreeNode<T>> tgt ) {
        if ( src.size() != 0 && tgt.size() != 0 ) {

            MatchBestConnections2<MatchingTask<TreeNode<T>>,TreeNode<T>> match =
                    new MatchBestConnections2<MatchingTask<TreeNode<T>>,TreeNode<T>>( new TreeLeafDistance<T>() {

                public double distance(MatchingTask<TreeNode<T>> info, TreeNode<T> a, TreeNode<T> b) {
                    double dist = 1.0;
                    // parent
                    if ( matchingTask.isMatched( a.getParent())) {
                        TreeNode aparentMatch = matchingTask.getDirs().getMatch( a.getParent());
                        if ( aparentMatch == b.getParent() ) {
                            dist *= 0.25;
                        }
                    }
                    // TODO ???
//                    else if ( b.getParent().getId() == null ) {
//                        dist *= 0.3;
//                    }

                    if ( a.getContent().getName().equals( b.getContent().getName() )) {
                        dist *= 0.2;
                    }

                    return dist;
                }
            },
                    true );

            match.match( matchingTask.getDirs(), matchingTask.getLeaves(), src, tgt  );
        }
    }

}
