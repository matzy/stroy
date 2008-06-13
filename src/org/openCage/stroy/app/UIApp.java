package org.openCage.stroy.app;

import org.openCage.stroy.ui.difftree.NWayDiffPane;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.content.Content;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.*;
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

/**
 * The class to hold the data in the ui case
 * No logic here
 */
public class UIApp<T extends Content> {

    private NWayDiffPane                        diffPane;
    private List<DefaultMutableTreeNode>        uiRoots;
    private Tasks<T>                            tasks;
//    private JFrame                              

    public UIApp( NWayDiffPane diffPane, List<DefaultMutableTreeNode> uiRoots, Tasks<T> tasks ) {
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

    public List<TreeMatchingTask<T>> getTasks() {
        return tasks.getTasks();
    }

    public List<TreeDirNode<T>> getTreeRoots() {
        return tasks.getRoots();
    }
}
