package org.openCage.stroy.graph;

import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.content.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.diff.ContentDiff;

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


public class DiffReporter {

    public static <T extends Content> boolean isMoved( TreeMatchingTask task, LindenNode node ) {
        if ( !task.isMatched( node )) {
            return false;
        }

        // root?
        if ( node.getParent() == null ) {
            return false;
        }


        LindenNode parentMatch = task.getMatch( node.getParent() );
        LindenDirNode matchParent = task.getMatch( node ).getParent();

        return parentMatch != matchParent;
    }

    public static <T extends Content> boolean isRenamed(TreeMatchingTask task, LindenNode node) {
        if ( !task.isMatched( node )) {
            return false;
        }


        return !node.getContent().getName().equals( task.getMatch( node ).getContent().getName());
    }

    public static <T extends Content> ContentDiff isContentChanged(TreeMatchingTask task, LindenNode node) {
        if ( !task.isMatched( node )) {
            return null;
        }

        if ( !node.isLeaf() ) {
            return task.getDirs().getDifference( (LindenDirNode)node );
        }

        return task.getLeaves().getDifference( node );
    }

    public static <T extends Content> ChangeVector getChangeVector( TreeMatchingTask task, LindenNode node) {
        return null;
    }
}
