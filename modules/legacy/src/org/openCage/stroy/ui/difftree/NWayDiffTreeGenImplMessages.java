package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.graph.matching.TreeMatchingTask;
import com.google.inject.Inject;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class NWayDiffTreeGenImplMessages implements NWayDiffPaneGenerator {

    private final ShowChangeTreeCellRenderer showChangeTreeCellRenderer;

    @Inject
    public NWayDiffTreeGenImplMessages( final ShowChangeTreeCellRenderer showChangeTreeCellRenderer ) {
        this.showChangeTreeCellRenderer  = showChangeTreeCellRenderer;
    }


    public NWayDiffPane getDiffPane(  final List<TreeMatchingTask> tasks,
                                      final List<DefaultMutableTreeNode>        roots ) {
        return new NWayDiffPaneMessages( tasks, roots, showChangeTreeCellRenderer );
    }
}
