package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.DiffReporter;
import org.openCage.stroy.ui.difftree.ChangeNumbers;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.diff.ContentDiff;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import java.util.ArrayList;


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

public class UINodeImpl<T extends Content> implements UINode<T> {

    private final TreeNode<T>         node;
    private final TreeMatchingTask<T> taskLeft;
    private final TreeMatchingTask<T> taskRight;

    private ChangeNumbers cn;
    private List<DefaultMutableTreeNode> hidden = new ArrayList<DefaultMutableTreeNode>();

    // TODO change to left, right to display more information?

//    public UINodeImpl( TreeNode<T> node, TreeMatchingTask<T> task1) {
//        this.node  = node;
//        this.taskLeft = task1;
//        this.taskRight = null;
//    }

    public UINodeImpl( TreeNode<T>         node,
                       TreeMatchingTask<T> task1,
                       TreeMatchingTask<T> task2 ) {
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

    public TreeNode<T> get() {
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
