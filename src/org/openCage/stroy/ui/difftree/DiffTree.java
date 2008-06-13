package org.openCage.stroy.ui.difftree;

import com.google.inject.Inject;
import org.openCage.util.iterator.Count;
import org.openCage.util.iterator.Iterators;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TaskUtils;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.*;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.ui.popup.DiffPopup;
import org.openCage.stroy.ui.popup.PopupSelector;
import org.openCage.stroy.content.Content;
import org.openCage.util.ui.TreeUtils;
import org.openCage.util.ui.skvTree.SkvTree;
import org.openCage.util.ui.skvTree.JudgeBlock;
import org.openCage.util.ui.skyviewbar.ConfigProvider;
import org.openCage.util.ui.skyviewbar.InfosAsColor;
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
    private final TreeMatchingTask<T> taskRight;
    private final TreeMatchingTask<T> taskLeft;
    private final List<SynchronizeListener>     syncListeners = new ArrayList<SynchronizeListener>();
    private final DefaultMutableTreeNode        root;
    private final int                           idx;
    private boolean                             myEvent = false;
    private boolean                             myScroll = false;
    private PopupSelector                       popup;
    private SkvTree                             skvLeft;
    private SkvTree                             skvRight;

    @Inject
    public DiffTree( final int                           idx,
                     final TreeMatchingTask<T>           taskLeft,
                     final TreeMatchingTask<T>           taskRight,
                     final DefaultMutableTreeNode        root,
                     final ShowChangeTreeCellRenderer    showChangeTreeCellRenderer ) {
        this.idx       = idx;
        this.taskLeft  = taskLeft;
        this.taskRight = taskRight;
        this.root      = root;

        tree   = new JTree( root );
        tree.setRowHeight(0);
        ToolTipManager.sharedInstance().registerComponent( tree );
        tree.setCellRenderer( showChangeTreeCellRenderer );

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

        tree.addTreeSelectionListener( new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {

                if ( myEvent ) {
                    myEvent = false;
                    return;
                }

                Log.finest( " selection listener " + idx ); // NON-NLS


                Rectangle             rec     = tree.getPathBounds( treeSelectionEvent.getPath() );
                Rectangle             relRec  = Scrolling.getRelativeLocation( scroll.getViewport(), rec );

                if ( relRec == null ) {
                    return;
                }

                TreeNode<T> node    = NodeToNode.pathToNode( treeSelectionEvent.getPath() );

                for ( SynchronizeListener listener : syncListeners ) {
                    listener.scrollTo( node, relRec, idx );
                }
            }
        });

        tree.addTreeExpansionListener( new TreeExpansionListener() {
            public void treeExpanded( TreeExpansionEvent event ) {
                Log.finest( " selection listener " + idx ); // NON-NLS

                TreeNode<T> node    = NodeToNode.pathToNode( event.getPath() );

                for ( SynchronizeListener listener : syncListeners ) {
                    listener.expanded( node, true, idx );
                }
            }

            public void treeCollapsed( TreeExpansionEvent event ) {
                Log.finest( " selection listener " + idx ); // NON-NLS

                TreeNode<T> node    = NodeToNode.pathToNode( event.getPath() );

                for ( SynchronizeListener listener : syncListeners ) {
                    listener.expanded( node, false, idx );
                }
            }
        } );


        scroll.getVerticalScrollBar().addAdjustmentListener( new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {

                if ( myScroll ) {
                    myScroll = false;
                    return;
                }

                Log.finest( "scroll event " + idx ); // NON-NLS

                Rectangle             rec     = tree.getPathBounds( tree.getSelectionPath() );

                if ( rec != null ) {
                    if ( !scroll.getViewport().getViewRect().contains( rec ) ) {
                        // go 3 down

                        int vy = (int)scroll.getViewport().getViewRect().getY();
                        int hy = (int)(scroll.getViewport().getViewRect().getHeight() / 2);
                        int yy = vy + hy;

//                        int row = tree.getSelectionRows()[0];
//
//                        tree.getRowBounds();


                        tree.setSelectionPath(tree.getClosestPathForLocation( 0, yy ));

                    } else {

                        Rectangle             relRec  = Scrolling.getRelativeLocation( scroll.getViewport(), rec );
                        if ( relRec == null ) {
                            return;
                        }

                        TreeNode<T> node  = NodeToNode.pathToNode( tree.getSelectionPath() );

                        for ( SynchronizeListener listener : syncListeners ) {
                            listener.scrollTo( node, relRec, idx );
                        }
                    }
                }
            }
        });

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

        popup = new PopupSelector( taskLeft, taskRight );


    }


    private JudgeBlock getJudgeBlock( final TreeMatchingTask<T> matching ) {
        return new TreeNodeJudge<T>( matching );
    }


