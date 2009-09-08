package org.openCage.stroy.ui.docking;

import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.util.external.MacFileMerge;
import org.openCage.util.iterator.Count;
import org.openCage.util.iterator.Iterators;
import zappini.designgridlayout.DesignGridLayout;
import zappini.designgridlayout.Row;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

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
public class ShowCurrentDiff extends JPanel implements NSelectionListener{

    private final int count;
    private final List<JLabel> names   = new ArrayList<JLabel>();
    private final List<JLabel> nameDiffs = new ArrayList<JLabel>();
    private final List<JLabel> parents = new ArrayList<JLabel>();
    private final List<JLabel> parentDiffs = new ArrayList<JLabel>();
    private final List<JLabel> contentDiffs = new ArrayList<JLabel>();

    private final List<JButton> contentButton = new ArrayList<JButton>();
    private final List<JButton> viewButton = new ArrayList<JButton>();

    private final List<TreeMatchingTask<FileContent>> matchings;

    private Collection<SelectionState> currentStates;

    private final JButton externalDiff = new JButton( "content diff" );
    private final JButton breakMatch = new JButton( "breakMatch" );
    private static final String EQUAL = "===";
    private static final String NOT_EQUAL = "=/=";

    public ShowCurrentDiff( final List<TreeMatchingTask<FileContent>> matchings ) {

        this.matchings = matchings;
        this.count     = matchings.size() + 1;

        final JPanel top = new JPanel();
        final DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );


