package org.openCage.stroy.graph.node;

import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.content.Content;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

public class TreeNodeUtils {

    public static <T> List<TreeNode<T>> toList( TreeNode<T> root ) {
        List<TreeNode<T>> list = new ArrayList<TreeNode<T>>();

        addNodes( list, root );

        return list;
    }

    private static <T> void addNodes(List<TreeNode<T>> list, TreeNode<T> node) {
        list.add( node );

        if ( node.isLeaf() ) {
            return;
        }

        for ( TreeNode<T> child : ((TreeDirNode<T>)node).getChildren() ) {
            addNodes( list, child );
        }
    }

    public static <T> TreeNode<T> down( TreeNode<T> node ) {

        if ( !node.isLeaf() ) {
            Collection<TreeNode<T>> childs = ((TreeDirNode<T>)node).getChildren();

            if ( childs != null && childs.size() > 0 ) {
                return childs.iterator().next();
            }
        }

        TreeDirNode<T> parent = node.getParent();

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

    private static <T> TreeNode<T> nextSibling( TreeDirNode<T> parent, TreeNode<T> node ) {

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

    public static <T extends Content> TreeNode<T> getNode( TreeNode<T> root, String ... path ) {

        for ( String name : path ) {

            if ( root.isLeaf() ) {
                throw new IllegalArgumentException( "not a dir" );
            }
            TreeDirNode<T> dir = (TreeDirNode<T>)root;

            boolean found = false;
            for ( TreeNode<T> child : dir.getChildren() ) {
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


}
