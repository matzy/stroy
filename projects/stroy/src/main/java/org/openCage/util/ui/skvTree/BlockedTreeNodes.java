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
