package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.ui.difftree.NWayDiffPane;
import org.openCage.stroy.ui.difftree.ShowChangeTreeCellRenderer;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.content.Content;
import org.openCage.util.iterator.Count;
import org.openCage.util.iterator.Iterators;

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
public class NWayDiffPaneMessages<T extends Content> extends JPanel implements NWayDiffPane {

    private final List<DiffTree>           dtrees;
    private List<DefaultMutableTreeNode>   roots;
    private final JButton mergeToRight = new JButton( "merge =>");

    public NWayDiffPaneMessages( final List<TreeMatchingTask<T>>      tasks,
                                 final List<DefaultMutableTreeNode>   roots,
                                 final ShowChangeTreeCellRenderer     showChangeTreeCellRenderer ) {

        this.roots = roots;
        dtrees     = createTrees(tasks, roots, showChangeTreeCellRenderer);
        connectListeners(dtrees);
        createLayout( createPanels() );

        mergeToRight.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                tasks.get(0).merge();
            }
        });
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
        top.setLayout( layout );
        // layout.row().add( mergeToRight ); TODO more merge

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
                dtrees.get( dtree.count - 1 ).addSyncListener( dtree.o );
                dtree.o.addSyncListener( dtrees.get( dtree.count - 1 ) );
            }
        }

    }

    private List<DiffTree> createTrees( List<TreeMatchingTask<T>>    tasks,
                                        List<DefaultMutableTreeNode> roots,
                                        ShowChangeTreeCellRenderer   showChangeTreeCellRenderer) {
        List<DiffTree> dtrees = new ArrayList<DiffTree>();

        for ( Count<DefaultMutableTreeNode> root : Iterators.count( roots )) {
            if ( root.isFirst() ) {
                dtrees.add( new DiffTree(
                        root.count,
                        null,
                        tasks.get(0),
                        root.o,
                        showChangeTreeCellRenderer ));
            } else if ( root.isLast() ) {
                dtrees.add( new DiffTree(
                        root.count,
                        tasks.get(root.count - 1 ),
                        null,
                        root.o,
                        showChangeTreeCellRenderer ));
            } else {
                dtrees.add( new DiffTree(
                        root.count,
                        tasks.get(root.count - 1 ),
                        tasks.get(root.count ),
                        root.o,
                        showChangeTreeCellRenderer ));
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
