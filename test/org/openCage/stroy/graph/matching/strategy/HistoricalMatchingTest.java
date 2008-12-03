package org.openCage.stroy.graph.matching.strategy;

import org.openCage.stroy.content.ReducedContent;
import org.openCage.stroy.RuntimeModule;
import org.openCage.stroy.graph.matching.TreeLeafNodeFuzzyLeafDistance;
import com.google.inject.Injector;
import com.google.inject.Guice;

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

public class HistoricalMatchingTest extends StrategyTestsWorker implements StrategyTests {

    private MatchStrategy<ReducedContent> strategy;

    protected void setUp() throws Exception {
        super.setUp();

        Injector injector = Guice.createInjector( new RuntimeModule() );

        TreeLeafNodeFuzzyLeafDistance<ReducedContent> dist = injector.getInstance( TreeLeafNodeFuzzyLeafDistance.class );
        strategy = new HistoricalMatching<ReducedContent>( dist);

    }

    public void testSimpleIdentical() {
        strategyTestSimpleIdentical(
                strategy,
                3,0,3,0, 1,1,1,1);
    }

    public void testRootMatch() {
        strategyTestRootMatch(
                strategy );
    }


    public void testMoved() {
        strategyTestMoved(
                strategy,
                3,0,3,0, 1,1,1,2);
    }

    public void testIgnoreChecksum() {
        strategyTestIgnoreChecksum(
                strategy,
                3,0,3,0, 1,1,1,1);
    }


//
//        TreeNode<ReducedContent> node = TreeNodeUtils.getNode( task.getLeftRoot(), "a" );
//        assertTrue( 0.9 > task.getLeaves().getMatchQuality( (TreeLeafNode<ReducedContent>)node ));
//    }

}