//    private InfosAsColor<TreeNode<T>> getInfosAsColor( final TreeMatchingTask<T> matching ) {
//        return new InfosAsColor<TreeNode<T>>() {
//            public Color getColor(final java.util.List<TreeNode<T>> treeNodes) {
//
//                boolean only      = false;
//                boolean structure = false;
//                boolean content   = false;
//
//                for ( TreeNode<T> node : treeNodes ) {
//                    final ChangeVector cv = matching.getChangeVector( node );
//
//                    only |= cv.only;
//                    structure |= (cv.name || cv.parent );
//                    content   |= cv.content;
//                }
//
//                if ( content && structure ) {
//                    return Colors.CONTENT_AND_STRUCTUR;
//                }
//
//                if ( content ) {
//                    return Colors.CONTENT;
//                }
//
//                if ( structure ) {
//                    return Colors.STRUCTUR;
//                }
//
//                if ( only ) {
//                    return Colors.ONLYHERE;
//                }
//
//                return null;
//
//            }
//
//            public Color getEmphasized() {
//                return Colors.EMPHASIZED;
//            }
//
//            public TreeNode<T> getInteresting(java.util.List<TreeNode<T>> treeNodes ) {
//                for (Count<TreeNode<T>> node : Iterators.count( treeNodes )) {
//                    final ChangeVector cv = matching.getChangeVector( node.o );
//
//                    if ( cv.isAny()) {
//                        return node.o;
//                    }
//
//                }
//
//                return treeNodes.get(0);
//            }
//        };
//    }





//    private JViewport getViewPort() {
//        return scroll.getViewport();
//    }

//    private boolean hasLeftSkyView() {
//        return taskRight != null;
//    }
//
//    private boolean hasRightSkyView() {
//        return taskLeft != null;
//    }

    private boolean scrollbarOnLeft() {
        return taskLeft == null;
    }


    public void addSyncListener( SynchronizeListener listener ) {
        syncListeners.add( listener );
    }

    public void scrollTo(TreeNode<T> node, Rectangle rect, int sourceIdx) {

        Log.finest( " scrollTo " + idx); // NON-NLS

        TreePath path;

        if ( sourceIdx > idx ) {
            // right
            path = NodeToNode.nodeToTreePath(
                    root,
                    TaskUtils.getBestMatch( taskRight, node ));
        } else {
            // left
            path = NodeToNode.nodeToTreePath(
                    root,
                    TaskUtils.getBestMatch( taskLeft, node ));
        }

        if ( path == null ) {
            Log.warning( "synchronized scrolling failed to match (?)" ); // NON-NLS
        }


        Rectangle here = tree.getPathBounds( path );
        myScroll = true;
        Scrolling.scrollToRelative( scroll.getViewport(), here, rect );
        myEvent = true;
        tree.setSelectionPath( path );
    }

    public void expanded( TreeNode<T> node, boolean expanded, int sourceIdx ) {
        Log.finest( "expand message from " + idx ); // NON-NLS
        TreePath path;

        if ( sourceIdx > idx ) {
            // right
            path = NodeToNode.nodeToTreePath(
                    root,
                    TaskUtils.getBestMatch( taskRight, node ));
        } else {
            // left
            path = NodeToNode.nodeToTreePath(
                    root,
                    TaskUtils.getBestMatch( taskLeft, node ));
        }

        if ( path == null ) {
            Log.warning( "synchronized scrolling failed to match (?)" ); // NON-NLS
        }


        myEvent = true;
        if ( expanded ) {
            tree.expandPath( path );
        } else {
            tree.collapsePath( path );
        }
    }

    //    private void addObjectListener( SkyViewBar skv, final TreeNode<T> nodeRoot) {
    private void addObjectListener( SkvTree skv ) {
        skv.addObjectListener( new ObjectListener<TreePath>() {
            public void eventOccured(final TreePath path) {
                tree.expandPath( path );
                Rectangle rec = tree.getPathBounds( path );
                myScroll = true;
                Scrolling.scrollToMiddle( scroll.getViewport(), rec );
                tree.setSelectionPath( path );
            }
        });

    }

    public JTree getTree() {
        return tree;
    }

//    public SkyViewBar getSkyView() {
//        if ( skvLeft != null ) {
//            return skvLeft;
//        }
//
//        return skvRight;
//
//    }

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

