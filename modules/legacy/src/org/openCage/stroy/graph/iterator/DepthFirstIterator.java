package org.openCage.stroy.graph.iterator;

import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;

import java.util.Iterator;
import java.util.Collection;

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

public class DepthFirstIterator<T> implements Iterator<TreeNode<T>> {

    private TreeNode<T> node;


    public DepthFirstIterator(TreeNode<T> node ) {
        this.node = node;
    }

    public boolean hasNext() {
        return node != null;
    }

    public TreeNode<T> next() {
        TreeNode<T> oldNode = node;

        if ( !node.isLeaf() ) {
            Collection<TreeNode<T>> childs = ((TreeDirNode<T>)node).getChildren();

            if ( childs != null && childs.size() > 0 ) {
                node = childs.iterator().next();
                return oldNode;
            }
        }


        TreeDirNode<T> parent = node.getParent();

        while ( parent != null ) {

            node  = nextSibling( parent, node );

            if ( node != null ) {
                return oldNode;
            }

            node   = parent;
            parent = parent.getParent();
        }

        node = null;

        return oldNode;
    }

    public void remove() {
        throw new Error( "not impl" );
    }

    private TreeNode<T> nextSibling( TreeDirNode<T> parent, TreeNode<T> node ) {

        boolean found = false;
        for ( TreeNode<T> child : parent.getChildren() ) {
            if ( found ) {
                return child;
            }

            if ( child == node ) {
                found = true;
            }
        }

        assert( found );
        return null;
    }
}