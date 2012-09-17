package org.openCage.stroy.graph.matching;

import org.openCage.lindwurm.LindenDirNode;
import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.graph.SameContent;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.ui.ChangeVector;

import java.util.Collection;


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

public interface TreeMatchingTask {

    public void addDup(final SameContent sh);

    public MatchingTask<LindenNode>  getLeaves();
    public MatchingTask<LindenDirNode>   getDirs();

    public boolean     isMatched(  final LindenNode obj );
    public LindenNode getMatch(   final LindenNode obj );
    public double      getMatchQuality( final LindenNode obj );

    public Collection<SameContent> getDuplicates();

    public void shortStatus();

    public void status();

    public int                            getLeftLeaveCount();
    public int getLeftDirCount();
    public int getRightLeaveCount();
    public int getRightDirCount();

    public Collection<LindenNode>    getModifiedLeaves();
    public Collection<LindenNode>    getRenamedLeaves();
    public Collection<LindenNode>    getMovedLeaves();

    public Collection<LindenDirNode>     getRenamedDirs();
    public Collection<LindenDirNode>     getMovedDirs();

    public Collection<LindenNode>     getComplexModifiedLeaves();
    
    

    public Collection<LindenNode> getUnmatchedLeftFiles();
    public Collection<LindenDirNode> getUnmatchedLeftDirs();
    public Collection<LindenNode> getUnmatchedRightFiles();
    public Collection<LindenDirNode> getUnmatchedRightDirs();

    public LindenDirNode getLeftRoot();
    public LindenDirNode getRightRoot();

    public boolean isContentChanged( LindenNode obj );

    public ChangeVector getChangeVector( LindenNode obj );

    public void remove(LindenNode node);

    void merge();

    public MatchingTask<LindenDirNode> getDirTask();

    public MatchingTask<LindenNode> getFileTask();

}
