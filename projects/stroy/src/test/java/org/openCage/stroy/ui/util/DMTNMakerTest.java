package org.openCage.stroy.ui.util;

import junit.framework.TestCase;
import org.openCage.stroy.graph.node.SimpleContentTreeBuilder;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutralBuilder;
import org.openCage.stroy.content.ReducedContent;
import org.openCage.stroy.ui.difftree.UINode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

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

public class DMTNMakerTest extends TestCase {

    private TreeMatchingTask<ReducedContent> taskLeft;
    private TreeMatchingTask<ReducedContent> taskRight;


    protected void setUp() throws Exception {
        super.setUp();

        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode<ReducedContent> treeOne = b.d( "f", b.l( "a"),
                                                     b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode<ReducedContent> treeTwo = b.d( "f2", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                 b.l("c")));
        TreeNode<ReducedContent> treeThree = b.d( "f3", b.l( "a"),
                                                        b.d( "g", b.l("b"),
                                                                 b.l("c")));

        taskLeft = TreeMatchingTaskNeutralBuilder.build( treeOne, treeTwo );
        taskRight = TreeMatchingTaskNeutralBuilder.build( treeTwo, treeThree );
    }

    public void testCount() {
        assertEquals( 2, DMTNMaker.makeDFTNs( taskLeft, null ).size());
        assertEquals( 3, DMTNMaker.makeDFTNs( taskLeft, taskRight ).size());
    }

    public void testRoots() {
        List<DefaultMutableTreeNode> roots = DMTNMaker.makeDFTNs(taskLeft, null );

        assertEquals( roots.get(0), roots.get(0).getRoot() );
        assertEquals( roots.get(1), roots.get(1).getRoot() );

        assertEquals( "f", ((UINode<ReducedContent>)roots.get(0).getUserObject()).get().getContent().getName() );
        assertEquals( "f2", ((UINode<ReducedContent>)roots.get(1).getUserObject()).get().getContent().getName() );
    }

    public void testExpectation() {

        try {
            DMTNMaker.makeDFTNs( null, taskLeft );
            fail( "task1 should not be null" );
        } catch ( IllegalArgumentException exp )
        {}




        try {
            DMTNMaker.makeDFTNs(taskLeft, taskLeft );
            fail( "roots not checked" );
        } catch ( IllegalArgumentException exp )
        {}

        DMTNMaker.makeDFTNs( taskLeft, taskRight );
    }


    public void testSameTree() {
        DefaultMutableTreeNode      uiRoot = DMTNMaker.makeDFTNs( taskLeft, null ).get(0);
        TreeDirNode<ReducedContent> mRoot  = taskLeft.getLeftRoot();

        for ( TreeNode<ReducedContent> child : mRoot.getChildren() ) {
//            NodeToNode.nodeToPath( uiRoot, mRoot, child );
        }
    }
}
