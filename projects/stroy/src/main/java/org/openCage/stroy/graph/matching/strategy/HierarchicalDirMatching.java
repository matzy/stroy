package org.openCage.stroy.graph.matching.strategy;

import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.content.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.locale.Message;
import org.openCage.util.logging.Log;

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

public class HierarchicalDirMatching<T extends Content> implements MatchStrategy {

    private final SimpleDirMatching simpleMatcher = new SimpleDirMatching();

    public void match( TreeMatchingTask treeMatchingTask, Reporter reporter) {

        Log.fine( "match dir hierarchical");
        reporter.title( Message.get( "Strategy.HierarchicalDir" ));


        // TODO more reports

        matchHir(treeMatchingTask, treeMatchingTask.getDirs().getLeftRoot(), reporter);
    }

    private void matchHir( TreeMatchingTask treeMatchingTask, LindenDirNode src, Reporter reporter) {

        if ( ! treeMatchingTask.isMatched( src )) {
            LindenDirNode bestMatch = matchHirBest(treeMatchingTask, src );

            if ( bestMatch != null ) {

                // TODO: test
                // TODO: store quality
                treeMatchingTask.getDirs().match( src, bestMatch, 0.99 );


                for ( LindenNode fm : src.getChildren() ) {
                    if ( !fm.isLeaf() ) {
                        simpleMatcher.matchInChildList(treeMatchingTask, reporter, (LindenDirNode)fm, bestMatch );
                    }
                }
            } else {
                Log.fine( "deleted :" + src );
            }
        }

        for ( LindenNode fm : src.getChildren() ) {
            if ( !fm.isLeaf() ) {
                matchHir(treeMatchingTask, (LindenDirNode)fm, reporter);
            }
        }
    }

    private LindenDirNode matchHirBest( TreeMatchingTask treeMatchingTask, LindenDirNode src) {

        double         bestDist  = 2.0;
        LindenDirNode bestMatch = null;

        for ( LindenDirNode tgt : treeMatchingTask.getDirs().getUnmatchedRight() ) {
            double dist = distance(treeMatchingTask, src, tgt );
            if ( dist < bestDist && dist < 0.31) {
                bestDist = dist;
                bestMatch = tgt;
            }
        }

        return bestMatch;
    }

    private double distance( TreeMatchingTask treeMatchingTaskImpl, LindenDirNode a, LindenDirNode b) {

        int deleted       = 0;
        int same          = 0;
        int sameName      = 0;

        for ( LindenNode kid : a.getChildren() ) {
            if ( !treeMatchingTaskImpl.isMatched( kid ) ) {
                boolean findName = false;
                for ( LindenNode bkid : b.getChildren() ) {

                    if ( ! treeMatchingTaskImpl.isMatched( bkid ) ) {
                        if ( bkid.getContent().getName().equals( kid.getContent().getName() )) {
                            findName = true;
                            sameName++;
                            break;
                        }
                    }
                }

                if ( !findName ) {
                    deleted++;
                }

            } else {
                if ( treeMatchingTaskImpl.getMatch( kid ).getParent() == b ) {
                    same++;
                } else {
                    deleted++;
                }
            }
        }

        double val = Math.min( 1.0, 1 - (((double)same + 0.5 * sameName )/ ((double)a.getChildren().size() )));

        if ( a.getContent().getName().equals( b.getContent().getName() ) ) {
            val *= 0.5;
        }

        return val;
    }



}
