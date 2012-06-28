package org.openCage.stroy.ui.difftree;

import com.google.inject.Inject;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.popup.PopupSelectorFactory;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.ui.popup.PopupSelector;
import org.openCage.stroy.content.Content;
import org.openCage.util.ui.TreeUtils;
import org.openCage.util.ui.skvTree.SkvTree;
import org.openCage.util.ui.skvTree.JudgeBlock;
import org.openCage.util.ui.skyviewbar.ConfigProvider;
import org.openCage.util.ui.skyviewbar.ObjectListener;
import org.openCage.util.logging.Log;
import org.openCage.util.swing.Scrolling;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
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

/*
 * events
 *    1) click in tree
 *          no scroll
 *          message with treenode + rectangle
 *    2) click in skyView
 *          set selected
 *          scroll to middle
 *          message with treende + rectangel (not orig skyview)
 *    3) set selected (programming)
 *          2)
 *    3) scroll
 *          message with treenode + rec
 *    4) connected tree item selected
 *    5) connected tree scrolled
 */
/**
 * DiffTree is a the JTree in the scroll ui
 * it know how to match its nodes to related nodes in other difftrees
 * it has two matching task one to the left and one to the right
 *
 * it is meant to work as one of many of an array of difftrees. it knows its possition in the array
 *
 *    ... task(leftRoot, rightRoot) | diffTree | task(leftNode, rightNode ) | diffTree ...
 */
public class DiffTree<T extends Content> extends JPanel implements SynchronizeListener<T> {


    private final JTree                         tree;
    private final JScrollPane                   scroll;
    private final TreeMatchingTask<T>           taskRight;
    private final TreeMatchingTask<T>           taskLeft;
    private final List<SynchronizeListener>     syncListeners = new ArrayList<SynchronizeListener>();
    private final DefaultMutableTreeNode        root;
    private final int                           idx;
//    private boolean                             myEvent = false;
    private boolean                             myScroll = false;
    private PopupSelector                       popup;
    private SkvTree                             skvLeft;
    private SkvTree                             skvRight;

//    private TreePath                            lastSelectionPath;

    @Inject
    public DiffTree( final int                           idx,
                     final TreeMatchingTask<T>           taskLeft,
                     final TreeMatchingTask<T>           taskRight,
                     final DefaultMutableTreeNode        root,
                     final ShowChangeTreeCellRenderer    showChangeTreeCellRenderer,
                     final PopupSelectorFactory<T>       popupSelectorFactory ) {
        this.idx       = idx;
        this.taskLeft  = taskLeft;
        this.taskRight = taskRight;
        this.root      = root;

        tree   = new JTree( root );
        tree.setRowHeight(0);
        ToolTipManager.sharedInstance().registerComponent( tree );
        tree.setCellRenderer( showChangeTreeCellRenderer );
        showChangeTreeCellRenderer.setTree( tree );

        scroll = new JScrollPane( tree );

        skvLeft = null;
        skvRight = null;

        if ( taskLeft != null && taskRight != null ) {
            skvLeft = new SkvTree( ConfigProvider.getConfig(), tree );
            addObjectListener( skvLeft );

            skvRight = new SkvTree(  ConfigProvider.getConfig(), tree );
            addObjectListener( skvRight );

        } else if ( taskRight != null ) {
            skvLeft = new SkvTree(  ConfigProvider.getConfig(), tree);
            addObjectListener( skvLeft );
            skvLeft.setJudgeBlock( getJudgeBlock( taskRight ));

        } else if ( taskLeft != null ) {
            skvRight = new SkvTree(  ConfigProvider.getConfig(), tree );
            addObjectListener( skvRight );
            skvRight.setJudgeBlock( getJudgeBlock( taskLeft ));

        }

        setLayout( new BorderLayout() );


        if ( scrollbarOnLeft() ) {
            scroll.setComponentOrientation( ComponentOrientation.RIGHT_TO_LEFT);
        }

//             scrollPane.getViewport().addChangeListener( new ChangeListener() {
//                 public void stateChanged(ChangeEvent changeEvent) {
////                 System.out.println(changeEvent);
//                 }
//             });


        add( scroll, BorderLayout.CENTER );

        if ( skvLeft != null ) {
            add( skvLeft, BorderLayout.WEST );
        }

        if ( skvRight != null ) {
            add( skvRight, BorderLayout.EAST );
        }

        TreeUtils.expandAll( tree, true );
        addTreeListeners( idx );


        addScrollListeners( idx );

        popup = popupSelectorFactory.get( taskLeft, taskRight );


    }

