package org.openCage.util.ui.skvTree;

import org.openCage.util.ui.skyviewbar.Config;
import org.openCage.util.ui.skyviewbar.ObjectListener;
import org.openCage.util.iterator.Count;
import org.openCage.util.iterator.Iterators;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.ui.difftree.SynchronizeListener;

import javax.swing.*;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.*;
import java.util.*;
import java.util.List;

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
 * show the state of a JTree (DefaultMutableTreeNodes)
 * a bar of colored strips
 * color representing state
 */
public class SkvTree extends JPanel {


    private final Config                         config;
    private final BlockedTreeNodes               blockedList;
    private final List<ObjectListener<TreePath>> listeners = new ArrayList<ObjectListener<TreePath>>();

    private JudgeBlock judgeBlock;

    // TODO sync
    private int oldCursor = -1;
    private int cursor    = -1;



    public SkvTree( final Config config, final JTree tree  ) {

        this.config = config;
        this.blockedList = new BlockedTreeNodes( tree, config.getMinimalBlockSize(), config.getMaximalBlockSize(), getAdaptedStartY() );


        addMouseListener( new MouseListener() {
            public void mouseClicked(MouseEvent mouseEvent) {
                clicked( mouseEvent );
            }

            public void mousePressed(MouseEvent mouseEvent) {}
            public void mouseReleased(MouseEvent mouseEvent) {}
            public void mouseEntered(MouseEvent mouseEvent) {}
            public void mouseExited(MouseEvent mouseEvent) {}
        });

        addComponentListener(  new ComponentListener() {
            public void componentResized( ComponentEvent e ) {
                blockedList.recalc();                
            }

            public void componentMoved( ComponentEvent e ) {}
            public void componentShown( ComponentEvent e ) {}
            public void componentHidden( ComponentEvent e ) {}
        } );

//        setMinimumSize( new Dimension( 15, 30 ));
        setMinimumSize( new Dimension( 14, 30 ));
        setPreferredSize( new Dimension( 14, 30 ) );

        tree.addTreeExpansionListener( new TreeExpansionListener() {
            public void treeExpanded(TreeExpansionEvent treeExpansionEvent) {
                refresh();
            }

            public void treeCollapsed(TreeExpansionEvent treeExpansionEvent) {
                refresh();
            }
        });

        tree.addTreeSelectionListener( new TreeSelectionListener() {
            public void valueChanged( TreeSelectionEvent e ) {
                TreePath old = e.getOldLeadSelectionPath();
                TreePath nw = e.getNewLeadSelectionPath();

                int newIdx = blockedList.getBlock( nw );
                int oldIdx = blockedList.getBlock( old );

                if ( newIdx == oldIdx ) {
                    return;
                }

                setSkyCursor( newIdx );

                // TODO
            }
        }
        );
    }

    private void setSkyCursor( int newIdx ) {
        if ( oldCursor != -1 ) {
            oldCursor = -1;
            cursor    = newIdx;
        } else {
            oldCursor = cursor;
            cursor    = newIdx;
        }

        repaint();
    }

    private void clicked(MouseEvent mouseEvent) {
        List<DefaultMutableTreeNode> block = blockedList.get( mouseEvent.getPoint() );

        DefaultMutableTreeNode t = blockedList.get( mouseEvent.getPoint() ).get(0);


        if ( judgeBlock != null ) {
            t = judgeBlock.getInteresting( block );
        }

        if ( t == null ) {
            return;
        }

        TreePath path = NodeToNode.getTreePath(t );


        for ( ObjectListener<TreePath> ol  : listeners ) {
            ol.eventOccured( path );
        }
    }



    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.clearRect( 0, 0, getWidth(), getHeight() );

        // helper to find the right adaption values
        //graphics.fillRect( 0, getAdaptedStartY(), 2, getAdaptedHeight() );

        blockedList.setHeight( getAdaptedHeight() );

        int cellHeight = blockedList.getBlocksize();

        for ( Count<List<DefaultMutableTreeNode>> block : Iterators.count(blockedList.get()) ) {
            paintBlock( block.o, block.count, graphics, cellHeight, false );
        }

    }

    private void paintBlock( List<DefaultMutableTreeNode> block, int idx, Graphics graphics, int cellHeight, boolean onlyDiff ) {
        if ( block != null ) {

            List<Color> cols = blockedList.getCols( idx );

            if ( cols == null ) {
                cols = judgeBlock.getColor( block );               
                blockedList.setCols( idx, cols );
            } else if ( onlyDiff ) {
                List<Color> current =  judgeBlock.getColor( block );
                if ( current.equals( cols )) {
                    return;
                }

                cols = current;
                blockedList.setCols( idx, cols );                
            }

            paintColorBar( graphics, cols, cellHeight, idx );
        }

        paintCursor( idx, graphics, cellHeight );
    }

    private void paintColorBar( Graphics graphics, List<Color> cols, int cellHeight, int idx ) {

        int count = cols.size();

        if ( count == 0 ) {
            graphics.clearRect( 0, cellHeight * idx + getAdaptedStartY(), getWidth(), cellHeight );
            return;
        }

        int width = getWidth() - 2;
        int ww    = width / count;

        for ( int i = 0; i < count; ++i ) {
            graphics.setColor( cols.get(i) );
            graphics.fill3DRect( 1 + (i * ww), cellHeight * idx + getAdaptedStartY(), ww, cellHeight, true );
        }

    }

    private void paintCursor( int idx, Graphics graphics, int cellHeight ) {
        if ( idx == cursor ) {
            graphics.setColor( Color.BLACK );
            graphics.draw3DRect( 0, cellHeight * idx - 1 + getAdaptedStartY(), getWidth() -1, cellHeight, true );
//            graphics.fill3DRect( 0, cellHeight * idx - 1 + getAdaptedStartY(), 2, cellHeight + 2, true );
//            graphics.fill3DRect( getWidth() - 2, cellHeight * idx - 1 + getAdaptedStartY(), 2, cellHeight + 2, true );
        }
    }

    private int getAdaptedStartY() {
        return config.getvStart();
    }

    private int getAdaptedHeight() {
        return Math.max( 10, getHeight() - config.getHeightAdaptor() );
    }


    public void refresh() {
        blockedList.recalc();
        repaint();
    }

    public void elementRefresh() {
        int cellHeight = blockedList.getBlocksize();

        for ( Count<List<DefaultMutableTreeNode>> block : Iterators.count(blockedList.get()) ) {
            paintBlock( block.o, block.count, getGraphics(), cellHeight, true );
        }
    }

    public void addObjectListener( ObjectListener<TreePath> objectListener) {
        listeners.add( objectListener );
    }

    public void setJudgeBlock(JudgeBlock judgeBlock) {
        this.judgeBlock = judgeBlock;
    }

}