        for ( int i = 0; i < count; ++i ) {
            names.add( new JLabel("-"));
            parents.add( new JLabel("-"));

            final int which = i;
            JButton view = new JButton( "view" );
            view.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    view( which );
                }
            });
            viewButton.add( view );
        }

        for ( int i = 0; i < count - 1; ++i ) {
            nameDiffs.add( new JLabel(EQUAL));
            parentDiffs.add( new JLabel(EQUAL));
            contentDiffs.add( new JLabel(EQUAL));

            JButton cd = new JButton("--");
            final int which = i;
            cd.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    diff( which );
                }
            });
            contentButton.add( cd );
        }

        Row row = layout.row().label("parent name:   ");
        for ( int i = 0; i < count; ++i ) {
            row.add( parents.get(i));
            if ( i < count - 1 ) {
                row.add( parentDiffs.get(i));
            }
        }

        row = layout.row().label("file name:   ");
        for ( int i = 0; i < count; ++i ) {
            row.add( names.get(i));
            if ( i < count - 1 ) {
                row.add( nameDiffs.get(i));
            }
        }

        row = layout.row().label("content:   ");
        for ( int i = 0; i < count; ++i ) {
            row.add( viewButton.get(i), 3);
            if ( i < count - 1 ) {
                row.add( contentButton.get(i));
            }
        }



        int factor = matchings.size();

        layout.row().add( new JLabel(""),factor).add( externalDiff).add( new JLabel(""),factor);

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );


        final ShowCurrentDiff tis = this;

        externalDiff.setEnabled( false );
        externalDiff.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                List<TreeNode<FileContent>> nodes = new ArrayList<TreeNode<FileContent>>();

                for ( SelectionState st : tis.getCurrentStates() ) {
                    if ( st.isMatch() ) {
                        nodes.add( st.getNode() );
                    }
                }

                if ( nodes.size() == 2 ) {
                    new MacFileMerge().diff( nodes.get(0).getContent().getFile().getAbsolutePath(),
                            nodes.get(1).getContent().getFile().getAbsolutePath() );
                } else if ( nodes.size() == 3 ) {
                    new MacFileMerge().diff3( nodes.get(0).getContent().getFile().getAbsolutePath(),
                            nodes.get(1).getContent().getFile().getAbsolutePath(),
                            nodes.get(2).getContent().getFile().getAbsolutePath() );

                }
            }
        });

    }

    private void view(int which) {
        List<TreeNode<FileContent>> nodes = new ArrayList<TreeNode<FileContent>>();
        for ( SelectionState st : getCurrentStates() ) {
            nodes.add( st.getNode() );
        }

        try {

            String[] cmd = new String[2];
            cmd[0] = "/usr/bin/open";
            cmd[1] = nodes.get(which).getContent().getFile().getAbsolutePath();

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }        
    }

    private void diff(int which) {
        List<TreeNode<FileContent>> nodes = new ArrayList<TreeNode<FileContent>>();

        for ( SelectionState st : getCurrentStates() ) {
            nodes.add( st.getNode() );
        }

        if ( which == 0 ) {
            new MacFileMerge().diff(
                    nodes.get(0).getContent().getFile().getAbsolutePath(),
                    nodes.get(1).getContent().getFile().getAbsolutePath() );
        } else {
            new MacFileMerge().diff(
                    nodes.get(1).getContent().getFile().getAbsolutePath(),
                    nodes.get(2).getContent().getFile().getAbsolutePath() );            

        }
    }


    public void selected(Collection<SelectionState> states) {

        currentStates = states;

        boolean enable = false;


        for ( Count<SelectionState> st : Iterators.count(  states )) {
            if ( st.o.isMaster() ) {
                names.get( st.count ).setForeground( Color.BLUE );
                parents.get( st.count ).setForeground( Color.BLUE );
            } else {
                names.get( st.count ).setForeground( Color.BLACK);
                parents.get( st.count ).setForeground( Color.BLACK);
            }

            TreeNode<FileContent> nd = st.o.getNode();

            if ( st.o.isMatch() ) {

                names.get( st.count ).setText( nd.getContent().getName() );

//                final String ext = FileUtils.getExtension( file );
//
//                if ( !broker.contains( ext )) {
//                    Log.warning( "unknown extension for FuzzyHashGeneration " + ext);
//
//                    return new FuzzyHashNever();
//                }
//
//                switch( broker.get(ext)) {



                if ( nd.getParent() == null ) {
                    parents.get( st.count ).setText( "<root>");
                } else {
                    parents.get( st.count ).setText( nd.getParent().getContent().getName() );

                }
            } else if ( st.o.isParentMatch() ) {

                names.get( st.count ).setText( "-" );
                parents.get( st.count ).setText( nd.getContent().getName() );
            } else {
                names.get( st.count ).setText( "-" );
                parents.get( st.count ).setText( "-" );
            }
        }


        for ( Count<TreeMatchingTask<FileContent>> pp : Iterators.count( matchings )) {

            TreeMatchingTask<FileContent> matching = pp.o;
            int                            idx = pp.count;

            Iterator<SelectionState> it = states.iterator();
            SelectionState left = it.next();
            SelectionState right = it.next();

            if ( !left.isMatch() || !right.isMatch() ) {
                contentDiffs.get( pp.count ).setText( "" );
                nameDiffs.get( pp.count ).setText( "" );
                parentDiffs.get( pp.count ).setText( "" );
            } else {
                TreeNode<FileContent> nd = left.getNode();

                ChangeVector cv =  matching.getChangeVector( nd );

                if ( nd.isLeaf() ) {
                    if ( cv.content ) {
                        contentDiffs.get( pp.count ).setText(NOT_EQUAL);
                        enable = true;
                    } else {
                        contentDiffs.get( pp.count ).setText( EQUAL );
                    }
                } else {
                    contentDiffs.get( pp.count ).setText( "");
                }

                if ( cv.name ) {
                    nameDiffs.get( pp.count ).setText(NOT_EQUAL);
                } else {
                    nameDiffs.get( pp.count ).setText( EQUAL);
                }

                if ( cv.parent ) {
                    parentDiffs.get( pp.count ) .setText(NOT_EQUAL);
                } else {
                    if ( cv.parentRenamed ) {
                        parentDiffs.get( pp.count ) .setText( "(==)" );
                    } else {
                        parentDiffs.get( pp.count ) .setText( EQUAL );
                    }
                }

            }
        }


        externalDiff.setEnabled( true ); //enable );
    }


    public Collection<SelectionState> getCurrentStates() {
        return currentStates;
    }
}
