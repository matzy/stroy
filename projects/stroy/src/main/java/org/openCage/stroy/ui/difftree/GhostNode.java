package org.openCage.stroy.ui.difftree;

import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.diff.ContentDiff;
import org.openCage.lindwurm.content.Content;

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

/**
 * UINode for a node without LindenNode representation
 * this is a placeholder for deleted files on the other side
 */
public class GhostNode<T extends Content> implements UINode {

//    private final LindenNode         node;
    private final TreeMatchingTask taskLeft;
    private final TreeMatchingTask taskRight;

    private ChangeNumbers cn;

    private ChangeVector cvLeft;
    private ChangeVector cvRight;
    public static final String GHOST_TAG = ":";

    private String name;


    public GhostNode( LindenNode node,
                      TreeMatchingTask task1,
                      TreeMatchingTask task2,
                      boolean left,
                      boolean right ) {
//        this.node       = node;
        this.taskLeft   = task1;
        this.taskRight  = task2;

        cvLeft         = new ChangeVector();
        cvLeft.ghost   = left;
        cvLeft.content = ContentDiff.same;

        cvRight         = new ChangeVector();
        cvRight.ghost   = right;
        cvRight.content = ContentDiff.same;

        name = node.getContent().getName();
    }


    public ChangeVector getChangeVectorLeft() {
        return cvLeft;
    }

    public ChangeVector getChangeVectorRight() {
        return cvRight;
    }

    public LindenNode get() {
        return null;
    }

    public void setChangeNumbers(ChangeNumbers cn) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getToolTip() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isMovedLeft() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isRenamedLeft() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ContentDiff getContentChangedLeft() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isOnlyLeft() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isMovedRight() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isRenamedRight() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ContentDiff getContentChangedRight() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isOnlyRight() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addHidden(DefaultMutableTreeNode dfmtn) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<DefaultMutableTreeNode> getHidden() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String toString() {
        return GHOST_TAG + name;// node.getContent().getName();
    }

}
