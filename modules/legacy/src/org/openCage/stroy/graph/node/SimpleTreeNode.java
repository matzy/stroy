package org.openCage.stroy.graph.node;

import org.openCage.vfs.protocol.TreeNode;
import java.util.Collection;
import java.util.Arrays;
import org.openCage.vfs.protocol.Content;

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

public class SimpleTreeNode implements TreeNode {
    private Content               content;
    private Collection<TreeNode>  kids;
    private TreeNode              parent;



    public SimpleTreeNode( Content t ) {
        this.content = t;
    }

    public SimpleTreeNode( Content t, Collection<TreeNode> kids ) {
        this.content = t;
        this.kids = kids;

        for ( TreeNode kid : kids ) {
            kid.setParent( this );
        }
    }

    public SimpleTreeNode( Content t, TreeNode ... kids ) {
        this.content = t;
        this.kids    = Arrays.asList(  kids );

        for ( TreeNode kid : kids ) {
            kid.setParent( this );
        }
    }


    public Collection<TreeNode> getChildren() {
        return (Collection)kids;
    }

    public void removeChild(TreeNode child) {
        // TODO impl
    }

    public boolean isLeaf() {
        return kids == null;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    public Content getContent() {
        return content;
    }

    public String toString() {
        if ( isLeaf() ) {
            return "leaf<" + content + ">";
        }

        return "dir<" + content + " (" + kids.size() + ")>";
    }
}
