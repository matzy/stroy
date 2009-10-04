package org.openCage.stroy.app;

import org.openCage.stroy.ui.difftree.NWayDiffPane;
import org.openCage.stroy.graph.matching.TreeMatchingTask;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import org.openCage.stroy.graph.node.TreeNode;

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

/**
 * The class to hold the data in the ui case
 * No logic here
 */
public class UIApp {

    private NWayDiffPane                        diffPane;
    private List<DefaultMutableTreeNode>        uiRoots;
    private Tasks                            tasks;
//    private JFrame                              

    public UIApp( NWayDiffPane diffPane, List<DefaultMutableTreeNode> uiRoots, Tasks tasks ) {
        this.diffPane = diffPane;
        this.uiRoots = uiRoots;
        this.tasks = tasks;
    }

    public NWayDiffPane getDiffPane() {
        return diffPane;
    }

    public List<DefaultMutableTreeNode> getUIRoots() {
        return uiRoots;
    }

    public List<TreeMatchingTask> getTasks() {
        return tasks.getTasks();
    }

    public List<? extends TreeNode> getTreeRoots() {
        return tasks.getRoots();
    }
}
