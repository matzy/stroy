package org.openCage.stroy.graph.matching.strategy;

import junit.framework.TestCase;
import org.openCage.stroy.content.ReducedContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutralBuilder;
import org.openCage.vfs.protocol.TreeNodeUtils;
import org.openCage.vfs.impl.SimpleContentTreeBuilder;
import org.openCage.vfs.protocol.TreeNode;

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
public abstract class StrategyTestsWorker extends TestCase {

    public void strategyTestSimpleIdentical(MatchStrategy strategy,
                                            int lml,
                                            int lul,
                                            int lmr,
                                            int lur,
                                            int dml,
                                            int dul,
                                            int dmr,
                                            int dur) {

        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode treeRight = b.d( "f", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));

        TreeMatchingTask task =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );


        assertEquals( 0, task.getLeaves().getMatchedLeft().size() );
        assertEquals( 3, task.getLeaves().getUnmatchedLeft().size() );
        assertEquals( 0, task.getLeaves().getMatchedRight().size() );
        assertEquals( 3, task.getLeaves().getUnmatchedRight().size() );

        assertEquals( 1, task.getDirs().getMatchedLeft().size() );
        assertEquals( 1, task.getDirs().getUnmatchedLeft().size() );
        assertEquals( 1, task.getDirs().getMatchedRight().size() );
        assertEquals( 1, task.getDirs().getUnmatchedRight().size() );

        strategy.match( task, new NullReporter() );

        assertEquals( lml, task.getLeaves().getMatchedLeft().size() );
        assertEquals( lul, task.getLeaves().getUnmatchedLeft().size() );
        assertEquals( lmr, task.getLeaves().getMatchedRight().size() );
        assertEquals( lur, task.getLeaves().getUnmatchedRight().size() );

        assertEquals( dml, task.getDirs().getMatchedLeft().size() );
        assertEquals( dul, task.getDirs().getUnmatchedLeft().size() );
        assertEquals( dmr, task.getDirs().getMatchedRight().size() );
        assertEquals( dur, task.getDirs().getUnmatchedRight().size() );
    }

    public void strategyTestRootMatch(MatchStrategy strategy ) {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode treeRight = b.d( "uuu" );

        TreeMatchingTask task = TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );
        strategy.match( task, new NullReporter() );

        assertTrue( task.isMatched( TreeNodeUtils.getNode( task.getLeftRoot())));

        assertEquals( "uuu",
                      task.getMatch( TreeNodeUtils.getNode( task.getLeftRoot())).getContent().getName() );
    }

    public void strategyTestMoved(MatchStrategy strategy,
                                            int lml,
                                            int lul,
                                            int lmr,
                                            int lur,
                                            int dml,
                                            int dul,
                                            int dmr,
                                            int dur) {

        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode treeRight = b.d( "f", b.l( "a"),
                                                       b.d( "z",
                                                            b.d( "g", b.l("b"),
                                                                      b.l("c"))));


        TreeMatchingTask task =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );

        assertEquals( 0, task.getLeaves().getMatchedLeft().size() );
        assertEquals( 3, task.getLeaves().getUnmatchedLeft().size() );
        assertEquals( 0, task.getLeaves().getMatchedRight().size() );
        assertEquals( 3, task.getLeaves().getUnmatchedRight().size() );

        assertEquals( 1, task.getDirs().getMatchedLeft().size() );
        assertEquals( 1, task.getDirs().getUnmatchedLeft().size() );
        assertEquals( 1, task.getDirs().getMatchedRight().size() );
        assertEquals( 2, task.getDirs().getUnmatchedRight().size() );

        strategy.match( task, new NullReporter() );

        assertEquals( lml, task.getLeaves().getMatchedLeft().size() );
        assertEquals( lul, task.getLeaves().getUnmatchedLeft().size() );
        assertEquals( lmr, task.getLeaves().getMatchedRight().size() );
        assertEquals( lur, task.getLeaves().getUnmatchedRight().size() );

        assertEquals( dml, task.getDirs().getMatchedLeft().size() );
        assertEquals( dul, task.getDirs().getUnmatchedLeft().size() );
        assertEquals( dmr, task.getDirs().getMatchedRight().size() );
        assertEquals( dur, task.getDirs().getUnmatchedRight().size() );
    }

    public void strategyTestIgnoreChecksum(MatchStrategy strategy,
                                            int lml,
                                            int lul,
                                            int lmr,
                                            int lur,
                                            int dml,
                                            int dul,
                                            int dmr,
                                            int dur) {

        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode treeRight = b.d( "f", b.l( "a", "a12"),
                                                       b.d( "g", b.l("b", "b23"),
                                                                 b.l("c", "c7")));

        TreeMatchingTask task =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );

        assertEquals( 0, task.getLeaves().getMatchedLeft().size() );
        assertEquals( 3, task.getLeaves().getUnmatchedLeft().size() );
        assertEquals( 0, task.getLeaves().getMatchedRight().size() );
        assertEquals( 3, task.getLeaves().getUnmatchedRight().size() );

        assertEquals( 1, task.getDirs().getMatchedLeft().size() );
        assertEquals( 1, task.getDirs().getUnmatchedLeft().size() );
        assertEquals( 1, task.getDirs().getMatchedRight().size() );
        assertEquals( 1, task.getDirs().getUnmatchedRight().size() );

        strategy.match( task, new NullReporter() );

        assertEquals( lml, task.getLeaves().getMatchedLeft().size() );
        assertEquals( lul, task.getLeaves().getUnmatchedLeft().size() );
        assertEquals( lmr, task.getLeaves().getMatchedRight().size() );
        assertEquals( lur, task.getLeaves().getUnmatchedRight().size() );

        assertEquals( dml, task.getDirs().getMatchedLeft().size() );
        assertEquals( dul, task.getDirs().getUnmatchedLeft().size() );
        assertEquals( dmr, task.getDirs().getMatchedRight().size() );
        assertEquals( dur, task.getDirs().getUnmatchedRight().size() );

//        assertEquals( 1.0, task.getLeaves().getMatchQuality( (TreeNode)node ));

    }
}
