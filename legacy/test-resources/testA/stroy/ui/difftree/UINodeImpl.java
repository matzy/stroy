package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.difftree.ChangeNumbers;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.stroy.ui.ChangeVector;


/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class UINodeImpl implements UINode {

    private final TreeNode<FileContent> node;
    private final TreeMatchingTask<FileContent> matching1;
    private final TreeMatchingTask<FileContent> matching2;

    private ChangeNumbers cn;

    public UINodeImpl( TreeNode<FileContent> node, TreeMatchingTask<FileContent> matching1) {
        this.node = node;
        this.matching1 = matching1;
        this.matching2 = null;
    }

    public UINodeImpl( TreeNode<FileContent> node, TreeMatchingTask<FileContent> matching1, TreeMatchingTask<FileContent> matching2 ) {
        this.node = node;
        this.matching1 = matching1;
        this.matching2 = matching2;
    }

    public ChangeVector getChangeVector1() {
        return matching1.getChangeVector( node );
    }

    public ChangeVector getChangeVector2() {

        if ( matching2 == null ) {
            return null;
        }

        return matching2.getChangeVector( node );
    }

    public TreeNode<FileContent> get() {
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


    public String toString() {

        if ( !node.isLeaf() && cn != null && cn.content + cn.structure + cn.only > 0 ) {
//            return node.getContent().getName() + "     c" + cn.content + " >" + cn.structure + " +" + cn.only;
            return node.getContent().getName() + " +";
        }

        return node.getContent().getName();
    }
}
