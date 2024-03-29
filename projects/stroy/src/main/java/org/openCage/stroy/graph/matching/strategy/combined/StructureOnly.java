package org.openCage.stroy.graph.matching.strategy.combined;

import com.google.inject.Inject;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.DuplicateMatching;
import org.openCage.stroy.graph.matching.strategy.HierarchicalDirMatching;
import org.openCage.stroy.graph.matching.strategy.HistoricalMatching;
import org.openCage.stroy.graph.matching.strategy.IdenticalLeafMatchStrategy;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.graph.matching.strategy.NameOnly;
import org.openCage.stroy.graph.matching.strategy.Reporter;
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

public class StructureOnly implements MatchStrategy {

    private final MatchStrategy identicalLeafMatcher =
            new IdenticalLeafMatchStrategy();
    private final MatchStrategy hirDirMatcher =
            new HierarchicalDirMatching();
    private final MatchStrategy dupMatcher =
            new DuplicateMatching();
    private final MatchStrategy nameOnly =
            new NameOnly();
    private final MatchStrategy historyMatcher;

    @Inject
    public StructureOnly( final TreeLeafNodeFuzzyLeafDistance fuzzyLeafDistance ) {
        historyMatcher = new HistoricalMatching( fuzzyLeafDistance );
    }

    public void match( TreeMatchingTask task, Reporter reporter) {
        task.shortStatus();

        Log.info(  "Simple dir matching" );
        nameOnly.match(task, reporter);
        task.shortStatus();

        Log.info( "matching identical leafs" );
        identicalLeafMatcher.match(task, reporter);
        task.shortStatus();

//        Log.info(  "Simple dir matching" );
//        nameOnly.match(task);
//        task.shortStatus();

        Log.info(  "dupplicates" );
        dupMatcher.match(task, reporter);
        task.shortStatus();

        Log.info(  "hierarchy dir" );
        hirDirMatcher.match(task, reporter);
        task.shortStatus();

        Log.info(  "history" );
        historyMatcher.match(task, reporter);
        task.shortStatus();

        Log.info(  "hierarchy dir" );
        hirDirMatcher.match(task, reporter);
        task.shortStatus();

//        Log.info(   "" );
        //To change body of implemented methods use File | Settings | File Templates.

        // TODO
        //match radically changed files with same name and parent

        //treeMatchingTask.state();
    }
}

