package org.openCage.stroy.graph.matching.strategy;

import junit.framework.TestCase;
import org.openCage.stroy.content.ReducedContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutralBuilder;
import org.openCage.stroy.graph.node.TreeNodeUtils;
import org.openCage.stroy.graph.node.SimpleContentTreeBuilder;
import org.openCage.stroy.graph.node.TreeNode;

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
public abstract class StrategyTestsWorker extends TestCase {

    public void strategyTestSimpleIdentical(MatchStrategy<ReducedContent> strategy,
                                            int lml,
                                            int lul,
                                            int lmr,
                                            int lur,
                                            int dml,
                                            int dul,
                                            int dmr,
                                            int dur) {

        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode<ReducedContent> treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode<ReducedContent> treeRight = b.d( "f", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));

        TreeMatchingTask<ReducedContent> task =
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

    public void strategyTestRootMatch(MatchStrategy<ReducedContent> strategy ) {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode<ReducedContent> treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode<ReducedContent> treeRight = b.d( "uuu" );

        TreeMatchingTask<ReducedContent> task = TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );
        strategy.match( task, new NullReporter() );

        assertTrue( task.isMatched( TreeNodeUtils.getNode( task.getLeftRoot())));

        assertEquals( "uuu",
                      task.getMatch( TreeNodeUtils.getNode( task.getLeftRoot())).getContent().getName() );
    }

    public void strategyTestMoved(MatchStrategy<ReducedContent> strategy,
                                            int lml,
                                            int lul,
                                            int lmr,
                                            int lur,
                                            int dml,
                                            int dul,
                                            int dmr,
                                            int dur) {

        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode<ReducedContent> treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode<ReducedContent> treeRight = b.d( "f", b.l( "a"),
                                                       b.d( "z",
                                                            b.d( "g", b.l("b"),
                                                                      b.l("c"))));


        TreeMatchingTask<ReducedContent> task =
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

    public void strategyTestIgnoreChecksum(MatchStrategy<ReducedContent> strategy,
                                            int lml,
                                            int lul,
                                            int lmr,
                                            int lur,
                                            int dml,
                                            int dul,
                                            int dmr,
                                            int dur) {

        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode<ReducedContent> treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode<ReducedContent> treeRight = b.d( "f", b.l( "a", "a12"),
                                                       b.d( "g", b.l("b", "b23"),
                                                                 b.l("c", "c7")));

        TreeMatchingTask<ReducedContent> task =
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

//        assertEquals( 1.0, task.getLeaves().getMatchQuality( (TreeLeafNode<ReducedContent>)node ));

    }
}
