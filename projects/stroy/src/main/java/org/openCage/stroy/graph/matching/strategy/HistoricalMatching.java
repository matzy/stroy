package org.openCage.stroy.graph.matching.strategy;

import com.google.inject.Inject;
import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.TreeLeafDistance;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.array.MatchBestConnections2;
import org.openCage.stroy.graph.matching.TreeMatchingTask;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

/**
 * match by fuzzyhash
 */
public class HistoricalMatching implements MatchStrategy {

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

        final MatchBestConnections2<MatchingTask<LindenDirNode>,LindenNode> match =
                new MatchBestConnections2<MatchingTask<LindenDirNode>, LindenNode>(
                        fuzzyTreeLeafDistance, false, reporter );

        match.match( treeMatchingTask.getDirs(),
                     treeMatchingTask.getLeaves(),
                     treeMatchingTask.getLeaves().getUnmatchedLeft(),
                     treeMatchingTask.getLeaves().getUnmatchedRight() );

    }
}
