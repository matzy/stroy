package org.openCage.stroy.ui.docking;

import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.difftree.UINode;
import org.openCage.stroy.ui.Colors;
import org.openCage.stroy.content.Content;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import zappini.designgridlayout.DesignGridLayout;

import java.awt.*;

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

public class ShowDiffSummary<T extends Content> extends JPanel {
    private final JLabel multiple = new JLabel( "multiple");
    private final JLabel only = new JLabel( "only" );
    private final JLabel content = new JLabel( "content" );
    private final JLabel renamed = new JLabel( "renamed" );
    private final JLabel moved = new JLabel( "moved" );

    public ShowDiffSummary( final java.util.List<TreeMatchingTask<T>> matchings,
                            final java.util.List<DefaultMutableTreeNode> roots ) {

        final JPanel top = new JPanel();
        final DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        only.setForeground( Colors.ONLYHERE );
        content.setForeground( Colors.CONTENT );
        renamed.setForeground( Colors.STRUCTUR );
        moved.setForeground( Colors.STRUCTUR );
        multiple.setForeground( Colors.CONTENT_AND_STRUCTUR );


        layout.row().label("   ").
                add( new JLabel( "" ), 5 ).
                add( new JLabel("dirs files" )).
                add( only ).
                add( content ).
                add( renamed ).
                add( moved ).
                add( multiple) ;

        layout.row().label( "Left:   " ).
                add( new JLabel( getRootPath( roots.get(0) ) ), 5 ).
                add( new JLabel( "" + matchings.get(0).getRightDirCount() + " " + matchings.get(0).getRightLeaveCount())).
                add( new JLabel( "" + matchings.get(0).getUnmatchedRightDirs().size() + " " + matchings.get(0).getUnmatchedRightFiles().size() )).
                add( new JLabel(""),4)
                ;

        layout.row().label( "" ).
                add( new JLabel(""), 5 ).
                add( new JLabel("") ).
                add( new JLabel("") ).
                add( new JLabel( "- " + matchings.get(0).getModifiedLeaves().size() )).
                add( new JLabel( "" + matchings.get(0).getRenamedDirs().size() + " " + matchings.get(0).getRenamedLeaves().size())).
                add( new JLabel( "" + matchings.get(0).getMovedDirs().size() + " " + matchings.get(0).getMovedLeaves().size() )).
                add( new JLabel( "- " + matchings.get(0).getComplexModifiedLeaves().size()) );

        if ( matchings.size() ==  1 ) {
            layout.row().label( "Right:   ").
                    add( new JLabel( getRootPath( roots.get(1) ) ), 5 ).
                    add( new JLabel( "" + matchings.get(0).getLeftDirCount() + " " + matchings.get(0).getLeftLeaveCount())).
                    add( new JLabel( "" + matchings.get(0).getUnmatchedLeftDirs().size() + " " + matchings.get(0).getUnmatchedLeftFiles().size() )).
                    add( new JLabel(""),4)
                    ;
        } else {
            layout.row().label( "Middle:   ").
                    add( new JLabel( getRootPath( roots.get(1) ) ), 5 ).
                    add( new JLabel( "" + matchings.get(0).getLeftDirCount() + " " + matchings.get(0).getLeftLeaveCount())).
                    add( new JLabel( "" + matchings.get(0).getUnmatchedLeftDirs().size() + " " + matchings.get(0).getUnmatchedLeftFiles().size() )).
                    add( new JLabel(""),4)
                    ;
            layout.row().label( "Middle:   ").
                    add( new JLabel( getRootPath( roots.get(1) ) ), 5 ).
                    add( new JLabel( "" + matchings.get(1).getLeftDirCount() + " " + matchings.get(1).getLeftLeaveCount())).
                    add( new JLabel( "" + matchings.get(1).getUnmatchedLeftDirs().size() + " " + matchings.get(1).getUnmatchedLeftFiles().size() )).
                    add( new JLabel(""),4)
                    ;

            layout.row().label( "" ).
                    add( new JLabel(""), 5 ).
                    add( new JLabel("") ).
                    add( new JLabel("") ).
                    add( new JLabel( "- " + matchings.get(1).getModifiedLeaves().size() )).
                    add( new JLabel( "" + matchings.get(1).getRenamedDirs().size() + " " + matchings.get(1).getRenamedLeaves().size())).
                    add( new JLabel( "" + matchings.get(1).getMovedDirs().size() + " " + matchings.get(1).getMovedLeaves().size() )).
                    add( new JLabel( "- " + matchings.get(1).getComplexModifiedLeaves().size()) );

            layout.row().label( "Right:   ").
                    add( new JLabel( getRootPath( roots.get(2) ) ), 5 ).
                    add( new JLabel( "" + matchings.get(1).getRightDirCount() + " " + matchings.get(1).getRightLeaveCount())).
                    add( new JLabel( "" + matchings.get(1).getUnmatchedRightDirs().size() + " " + matchings.get(1).getUnmatchedRightFiles().size() )).
                    add( new JLabel(""),4)
                    ;
        }

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );


    }

    private String getRootPath(  final DefaultMutableTreeNode root ) {
        final UINode<FileContent> uiNode = (UINode<FileContent>)root.getUserObject();
        return uiNode.get().getContent().getLocation();
    }


}
