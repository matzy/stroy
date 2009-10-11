package org.openCage.stroy.ui.popup;

import org.junit.Test;
import org.openCage.lang.protocol.tuple.T2;
import org.openCage.stroy.graph.node.SimpleContentTreeBuilder;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.graph.node.TreeNodeUtils;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutralBuilder;
import org.openCage.stroy.graph.matching.strategy.StandardMatching;
import org.openCage.stroy.graph.matching.strategy.NullReporter;

import static org.junit.Assert.*;

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
public class DiffPopupTest {

    /**
     * bug id: 228
     */
    @Test
    public void testLeftRight_right() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode treeLeft = b.d( "left", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode treeRight = b.d( "right", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));

        TreeMatchingTask task =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );

        new StandardMatching().match( task, new NullReporter() );

        DiffPopup popup =
                new DiffPopup( task, null );

        T2<TreeNode, TreeNode> lr =
                popup.getLeftAndRightNode( TreeNodeUtils.getNode( task.getRightRoot() ));

        assertEquals( TreeNodeUtils.getNode( task.getLeftRoot()),
                      lr.i0 );
        assertEquals( TreeNodeUtils.getNode( task.getRightRoot()),
                      lr.i1 );
    }

    @Test
    public void testLeftRight_left() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode treeLeft = b.d( "left", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode treeRight = b.d( "right", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));

        TreeMatchingTask task =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );

        new StandardMatching().match( task, new NullReporter() );

        DiffPopup popup =
                new DiffPopup( null, task );

        T2<TreeNode, TreeNode> lr =
                popup.getLeftAndRightNode( TreeNodeUtils.getNode( task.getLeftRoot() ));

        assertEquals( TreeNodeUtils.getNode( task.getLeftRoot()),
                      lr.i0 );
        assertEquals( TreeNodeUtils.getNode( task.getRightRoot()),
                      lr.i1 );
    }
}
