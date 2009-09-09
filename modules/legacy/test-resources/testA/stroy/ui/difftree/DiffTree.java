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
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
public class DiffTree extends JPanel implements SynchronizeListener {


    private final JTree                         tree;
    private final JScrollPane                   scroll;
    private final TreeMatchingTask<FileContent> taskRight;
    private final TreeMatchingTask<FileContent> taskLeft;
    //    private final ShowChangeTreeCellRenderer    showChangeTreeCellRenderer;
    private final List<SynchronizeListener>     syncListeners = new ArrayList<SynchronizeListener>();
    private final DefaultMutableTreeNode        root;
    private final int                           idx;
    private boolean                             myEvent = false;
    private boolean                             myScroll = false;
    private DiffPopup                           popup;
//    private SkyViewBar<TreeNode<FileContent>> skvLeft;
//    private SkyViewBar<TreeNode<FileContent>> skvRight;
    private SkvTree skvLeft;
    private SkvTree skvRight;

    @Inject
    public DiffTree( final int                           idx,
                     final TreeMatchingTask<FileContent> taskLeft,
                     final TreeMatchingTask<FileContent> taskRight,
                     final DefaultMutableTreeNode        root,
                     final ShowChangeTreeCellRenderer    showChangeTreeCellRenderer
    ) {
        this.idx       = idx;
        this.taskLeft  = taskLeft;
        this.taskRight = taskRight;
        this.root      = root;

        tree   = new JTree( root );
        ToolTipManager.sharedInstance().registerComponent( tree );
        tree.setCellRenderer( showChangeTreeCellRenderer );

        scroll = new JScrollPane( tree );


        skvLeft = null;
        skvRight = null;

        if ( taskLeft != null && taskRight != null ) {
//            skvLeft = new SkyViewBar(  ConfigProvider.getConfig());
//            skvLeft.setList( getInfosAsColor( taskLeft ), TreeNodeUtils.toList( taskLeft.getLeftRoot()));
//            addObjectListener( skvLeft, taskLeft.getLeftRoot() );
            skvLeft = new SkvTree( ConfigProvider.getConfig(), tree );
//            skvLeft.setList( getInfosAsColor( taskLeft ), TreeNodeUtils.toList( taskLeft.getLeftRoot()));
            addObjectListener( skvLeft );

//            skvRight = new SkyViewBar(  ConfigProvider.getConfig());
//            skvRight.setList( getInfosAsColor( taskRight ), TreeNodeUtils.toList( taskRight.getLeftRoot()));
//            addObjectListener( skvRight, taskRight.getLeftRoot() );
            skvRight = new SkvTree(  ConfigProvider.getConfig(), tree );
//            skvRight.setList( getInfosAsColor( taskRight ), TreeNodeUtils.toList( taskRight.getLeftRoot()));
            addObjectListener( skvRight );

        } else if ( taskRight != null ) {
//            skvLeft = new SkyViewBar(  ConfigProvider.getConfig());
//            skvLeft.setList( getInfosAsColor( taskRight ), TreeNodeUtils.toList( taskRight.getRightRoot()));
            //addObjectListener( skvLeft );
            skvLeft = new SkvTree(  ConfigProvider.getConfig(), tree);
//            skvLeft.setList( getInfosAsColor( taskRight ), TreeNodeUtils.toList( taskRight.getRightRoot()));
            addObjectListener( skvLeft );
            skvLeft.setJudgeBlock( getJudgeBlock( taskRight ));

        } else if ( taskLeft != null ) {
//            skvRight = new SkyViewBar(  ConfigProvider.getConfig());
//            skvRight.setList( getInfosAsColor( taskLeft ), TreeNodeUtils.toList( taskLeft.getLeftRoot()));
            //addObjectListener( skvRight );
            skvRight = new SkvTree(  ConfigProvider.getConfig(), tree );
//            skvRight.setList( getInfosAsColor( taskLeft ), TreeNodeUtils.toList( taskLeft.getLeftRoot()));
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

                Log.finest( " selection listener " + idx );

                Rectangle             rec     = tree.getPathBounds( treeSelectionEvent.getPath() );
                Rectangle             relRec  = Scrolling.getRelativeLocation( scroll.getViewport(), rec );

                if ( relRec == null ) {
                    return;
                }

                TreeNode<FileContent> node    = NodeToNode.pathToNode( treeSelectionEvent.getPath() );

                for ( SynchronizeListener listener : syncListeners ) {
                    listener.scrollTo( node, relRec, idx );
                }
            }
        });

        scroll.getVerticalScrollBar().addAdjustmentListener( new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {

                if ( myScroll ) {
                    myScroll = false;
                    return;
                }

                Log.finest( "scroll event " + idx );

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

                        TreeNode<FileContent> node    = NodeToNode.pathToNode( tree.getSelectionPath() );

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

        popup = new DiffPopup( taskLeft, taskRight );


    }


    private JudgeBlock getJudgeBlock( final TreeMatchingTask<FileContent> matching ) {
        return new JudgeBlock() {
            public DefaultMutableTreeNode getInteresting(List<DefaultMutableTreeNode> block) {
                for (Count<DefaultMutableTreeNode> node : Iterators.count( block )) {

                    TreeNode<FileContent> nn = ((UINode)node.o.getUserObject()).get();

                    final ChangeVector cv = matching.getChangeVector( nn );

                    if ( cv.isAny()) {
                        return node.o;
                    }

                }

                return null;
            }

            public List<Color> getColor(List<DefaultMutableTreeNode> block) {
                if ( null == getInteresting( block )) {
                    return null;
                }

                List<Color> colors = new ArrayList<Color>();


                boolean only      = false;
                boolean structure = false;
                boolean content   = false;

                for ( DefaultMutableTreeNode dmtn : block ) {
                    TreeNode<FileContent> node = ((UINode)dmtn.getUserObject()).get();
                    final ChangeVector cv = matching.getChangeVector( node );

                    if ( cv.only ) {
                        if ( !only ) {
                            only = true;
                            colors.add( Colors.ONLYHERE );
                        }
                    } else if ( cv.name || cv.parent ) {
                        if ( !structure) {
                            structure = true;
                            colors.add( Colors.STRUCTUR );
                        }
                    } else if ( cv.content ) {
                        if ( !content ) {
                            content = true;
                            colors.add( Colors.CONTENT );
                        }
                    }

                }


                return colors;
            }
        };
    }


    private InfosAsColor<TreeNode<FileContent>> getInfosAsColor( final TreeMatchingTask<FileContent> matching ) {
        return new InfosAsColor<TreeNode<FileContent>>() {
            public Color getColor(final java.util.List<TreeNode<FileContent>> treeNodes) {

                boolean only      = false;
                boolean structure = false;
                boolean content   = false;

                for ( TreeNode<FileContent> node : treeNodes ) {
                    final ChangeVector cv = matching.getChangeVector( node );

                    only |= cv.only;
                    structure |= (cv.name || cv.parent );
                    content   |= cv.content;
                }

                if ( content && structure ) {
                    return Colors.CONTENT_AND_STRUCTUR;
                }

                if ( content ) {
                    return Colors.CONTENT;
                }

                if ( structure ) {
                    return Colors.STRUCTUR;
                }

                if ( only ) {
                    return Colors.ONLYHERE;
                }

                return null;

            }

            public Color getEmphasized() {
                return Colors.EMPHASIZED;
            }

            public TreeNode<FileContent> getInteresting(java.util.List<TreeNode<FileContent>> treeNodes ) {
                for (Count<TreeNode<FileContent>> node : Iterators.count( treeNodes )) {
                    final ChangeVector cv = matching.getChangeVector( node.o );

                    if ( cv.isAny()) {
                        return node.o;
                    }

                }

                return treeNodes.get(0);
            }
        };
    }





    private JViewport getViewPort() {
        return scroll.getViewport();
    }

    private boolean hasLeftSkyView() {
        return taskRight != null;
    }

    private boolean hasRightSkyView() {
        return taskLeft != null;
    }

    private boolean scrollbarOnLeft() {
        return taskLeft == null;
    }


    public void addSyncListener( SynchronizeListener listener ) {
        syncListeners.add( listener );
    }

    public void scrollTo(TreeNode<FileContent> node, Rectangle rect, int sourceIdx) {

        Log.finest( " scrollTo " + idx);

        TreePath path = null;

        if ( sourceIdx > idx ) {
            // right
            path = NodeToNode.nodeToPath(
                    root,
                    taskRight.getLeftRoot(),
                    TaskUtils.getBestMatch( taskRight, node ));
        } else {
            // left
            path = NodeToNode.nodeToPath(
                    root,
                    taskLeft.getRightRoot(),
                    TaskUtils.getBestMatch( taskLeft, node ));
        }

        if ( path == null ) {
            Log.warning( "synchronized scrolling failed to match (?)" );
        }


        Rectangle here = tree.getPathBounds( path );
        myScroll = true;
        Scrolling.scrollToRelative( scroll.getViewport(), here, rect );
        myEvent = true;
        tree.setSelectionPath( path );
    }

//    private void addObjectListener( SkyViewBar skv, final TreeNode<FileContent> nodeRoot) {
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
    }
}

