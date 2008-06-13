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
