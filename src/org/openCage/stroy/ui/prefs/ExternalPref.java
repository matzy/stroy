package org.openCage.stroy.ui.prefs;

import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.file.SimilarityAlgorithm;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.ui.Colors;
import org.openCage.util.ui.FileChooser;
import org.openCage.util.ui.JTextFields;
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
import java.io.File;


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

public class ExternalPref extends JPanel {
    private JTextField descriptionField = new JTextField();

    private FileTypes fileTypes;

    private JComboBox algoBox;
    private JButton algoReset = new JButton( Message.get( "Button.reset" ) );
    private JButton diffReset = new JButton( Message.get( "Button.reset" ) );
    private JList extList;
    private JButton openReset = new JButton( Message.get( "Button.reset" ) );
//    private final JButton addButton = new JButton( "+" );
    private final JButton openDir = new JButton( ".." );
    private final JButton diffDir = new JButton( ".." );
    private final JTextField diffOtherText = new JTextField("");
    private final JTextField openText = new JTextField("");
    private final JFrame frame;
    private final JButton openSystem = new JButton( Message.get( "Pref.FileType.system" ));
    //    private final JButton diffStd = new JButton( Message.get( "Pref.FileType.std" ));
    private JRadioButton openWithAssociated;
    private JRadioButton openWithText;
    private JRadioButton openWithImage;
    private JRadioButton openWithAudio;
    private JRadioButton openWithOther;
    private JRadioButton diffText;
    private JRadioButton diffOther;
    private JRadioButton diffUnknown;

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
//        addButton.setEnabled( false );
        addListeners();
    }

    private void addListeners() {
        extList.addListSelectionListener( new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                String ext = (String)extList.getSelectedValue();
                descriptionField.setText( fileTypes.getDescription( ext ));
                algoBox.setSelectedItem( fileTypes.getAlgo(ext ).toString() );
                setDiff(ext);
                setOpen(ext);
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

    private void setDiff(String ext) {
        String text = fileTypes.getDiffType(ext);

        if ( text.equals( ExternalProgs.STANDARD_DIFF )) {
            diffOtherText.setText("");
            diffOtherText.setEnabled( false );
            diffDir.setEnabled( false );
            diffText.setSelected( true );
        } else if ( text.equals( ExternalProgs.unknown )) {
            diffOtherText.setText("");
            diffOtherText.setEnabled( false );
            diffDir.setEnabled( false );
            diffUnknown.setSelected( true );
        } else {
            diffOtherText.setText( text );
            diffOtherText.setEnabled( true );
            diffDir.setEnabled( true );
            diffOther.setSelected( true );
        }

    }

    private void setOpen(String ext) {
        String text = fileTypes.getOpen( ext );

        if ( text.equals( ExternalProgs.open )) {
            openText.setText( "" );
            openText.setBackground(Colors.BACKGROUND_NEUTRAL);
            openText.setEnabled( false );
            openDir.setEnabled( false );
            openWithAssociated.setSelected( true );
        } else if ( text.equals( ExternalProgs.openAsText )) {
            openText.setText( "" );
            openText.setBackground(Colors.BACKGROUND_NEUTRAL);
            openText.setEnabled( false );
            openDir.setEnabled( false );
            openWithText.setSelected( true );
        } else {
            openText.setText( text);
            openText.setBackground(Colors.BACKGROUND_NEUTRAL);
            openText.setEnabled( true );
            openDir.setEnabled( true );
            openWithOther.setSelected(true);
        }
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

                if ( JTextFields.isFile( openText, Colors.BACKGROUND_NEUTRAL, Colors.BACKGROUND_WARN)) {
                    fileTypes.setOpenProg( (String)extList.getSelectedValue(), openText.getText() );
                }
            }
        });
//        openReset.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                String ext = (String)extList.getSelectedValue();
//                fileTypes.resetOpenProg( ext );
//                openText.setText( fileTypes.getOpen(ext));
//            }
//        });
        openDir.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String path = FileChooser.open( frame, FileUtils.getCurrentDir());

                if ( path != null ) {
                    openText.setText( FileUtils.normalizePath( path ));
                    fileTypes.setOpenProg( (String)extList.getSelectedValue(), path );
                    openText.setBackground(Colors.BACKGROUND_NEUTRAL);                    
                }

            }
        });

//        openSystem.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                openText.setText( ExternalProgs.open );
//                fileTypes.setOpenProg( (String)extList.getSelectedValue(), ExternalProgs.open );
//            }
//        });

        openWithAssociated.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                openText.setText( "" );
                openText.setBackground(Colors.BACKGROUND_NEUTRAL);
                openText.setEnabled( false );
                openDir.setEnabled( false );
                fileTypes.setOpenProg( (String)extList.getSelectedValue(), ExternalProgs.open );
            }
        });

        openWithText.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                openText.setText( "" );
                openText.setBackground(Colors.BACKGROUND_NEUTRAL);
                openText.setEnabled( false );
                openDir.setEnabled( false );
                fileTypes.setOpenProg( (String)extList.getSelectedValue(), ExternalProgs.openAsText );
            }
        });

        openWithOther.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                openText.setEnabled( true );
                openDir.setEnabled( true );
                //fileTypes.setOpenProg( (String)extList.getSelectedValue(), ExternalProgs.openAsText );
            }
        });

    }

    private void diffListeners() {
        diffOtherText.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased( keyEvent );
                if ( JTextFields.isFile( diffOtherText, Colors.BACKGROUND_NEUTRAL, Colors.BACKGROUND_WARN)) {
                    fileTypes.setDiffProg( (String)extList.getSelectedValue(), diffText.getText() );
                }
            }
        });
