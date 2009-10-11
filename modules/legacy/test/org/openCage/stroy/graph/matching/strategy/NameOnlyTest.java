package org.openCage.stroy.graph.matching.strategy;

import junit.framework.TestCase;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutralBuilder;
import org.openCage.stroy.graph.node.SimpleContentTreeBuilder;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.graph.node.TreeNodeUtils;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.content.ReducedContent;
import org.openCage.util.ui.TreeUtils;

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

public class NameOnlyTest extends StrategyTestsWorker implements StrategyTests {

    public void testSimpleIdentical() {
        strategyTestSimpleIdentical(
                new NameOnly(),
                3,0,3,0, 2,0,2,0);
    }

    public void testRootMatch() {
        strategyTestRootMatch( new NameOnly());
    }


    public void testMoved() {
        strategyTestMoved(
                new NameOnly(),
                1,2,1,2, 1,1,1,2);
    }

    public void testIgnoreChecksum() {
        strategyTestIgnoreChecksum(
                new NameOnly(),
                3,0,3,0, 2,0,2,0);
    }

//        assertEquals( 1.0, task.getLeaves().getMatchQuality( (TreeNode)node ));
//    }
}
