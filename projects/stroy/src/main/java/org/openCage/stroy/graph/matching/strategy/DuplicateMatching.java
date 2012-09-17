package org.openCage.stroy.graph.matching.strategy;

import org.openCage.kleinod.type.Null;
import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.TreeLeafDistance;
import org.openCage.stroy.array.MatchBestConnections2;
import org.openCage.stroy.graph.SameContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.util.logging.Log;

import java.util.List;

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

@SuppressWarnings({"HardCodedStringLiteral"})
public class DuplicateMatching implements MatchStrategy {
    public void match(TreeMatchingTask treeMatchingTask, Reporter reporter) {

        Log.fine( "duplicate matching" );
        reporter.title( Message.get( "Strategy.Duplicates" ));

        // TODO reporting

        for (SameContent sh : treeMatchingTask.getDuplicates() ) {
            match(treeMatchingTask, sh.getSources(), sh.getTargets() );
        }
    }


    // TODO: refactor with other

    private void match( final TreeMatchingTask matchingTask, List<LindenNode> src, List<LindenNode> tgt ) {
        if ( src.size() != 0 && tgt.size() != 0 ) {

            MatchBestConnections2<MatchingTask<LindenDirNode>,LindenNode> match =
                    new MatchBestConnections2<MatchingTask<LindenDirNode>,LindenNode>( new TreeLeafDistance() {

                public double distance(MatchingTask<LindenDirNode> info, LindenNode a, LindenNode b) {
                    double dist = 1.0;
                    // parent
                    if ( matchingTask.isMatched( a.getParent())) {
                        LindenDirNode aparentMatch = matchingTask.getDirs().getMatch( a.getParent());
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
                    true, Null.of(Reporter.class) );

            match.match( matchingTask.getDirs(), matchingTask.getLeaves(), src, tgt  );
        }
    }

}
