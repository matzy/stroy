package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.ui.difftree.ChangeNumbers;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.Difference;
import org.openCage.stroy.diff.ContentDiff;

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

public interface UINode<T extends Content> {
    public ChangeVector getChangeVectorLeft();
    public ChangeVector getChangeVectorRight();

    public TreeNode<T> get();

    public void        setChangeNumbers(ChangeNumbers cn);

    public String      getToolTip();

    public boolean isMovedLeft();
    public boolean isRenamedLeft();
    public ContentDiff getContentChangedLeft();
    public boolean isOnlyLeft();

    public boolean isMovedRight();
    public boolean isRenamedRight();
    public ContentDiff getContentChangedRight();

    /**
     * The node has no match to the right 
     * @return
     */
    public boolean isOnlyRight();

    public void addHidden( DefaultMutableTreeNode dfmtn );
    public List<DefaultMutableTreeNode>           getHidden();
    

}


