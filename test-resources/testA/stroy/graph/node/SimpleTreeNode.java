package org.openCage.stroy.graph.node;

import java.util.Collection;
import java.util.Arrays;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
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

public class SimpleTreeNode<T> implements TreeDirNode<T>, TreeLeafNode<T> {
    private T                       content;
    private Collection<TreeNode<T>> kids;
    private TreeDirNode<T>          parent;



    public SimpleTreeNode( T t ) {
        this.content = t;
    }

    public SimpleTreeNode( T t, Collection<TreeNode<T>> kids ) {
        this.content = t;
        this.kids = kids;

        for ( TreeNode<T> kid : kids ) {
            kid.setParent( this );
        }
    }

    public SimpleTreeNode( T t, TreeNode<T> ... kids ) {
        this.content = t;
        this.kids    = Arrays.asList(  kids );

        for ( TreeNode<T> kid : kids ) {
            kid.setParent( this );
        }
    }


    public Collection<TreeNode<T>> getChildren() {
        return (Collection)kids;
    }

    public void removeChild(TreeNode<T> child) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isLeaf() {
        return kids == null;
    }

    public void setParent(TreeDirNode<T> parent) {
        this.parent = parent;
    }

    public TreeDirNode<T> getParent() {
        return parent;
    }

    public T getContent() {
        return content;
    }

    public String toString() {
        if ( isLeaf() ) {
            return "leaf<" + content + ">";
        }

        return "dir<" + content + " (" + kids.size() + ")>";
    }
}
