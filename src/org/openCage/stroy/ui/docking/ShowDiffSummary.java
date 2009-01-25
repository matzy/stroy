package org.openCage.stroy.ui.docking;

import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.stroy.ui.Colors;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.locale.Message;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

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
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class ShowDiffSummary<T extends Content> extends JPanel {
    private final JLabel multiple = new JLabel( Message.get( "Summary.multiple" ));
    private final JLabel only = new JLabel( Message.get( "Summary.only", 0 ));
    private final JLabel content = new JLabel( Message.get( "Summary.content" ));
    private final JLabel renamed = new JLabel( Message.get( "Summary.renamed" ));
    private final JLabel moved = new JLabel( Message.get( "Summary.moved" ));
    private final JButton refresh = new JButton( Message.get( "Button.refresh" ));
    private final JLabel leftRootDir;
    private final List<TreeMatchingTask<T>> matchings;
    private JLabel leftDirsTotal;
    private JLabel leftFilesTotal;
    private JLabel leftDirsOnly;
    private JLabel leftFilesOnly;
    private JLabel renamedDirs;
    private JLabel movedDirs;
    private JLabel contentLeaves;
    private JLabel renamedFiles;
    private JLabel movedFiles;
    private JLabel complexChanged;
    private JLabel rightDirsTotal;
    private JLabel rightDirsOnly;
    private JLabel rightFilesTotal;
    private JLabel rightFilesOnly;
    private boolean onlyhas2Rows = false;

    public ShowDiffSummary( final java.util.List<TreeMatchingTask<T>> matchings,
                            final java.util.List<DefaultMutableTreeNode> roots ) {

        this.matchings = matchings;

        final JPanel top = new JPanel();
        final DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        int totalSize = 1;
        int onlySize = 1;
        int contentSize = 1;
        int renamedSize = 1;
        int movedSize = 1;
        int multipleSize = 1;

//        if ( true ) {
//            totalSize = 2;
//            onlySize = 3;
//            movedSize = 2;
//            multipleSize = 3;
//        }


        leftRootDir = new JLabel( getRootPath( roots.get(0) ) );
        layout.row().label( new JLabel( Message.get( "Summary.first" ))).add( leftRootDir, contentSize + renamedSize + movedSize ).add( refresh, multipleSize );
        layout.row().label( new JLabel( Message.get( "Summary.second" ))).add( new JLabel( getRootPath( roots.get(1) ) ));

        layout.emptyRow( 20 );

        // total + only

        JLabel only2 = new JLabel();
        only.setForeground( Colors.ONLYHERE );
        only2.setForeground( Colors.ONLYHERE );

        layout.row().empty().
                add( new JLabel( Message.get( "Summary.total" )), totalSize).
                add( only, onlySize );
        if ( Message.hasNewLines( "Summary.only" )) {

            only2.setText( Message.get("Summary.only", 1 ));

            layout.row().empty( 1 + totalSize ).
                    add( only2, onlySize )
                    //empty(contentSize + renamedSize + movedSize + multipleSize)
                    ;
        }

        leftDirsTotal = new JLabel( "" + matchings.get(0).getRightDirCount());
        leftDirsOnly = new JLabel( "" + matchings.get(0).getUnmatchedRightDirs().size() );

        layout.row().label( Message.getl( "Summary.first" )).add( new JLabel( Message.get("Summary.dirs"))).
                add( leftDirsTotal, totalSize ).
                add( leftDirsOnly, onlySize )
                //empty(contentSize + renamedSize + movedSize + multipleSize)
                ;
        leftFilesTotal = new JLabel( "" + matchings.get(0).getRightLeaveCount());
        leftFilesOnly = new JLabel( "" + matchings.get(0).getUnmatchedRightFiles().size() );
        layout.row().add( Message.getl( "Summary.files" )).
                add( leftFilesTotal, totalSize ).
                add( leftFilesOnly, onlySize )
                //empty(4)
                ;


        rightDirsTotal = new JLabel( "" + matchings.get(0).getLeftDirCount() );
        rightDirsOnly = new JLabel( "" + matchings.get(0).getUnmatchedLeftDirs().size()  );
        layout.row().label( Message.getl( "Summary.second" )).add( new JLabel( Message.get("Summary.dirs"))).
                add( rightDirsTotal, totalSize ).
                add( rightDirsOnly, onlySize )
                //empty(4)
                ;
        rightFilesTotal = new JLabel( "" + matchings.get(0).getLeftLeaveCount());
        rightFilesOnly = new JLabel( "" + matchings.get(0).getUnmatchedLeftFiles().size() );
        layout.row().add( Message.getl( "Summary.files" )).
                add( rightFilesTotal, totalSize ).
                add( rightFilesOnly, onlySize )
                //empty(4)
                ;


        // change

        content.setForeground( Colors.CONTENT );
        renamed.setForeground( Colors.STRUCTUR );
        moved.setForeground( Colors.STRUCTUR );
        multiple.setForeground( Colors.CONTENT_AND_STRUCTUR );

        layout.row().empty();
        layout.row().empty().
                add( content, contentSize ).
                add( renamed, renamedSize ).
                add( moved, movedSize ).
                add( multiple, multipleSize) ;

        renamedDirs = new JLabel( "" + matchings.get(0).getRenamedDirs().size());
        movedDirs = new JLabel( "" + matchings.get(0).getMovedDirs().size() );
        layout.row().label( Message.getl( "Summary.changed" )).
                add( new JLabel( Message.get("Summary.dirs"))).
                add( new JLabel( "-" ), contentSize ).
                add( renamedDirs, renamedSize ).
                add( movedDirs, movedSize ).
                add( new JLabel( "-" ), multipleSize
                );

        contentLeaves = new JLabel( "" + matchings.get(0).getModifiedLeaves().size() );
        renamedFiles = new JLabel( "" + matchings.get(0).getRenamedLeaves().size());
        movedFiles = new JLabel( "" + matchings.get(0).getMovedLeaves().size() );
        complexChanged = new JLabel( "" + matchings.get(0).getComplexModifiedLeaves().size());
        layout.row().
                add( Message.getl( "Summary.files" )).
                add( contentLeaves, contentSize ).
                add( renamedFiles, renamedSize ).
                add( movedFiles, movedSize ).
                add( complexChanged, multipleSize );

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );

        refresh.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                refresh();
            }
        } );



        addComponentListener( new ComponentListener() {
            public void componentResized( ComponentEvent e ) {
                refresh();
            }

            public void componentMoved( ComponentEvent e ) {}
            public void componentShown( ComponentEvent e ) {}
            public void componentHidden( ComponentEvent e ) {}
        } );


    }

    private void refresh() {
        leftDirsTotal.setText( "" + matchings.get(0).getRightDirCount());
        leftDirsOnly.setText(  "" + matchings.get(0).getUnmatchedRightDirs().size() );
        leftFilesTotal.setText( "" + matchings.get(0).getRightLeaveCount());
        leftFilesOnly.setText(  "" + matchings.get(0).getUnmatchedRightFiles().size() );
        renamedDirs.setText(  "" + matchings.get(0).getRenamedDirs().size());
        movedDirs.setText( "" + matchings.get(0).getMovedDirs().size() );
        contentLeaves.setText(  "" + matchings.get(0).getModifiedLeaves().size() );
        renamedFiles.setText( "" + matchings.get(0).getRenamedLeaves().size());
        movedFiles.setText(  "" + matchings.get(0).getMovedLeaves().size() );
        complexChanged.setText( "" + matchings.get(0).getComplexModifiedLeaves().size());
        rightDirsTotal.setText( "" + matchings.get(0).getLeftDirCount() );
        rightDirsOnly.setText( "" + matchings.get(0).getUnmatchedLeftDirs().size()  );
        rightFilesTotal.setText( "" + matchings.get(0).getLeftLeaveCount());
        rightFilesOnly.setText(  "" + matchings.get(0).getUnmatchedLeftFiles().size() );
    }

    private String getRootPath(  final DefaultMutableTreeNode root ) {
        final UINode<FileContent> uiNode = (UINode<FileContent>)root.getUserObject();
        return uiNode.get().getContent().getLocation();
    }


}
