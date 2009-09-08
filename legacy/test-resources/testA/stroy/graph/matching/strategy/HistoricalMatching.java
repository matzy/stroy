package org.openCage.stroy.graph.matching.strategy;

import com.google.inject.Inject;
import org.openCage.stroy.TreeLeafDistance;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.array.MatchBestConnections2;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;

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
 * match by fuzzyhash
 */
public class HistoricalMatching<T extends Content> implements MatchStrategy<T> {

    private final TreeLeafDistance<T> fuzzyTreeLeafDistance;

    @Inject
    public HistoricalMatching( final TreeLeafNodeFuzzyLeafDistance<T> fuzzyDistance ) {
        this.fuzzyTreeLeafDistance = fuzzyDistance;
    }

    public void match(final TreeMatchingTask<T> treeMatchingTask) {

        if ( !treeMatchingTask.isMatched( treeMatchingTask.getLeftRoot())) {
            treeMatchingTask.getDirs().match( treeMatchingTask.getLeftRoot(),
                                              treeMatchingTask.getRightRoot(), 1.0 );
        }


        final MatchBestConnections2<MatchingTask<TreeDirNode<T>>,TreeLeafNode<T>> match =
                new MatchBestConnections2<MatchingTask<TreeDirNode<T>>, TreeLeafNode<T>>(fuzzyTreeLeafDistance, false );

        match.match( treeMatchingTask.getDirs(),
                     treeMatchingTask.getLeaves(),
                     treeMatchingTask.getLeaves().getUnmatchedLeft(),
                     treeMatchingTask.getLeaves().getUnmatchedRight() );

    }
}
