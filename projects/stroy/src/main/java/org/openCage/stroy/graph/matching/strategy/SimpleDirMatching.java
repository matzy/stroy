package org.openCage.stroy.graph.matching.strategy;

import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
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
 * match dirs based on name and same path
 */
public class SimpleDirMatching implements MatchStrategy {

    public void match(TreeMatchingTask treeMatchingTask, Reporter reporter) {
        Log.fine( "match Simple dirs" );

        if ( !treeMatchingTask.isMatched( treeMatchingTask.getLeftRoot())) {
            treeMatchingTask.getDirs().match( treeMatchingTask.getLeftRoot(),
                                              treeMatchingTask.getRightRoot(), 1.0 );
        }

        matchInChildList(treeMatchingTask, reporter, treeMatchingTask.getDirs().getLeftRoot(), treeMatchingTask.getDirs().getRightRoot());
    }


    public void matchInChildList( TreeMatchingTask treeMatchingTask, Reporter reporter, LindenDirNode fromDir, LindenDirNode toParent ) {

        if ( ! treeMatchingTask.isMatched( fromDir )  ) {

            String name = fromDir.getContent().getName();

            if ( toParent == null ) {
                throw new IllegalStateException( "prog error" );
            }

            reporter.detail( Message.get( "testing"), fromDir.toString() );

            for ( LindenNode tgtKid : toParent.getChildren() ) {

                if ( !tgtKid.isLeaf() &&
                     !treeMatchingTask.isMatched( tgtKid ) &&
                     tgtKid.getContent().getName().equals( name )) {

                    treeMatchingTask.getDirs().match( fromDir, (LindenDirNode)tgtKid, 1.0 );
                    break;
                }
            }
            
        }

        LindenDirNode newParent = treeMatchingTask.getDirs().getMatch(fromDir);

        if ( newParent == null ) {
            return;
        }

        for ( LindenNode fm : fromDir.getChildren() ) {

            if ( !fm.isLeaf() ) {
                matchInChildList( treeMatchingTask, reporter, (LindenDirNode)fm, newParent );
            }
        }

    }

}
