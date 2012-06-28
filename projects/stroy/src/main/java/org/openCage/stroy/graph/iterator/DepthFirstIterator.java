package org.openCage.stroy.graph.iterator;

import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;

import java.util.Iterator;
import java.util.Collection;

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