    private void addScrollListeners( final int idx ) {
        scroll.getVerticalScrollBar().addAdjustmentListener( new AdjustmentListener() {
            public void adjustmentValueChanged( AdjustmentEvent adjustmentEvent) {

                // vertical scrollbar was moved


                if ( myScroll ) {
                    myScroll = false;
//                    Log.warning( "+++++++ skip ++++++ " + this  );
                    return;
                }

                Log.finest( "scroll event " + idx ); // NON-NLS

                Rectangle rec = tree.getPathBounds( tree.getSelectionPath() );

                if ( rec != null ) {

                    if ( !scroll.getViewport().getViewRect().contains( rec ) ) {

                        // the current selected node was moved outside the window
                        // => select a new node

                        int vy = (int)scroll.getViewport().getViewRect().getY();
                        int hy = (int)(scroll.getViewport().getViewRect().getHeight() / 2);
                        int yy = vy + hy;

                        tree.setSelectionPath(tree.getClosestPathForLocation( 0, yy ));

                    } else {

                        Rectangle             relRec  = Scrolling.getRelativeLocation( scroll.getViewport(), rec );
                        if ( relRec == null ) {
                            return;
                        }

                        for ( SynchronizeListener listener : syncListeners ) {
                            listener.scrollTo( tree.getSelectionPath(), relRec, idx );
                        }
                    }
                }
            }
        });
    }

    private void addTreeListeners( final int idx ) {
        tree.addTreeSelectionListener( new TreeSelectionListener() {
            public void valueChanged( TreeSelectionEvent treeSelectionEvent) {

                // if the selection did not change => nothing to do (prevent loops)
                if ( treeSelectionEvent.getOldLeadSelectionPath() != null &&
                     treeSelectionEvent.getNewLeadSelectionPath() != null &&
                     treeSelectionEvent.getOldLeadSelectionPath().equals( treeSelectionEvent.getNewLeadSelectionPath() )) {
                    return;
                }

                Rectangle rec     = tree.getPathBounds( treeSelectionEvent.getPath() );
                Rectangle  relRec = Scrolling.getRelativeLocation( scroll.getViewport(), rec );

                if ( relRec == null ) {
                    return;
                }

                for ( SynchronizeListener listener : syncListeners ) {
                    listener.scrollTo( treeSelectionEvent.getPath(), relRec, idx );
                }
            }
        });

        tree.addTreeExpansionListener( new TreeExpansionListener() {
            public void treeExpanded( TreeExpansionEvent event ) {
                for ( SynchronizeListener listener : syncListeners ) {
                    listener.expanded( event.getPath(), true, idx );
                }
            }

            public void treeCollapsed( TreeExpansionEvent event ) {
                for ( SynchronizeListener listener : syncListeners ) {
                    listener.expanded( event.getPath(), false, idx );
                }
            }
        } );

        tree.addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

            private void maybeShowPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popup.open( e, tree.getClosestPathForLocation( e.getX(), e.getY()) );
                }
            }
        });
    }


    private JudgeBlock getJudgeBlock( final TreeMatchingTask<T> matching ) {
        return new TreeNodeJudge<T>( matching );
    }

    private boolean scrollbarOnLeft() {
        return taskLeft == null;
    }


    public void addSyncListener( SynchronizeListener listener ) {
        syncListeners.add( listener );
    }

    public void scrollTo( TreePath pathOtherTree /*TreeNode<T> node*/, Rectangle rect, int sourceIdx) {

        Log.finest( " scrollTo " + idx); // NON-NLS

        TreePath path = getMatchingPath( sourceIdx, pathOtherTree );

        if ( path == null ) {
            Log.warning( "synchronized scrolling failed to match (?)" ); // NON-NLS
        }

        Rectangle here = tree.getPathBounds( path );
        myScroll = true;
        Scrolling.scrollToRelative( scroll.getViewport(), here, rect );
        myScroll = false;

        tree.setSelectionPath( path );
    }

    private TreePath getMatchingPath( int sourceIdx, TreePath pathOtherTree ) {
        TreePath path;
        if ( sourceIdx > idx ) {
            // right
             path = TreeUtils.getPath( NodeToNode.findMatchingNode( root, pathOtherTree, taskRight ));
        } else {
            // left
            path = TreeUtils.getPath( NodeToNode.findMatchingNode( root, pathOtherTree, taskLeft ));
        }
        return path;
    }


    public void expanded( TreePath pathOtherTree, boolean expanded, int sourceIdx ) {
        Log.finest( "expand message from " + idx ); // NON-NLS

        TreePath path = getMatchingPath( sourceIdx, pathOtherTree );

        if ( path == null ) {
            Log.warning( "synchronized scrolling failed to match (?)" ); // NON-NLS
        }

  //      myEvent = true;
        if ( expanded ) {
            tree.expandPath( path );
        } else {
            tree.collapsePath( path );
        }
    }

    private void addObjectListener( SkvTree skv ) {
        skv.addObjectListener( new ObjectListener<TreePath>() {
            public void eventOccured(final TreePath path) {
                tree.expandPath( path );
                Rectangle rec = tree.getPathBounds( path );
                myScroll = true;
                Scrolling.scrollToMiddle( scroll.getViewport(), rec );
                myScroll = false;
                tree.setSelectionPath( path );
            }
        });

    }

    public JTree getTree() {
        return tree;
    }

    public void refresh() {

        if ( skvLeft != null ) {
            skvLeft.refresh();
        }

        if ( skvRight != null ) {
            skvRight.refresh();
        }

        // todo local
        tree.repaint();
    }

    public void elementRefresh() {

        if ( skvLeft != null ) {
            skvLeft.elementRefresh();
        }

        if ( skvRight != null ) {
            skvRight.elementRefresh();
        }
        // todo local
        tree.repaint();
    }



}

