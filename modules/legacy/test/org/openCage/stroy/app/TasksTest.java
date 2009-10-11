package org.openCage.stroy.app;

import junit.framework.TestCase;
import org.openCage.stroy.graph.node.SimpleContentTreeBuilder;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutralBuilder;
import org.openCage.stroy.content.ReducedContent;

import java.util.Arrays;

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

@SuppressWarnings({"HardCodedStringLiteral"})
public class TasksTest extends TestCase {

    public void testRootsSanity() {

        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode treeRight = b.d( "f", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));

        TreeMatchingTask task =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );
        TreeMatchingTask task2 =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );


        try {
            new Tasks( Arrays.asList( task, task2 ));
            fail( "roots don't match, throw expected" );
        } catch ( Exception exp ) {
            // expected
        }
    }

    public void testRootSanityPositiv() {
        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();

        TreeNode treeLeft = b.d( "f", b.l( "a"),
                                                      b.d( "g", b.l("b"),
                                                                b.l("c")));

        TreeNode treeRight = b.d( "f2", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));
        TreeNode tree3 = b.d( "f3", b.l( "a"),
                                                       b.d( "g", b.l("b"),
                                                                 b.l("c")));

        TreeMatchingTask task =
                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );
        TreeMatchingTask task2 =
                TreeMatchingTaskNeutralBuilder.build( treeRight, tree3 );


        new Tasks( Arrays.asList( task, task2 ));

    }


}
