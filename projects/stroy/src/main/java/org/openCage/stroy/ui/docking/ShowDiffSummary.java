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

public class ShowDiffSummary<T extends Content> extends JPanel {
    private final JLabel multiple = new JLabel( Message.get( "Summary.multiple" ));
    private final JLabel only = new JLabel( Message.get( "Summary.only", 0 ));
    private final JLabel content = new JLabel( Message.get( "Summary.content" ));
    private final JLabel renamed = new JLabel( Message.get( "Summary.renamed" ));
    private final JLabel moved = new JLabel( Message.get( "Summary.moved" ));
    private final JButton refresh = new JButton( Message.get( "Button.refresh" ));
    private final JLabel leftRootDir;
    private final List<TreeMatchingTask<T>> matchings;
    private final JLabel leftDirsTotal = new JLabel();
    private final JLabel leftFilesTotal =  new JLabel();
    private final JLabel leftDirsOnly = new JLabel();
    private final JLabel leftFilesOnly = new JLabel();
    private final JLabel renamedDirs = new JLabel();
    private final JLabel movedDirs = new JLabel();
    private final JLabel contentLeaves = new JLabel();
    private final JLabel renamedFiles = new JLabel();
    private final JLabel movedFiles = new JLabel();
    private final JLabel complexChanged = new JLabel();
    private final JLabel rightDirsTotal = new JLabel();
    private final JLabel rightDirsOnly = new JLabel();
    private final JLabel rightFilesTotal = new JLabel();
    private final JLabel rightFilesOnly = new JLabel();
    private boolean onlyhas2Rows = false;

    public ShowDiffSummary( final java.util.List<TreeMatchingTask<T>> matchings,
                            final java.util.List<DefaultMutableTreeNode> roots ) {

        this.matchings = matchings;

        refresh();

        final JPanel top = new JPanel();
        final DesignGridLayout layout = new DesignGridLayout( top );
        //top.setLayout( layout );

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
        layout.row().grid( new JLabel( Message.get( "Summary.first" ))).add( leftRootDir, contentSize + renamedSize + movedSize ).add( refresh, multipleSize );
        layout.row().grid( new JLabel( Message.get( "Summary.second" ))).add( new JLabel( getRootPath( roots.get(1) ) ));

        layout.row().grid().empty( 20 );

        // total + only

        JLabel only2 = new JLabel();
        only.setForeground( Colors.ONLYHERE );
        only2.setForeground( Colors.ONLYHERE );

        layout.row().grid().empty().
                add( new JLabel( Message.get( "Summary.total" )), totalSize).
                add( only, onlySize );
        if ( Message.hasNewLines( "Summary.only" )) {

            only2.setText( Message.get("Summary.only", 1 ));

            layout.row().grid().empty( 1 + totalSize ).
                    add( only2, onlySize )
                    //empty(contentSize + renamedSize + movedSize + multipleSize)
                    ;
        }

        layout.row().grid( Message.getl( "Summary.first" )).add( new JLabel( Message.get("Summary.dirs"))).
                add( leftDirsTotal, totalSize ).
                add( leftDirsOnly, onlySize )
                //empty(contentSize + renamedSize + movedSize + multipleSize)
                ;
        layout.row().grid().add( Message.getl( "Summary.files" )).
                add( leftFilesTotal, totalSize ).
                add( leftFilesOnly, onlySize )
                //empty(4)
                ;


        layout.row().grid( Message.getl( "Summary.second" )).add( new JLabel( Message.get("Summary.dirs"))).
                add( rightDirsTotal, totalSize ).
                add( rightDirsOnly, onlySize )
                //empty(4)
                ;
        layout.row().grid().add( Message.getl( "Summary.files" )).
                add( rightFilesTotal, totalSize ).
                add( rightFilesOnly, onlySize )
                //empty(4)
                ;


        // change

        content.setForeground( Colors.CONTENT );
        renamed.setForeground( Colors.STRUCTUR );
        moved.setForeground( Colors.STRUCTUR );
        multiple.setForeground( Colors.CONTENT_AND_STRUCTUR );

        layout.row().grid().empty();
        layout.row().grid().empty().
                add( content, contentSize ).
                add( renamed, renamedSize ).
                add( moved, movedSize ).
                add( multiple, multipleSize) ;

        layout.row().grid( Message.getl( "Summary.changed" )).
                add( new JLabel( Message.get("Summary.dirs"))).
                add( new JLabel( "-" ), contentSize ).
                add( renamedDirs, renamedSize ).
                add( movedDirs, movedSize ).
                add( new JLabel( "-" ), multipleSize
                );

        layout.row().grid().
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
        renamedDirs.setText(  "" + matchings.get(0).getRenamedDirs().size());
        movedDirs.setText( "" + matchings.get(0).getMovedDirs().size() );
        contentLeaves.setText(  "" + matchings.get(0).getModifiedLeaves().size() );
        renamedFiles.setText( "" + matchings.get(0).getRenamedLeaves().size());
        movedFiles.setText(  "" + matchings.get(0).getMovedLeaves().size() );
        complexChanged.setText( "" + matchings.get(0).getComplexModifiedLeaves().size());

        rightDirsTotal.setText( "" + matchings.get(0).getRightDirCount());
        rightDirsOnly.setText(  "" + matchings.get(0).getUnmatchedRightDirs().size() );
        rightFilesTotal.setText( "" + matchings.get(0).getRightLeaveCount());
        rightFilesOnly.setText(  "" + matchings.get(0).getUnmatchedRightFiles().size() );

        leftDirsTotal.setText( "" + matchings.get(0).getLeftDirCount() );
        leftDirsOnly.setText( "" + matchings.get(0).getUnmatchedLeftDirs().size()  );
        leftFilesTotal.setText( "" + matchings.get(0).getLeftLeaveCount());
        leftFilesOnly.setText(  "" + matchings.get(0).getUnmatchedLeftFiles().size() );
    }

    private String getRootPath(  final DefaultMutableTreeNode root ) {
        final UINode<FileContent> uiNode = (UINode<FileContent>)root.getUserObject();
        return uiNode.get().getContent().getLocation();
    }


}
