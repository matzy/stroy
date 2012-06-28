package org.openCage.stroy.graph.node;

import java.util.Collection;
import java.util.Arrays;

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
