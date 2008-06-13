package org.openCage.stroy.graph.matching.strategy.combined;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;
import org.openCage.stroy.graph.matching.strategy.*;
import org.openCage.util.logging.Log;
import com.google.inject.Inject;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class FastFirst<T extends Content> implements MatchStrategy<T> {

    private final MatchStrategy<T> identicalLeafMatcher =
            new IdenticalLeafMatchStrategy<T>();
    private final MatchStrategy<T> hirDirMatcher =
            new HierarchicalDirMatching<T>();
    private final MatchStrategy<T> dupMatcher =
            new DuplicateMatching<T>();
    private final MatchStrategy<T> nameOnly =
            new NameOnly<T>();
    private final MatchStrategy<T> historyMatcher;

    @Inject
    public FastFirst( final TreeLeafNodeFuzzyLeafDistance<T> fuzzyLeafDistance ) {
        historyMatcher = new HistoricalMatching<T>( fuzzyLeafDistance );
    }

    public void match( TreeMatchingTask<T> task, Reporter reporter) {
        task.shortStatus();

        Log.info(  "Simple dir matching" );
        nameOnly.match(task, reporter);
        task.shortStatus();

//        Log.info( "matching identical leafs" );
//        identicalLeafMatcher.match(task, reporter);
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

