package org.openCage.stroy.ui.difftree;

import org.openCage.lang.iterators.Count;
import org.openCage.lang.iterators.Iterators;
import org.openCage.stroy.ui.popup.PopupSelectorFactory;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.content.Content;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.util.logging.Log;


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
public class NWayDiffPaneMessages<T extends Content> extends JPanel implements NWayDiffPane {

    private final List<DiffTree>           dtrees;
    private List<DefaultMutableTreeNode>   roots;
    private final JButton mergeToRight = new JButton( "merge =>");
    private PopupSelectorFactory<T> popupSelectorFactory;

    public NWayDiffPaneMessages( final List<TreeMatchingTask<T>>      tasks,
                                 final List<DefaultMutableTreeNode>   roots,
                                 final ShowChangeTreeCellRenderer     showChangeTreeCellRenderer,
                                 final PopupSelectorFactory<T> popupSelectorFactory ) {

        try {
        this.popupSelectorFactory = popupSelectorFactory;
        this.roots = roots;
        } catch (Throwable exp ) {
            Log.log(exp);
        }

        List<DiffTree> dt = null;

        try {
            dt = createTrees(tasks, roots, showChangeTreeCellRenderer);
        } catch (Throwable exp ) {
            Log.log(exp);
        }
        dtrees = dt;

        try{
        connectListeners(dtrees);
        createLayout( createPanels() );

        mergeToRight.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                tasks.get(0).merge();
            }
        });
        } catch ( Throwable exp ) {
            Log.log(exp);
        }
    }

    private List<JPanel> createPanels() {
        List<JPanel> panels = new ArrayList<JPanel>();

        for ( DiffTree tree : dtrees ) {
            JPanel panel = new JPanel();
            panel.setLayout( new BorderLayout());

            panel.add( tree, BorderLayout.CENTER );
            final JTextField filePath = new JTextField();
            filePath.setEditable( false );
            panel.add( filePath, BorderLayout.SOUTH );

            tree.getTree().addTreeSelectionListener( new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                    filePath.setText( NodeToNode.getStringPath( treeSelectionEvent.getPath()) );
                }
            });

            panels.add( panel );
        }


        return panels;
    }

    private void createLayout(List<JPanel> panels ) {
        // TODO: generalize for more than 3


        setLayout( new BorderLayout( ));

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        // layout.row().grid().add( mergeToRight ); TODO more merge

        add( top, BorderLayout.NORTH );

        if ( dtrees.size() == 2 ) {
            final JSplitPane center      = new JSplitPane();

            // divider can fully collaps
            center.setOneTouchExpandable( true );
            // devider is in the middle
            center.setResizeWeight(0.5 );

            center.setLeftComponent( panels.get(0) );
            center.setRightComponent( panels.get( 1 ));

            add( center, BorderLayout.CENTER );

            return;
        }

        if ( dtrees.size() == 3 ) {
            final JSplitPane center      = new JSplitPane();

            // divider can fully collaps
            center.setOneTouchExpandable( true );
            // devider is in the middle
            center.setResizeWeight(0.66 );

            final JSplitPane left      = new JSplitPane();
            left.setOneTouchExpandable( true );
            left.setResizeWeight(0.5 );


            left.setLeftComponent( panels.get(0) );
            left.setRightComponent( panels.get( 1 ));
            center.setLeftComponent( left );
            center.setRightComponent( panels.get( 2 ));

            add( center, BorderLayout.CENTER );

            return;
        }

        throw new IllegalStateException("not impl > 3");
    }

    private void connectListeners(List<DiffTree> dtrees) {

        for ( Count<DiffTree> dtree : Iterators.count(dtrees)) {
            if ( !dtree.isFirst() ) {
                dtrees.get( dtree.idx() - 1 ).addSyncListener( dtree.obj() );
                dtree.obj().addSyncListener( dtrees.get( dtree.idx() - 1 ) );
            }
        }

    }

    private List<DiffTree> createTrees( List<TreeMatchingTask<T>>    tasks,
                                        List<DefaultMutableTreeNode> roots,
                                        ShowChangeTreeCellRenderer   showChangeTreeCellRenderer) {
        List<DiffTree> dtrees = new ArrayList<DiffTree>();

        for ( Count<DefaultMutableTreeNode> root : Iterators.count(roots)) {
            if ( root.isFirst() ) {
                dtrees.add( new DiffTree(
                        root.idx(),
                        null,
                        tasks.get(0),
                        root.obj(),
                        showChangeTreeCellRenderer,
                        popupSelectorFactory));
            } else if ( root.isLast() ) {
                dtrees.add( new DiffTree(
                        root.idx(),
                        tasks.get(root.idx() - 1 ),
                        null,
                        root.obj(),
                        showChangeTreeCellRenderer,
                        popupSelectorFactory ));
            } else {
                dtrees.add( new DiffTree(
                        root.idx(),
                        tasks.get(root.idx() - 1 ),
                        tasks.get(root.idx() ),
                        root.obj(),
                        showChangeTreeCellRenderer,
                        popupSelectorFactory ));
            }
        }

        return dtrees;
    }


    public JPanel getPanel() {
        return this;
    }

    public JTree getTree(int idx) {
        return dtrees.get(idx).getTree();
    }

    public DefaultMutableTreeNode getRoot(int idx) {
        return roots.get(idx);
    }

    //    public SkyViewBar getSkyView(int idx);

    public void refresh() {
        for ( DiffTree tree : dtrees ) {
            tree.refresh();
        }
    }

    public void elementRefresh() {
        for ( DiffTree tree : dtrees ) {
            tree.elementRefresh();
        }
    }

//    public SkyViewBar getSkyView(int idx) {
//        return dtrees.get(idx).getSkyView();
//    }
}
