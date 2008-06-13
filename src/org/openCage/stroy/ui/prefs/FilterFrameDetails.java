package org.openCage.stroy.ui.prefs;

import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.locale.Message;
import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;


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

public class FilterFrameDetails extends JPanel {

    private IgnoreCentral central;
    private JList extList;
    private JList patternList;
    private JList pathList;

    private JButton resetExtension = new JButton( "reset" );
    private JButton resetPaths = new JButton( "reset" );
    private JButton resetPatterns = new JButton( "reset" );
    private final JButton addPatternButton = new JButton( "+");
    private final JButton delPatternButton = new JButton( "-");
    private final JTextField addPatternField = new JTextField();

    private final JButton addPathButton = new JButton( "+");
    private final JButton delPathButton = new JButton( "-");
    private final JTextField addPathField = new JTextField();

    private final JButton addExtButton = new JButton( "+");
    private final JButton delExtButton = new JButton( "-");
    private final JTextField addExtField = new JTextField();

    public FilterFrameDetails() {


        central     = IgnoreCentral.create();
        extList     = new JList( new Vector( central.get().extensions  ));
        patternList = new JList( new Vector( central.get().patterns  ));
        pathList    = new JList( new Vector( central.get().paths  ));

        createLayout();

        addListenersStd( extList, delExtButton );
        addListenersStd( pathList, delPathButton );
        addListenersStd( patternList, delPatternButton );

        resetExtension.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                central.resetExtensions();
                extList.setListData(new Vector( central.get().extensions  ));
            }
        });

        resetPaths.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                central.resetPaths();
                pathList.setListData(new Vector( central.get().paths  ));
            }
        });

        resetPatterns.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                central.resetPatterns();
                patternList.setListData(new Vector( central.get().patterns  ));
            }
        });

        addExtButton.setEnabled( false );
        addPathButton.setEnabled( false );
        addPatternButton.setEnabled( false );
        delExtButton.setEnabled( false );
        delPathButton.setEnabled( false );
        delPatternButton.setEnabled( false );

        delExtButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ext = (String)extList.getSelectedValue();

                central.removeExtension( ext );
                extList.setListData(new Vector( central.get().extensions  ));
            }
        });

        delPatternButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pat = (String)patternList.getSelectedValue();

                central.removePattern( pat );
                patternList.setListData(new Vector( central.get().patterns  ));
            }
        });

        delPathButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = (String)pathList.getSelectedValue();

                central.removePath( path );
                pathList.setListData(new Vector( central.get().paths  ));
            }
        });
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
        add( createPatternList( "By Extension", extList, addExtButton, delExtButton, resetExtension, addExtField ), cnstraint );


        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 2;
        cnstraint.weightx = 0.7;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);
        add( createPatternList( "By Pattern", patternList, addPatternButton, delPatternButton, resetPatterns, addPatternField ), cnstraint );

        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 3;
        cnstraint.weightx = 0.7;
        cnstraint.weighty = 0.33;
        cnstraint.insets = new Insets(5,5,0,0);
        add( createPatternList( "By Path", pathList, addPathButton, delPathButton, resetPaths, addPathField ), cnstraint );
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
        top.setLayout( layout );
        layout.row().add( addButton).add( field, 5 );
        layout.row().add( delButton).add( new JLabel(" "), 5 );
        layout.row().add( new JLabel(" ") );
        layout.row().add( new JLabel(" ") );
        layout.row().add( new JLabel(" ") );
        layout.row().add( resetButton).add( new JLabel(" "), 5 );

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
