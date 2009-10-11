package org.openCage.stroy.graph.node;

import org.openCage.vfs.protocol.TreeNode;
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

public abstract class ContentTreeNodeBaseImpl implements TreeNode {
    private final Content         content;
    private TreeNode  parent;

    public ContentTreeNodeBaseImpl( Content file, boolean generateId ) {
        this.content = file;
    }

    public Content getContent() {
        return content;
    }

    public void setParent( TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }


    public String getName() {
        return content.getName();
    }


    public String toString() {
        String ret = content.getName();
        if ( parent != null ) {
            ret = parent.toString() + ret;
        }

        if ( isLeaf() ) {
            return ret;
        }

        return ret + "/";
    }
}
