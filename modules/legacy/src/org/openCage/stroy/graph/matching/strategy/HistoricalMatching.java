package org.openCage.stroy.graph.matching.strategy;

import com.google.inject.Inject;
import java.util.logging.Logger;
import org.openCage.stroy.TreeLeafDistance;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.array.MatchBestConnections2;
import org.openCage.vfs.protocol.Content;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;
import org.openCage.vfs.protocol.VNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;

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
 * match by fuzzyhash
 */
public class HistoricalMatching implements MatchStrategy {

    private static final Logger LOG = Logger.getLogger( HistoricalMatching.class.getName() );

    private final TreeLeafDistance fuzzyTreeLeafDistance;

    @Inject
    public HistoricalMatching( final TreeLeafNodeFuzzyLeafDistance fuzzyDistance ) {
        this.fuzzyTreeLeafDistance = fuzzyDistance;
    }

    public void match(final TreeMatchingTask treeMatchingTask, Reporter reporter) {

        reporter.title( Message.get( "Strategy.Similarity" ));


        if ( !treeMatchingTask.isMatched( treeMatchingTask.getLeftRoot())) {
            treeMatchingTask.getDirs().match( treeMatchingTask.getLeftRoot(),
                                              treeMatchingTask.getRightRoot(), 1.0 );
        }

        // TODO reporting

        final MatchBestConnections2<MatchingTask<VNode>,VNode> match =
                new MatchBestConnections2<MatchingTask<VNode>, VNode>(
                        fuzzyTreeLeafDistance, false, reporter );

        match.match( treeMatchingTask.getDirs(),
                     treeMatchingTask.getLeaves(),
                     treeMatchingTask.getLeaves().getUnmatchedLeft(),
                     treeMatchingTask.getLeaves().getUnmatchedRight() );

    }
}
