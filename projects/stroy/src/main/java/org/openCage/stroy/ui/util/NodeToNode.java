package org.openCage.stroy.ui.util;

import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeNodeUtils;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.content.FileContent;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.stroy.ui.difftree.GhostNode;
import org.openCage.stroy.content.Content;
import org.openCage.util.io.FileUtils;
import org.openCage.util.ui.TreeUtils;

import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

import static org.openCage.lang.errors.Unchecked.wrap;


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
        TreeNode<Content> treeNode = pathToNode(path);
        if ( treeNode.getContent() instanceof FileContent ) {
            return ((FileContent)treeNode.getContent()).getFile();
        }

        // TODO make util in io
        try {
            File ret = FSPathBuilder.getTmpFile(treeNode.getContent().getType()).toFile();
            FileUtils.copy( treeNode.getContent().getStream(),
                            new FileOutputStream(ret));

            return ret;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw wrap(e);
        }
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

    public static <T extends Content> boolean isGhost( DefaultMutableTreeNode root, TreeNode<T> node ) {
        List<String> namePath = TreeNodeUtils.getNamePath( node );

        if ( namePath.size() < 1 ) {
            throw new IllegalArgumentException( "invalid node " + node );
        }

        // NEXT: why has treenode that long name
        return ! getName( root ).equals( namePath.get(0));

    }

    public static <T extends Content> DefaultMutableTreeNode nodeToDMTNode( DefaultMutableTreeNode root, TreeNode<T> node ) {

        List<String> namePath = TreeNodeUtils.getNamePath( node );

        if ( namePath.size() < 1 ) {
            throw new IllegalArgumentException( "invalid node " + node );
        }

        if ( !isInThatTree( root, node )) {

        }


        if ( ! isInThatTree( root, node )) {
//        if ( ! getName( root ).equals( namePath.get(0))) {
            throw new IllegalArgumentException( "DMTN <" + root + "> and node <" + node +"> are not from the same tree" );
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

    private static <T extends Content> boolean isInThatTree( DefaultMutableTreeNode root, TreeNode<T> node ) {

        return true;
//        if ( !root.isRoot()) {
//            root = (DefaultMutableTreeNode)root.getRoot();
//        }
//
//
//        return ((FileContent)node.getContent()).getFile().getAbsolutePath().contains(
//                ((FileContent)(((UINode)root.getUserObject()).get().getContent())).getFile().getAbsolutePath());
    }

    public static <T extends Content> TreePath nodeToTreePath( DefaultMutableTreeNode root, TreeNode<T> node ) {

        return TreeUtils.getPath( nodeToDMTNode( root, node ));
    }


    public static DefaultMutableTreeNode findChild( DefaultMutableTreeNode parent, String name ) {
        for (Enumeration child = parent.children(); child.hasMoreElements(); ) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)child.nextElement();
            if ( name.length() == 0 || getName( node ).equals( name )) { // || ("/" + getName( node )).equals( name )) {
                return node;
            }
        }

        return null;
    }

    public static String getName( DefaultMutableTreeNode node ) {
//        if ( node.isRoot() ) {
//            return ((UINode<Content>)node.getUserObject()).get().getContent().getName();
//        } else {
            return ((UINode<Content>)node.getUserObject()).toString(); //getContent().getName();
//        }
    }

    /**
     * Find the node under root matching to path from other tree
     * @param root A tree root
     * @param path A treepath from an other tree
     * @param task A task connecting both trees
     * @return
     */
    public static <T extends Content> DefaultMutableTreeNode findMatchingNode( DefaultMutableTreeNode root, TreePath path, TreeMatchingTask<T> task) {
        List<String> namePath = new ArrayList<String>();

        UINode      uiNode;
        TreeNode<T> node;
        while( true ) {
            uiNode = ((UINode)((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject());
            node   = uiNode.get();

            if ( node == null ) {
                namePath.add( 0, uiNode.toString().substring( 1 ) );
            } else if ( !task.isMatched( node )) {
                namePath.add( 0, GhostNode.GHOST_TAG + uiNode.toString() );
            } else {
                break;
            }

            path = path.getParentPath();
        }

//        while ( !task.isMatched(node)) {
//            namePath.add( 0, GhostNode.GHOST_TAG + node.getContent().getName());
//            node = node.getParent();
//        }

        DefaultMutableTreeNode dmNode = nodeToDMTNode( root, task.getMatch( node ));


//        namePath.set( 0, "" ); // done
//
//        DefaultMutableTreeNode next = root;

        for ( String name : namePath ) {

            if ( name.length() != 0 ) {
                dmNode = findChild( dmNode, name );

                if ( dmNode == null ) {
                    return null;
                }
            }
        }


        return dmNode;

    }


    /**
     * Find the matching dmTreeNode on the oter tree (including ghosts)
     * @param root The root of the tree where the match is to be found
     * @param node A node from the other tree
     * @param task The task connecting both sides
     * @return
     */
    public static <T extends Content> DefaultMutableTreeNode findMatchingNode( DefaultMutableTreeNode root, TreeNode<T> node, TreeMatchingTask<T> task) {

        // find first matching parents remember list of names
        // match
        // traverse by name

        if ( !isGhost( root, node )) {
            throw new Error( "impl me" );
        }

        List<String> namePath = new ArrayList<String>();

        while ( !task.isMatched(node)) {
            namePath.add( 0, GhostNode.GHOST_TAG + node.getContent().getName());
            node = node.getParent();
        }

        DefaultMutableTreeNode dmNode = nodeToDMTNode( root, task.getMatch( node ));


//        namePath.set( 0, "" ); // done
//
//        DefaultMutableTreeNode next = root;

        for ( String name : namePath ) {

            if ( name.length() != 0 ) {
                dmNode = findChild( dmNode, name );

                if ( dmNode == null ) {
                    return null;
                }
            }
        }


        return dmNode;
    }

}
