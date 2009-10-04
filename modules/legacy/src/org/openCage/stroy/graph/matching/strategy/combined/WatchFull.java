package org.openCage.stroy.graph.matching.strategy.combined;

import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.*;
import org.openCage.util.logging.Log;
import com.google.inject.Inject;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;

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

public class WatchFull implements MatchStrategy {

    private final MatchStrategy identicalLeafMatcher =
            new IdenticalLeafMatchStrategy();
    private final MatchStrategy hirDirMatcher =
            new HierarchicalDirMatching();
    private final MatchStrategy dupMatcher =
            new DuplicateMatching();
    private final MatchStrategy historyMatcher;
    private final MatchStrategy computeDiff =
            new ComputeDifference();

    @Inject
    public WatchFull( final TreeLeafNodeFuzzyLeafDistance fuzzyLeafDistance ) {
        historyMatcher = new HistoricalMatching( fuzzyLeafDistance );
    }

    public void match( TreeMatchingTask task, Reporter reporter) {
        task.shortStatus();

//        Log.info( "diff" );
//        computeDiff.match(task, reporter);
//        task.shortStatus();


        Log.info( "matching identical leafs" );
        identicalLeafMatcher.match(task, reporter);
        task.shortStatus();


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

        Log.info( "diff" );
        computeDiff.match(task, reporter);
        task.shortStatus();
        
//        Log.info(   "" );
        //To change body of implemented methods use File | Settings | File Templates.

        // TODO
        //match radically changed files with same name and parent

        //treeMatchingTask.state();
    }
}

