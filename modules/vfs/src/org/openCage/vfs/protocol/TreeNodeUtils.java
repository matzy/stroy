package org.openCage.vfs.protocol;

import org.openCage.vfs.protocol.VNode;
import org.openCage.vfs.protocol.Content;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

public class TreeNodeUtils {

    public static <T> List<VNode> toList( VNode root ) {
        List<VNode> list = new ArrayList<VNode>();

        addNodes( list, root );

        return list;
    }

    private static <T> void addNodes(List<VNode> list, VNode node) {
        list.add( node );

        if ( node.isLeaf() ) {
            return;
        }

        for ( VNode child : ((VNode)node).getChildren() ) {
            addNodes( list, child );
        }
    }

    public static <T> VNode down( VNode node ) {

        if ( !node.isLeaf() ) {
            Collection<? extends VNode> childs = ((VNode)node).getChildren();

            if ( childs != null && childs.size() > 0 ) {
                return childs.iterator().next();
            }
        }

        VNode parent = node.getParent();

        while ( parent != null ) {

            node  = nextSibling( parent, node );

            if ( node != null ) {
                return node;
            }

            node   = parent;
            parent = parent.getParent();
        }

        return null;
    }

    private static <T> VNode nextSibling( VNode parent, VNode node ) {

        boolean found = false;
        for ( VNode child : parent.getChildren() ) {
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

    public static <T extends Content> VNode getNode( VNode root, String ... path ) {

        for ( String name : path ) {

            if ( root.isLeaf() ) {
                throw new IllegalArgumentException( "not a dir" );
            }
            VNode dir = (VNode)root;

            boolean found = false;
            for ( VNode child : dir.getChildren() ) {
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
    public static <T  extends Content> List<String> getNamePath( VNode node ) {
        List<String> path = new ArrayList<String>();

        do {
            path.add(0, node.getContent().getName() );
            node = node.getParent();
        } while ( node != null );

        return path;
    }

    public static <T extends Content> String getStringPath( VNode node ) {

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
    public static <T> VNode getRoot( VNode nd ) {
        VNode nodeRoot = nd;

        while ( nodeRoot.getParent() != null ) {
            nodeRoot = nodeRoot.getParent();
        }

        return nodeRoot;
    }





}
