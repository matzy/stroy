package org.openCage.lindwurm;

import org.openCage.lindwurm.content.Content;
import org.openCage.lindwurm.content.ReducedContent;

import java.util.*;

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

public class SimpleTreeNode implements LindenDirNode {
    private Content              content;
    private Collection<LindenNode> kids;
    private LindenDirNode parent;



    public SimpleTreeNode( Content t ) {
        this.content = t;
    }

    public SimpleTreeNode( Content t, Collection<LindenNode> kids ) {
        this.content = t;
        this.kids = kids;

        for ( LindenNode kid : kids ) {
            addChild(kid);
        }
    }

    public SimpleTreeNode( Content t, LindenNode... kids ) {
        this.content = t;
        this.kids    = Arrays.asList(  kids );

        for ( LindenNode kid : kids ) {
            addChild(kid);
        }
    }


    public Collection<LindenNode> getChildren() {
        return kids;
    }

    public void removeChild(LindenNode child) {
        throw new Error("impl me");
    }

    @Override
    public void addChild(LindenNode child) {
        ((SimpleTreeNode)child).parent = this;
        kids.add( child );
    }

    public boolean isLeaf() {
        // note: null not empty
        // an empty kids  list means could have kids
        return kids == null;
    }

    public void setParent(LindenDirNode parent) {
        this.parent = parent;
    }

    public LindenDirNode getParent() {
        return parent;
    }

    public Content getContent() {
        return content;
    }

    @Override
    public LindenDirNode dir() {
        if ( isLeaf() ) {
            throw new IllegalArgumentException( "not a dir" );
        }
        return this;
    }

    public String toString() {
        if ( isLeaf() ) {
            return "leaf<" + content + ">";
        }

        return "dir<" + content + " (" + kids.size() + ")>";
    }

    public static LindenDirNode makeDir( Content content ) {
        return new SimpleTreeNode(content, new ArrayList<LindenNode>());
    }
    public static LindenDirNode makeDir( String name ) {
        // TODO type class
        return new SimpleTreeNode(new ReducedContent(name, "dir"), new ArrayList<LindenNode>());
    }

    @Override
    public Iterator<LindenNode> iterator() {
        return new NodeIterator( this );
    }

//    private class NodeIter implements Iterator<LindenNode> {
//
//        private LindenNode ptr;
//
//        public NodeIter(SimpleTreeNode root ) {
//            ptr = root;
//        }
//
//        public boolean hasNext() {
//            return !Null.is( ptr );
//        }
//
//        public LindenNode next() {
//            LindenNode oldNode = ptr;
//
//            if ( !ptr.isLeaf() ) {
//                Collection<LindenNode> childs = ptr.dir().getChildren();
//
//                if ( childs != null && childs.size() > 0 ) {
//                    ptr = childs.iterator().next();
//                    return oldNode;
//                }
//            }
//
//
//            LindenDirNode parent = ptr.getParent();
//
//            while ( parent != null ) {
//
//                ptr = nextSibling( parent, ptr);
//
//                if ( ptr != null ) {
//                    return oldNode;
//                }
//
//                ptr = parent;
//                parent = parent.getParent();
//            }
//
//            ptr = null;
//
//            return oldNode;
//        }
//
//        public void remove() {
//            throw new Error( "not impl" );
//        }
//
//        private LindenNode nextSibling( LindenDirNode parent, LindenNode node ) {
//
//            boolean found = false;
//            for ( LindenNode  child : parent.getChildren() ) {
//                if ( found ) {
//                    return child;
//                }
//
//                if ( child == node ) {
//                    found = true;
//                }
//            }
//
//            assert( found );
//            return null;
//        }
//    }

}
