package org.openCage.stroy.ui.prefs;

import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.file.SimilarityAlgorithm;
import org.openCage.util.ui.FileChooser;
import org.openCage.util.io.FileUtils;
import org.openCage.util.external.ExternalProgs;
import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.*;
import java.util.List;


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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
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

public class ExternalPref extends JPanel {
    private JTextField descriptionField = new JTextField();

    private FileTypes fileTypes;

    private JComboBox algoBox;
    private JButton algoReset = new JButton( "reset" );
    private JButton diffReset = new JButton( "reset" );
    private JList extList;
    private JButton openReset = new JButton( "reset");
    private final JButton addButton = new JButton( "+" );
    private final JButton openDir = new JButton( ".." );
    private final JButton diffDir = new JButton( ".." );
    private final JTextField diffText = new JTextField("");
    private final JTextField openText = new JTextField("");
    private final JFrame frame;
    private final JButton openSystem = new JButton( "system" );
    private final JButton diffStd = new JButton( "std" );

    public ExternalPref( final JFrame frame ) {

        this.frame = frame;

        fileTypes = FileTypes.create();

        List<String> exts = new ArrayList<String>( fileTypes.getTypeList());
        Collections.sort( exts );
        extList = new JList( new Vector(exts ));

        List<String> algos = new ArrayList<String>();
        for ( SimilarityAlgorithm algo : SimilarityAlgorithm.values() ) {
            algos.add( algo.toString() );
        }

        algoBox = new JComboBox( new Vector(algos) );

        createLayout();
        setEnabledAll( false );
        addButton.setEnabled( false );
        addListeners();
    }

    private void addListeners() {
        extList.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                String ext = (String)extList.getSelectedValue();
                descriptionField.setText( fileTypes.getDescription( ext ));
                algoBox.setSelectedItem( fileTypes.getAlgo(ext ).toString() );
                diffText.setText( fileTypes.getDiffType(ext));
                openText.setText( fileTypes.getOpen( ext ));
                setEnabledAll( true );
            }
        });

        descriptionField.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased( keyEvent );
                fileTypes.setDescription( (String)extList.getSelectedValue(), descriptionField.getText() );
            }
        });



        diffListeners();
        openListeners();
        algoListeners();

    }

    private void algoListeners() {
        algoReset.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String ext = (String)extList.getSelectedValue();
                fileTypes.resetAlgo( ext );
                algoBox.setSelectedItem( fileTypes.getAlgo( ext ).toString() );
            }
        });

        algoBox.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                fileTypes.setAlgo( (String)extList.getSelectedValue(), (String)algoBox.getSelectedItem() );
            }
        });
    }

    private void openListeners() {
        openText.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased( keyEvent );
                fileTypes.setOpenProg( (String)extList.getSelectedValue(), openText.getText() );
            }
        });
        openReset.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String ext = (String)extList.getSelectedValue();
                fileTypes.resetOpenProg( ext );
                openText.setText( fileTypes.getOpen(ext));
            }
        });
        openDir.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                    String path = FileChooser.open( frame, FileUtils.getCurrentDir());

                    if ( path != null ) {
                        openText.setText( FileUtils.normalizePath( path ));
                        fileTypes.setOpenProg( (String)extList.getSelectedValue(), path );
                    }

            }
        });

        openSystem.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                openText.setText( ExternalProgs.open );
                fileTypes.setOpenProg( (String)extList.getSelectedValue(), ExternalProgs.open );
            }
        });
    }

    private void diffListeners() {
        diffText.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased( keyEvent );
                fileTypes.setDiffProg( (String)extList.getSelectedValue(), diffText.getText() );
            }
        });
        diffReset.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String ext = (String)extList.getSelectedValue();
                fileTypes.resetDiffProg( ext );
                diffText.setText( fileTypes.getDiffType(ext));
            }
        });

        diffDir.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                    String path = FileChooser.open( frame, FileUtils.getCurrentDir());

                    if ( path != null ) {
                        diffText.setText( FileUtils.normalizePath( path ));
                        fileTypes.setDiffProg( (String)extList.getSelectedValue(), path );
                    }

            }
        });
        diffStd.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                diffText.setText( StandardProgUI.STANDARD_DIFF_TEXT);
                fileTypes.setDiffProg( (String)extList.getSelectedValue(), StandardProgUI.STANDARD_DIFF_TEXT );
            }
        });
    }


    public void showExtension(String extension) {
        extList.setSelectedValue( extension, true );
    }

    public void createLayout() {


        JPanel bottom = new JPanel();
           DesignGridLayout layoutB = new DesignGridLayout( bottom );
           bottom.setLayout( layoutB );

        layoutB.row().add( new JLabel( "Description"), 3 ).        add( descriptionField, 6 );
        layoutB.row().add( new JLabel( "Similarity Algorithm"),3). add( algoBox, 6 );
        layoutB.row().add( new JLabel(" "), 3).                    add( algoReset, 1 ).add( new JLabel(" "), 5);
        layoutB.row().add( new JLabel( "External Diff Program"),3).add( diffText, 5 ).add( diffDir );
        layoutB.row().add( new JLabel(" "), 3).                    add( diffReset, 1 ).add( diffStd, 1 ).add( new JLabel(" "), 4);
        layoutB.row().add( new JLabel( "External Open Program"),3).add( openText, 5 ).add( openDir );
        layoutB.row().add( new JLabel(" "), 3).                    add( openReset, 1 ).add( openSystem, 1).add( new JLabel(" "), 4 );
        layoutB.row().add( new JLabel("  ") );
        layoutB.row().add( new JLabel("  ") );
        layoutB.row().add( new JLabel("  ") );
        layoutB.row().add( addButton, 1 ).add( new JLabel(" "), 8);



        setLayout( new GridBagLayout());

        GridBagConstraints cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 0;
        cnstraint.gridy = 0;
        cnstraint.weightx = 0.33;
        cnstraint.weighty = 0.5;
        cnstraint.insets = new Insets(5,5,0,0);
        JComponent types = new JScrollPane( extList );
        types.setMinimumSize( new Dimension( 200, 200));
        add( types, cnstraint );

        cnstraint = new GridBagConstraints();
        cnstraint.fill = GridBagConstraints.BOTH;
        cnstraint.gridx = 1;
        cnstraint.gridy = 0;
        cnstraint.weightx = 0.66;
        cnstraint.weighty = 0.5;
        add( bottom, cnstraint );

    }

    public void setEnabledAll( boolean on ) {
        descriptionField.setEnabled( on );
        algoBox.setEnabled( on );
        algoReset.setEnabled( on );
        diffText.setEnabled( on );
        diffReset.setEnabled( on );
        diffDir.setEnabled( on );
        openDir.setEnabled( on );
        openReset.setEnabled( on );
        openText.setEnabled( on );
        openSystem.setEnabled( on );
        diffStd.setEnabled( on );
    }
}
