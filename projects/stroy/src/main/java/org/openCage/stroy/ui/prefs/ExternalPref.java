package org.openCage.stroy.ui.prefs;

import org.openCage.comphy.property.ImmuProp;
import org.openCage.comphy.property.MapProperty;
import org.openCage.lang.inc.Str;
import org.openCage.stroy.file.*;
import org.openCage.stroy.file.Action;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.ui.Colors;
import org.openCage.util.prefs.MComboBox;
import org.openCage.util.ui.FileChooser;
import org.openCage.util.ui.JTextFields;
import org.openCage.util.io.FileUtils;
import org.openCage.util.external.ExternalProgs;

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

    private Map<String, String> algo2mesg = new HashMap<String, String>();
    private Map<String, String> mesg2algo = new HashMap<String, String>();
    private MComboBox mbox;
    private final MapProperty<ImmuProp<Str>> progList;

    public ExternalPref(final JFrame frame, FileTypes filesTypes, MapProperty<ImmuProp<Str>> progList) {

        this.frame = frame;

        this.fileTypes = filesTypes;
        this.progList = progList;

        List<String> exts = new ArrayList<String>( fileTypes.getTypeList());
        Collections.sort( exts );
        extList = new JList( new Vector(exts ));

        List<String> algomesg = new ArrayList<String>();
        for ( SimilarityAlgorithm algo : SimilarityAlgorithm.values() ) {
            String algoStr = algo.toString();
            String mesg = Message.get( "Pref.FileType.algos." + algoStr );
            algo2mesg.put( algoStr, mesg);
            mesg2algo.put( mesg, algoStr );
            algomesg.add( mesg );
        }

        algoBox = new JComboBox( new Vector(algomesg) );

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
                algoBox.setSelectedItem( algo2mesg.get( fileTypes.getAlgo(ext ).toString() ));
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
//                fileTypes.resetAlgo( ext ); TODO
                algoBox.setSelectedItem( algo2mesg.get(fileTypes.getAlgo( ext ).toString() ));
            }
        });

        algoBox.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                fileTypes.setAlgo( (String)extList.getSelectedValue(), mesg2algo.get((String)algoBox.getSelectedItem() ));
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
                fileTypes.setOpenProg( (String)extList.getSelectedValue(), ExternalProgs.open.get() );
            }
        });

        openWithText.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                openText.setText( "" );
                openText.setBackground(Colors.BACKGROUND_NEUTRAL);
                openText.setEnabled( false );
                openDir.setEnabled( false );
                fileTypes.setOpenProg( (String)extList.getSelectedValue(), ExternalProgs.openAsText.get() );
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
                    fileTypes.setDiffProg( (String)extList.getSelectedValue(), diffOtherText.getText() );
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
                fileTypes.setDiffProg( (String)extList.getSelectedValue(), ExternalProgs.STANDARD_DIFF.get() );
            }
        });

        diffUnknown.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                diffOtherText.setText( "" );
                diffOtherText.setBackground(Colors.BACKGROUND_NEUTRAL);
                diffOtherText.setEnabled( false );
                diffDir.setEnabled( false );
                fileTypes.setDiffProg( (String)extList.getSelectedValue(), ExternalProgs.unknown.get() );
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

        layoutB.row().grid().add( new JLabel("")).add( new JLabel(Message.get("Pref.FileType.intro")),10).add( new JLabel(""));
        layoutB.row().grid().add( new JLabel(" "), 10);


        layoutB.row().grid().add( new JLabel( Message.get("Pref.FileType.description")), 2 ).
                add( descriptionField, 8 );
        layoutB.row().grid().add( new JLabel( Message.get("Pref.FileType.algo")),2).
                add( algoBox, 8 );
        layoutB.row().grid().add( new JLabel(" "), 10);


        diffText = new JRadioButton( Message.get("Pref.FileType.Diff") );
        diffOther = new JRadioButton( Message.get("Pref.FileType.OtherDiff"));
        diffUnknown = new JRadioButton( Message.get("Pref.FileType.DiffUnknown"));
        ButtonGroup diffGroup = new ButtonGroup();
        diffGroup.add(diffText);
        diffGroup.add( diffOther);
        diffGroup.add( diffUnknown );

        layoutB.row().grid().add( new JLabel( Message.get("Pref.FileType.external")),2).
                add(diffText, 2 ).add( new JLabel(" "),6);
        layoutB.row().grid().add( new JLabel(" ") ,2 ).add(diffUnknown, 2).add( new JLabel(" "),6);
        layoutB.row().grid().add(new JLabel(" "), 2).add(diffOther,2).add( diffOtherText, 5).add( diffDir );
        layoutB.row().grid().add( new JLabel(" "), 10);

        this.mbox = new MComboBox( progList,  new Action(null));

        layoutB.row().grid().add(new JLabel(Message.get("Pref.FileType.external")), 2).
                add( mbox, 8);

//                add( diffText, 7 ).add( diffDir );
//        layoutB.row().grid().add( new JLabel(" "), 2).
//                /*add( diffReset, 1 ).add( diffStd, 1 ).*/add( new JLabel(" "), 8);

//                .add( openText, 5 ).add( openDir );
//        layoutB.row().grid().add( new JLabel(" "), 3).                    add( openReset, 1 ).add( openSystem, 1).add( new JLabel(" "), 4 );

        openWithAssociated = new JRadioButton( Message.get( "Pref.FileType.OpenAssociated") );
        openWithText = new JRadioButton( Message.get( "Pref.FileType.OpenTextEditor") );
        openWithOther = new JRadioButton( Message.get( "Pref.FileType.OpenOther") );
        ButtonGroup openGroup = new ButtonGroup();
        openGroup.add(openWithAssociated);
        openGroup.add( openWithText);
//        openGroup.add( openWithImage);
//        openGroup.add( openWithAudio);
        openGroup.add( openWithOther);

        layoutB.row().grid().add( new JLabel( Message.get("Pref.FileType.open")),2).
                add(openWithAssociated, 2 ).add( new JLabel(" "),6);
        layoutB.row().grid().add( new JLabel(" ") ,2 ).add(openWithText,2).add( new JLabel(" "),6);
//        openWithImage = new JRadioButton( "image editor" );
//        layoutB.row().grid().add( new JLabel(" ") ,1 ).add(openWithImage).add( new JLabel(" "),5);
//        openWithAudio = new JRadioButton( "audio program" );
//        layoutB.row().grid().add( new JLabel(" ") ,1 ).add(openWithAudio).add( new JLabel(" "),5);
        layoutB.row().grid().add( new JLabel(" ") ,2 ).add(openWithOther,2).add( openText, 5).add( openDir );



        layoutB.row().grid().add( new JLabel("  ") );
        layoutB.row().grid().add( new JLabel("  ") );
        layoutB.row().grid().add( new JLabel("  ") );
//        layoutB.row().grid().add( addButton, 2 ).add( new JLabel(" "), 8);



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
