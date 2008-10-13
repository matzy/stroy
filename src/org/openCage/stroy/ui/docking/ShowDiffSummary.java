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

public class ShowDiffSummary<T extends Content> extends JPanel {
    private final JLabel multiple = new JLabel( Message.get( "Summary.multiple" ));
    private final JLabel only = new JLabel( Message.get( "Summary.only" ));
    private final JLabel content = new JLabel( Message.get( "Summary.content" ));
    private final JLabel renamed = new JLabel( Message.get( "Summary.renamed" ));
    private final JLabel moved = new JLabel( Message.get( "Summary.moved" ));

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

        layout.row().label( new JLabel( "Summary.left" )).add( new JLabel( getRootPath( roots.get(0) ) ));
        layout.row().label( new JLabel( "Summary.right" )).add( new JLabel( getRootPath( roots.get(1) ) ));
;

        layout.row().label( new JLabel("   ")).
                add( new JLabel( Message.get( "Summary.total" ))).
                add( only ).
                add( content ).
                add( renamed ).
                add( moved ).
                add( multiple) ;

        layout.row().label( Message.getl( "Summary.leftdirs" )).
                add( new JLabel( "" + matchings.get(0).getRightDirCount())).
                add( new JLabel( "" + matchings.get(0).getUnmatchedRightDirs().size() )).
                add( new JLabel(""),4)
                ;
        layout.row().label( Message.getl( "Summary.leftfiles" )).
                add( new JLabel( "" + matchings.get(0).getRightLeaveCount())).
                add( new JLabel( "" + matchings.get(0).getUnmatchedRightFiles().size() )).
                add( new JLabel(""),4)
                ;

        layout.row().label( Message.getl( "Summary.changeddirs" )).
                add( new JLabel("") ).
                add( new JLabel("") ).
                add( new JLabel( "- " + matchings.get(0).getModifiedLeaves().size() )).
                add( new JLabel( "" + matchings.get(0).getRenamedDirs().size() + " " + matchings.get(0).getRenamedLeaves().size())).
                add( new JLabel( "" + matchings.get(0).getMovedDirs().size() + " " + matchings.get(0).getMovedLeaves().size() )).
                add( new JLabel( "- " + matchings.get(0).getComplexModifiedLeaves().size()) );
        layout.row().label( Message.getl( "Summary.changedfiles" )).
                add( new JLabel("") ).
                add( new JLabel("") ).
                add( new JLabel( "- " + matchings.get(0).getModifiedLeaves().size() )).
                add( new JLabel( "" + matchings.get(0).getRenamedDirs().size() + " " + matchings.get(0).getRenamedLeaves().size())).
                add( new JLabel( "" + matchings.get(0).getMovedDirs().size() + " " + matchings.get(0).getMovedLeaves().size() )).
                add( new JLabel( "- " + matchings.get(0).getComplexModifiedLeaves().size()) );

        if ( matchings.size() ==  1 ) {
            layout.row().label( Message.getl( "Summary.rightdirs" )).
                    add( new JLabel( "" + matchings.get(0).getLeftDirCount() )).
                    add( new JLabel( "" + matchings.get(0).getUnmatchedLeftDirs().size()  )).
                    add( new JLabel(""),4)
                    ;
            layout.row().label( Message.getl( "Summary.rightfiles" )).
                    add( new JLabel( "" + matchings.get(0).getLeftLeaveCount())).
                    add( new JLabel( "" + matchings.get(0).getUnmatchedLeftFiles().size() )).
                    add( new JLabel(""),4)
                    ;
        } else {

//            // TODO 3 way
//
//            layout.row().label( "Middle:   ").
//                    add( new JLabel( getRootPath( roots.get(1) ) ), 5 ).
//                    add( new JLabel( "" + matchings.get(0).getLeftDirCount() + " " + matchings.get(0).getLeftLeaveCount())).
//                    add( new JLabel( "" + matchings.get(0).getUnmatchedLeftDirs().size() + " " + matchings.get(0).getUnmatchedLeftFiles().size() )).
//                    add( new JLabel(""),4)
//                    ;
//            layout.row().label( "Middle:   ").
//                    add( new JLabel( getRootPath( roots.get(1) ) ), 5 ).
//                    add( new JLabel( "" + matchings.get(1).getLeftDirCount() + " " + matchings.get(1).getLeftLeaveCount())).
//                    add( new JLabel( "" + matchings.get(1).getUnmatchedLeftDirs().size() + " " + matchings.get(1).getUnmatchedLeftFiles().size() )).
//                    add( new JLabel(""),4)
//                    ;
//
//            layout.row().label( "" ).
//                    add( new JLabel(""), 5 ).
//                    add( new JLabel("") ).
//                    add( new JLabel("") ).
//                    add( new JLabel( "- " + matchings.get(1).getModifiedLeaves().size() )).
//                    add( new JLabel( "" + matchings.get(1).getRenamedDirs().size() + " " + matchings.get(1).getRenamedLeaves().size())).
//                    add( new JLabel( "" + matchings.get(1).getMovedDirs().size() + " " + matchings.get(1).getMovedLeaves().size() )).
//                    add( new JLabel( "- " + matchings.get(1).getComplexModifiedLeaves().size()) );
//
//            layout.row().label( "Right:   ").
//                    add( new JLabel( getRootPath( roots.get(2) ) ), 5 ).
//                    add( new JLabel( "" + matchings.get(1).getRightDirCount() + " " + matchings.get(1).getRightLeaveCount())).
//                    add( new JLabel( "" + matchings.get(1).getUnmatchedRightDirs().size() + " " + matchings.get(1).getUnmatchedRightFiles().size() )).
//                    add( new JLabel(""),4)
//                    ;
        }

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );


    }

    private String getRootPath(  final DefaultMutableTreeNode root ) {
        final UINode<FileContent> uiNode = (UINode<FileContent>)root.getUserObject();
        return uiNode.get().getContent().getLocation();
    }


}
