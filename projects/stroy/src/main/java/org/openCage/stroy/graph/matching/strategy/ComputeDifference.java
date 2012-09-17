package org.openCage.stroy.graph.matching.strategy;

import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.diff.ContentDiff;
import org.openCage.util.checksum.Checksummer;

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

public class ComputeDifference  implements MatchStrategy {

    private final Checksummer checksum;

    public ComputeDifference( Checksummer checksum) {
        this.checksum = checksum;
    }

    public void match(TreeMatchingTask treeMatchingTask, Reporter reporter) {

        reporter.title( Message.get( "Strategy.DifferenceCalculation" ));

        leaves(treeMatchingTask, reporter);

        for ( LindenDirNode left : treeMatchingTask.getDirs().getMatchedLeft() ) {
            boolean change = false;
            for ( LindenNode child : left.getChildren() ) {
                if ( !treeMatchingTask.isMatched( child )) {
                    change = true;
                    break;
                }
            }

            if ( !change ) {
                LindenDirNode right = (LindenDirNode)treeMatchingTask.getMatch( left );

                for ( LindenNode child : right.getChildren() ) {
                    if ( !treeMatchingTask.isMatched( child )) {
                        change = true;
                        break;
                    }
                }
            }

            if ( change ) {
                treeMatchingTask.getDirs().setDifference( left, ContentDiff.different );
            } else {
                treeMatchingTask.getDirs().setDifference( left, ContentDiff.same );
            }
        }
    }

    private void leaves(TreeMatchingTask treeMatchingTask, Reporter reporter) {
        for (LindenNode left : treeMatchingTask.getLeaves().getMatchedLeft()) {

            if ( treeMatchingTask.getLeaves().getDifference( left ).equals( ContentDiff.unknown )) {
                reporter.detail( Message.get( "Progress.checking" ), left.toString() );
                String checksumLeft = checksum.get(left.getContent());

                LindenNode right = treeMatchingTask.getMatch( left );
                reporter.detail( Message.get( "Progress.checking" ), right.toString() );
                String checksumRight = checksum.get(right.getContent());

                if ( checksumLeft.equals( checksumRight )) {
                    treeMatchingTask.getLeaves().setDifference( left, ContentDiff.same );
                } else {
                    treeMatchingTask.getLeaves().setDifference( left, ContentDiff.different );
                }
            }
        }
    }

}