//        diffReset.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                String ext = (String)extList.getSelectedValue();
//                fileTypes.resetDiffProg( ext );
//                diffText.setText( fileTypes.getDiffType(ext));
//            }
//        });
//
        diffDir.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String path = FileChooser.open( frame, FileUtils.getCurrentDir());

                if ( path != null ) {
                    diffOtherText.setText( FileUtils.normalizePath( path ));
                    fileTypes.setDiffProg( (String)extList.getSelectedValue(), path );
                    diffOtherText.setBackground(Colors.BACKGROUND_NEUTRAL);                    
                }

            }
        });

        diffText.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                diffOtherText.setText( "" );
                diffOtherText.setBackground(Colors.BACKGROUND_NEUTRAL);
                diffOtherText.setEnabled( false );
                diffDir.setEnabled( false );
                fileTypes.setDiffProg( (String)extList.getSelectedValue(), ExternalProgs.STANDARD_DIFF );
            }
        });

        diffUnknown.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                diffOtherText.setText( "" );
                diffOtherText.setBackground(Colors.BACKGROUND_NEUTRAL);
                diffOtherText.setEnabled( false );
                diffDir.setEnabled( false );
                fileTypes.setDiffProg( (String)extList.getSelectedValue(), ExternalProgs.unknown );
            }
        });

        diffOther.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                diffOtherText.setEnabled( true );
                diffDir.setEnabled( true );
                //fileTypes.setOpenProg( (String)extList.getSelectedValue(), ExternalProgs.openAsText );
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

        layoutB.row().add( new JLabel("")).add( new JLabel(Message.get("Pref.FileType.intro")),10).add( new JLabel(""));
        layoutB.row().add( new JLabel(" "), 10);


        layoutB.row().add( new JLabel( Message.get("Pref.FileType.description")), 2 ).
                add( descriptionField, 8 );
        layoutB.row().add( new JLabel( Message.get("Pref.FileType.algo")),2).
                add( algoBox, 8 );
        layoutB.row().add( new JLabel(" "), 10);


        diffText = new JRadioButton( Message.get("Pref.FileType.Diff") );
        diffOther = new JRadioButton( Message.get("Pref.FileType.OtherDiff"));
        diffUnknown = new JRadioButton( Message.get("Pref.FileType.DiffUnknown"));
        ButtonGroup diffGroup = new ButtonGroup();
        diffGroup.add(diffText);
        diffGroup.add( diffOther);
        diffGroup.add( diffUnknown );

        layoutB.row().add( new JLabel( Message.get("Pref.FileType.external")),2).
                add(diffText, 2 ).add( new JLabel(" "),6);
        layoutB.row().add( new JLabel(" ") ,2 ).add(diffUnknown,2).add( new JLabel(" "),6);
        layoutB.row().add( new JLabel(" ") ,2 ).add(diffOther,2).add( diffOtherText, 5).add( diffDir );
        layoutB.row().add( new JLabel(" "), 10);

//                add( diffText, 7 ).add( diffDir );
//        layoutB.row().add( new JLabel(" "), 2).
//                /*add( diffReset, 1 ).add( diffStd, 1 ).*/add( new JLabel(" "), 8);

//                .add( openText, 5 ).add( openDir );
//        layoutB.row().add( new JLabel(" "), 3).                    add( openReset, 1 ).add( openSystem, 1).add( new JLabel(" "), 4 );

        openWithAssociated = new JRadioButton( Message.get( "Pref.FileType.OpenAssociated") );
        openWithText = new JRadioButton( Message.get( "Pref.FileType.OpenTextEditor") );
        openWithOther = new JRadioButton( Message.get( "Pref.FileType.OpenOther") );
        ButtonGroup openGroup = new ButtonGroup();
        openGroup.add(openWithAssociated);
        openGroup.add( openWithText);
//        openGroup.add( openWithImage);
//        openGroup.add( openWithAudio);
        openGroup.add( openWithOther);

        layoutB.row().add( new JLabel( Message.get("Pref.FileType.open")),2).
                add(openWithAssociated, 2 ).add( new JLabel(" "),6);
        layoutB.row().add( new JLabel(" ") ,2 ).add(openWithText,2).add( new JLabel(" "),6);
//        openWithImage = new JRadioButton( "image editor" );
//        layoutB.row().add( new JLabel(" ") ,1 ).add(openWithImage).add( new JLabel(" "),5);
//        openWithAudio = new JRadioButton( "audio program" );
//        layoutB.row().add( new JLabel(" ") ,1 ).add(openWithAudio).add( new JLabel(" "),5);
        layoutB.row().add( new JLabel(" ") ,2 ).add(openWithOther,2).add( openText, 5).add( openDir );



        layoutB.row().add( new JLabel("  ") );
        layoutB.row().add( new JLabel("  ") );
        layoutB.row().add( new JLabel("  ") );
//        layoutB.row().add( addButton, 2 ).add( new JLabel(" "), 8);



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
        diffReset.setEnabled( on );
        openReset.setEnabled( on );
        if ( !on ) {
            openText.setEnabled( on );
            openDir.setEnabled( on );
            diffOtherText.setEnabled(on);
            diffDir.setEnabled( on );
        }
        openSystem.setEnabled( on );
        openWithAssociated.setEnabled( on );
        openWithText.setEnabled( on );
        openWithOther.setEnabled( on );

        diffOther.setEnabled(on);
        diffText.setEnabled( on );
        diffUnknown.setEnabled(on);
    }
}
