package org.openCage.stroy.ui.difftree;

import org.openCage.lindwurm.LindenNode;
import org.openCage.stroy.diff.ContentDiff;
import org.openCage.stroy.graph.DiffReporter;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.ChangeVector;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
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

public class UINodeImpl implements UINode {

    private final LindenNode node;
    private final TreeMatchingTask taskLeft;
    private final TreeMatchingTask taskRight;

    private ChangeNumbers cn;
    private List<DefaultMutableTreeNode> hidden = new ArrayList<DefaultMutableTreeNode>();

    // TODO change to left, right to display more information?

//    public UINodeImpl( LindenNode node, TreeMatchingTask task1) {
//        this.node  = node;
//        this.taskLeft = task1;
//        this.taskRight = null;
//    }

    public UINodeImpl( LindenNode node,
                       TreeMatchingTask task1,
                       TreeMatchingTask task2 ) {
        this.node       = node;
        this.taskLeft   = task1;
        this.taskRight  = task2;
    }

    public ChangeVector getChangeVectorLeft() {
        ChangeVector cv = new ChangeVector();
        cv.content = getContentChangedLeft();
        cv.name = isRenamedLeft();
        cv.parent = isMovedLeft();
        cv.only = isOnlyLeft();

        return cv;
    }

    public ChangeVector getChangeVectorRight() {

        ChangeVector cv = new ChangeVector();
        cv.content = getContentChangedRight();
        cv.name = isRenamedRight();
        cv.parent = isMovedRight();
        cv.only = isOnlyRight();
        
        return cv;
    }

    public LindenNode get() {
        return node;
    }

    public void setChangeNumbers(ChangeNumbers cn) {
        this.cn = cn;
    }

    public String getToolTip() {

        if ( !node.isLeaf() && cn != null && cn.content + cn.structure + cn.only > 0 ) {
            String str = "<html><h3>directory: " + node.getContent().getName() + "<br>";

            if ( cn.content > 0 ) {
                str += "                    " + cn.content + " content changes<br>";
            }

            if ( cn.structure > 0 ) {
                str += "                    " + cn.structure + " structure changes<br>";
            }

            if ( cn.only > 0 ) {
                str += "                    " + cn.only + " only here<br>";
            }

            str += "</h3></html>";

            return str;
        }

        return "";
    }

    public boolean isMovedLeft() {
        if ( taskLeft == null ) {
            return false;
        }

        return DiffReporter.isMoved( taskLeft, node );
    }

    public boolean isRenamedLeft() {
        if ( taskLeft == null ) {
            return false;
        }

        return DiffReporter.isRenamed( taskLeft, node );
    }

    public ContentDiff getContentChangedLeft() {
        if ( taskLeft == null ) {
            return null;
        }

        return DiffReporter.isContentChanged( taskLeft, node );
    }

    public boolean isOnlyLeft() {
        if ( taskLeft == null ) {
            return false;
        }

        return !taskLeft.isMatched( node );
    }

    public boolean isMovedRight() {
        if ( taskRight == null ) {
            return false;
        }

        return DiffReporter.isMoved( taskRight, node );
    }

    public boolean isRenamedRight() {
        if ( taskRight == null ) {
            return false;
        }

        return DiffReporter.isRenamed( taskRight, node );
    }

    public ContentDiff getContentChangedRight() {
        if ( taskRight == null ) {
            return null;
        }

        return DiffReporter.isContentChanged( taskRight, node );
    }

    public boolean isOnlyRight() {
        if ( taskRight == null ) {
            return false;
        }

        return !taskRight.isMatched( node );
    }

    public void addHidden(DefaultMutableTreeNode dfmtn) {
        hidden.add( dfmtn );
    }

    public List<DefaultMutableTreeNode> getHidden() {
        return hidden;
    }

    // this the namer of the node
    public String toString() {

        if ( !node.isLeaf() && cn != null && cn.content + cn.structure + cn.only > 0 ) {
//            return node.getContent().getName() + "     c" + cn.content + " >" + cn.structure + " +" + cn.only;
            return node.getContent().getName() + " +";
        }

        return node.getContent().getName();
    }

}
