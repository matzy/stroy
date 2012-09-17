package org.openCage.stroy.graph.matching.strategy;

import org.openCage.kleinod.lambda.Memo;
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

/**
 * match dirs and leaves based on same path
 */
public class StandardMatching implements MatchStrategy {

    private Memo<String,Content> checksum;

    public void match( TreeMatchingTask treeMatchingTask, Reporter reporter) {

        if ( checksum == null ) { throw new Error("settttttt"); }

        Log.fine( "match Simple dirs" );

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
                                  LindenNode leftNode,
                                  LindenDirNode toParent ) {

        if ( ! treeMatchingTask.isMatched( leftNode )  ) {

            String name = leftNode.getContent().getName();

            if ( toParent == null ) {
                throw new IllegalStateException( "prog error" );
            }

            reporter.detail( Message.get( "testing"), leftNode.toString() );

            for ( LindenNode tgtKid : toParent.getChildren() ) {

                if ( (leftNode.isLeaf() == tgtKid.isLeaf() ) &&
                     !treeMatchingTask.isMatched( tgtKid ) &&
                     tgtKid.getContent().getName().equals( name )) {

                    if ( leftNode.isLeaf() ) {
                        double qual = 0.7;
                        if ( checksum.get(leftNode.getContent()).equals( checksum.get(tgtKid.getContent()))) {
                            qual = 1.0;
                        }
                        treeMatchingTask.getLeaves().match( leftNode, tgtKid, qual );
                    } else {
                        treeMatchingTask.getDirs().match( (LindenDirNode)leftNode, (LindenDirNode)tgtKid, 1.0 );
                    }
                    break;
                }
            }

        }

        if ( leftNode.isLeaf() ) {
            return;
        }

        LindenDirNode newParent = treeMatchingTask.getDirs().getMatch((LindenDirNode)leftNode);

        if ( newParent == null ) {
            // no match
            return;
        }

        for ( LindenNode fm : ((LindenDirNode)leftNode).getChildren() ) {
            matchInChildList(treeMatchingTask, reporter, fm, newParent  );
        }

    }

    public void setChecksum(Memo<String,Content> checksum) {
        this.checksum = checksum;
    }
}

