package org.openCage.lindwurm;


import org.openCage.kleinod.type.Null;

import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

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

public class TreeNodes {

    public static  List<LindenNode> toList( LindenNode root ) {
        List<LindenNode> list = new ArrayList<LindenNode>();

        addNodes( list, root );

        return list;
    }

    private static  void addNodes(List<LindenNode> list, LindenNode node) {
        list.add( node );

        if ( node.isLeaf() ) {
            return;
        }

        for ( LindenNode child : ((LindenDirNode)node).getChildren() ) {
            addNodes( list, child );
        }
    }


    public static LindenNode getNode( LindenNode root, String ... path ) {

        for ( String name : path ) {

            if ( root.isLeaf() ) {
                throw new IllegalArgumentException( "not a dir" );
            }
            LindenDirNode dir = (LindenDirNode)root;

            boolean found = false;
            for ( LindenNode child : dir.getChildren() ) {
                if ( child.getContent().getName().equals( name )) {
                    root = child;
                    found = true;
                    break;
                }
            }

            if ( !found ) {
                throw new IllegalArgumentException( "path not found: " + name );
            }
        }

        return root;

    }

    /**
     * Returns the path of names from the root to the node
     * @param node
     * @return
     */
    public static List<String> getNamePath( LindenNode node ) {
        List<String> path = new ArrayList<String>();

        do {
            path.add(0, node.getContent().getName() );
            node = node.getParent();
        } while ( !Null.is(node));

        return path;
    }

    public static String getStringPath( LindenNode node ) {

        String ret = "";

        for ( String name : getNamePath(  node ) ) {
            ret += "/" + name;
        }

        return ret;
    }


    /**
     * Return the root of the tree.
     * @param nd A node.
     * @return The root of the tree.
     */
    public static LindenNode getRoot( LindenNode nd ) {
        LindenNode nodeRoot = nd;

        while ( !Null.is(nodeRoot.getParent())) {
            nodeRoot = nodeRoot.getParent();
        }

        return nodeRoot;
    }

    public static TreePath getTreePath( LindenNode nd ) {
        return getTreePath( getNamePath(nd));
    }

    public static TreePath getTreePath( List<String> nameList ) {
        TreePath ret = null;
        for ( String name : nameList ) {
            if ( ret == null ) {
                ret = new TreePath(name);
            } else {
                ret = ret.pathByAddingChild(name);
            }
        }

        return ret;
    }





}
