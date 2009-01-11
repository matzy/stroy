package org.openCage.stroy.graph.matching.strategy.combined;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;
import org.openCage.stroy.graph.matching.strategy.*;
import org.openCage.stroy.dir.FileContent;
import org.openCage.util.logging.Log;
import com.google.inject.Inject;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class ChecksumFirst<T extends Content> implements MatchStrategy<T> {

    private final MatchStrategy<T> identicalLeafMatcher =
            new IdenticalLeafMatchStrategy<T>();
    private final MatchStrategy<T> hirDirMatcher =
            new HierarchicalDirMatching<T>();
    private final MatchStrategy<T> dupMatcher =
            new DuplicateMatching<T>();
    private final MatchStrategy<T> simpleDirMatcher =
            new SimpleDirMatching<T>();
    private final MatchStrategy<T> historyMatcher;

    @Inject
    public ChecksumFirst( final TreeLeafNodeFuzzyLeafDistance<T> fuzzyLeafDistance ) {
        historyMatcher = new HistoricalMatching<T>( fuzzyLeafDistance );
    }

    public void match( TreeMatchingTask<T> task ) {
        task.shortStatus();

        Log.info( "matching identical leafs" );
        identicalLeafMatcher.match(task);
        task.shortStatus();

        Log.info(  "Simple dir matching" );
        simpleDirMatcher.match(task);
        task.shortStatus();

        Log.info(  "dupplicates" );
        dupMatcher.match(task);
        task.shortStatus();

        Log.info(  "hierarchy dir" );
        hirDirMatcher.match(task);
        task.shortStatus();

        Log.info(  "history" );
        historyMatcher.match(task);
        task.shortStatus();

        Log.info(  "hierarchy dir" );
        hirDirMatcher.match(task);
        task.shortStatus();

//        Log.info(   "" );
        //To change body of implemented methods use File | Settings | File Templates.

        // TODO
        //match radically changed files with same name and parent

        //treeMatchingTask.state();
    }
}

