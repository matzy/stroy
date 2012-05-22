package org.openCage.util.ui.skvTree;

import org.openCage.util.logging.Log;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.graph.node.TreeNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;

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

/**
 * The point of this class is to group nodes of a tree into blocks of about the same size
 * The result is a list of node lists (reduced list)
 * If tree is not fully expanded this class start with a list of blocks, i.e.
 * blocks have all nodes of a collapsed dir node
 */
public class BlockedTreeNodes {

    private final JTree tree;

    /**
     * the list of blocks where each block represents a visible node
     * if it is a collapsed dir node it has all the nodes in it
     */
    private List<List<DefaultMutableTreeNode>> source = new ArrayList<List<DefaultMutableTreeNode>>();


    private final BlockedBlocks<DefaultMutableTreeNode> blockedBlock;

    private final List<List<Color>> colors = new ArrayList<List<Color>>();

    /**
     * the target list of blocks
     */
    private final int        minBlockSize;
    private final int        yStart;
    private int              height = 0;

    public BlockedTreeNodes( JTree tree, int blocksize, int maxBlocksize, int yStart ) {
        this.tree         = tree;
        this.minBlockSize = blocksize;
        this.yStart       = yStart;

        blockedBlock = new BlockedBlocks( source, minBlockSize, 100 );
        recalc();
    }

    public List<DefaultMutableTreeNode> get( int idx ) {
        return blockedBlock.get( idx );
    }

    public List<DefaultMutableTreeNode> get( Point pt ) {
        return blockedBlock.getByPoint( (int)pt.getY() - yStart );
    }

    public List<List<DefaultMutableTreeNode>> get() {
        return blockedBlock.get();
    }

    public void setHeight( int height ) {
        if ( this.height == height ) {
            return;
        }

        this.height = height;

        blockedBlock.setFullsize( height - yStart );
    }


    private void recalcSource( DefaultMutableTreeNode node, List<List<DefaultMutableTreeNode>> list ) {


        if ( node.isLeaf() ) {
            list.add( Arrays.asList( node ));
            return;
        }

        if ( !tree.isExpanded( NodeToNode.getTreePath( node ))) {

            List<DefaultMutableTreeNode> block = new ArrayList<DefaultMutableTreeNode>();
            recalcSourceCollapsed( node, block );

            list.add( block );
            return;
        }
        

        list.add( Arrays.asList( node ));


        for ( int idx = 0; idx < node.getChildCount(); ++idx ) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode)node.getChildAt( idx );

            recalcSource( child, list );
        }
    }

    private void recalcSourceCollapsed(DefaultMutableTreeNode node, List<DefaultMutableTreeNode> block) {

        block.add( node );

        if ( node.isLeaf() ) {
            return;
        }

        for ( int idx = 0; idx < node.getChildCount(); ++idx ) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode)node.getChildAt( idx );

            recalcSourceCollapsed( child, block );
        }
    }


    public void recalc() {

        DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getModel().getRoot();

        source = new ArrayList<List<DefaultMutableTreeNode>>();

        recalcSource( root, source );

        blockedBlock.setSrc( source );
        colors.clear();
    }

//    private void add(int idx, List<DefaultMutableTreeNode> elem) {
//        if ( reducedList.size() - 1 == idx ) {
//            reducedList.get( idx ).addAll( elem );
//        } else if ( reducedList.size() == idx  ) {
//            List<DefaultMutableTreeNode> ll = new ArrayList<DefaultMutableTreeNode>();
//            ll.addAll( elem );
//            reducedList.add( ll );
//        } else {
//            Log.severe( "blocklist: prog error" );
//        }
//    }

//    public int getBlocksize() {
////        return (int)Math.min( maxBlockSize, Math.max( minBlockSize, Math.ceil( (double)height / source.size() )));
//        return Math.min( 15, (int)Math.max( minBlockSize, Math.ceil( (double)height / source.size() )));
//    }
//


    public int getBlocksize() {
        return minBlockSize;
    }

    public List<Color> getCols(int idx) {
        if ( colors.size() <= idx ) {
            return null;
        }

        return colors.get(idx);
    }

    public void setCols(int idx, List<Color> cols) {
        while ( colors.size() <= idx  ) {
            colors.add( null );
        }
        colors.set( idx, cols );
    }

    public int getBlock( TreePath path ) {

        int idx = 0;
        for ( List<DefaultMutableTreeNode> block : get() ) {
            for ( DefaultMutableTreeNode node : block ) {
                if ( NodeToNode.getTreePath( node ).equals( path )) {
                    return idx;
                }
            }

            idx++;
        }

        return -1;
    }
}
