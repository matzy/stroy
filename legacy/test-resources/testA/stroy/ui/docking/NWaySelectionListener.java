package org.openCage.stroy.ui.docking;

import org.openCage.util.iterator.Count;
import org.openCage.util.iterator.Iterators;
import org.openCage.util.iterator.T2;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.docking.EventExchange;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.util.ui.skyviewbar.SkyViewBar;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

/**
 * stroy, a differencing tool
 * Copyright (C) Aug 4, 2007 Stephan Pfab
 * <p/>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
public class NWaySelectionListener implements TreeSelectionListener {


    private final EventExchange eventExchange;
    private final int idx;
    private final List<TreeMatchingTask<FileContent>> matchings;
    private final List<JTree> trees;
//    private final List<JViewport> viewports;
    private final List<SkyViewBar<TreeNode<FileContent>>> skvs;
    private final List<NSelectionListener> nlisteners = new ArrayList<NSelectionListener>();

    public NWaySelectionListener( final List<TreeMatchingTask<FileContent>>      matchings,
                                  final List<JTree>                              trees,
                                  List<SkyViewBar<TreeNode<FileContent>>>        skvs,
                                  final EventExchange                            ee,
                                  final int                                      idx ) {

        this.matchings     = matchings;
        this.trees         = trees;
        this.eventExchange = ee;
        this.idx           = idx;
        this.skvs          = skvs;

        assert( trees.size() == matchings.size() + 1);
        assert( idx > 0 && idx < trees.size() );

    }

    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        if ( exchangeEvents() ) {
            return;
        }

        List<SelectionState> selectionStates = new ArrayList<SelectionState>();

        TreePath              selectedPath = treeSelectionEvent.getNewLeadSelectionPath();
        TreeNode<FileContent> selectedNode = NodeToNode.pathToNode(selectedPath);

        SelectionState selsta = new SelectionState( selectedNode, true, false, true);
        selectionStates.add( selsta );

        // propate selection left
        for ( int jdx = idx -1; jdx >= 0; --jdx ) {

            // 0 -> 0
            // 1 -> 0,1
            // 2 -> 1,2
            selsta = nextMatch( matchings.get(Math.max(0,jdx-1)), selsta );
            selectionStates.add(0, selsta );
        }

        // propate selection right
        selsta = selectionStates.get( selectionStates.size() - 1 ); // getlast == the oirignal selection
        for ( int jdx = idx +1; jdx < trees.size(); ++jdx ) {

            selsta = nextMatch( matchings.get(Math.max(0,jdx-1)), selsta );
            selectionStates.add( selsta );
        }

//        for ( SelectionState st : selectionStates ) {
//            System.out.println( st );
//        }




        for ( Count<SelectionState> st : Iterators.count( selectionStates )) {
            if ( !st.o.isMaster() ) {
                showNode( st.o.getNode(), st.o.isMatch(), trees.get( st.count ));
            }

            if ( st.count < 2 ) {
                showNodeInSkyView( skvs.get( st.count ), st.o.getNode() );
            }

            if ( st.count > 0 && selectionStates.size() > 2 ) {
                showNodeInSkyView( skvs.get( st.count + 1), st.o.getNode() );
            }
        }

        for ( NSelectionListener nsel : nlisteners ) {
            nsel.selected( selectionStates );
        }
        

    }

    private void showNodeInSkyView( SkyViewBar<TreeNode<FileContent>> skv, TreeNode<FileContent> node) {
        skv.select( node );
    }


    public void addNSelectionListener( NSelectionListener nsel ) {
        nlisteners.add( nsel );
    }

    private boolean exchangeEvents() {

        if ( eventExchange.ignore ) {
            eventExchange.ignore = false;
            return true;
        }

        return false;
    }

    private T2<TreeNode<FileContent>,Boolean> distribute( TreeNode<FileContent>          selectedNode,
                                                          TreeMatchingTask<FileContent> matching ) {

        TreeNode<FileContent> match = matching.getMatch(selectedNode);
        boolean               select = true;

        if ( match == null ) {

            while ( match == null && selectedNode != null ) {
                TreeNode<FileContent> parent = selectedNode.getParent();

                if ( parent != null ) {
                    match = matching.getMatch(parent);
                }

                selectedNode = parent;
            }

            select = false;
        }

        return new T2<TreeNode<FileContent>, Boolean>(match,select);
    }

    private void showNode( TreeNode<FileContent> node, boolean select, JTree tree ) {
        if ( node == null ) {
            return;
        }

        TreePath path = NodeToNode.nodeToPath( getRootDFMTN( tree ), getRootNode( tree ), node );
        eventExchange.ignore = true;
        if ( select ) {
            tree.setSelectionPath( path );
        } else {
            tree.clearSelection();
        }
        tree.scrollPathToVisible( path );
    }

    private DefaultMutableTreeNode getRootDFMTN(final JTree tree) {
        return ((DefaultMutableTreeNode)tree.getModel().getRoot());
    }

    private String getRootPath( final JTree tree) {
        final DefaultMutableTreeNode root = getRootDFMTN(tree);
        final UINode uiNode = (UINode)root.getUserObject();
        return uiNode.get().getContent().getFile().getPath();
    }

    private TreeNode<FileContent> getRootNode( final JTree tree) {
        final DefaultMutableTreeNode root = getRootDFMTN(tree);
        final UINode                 uiNode = (UINode)root.getUserObject();
        return uiNode.get();
    }


    private SelectionState nextMatch( TreeMatchingTask<FileContent> matching,
                                      SelectionState prev ) {
        TreeNode<FileContent> match = matching.getMatch( prev.getNode() );
        TreeNode<FileContent> node  = prev.getNode();
        boolean matched = prev.isMatch();
        boolean parentMatched = prev.isParentMatch();

        while ( match == null && node != null ) {
            if ( matched ) {
                matched = false;
                parentMatched = true;
            } else if ( parentMatched ) {
                parentMatched = false;
            }

            TreeNode<FileContent> parent = node.getParent();

            if ( parent != null ) {
                match = matching.getMatch(parent);
            }

            node = parent;
        }

        assert( node != null );

        return new SelectionState( match, matched, parentMatched, false );
    }

}
