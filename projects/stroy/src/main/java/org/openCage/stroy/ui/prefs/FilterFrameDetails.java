package org.openCage.stroy.ui.prefs;

import org.openCage.stroy.filter.IgnoreCentral5;
import org.openCage.stroy.locale.Message;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

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

public class FilterFrameDetails extends JPanel {

    private final IgnoreCentral5 central;
    private JList extList;
    private JList patternList;
    private JList pathList;

    private JButton resetExtension = new JButton( Message.get( "Button.reset") );
    private JButton resetPaths = new JButton( Message.get( "Button.reset") );
    private JButton resetPatterns = new JButton( Message.get( "Button.reset") );
    private final JButton addPatternButton = new JButton( "+");
    private final JButton delPatternButton = new JButton( "-");
    private final JTextField addPatternField = new JTextField();

    private final JButton addPathButton = new JButton( "+");
    private final JButton delPathButton = new JButton( "-");
    private final JTextField addPathField = new JTextField();

    private final JButton addExtButton = new JButton( "+");
    private final JButton delExtButton = new JButton( "-");
    private final JTextField addExtField = new JTextField();

    public FilterFrameDetails(IgnoreCentral5 central) {
        this.central = central;


//        extList     = new JList( new Vector( this.central.get().extensions  ));
        patternList = new JList( new Vector( this.central.getPatterns()  ));
//        pathList    = new JList( new Vector( this.central.get().paths  ));

        createLayout();

//        addListenersStd( extList, delExtButton );
//        addListenersStd( pathList, delPathButton );
        addListenersStd( patternList, delPatternButton );

//        resetExtension.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                FilterFrameDetails.this.central.resetExtensions();
//                extList.setListData(new Vector( FilterFrameDetails.this.central.get().extensions  ));
//            }
//        });

//        resetPaths.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                FilterFrameDetails.this.central.resetPaths();
//                pathList.setListData(new Vector( FilterFrameDetails.this.central.get().paths  ));
//            }
//        });

        resetPatterns.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
//                FilterFrameDetails.this.central.resetPatterns();
//                patternList.setListData(new Vector( FilterFrameDetails.this.central.get().patterns  ));
            }
        });

        addExtButton.setEnabled( false );
        addPathButton.setEnabled( false );
        addPatternButton.setEnabled( false );
        delExtButton.setEnabled( false );
        delPathButton.setEnabled( false );
        delPatternButton.setEnabled( false );

//        delExtButton.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String ext = (String)extList.getSelectedValue();
//
//                FilterFrameDetails.this.central.removeExtension(ext);
//                extList.setListData(new Vector( FilterFrameDetails.this.central.get().extensions  ));
//            }
//        });

        delPatternButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pat = (String)patternList.getSelectedValue();

                FilterFrameDetails.this.central.getPatterns().remove(pat);
                patternList.setListData(new Vector( FilterFrameDetails.this.central.getPatterns()  ));
            }
        });

//        delPathButton.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String path = (String)pathList.getSelectedValue();
//
//                FilterFrameDetails.this.central.removePath(path);
//                pathList.setListData(new Vector( FilterFrameDetails.this.central.get().paths  ));
//            }
//        });
    }

    private void addListenersStd( final JList list, final JButton delButton  ) {
        list.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Object obj = list.getSelectedValue();

                if ( obj == null ) {
                    delButton.setEnabled( false );
                } else {
                    delButton.setEnabled( true );
                }
            }
        });
    }

    private void createLayout() {

        setLayout( new GridBagLayout());

        GridBagConstraints cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 0;
  //      cnstraint.weightx = 0.7;
//        cnstraint.weighty = 0.33;
//        cnstraint.insets = new Insets(5,5,0,0);
        this.add( new JLabel( Message.get( "Pref.Filter.Intro" )));



        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 1;
        cnstraint.weightx = 0.7;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);
        add( createPatternList( Message.get("Pref.Filter.ByExtension"), extList, addExtButton, delExtButton, resetExtension, addExtField ), cnstraint );


        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 2;
        cnstraint.weightx = 0.7;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);
        add( createPatternList( Message.get("Pref.Filter.ByPattern"), patternList, addPatternButton, delPatternButton, resetPatterns, addPatternField ), cnstraint );

        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 3;
        cnstraint.weightx = 0.7;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);
        add( createPatternList( Message.get("Pref.Filter.ByPath"), pathList, addPathButton, delPathButton, resetPaths, addPathField ), cnstraint );
    }

    private JPanel createPatternList( String title, JList list, JButton addButton, JButton delButton, JButton resetButton, JTextField field ) {
        JPanel patternPanel = new JPanel();
        patternPanel.setBorder( new TitledBorder( title ));
        patternPanel.setLayout( new GridBagLayout());

        GridBagConstraints cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 0;
        cnstraint.weightx = 0.5;
        cnstraint.weighty = 1;
        //cnstraint.insets = new Insets(5,5,0,0);
        patternPanel.add( new JScrollPane( list ), cnstraint );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        layout.row().grid().add( addButton).add( field, 5 );
        layout.row().grid().add( delButton).add( new JLabel(" "), 5 );
        layout.row().grid().add( new JLabel(" ") );
        layout.row().grid().add( new JLabel(" ") );
        layout.row().grid().add( new JLabel(" ") );
        layout.row().grid().add( resetButton).add( new JLabel(" "), 5 );

        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 1;
        cnstraint.gridy = 0;
        cnstraint.weightx = 0.5;
        cnstraint.weighty = 1;
        //cnstraint.insets = new Insets(5,5,0,0);
        patternPanel.add( top, cnstraint );

        return patternPanel;

    }


}
