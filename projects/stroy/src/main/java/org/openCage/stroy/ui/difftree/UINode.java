package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.ui.difftree.ChangeNumbers;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.diff.ContentDiff;

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


