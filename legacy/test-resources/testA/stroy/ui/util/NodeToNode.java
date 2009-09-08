package org.openCage.stroy.ui.util;

import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.util.ui.TreeUtils;

import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;


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

// TODO factor out general stuff
    
public class NodeToNode {
    public static TreeNode<FileContent> pathToNode(TreePath path) {

        if ( path == null ) {
            return null;
        }
        
        Object[] pathList           = path.getPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)pathList[pathList.length - 1];
        UINode nd                   = (UINode)dmtn.getUserObject();

        return nd.get();
    }

    public static String getStringPath( TreeNode<FileContent> node ) {

        TreeNode<FileContent> nd = node;
        String ret = "";

        while ( nd != null ) {

            ret = "/" + nd.getContent().getName() + ret;
            nd  = nd.getParent();
        }

        return ret;
    }

    public static String getStringPath( TreePath path ) {
          if ( path == null ) {
            return null;
        }

        Object[] pathList           = path.getPath();

        String ret = "";
        for ( Object nd : pathList ) {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode)nd;

            ret += "/" + ((UINode)node.getUserObject()).get().getContent().getName();
        }

        return ret;

    }

    public static TreePath nodeToPathForTreesWithExtraRoot( DefaultMutableTreeNode root, TreeNode<FileContent> nodeRoot, TreeNode<FileContent> node ) {

        String path = node.getContent().getFile().getPath().substring( nodeRoot.getContent().getFile().getPath().length() );
        String[] pieces = path.split( "/|:");

        DefaultMutableTreeNode next = root;

        for ( String name : pieces ) {
            next = findChild( next, name );

            if ( next == null ) {
                return null;
            }
        }

        return TreeUtils.getPath( next );
    }

    public static TreePath nodeToPath( DefaultMutableTreeNode root, TreeNode<FileContent> nodeRoot, TreeNode<FileContent> node ) {

        String path = node.getContent().getFile().getPath().substring( nodeRoot.getContent().getFile().getPath().length() );
        String[] pieces = path.split( "/|:|\\\\");

        DefaultMutableTreeNode next = root;

        for ( String name : pieces ) {

            if ( name.length() != 0 ) {                 
                next = findChild( next, name );

                if ( next == null ) {
                    return null;
                }
            }
        }


        return TreeUtils.getPath( next );
    }

    public static TreePath getTreePath( DefaultMutableTreeNode node ) {

         return new TreePath( node.getPath() );
     }

    public static void removeTreeNode(  DefaultMutableTreeNode root, TreeNode<FileContent> nodeRoot, TreeNode<FileContent> node ) {
        String path = node.getContent().getFile().getPath().substring( nodeRoot.getContent().getFile().getPath().length() );
        String[] pieces = path.split( "/|:|\\\\");

        DefaultMutableTreeNode next = root;

        for ( String name : pieces ) {

            if ( name.length() != 0 ) {
                next = findChild( next, name );

                if ( next == null ) {
                    return;
                }
            }
        }

        

        ((DefaultMutableTreeNode)next.getParent()).remove( next );

    }

    public static void removeTreeNode(  DefaultMutableTreeNode root, TreeNode<FileContent> node ) {

        TreeNode<FileContent> nodeRoot = node;

        while ( nodeRoot.getParent() != null ) {
            nodeRoot = nodeRoot.getParent();
        }

        removeTreeNode( root, nodeRoot, node );
    }

    public static TreeNode<FileContent> getTreeNodeRoot( TreeNode<FileContent> nd ) {
        TreeNode<FileContent> nodeRoot = nd;

        while ( nodeRoot.getParent() != null ) {
            nodeRoot = nodeRoot.getParent();
        }

        return nodeRoot;
    }

    public static TreePath nodeToPath( DefaultMutableTreeNode root, TreeNode<FileContent> node ) {

        return TreeUtils.getPath( nodeToNode( root, node ));
    }

    public static DefaultMutableTreeNode nodeToNode( DefaultMutableTreeNode root, TreeNode<FileContent> node ) {

        String path = node.getContent().getFile().getPath().substring( getTreeNodeRoot(node).getContent().getFile().getPath().length() );
        String[] pieces = path.split( "/|:|\\\\");

        DefaultMutableTreeNode next = root;

        for ( String name : pieces ) {

            if ( name.length() != 0 ) {
                next = findChild( next, name );

                if ( next == null ) {
                    return null;
                }
            }
        }


        return next;
    }

    public static DefaultMutableTreeNode findChild( DefaultMutableTreeNode parent, String name ) {
        for (Enumeration child = parent.children(); child.hasMoreElements(); ) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)child.nextElement();
            if ( name.length() == 0 || ((UINode)node.getUserObject()) .get().getContent().getName().equals( name )) {
                return node;
            }
        }

        return null;
    }


}
