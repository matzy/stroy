package org.openCage.stroy.ui.util;

import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeNodeUtils;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.stroy.content.Content;
import org.openCage.util.ui.TreeUtils;

import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import java.io.File;


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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/


public class NodeToNode {

    public static TreeNode pathToNode(TreePath path) {

        if ( path == null ) {
            return null;
        }
        
        Object[] pathList           = path.getPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)pathList[pathList.length - 1];
        UINode nd                   = (UINode)dmtn.getUserObject();

        return nd.get();
    }

    public static File getFile( TreePath path ) {
        return ((TreeNode<Content>)pathToNode( path)).getContent().getFile();
    }

    public static String getStringPath( TreePath path ) {
          if ( path == null ) {
            return null;
        }

        Object[] pathList = path.getPath();

        String ret = "";
        for ( Object nd : pathList ) {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode)nd;

            ret += "/" + getName(node);
        }

        return ret;

    }

//    public static TreePath nodeToPathForTreesWithExtraRoot( DefaultMutableTreeNode root, TreeNode<FileContent> nodeRoot, TreeNode<FileContent> node ) {
//
//        String path = node.getContent().getFile().getPath().substring( nodeRoot.getContent().getFile().getPath().length() );
//        String[] pieces = path.split( "/|:");
//
//        DefaultMutableTreeNode next = root;
//
//        for ( String name : pieces ) {
//            next = findChild( next, name );
//
//            if ( next == null ) {
//                return null;
//            }
//        }
//
//        return TreeUtils.getPath( next );
//    }

//    public static TreePath nodeToPath( DefaultMutableTreeNode root, TreeNode<FileContent> nodeRoot, TreeNode<FileContent> node ) {
//
//        String path = node.getContent().getFile().getPath().substring( nodeRoot.getContent().getFile().getPath().length() );
//        String[] pieces = path.split( "/|:|\\\\");
//
//        DefaultMutableTreeNode next = root;
//
//        for ( String name : pieces ) {
//
//            if ( name.length() != 0 ) {
//                next = findChild( next, name );
//
//                if ( next == null ) {
//                    return null;
//                }
//            }
//        }
//
//
//        return TreeUtils.getPath( next );
//    }



    public static TreePath getTreePath( DefaultMutableTreeNode node ) {

         return new TreePath( node.getPath() );
     }

//    public static void removeTreeNode(  DefaultMutableTreeNode root, TreeNode<FileContent> nodeRoot, TreeNode<FileContent> node ) {
//        String path = node.getContent().getFile().getPath().substring( nodeRoot.getContent().getFile().getPath().length() );
//        String[] pieces = path.split( "/|:|\\\\");
//
//        DefaultMutableTreeNode next = root;
//
//        for ( String name : pieces ) {
//
//            if ( name.length() != 0 ) {
//                next = findChild( next, name );
//
//                if ( next == null ) {
//                    return;
//                }
//            }
//        }
//
//
//
//        ((DefaultMutableTreeNode)next.getParent()).remove( next );
//
//    }

//    public static void removeTreeNode(  DefaultMutableTreeNode root, TreeNode<FileContent> node ) {
//
//        TreeNode<FileContent> nodeRoot = node;
//
//        while ( nodeRoot.getParent() != null ) {
//            nodeRoot = nodeRoot.getParent();
//        }
//
//        removeTreeNode( root, nodeRoot, node );
//    }


    public static <T extends Content> TreePath nodeToPath( DefaultMutableTreeNode root, TreeNode<T> node ) {

        return TreeUtils.getPath( nodeToDMTNode( root, node ));
    }


    public static <T extends Content> DefaultMutableTreeNode nodeToDMTNode( DefaultMutableTreeNode root, TreeNode<T> node ) {

        List<String> namePath = TreeNodeUtils.getNamePath( node );

        if ( namePath.size() < 1 ) {
            throw new IllegalArgumentException( "invalid node " + node );
        }

        if ( ! getName( root ).equals( namePath.get(0))) {
            throw new IllegalArgumentException( "DMTN " + root + "and node " + node +"are not from the same tree" );
        }

        namePath.set( 0, "" ); // done

        DefaultMutableTreeNode next = root;

        for ( String name : namePath ) {

            if ( name.length() != 0 ) {
                next = findChild( next, name );

                if ( next == null ) {
                    return null;
                }
            }
        }


        return next;
    }

    public static <T extends Content> TreePath nodeToTreePath( DefaultMutableTreeNode root, TreeNode<T> node ) {

        return TreeUtils.getPath( nodeToDMTNode( root, node ));
    }


    public static DefaultMutableTreeNode findChild( DefaultMutableTreeNode parent, String name ) {
        for (Enumeration child = parent.children(); child.hasMoreElements(); ) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)child.nextElement();
            if ( name.length() == 0 || getName( node ).equals( name )) {
                return node;
            }
        }

        return null;
    }

    public static String getName( DefaultMutableTreeNode node ) {
        return ((UINode<Content>)node.getUserObject()) .get().getContent().getName();
    }


}
