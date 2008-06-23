package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.diff.ContentDiff;
import org.openCage.stroy.content.Content;

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
 * UINode for a node without TreeNode representation
 * this is a placeholder for deleted files on the other side
 */
public class GhostNode<T extends Content> implements UINode<T> {

//    private final TreeNode<T>         node;
    private final TreeMatchingTask<T> taskLeft;
    private final TreeMatchingTask<T> taskRight;

    private ChangeNumbers cn;

    private ChangeVector cvLeft;
    private ChangeVector cvRight;
    public static final String GHOST_TAG = ":";

    private String name;

    // NEXT just use the name ?


    public GhostNode( TreeNode<T>         node,
                      TreeMatchingTask<T> task1,
                      TreeMatchingTask<T> task2,
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

    public TreeNode get() {
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

    public String toString() {
        return GHOST_TAG + name;// node.getContent().getName();
    }

}
