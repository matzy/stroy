package org.openCage.stroy.ui.prefs;

import javax.swing.*;

import org.openCage.util.prefs.PreferenceString;
import org.openCage.util.ui.FileChooser;
import org.openCage.util.ui.JTextFields;
import org.openCage.util.io.FileUtils;
import org.openCage.util.external.ExternalProgs;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.ui.Colors;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.muchsoft.util.Sys;
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

public class StandardProgUI extends JPanel {

    public static final String STANDARD_DIFF_KEY = "stroy.standard.diff";
    public static final String STANDARD_TEXT_EDITOR_KEY = "stroy.standard.texteditor";


    private final JFrame frame;
    private final JTextField diffText = new JTextField();
    private final JButton diffButton = new JButton( "..");
    private final JButton resetButton = new JButton( "reset");
//    private final JTextField openText = new JTextField("opens the file with the assigned program");
    private final JTextField editorText = new JTextField();
    private final PreferenceString editorPref =  PreferenceString.getOrCreate( STANDARD_TEXT_EDITOR_KEY, "" ); // TODO
    private final PreferenceString diffPref =  PreferenceString.getOrCreate( STANDARD_DIFF_KEY, "" );

    private final JButton editButton = new JButton("..");
    private final JRadioButton stdEdit   = new JRadioButton( Message.get( "Pref.StandardProgs.osText" ));
    private final JRadioButton otherText = new JRadioButton( Message.get( "Pref.StandardProgs.textOther" ));
    private final JRadioButton stdDiff   = new JRadioButton( Message.get( "Pref.StandardProgs.osDiff" ));
    private final JRadioButton otherDiff = new JRadioButton( Message.get( "Pref.StandardProgs.DiffOther" ));

    public StandardProgUI( JFrame frme ) {
        this.frame = frme;

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        layout.row().add( new JLabel("")).add( new JLabel( Message.get( "Pref.StandardProgs.intro" )) ).add( new JLabel(""));        
        layout.row().add( new JLabel( Message.get( "" )));


        ButtonGroup diffGroup = new ButtonGroup();
        diffGroup.add( stdDiff );
        diffGroup.add( otherDiff );

        String stdDiffProg = "";
        if ( Sys.isMacOSX()) {
            stdDiffProg = "FileMerge";
        } else if ( Sys.isWindows() ) {
            stdDiffProg = "WinMerge";
        } else if ( Sys.isLinux() ) {
            stdDiffProg = "diff";
        }

        layout.row().add( new JLabel(Message.get( "Pref.StandardProgs.diff" )),2).add( stdDiff, 2 ).add( new JLabel(stdDiffProg), 5);
        layout.row().add( new JLabel(" "), 2).add( otherDiff,2 ).add( diffText, 4).add( diffButton, 1);
        layout.row().add( new JLabel( Message.get( "")));

//        openText.setEditable( false );
//        layout.row().label( ExternalProgs.open ).add( openText, 8 ).add( new JLabel( "" ), 1);
//        layout.row().add( "   " );

        //editorText.setEditable( false );
        ButtonGroup textGroup = new ButtonGroup();
        textGroup.add( stdEdit );
        textGroup.add( otherText );

        String stdText = "";
        if ( Sys.isMacOSX()) {
            stdText = "TextEdit";
        } else if ( Sys.isWindows() ) {
            stdText = "Notepad";
        } else if ( Sys.isLinux() ) {
            stdText = "vi";
        }

        layout.row().add( new JLabel(Message.get( "Pref.StandardProgs.text" )),2).add( stdEdit, 2 ).add( new JLabel(stdText), 5);
        layout.row().add( new JLabel(" "), 2).add( otherText,2 ).add( editorText, 4).add( editButton, 1);

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER );

        diffButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                    String path = FileChooser.open( frame, FileUtils.getCurrentDir());

                    if ( path != null ) {
                        String norm = FileUtils.normalizePath( path );
                        diffText.setText( norm );
                        diffPref.set( norm );
                    }
            }
        });


//        resetButton.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                diffText.reset();
//            }
//        });

        editButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                    String path = FileChooser.open( frame, FileUtils.getCurrentDir());

                    if ( path != null ) {
                        String norm = FileUtils.normalizePath( path );
                        editorText.setText( norm );
                        editorPref.set( norm );
                    }

            }
        });

        stdEdit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                editorText.setEnabled( false );
                editButton.setEnabled( false );
                editorPref.set( ExternalProgs.OS_TEXT_EDIT );
            }
        });

        otherText.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                editorText.setEnabled( true );
                editButton.setEnabled( true );
            }
        });

        stdDiff.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                diffText.setEnabled( false );
                diffButton.setEnabled( false );
                diffPref.set( ExternalProgs.OS_TEXT_EDIT );
            }
        });

        otherDiff.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                diffText.setEnabled( true );
                diffButton.setEnabled( true );
            }
        });


        // to fix preference setttings
        if ( editorPref.get().equals( "" )) {
            editorPref.set( ExternalProgs.OS_TEXT_EDIT );
        }
        if ( editorPref.get().equals( ExternalProgs.WIN_TEXT_EDIT )) {
            editorPref.set( ExternalProgs.OS_TEXT_EDIT );
        }
        if ( editorPref.get().equals( ExternalProgs.openAsText )) {
            editorPref.set( ExternalProgs.OS_TEXT_EDIT );
        }


        if ( editorPref.get().equals( ExternalProgs.OS_TEXT_EDIT ) ) {
            editorText.setEnabled( false );
            editButton.setEnabled( false );
            stdEdit.setSelected( true );
        } else {
            editorText.setEnabled( true );
            editButton.setEnabled( true );
            otherText.setSelected( true );
            editorText.setText( editorPref.get() );
        }


        // to fix preference setttings
        if ( diffPref.get().equals( "" )) {
            diffPref.set( ExternalProgs.STANDARD_DIFF );
        }
        if ( diffPref.get().equals( ExternalProgs.fileMerge )) {
            diffPref.set( ExternalProgs.STANDARD_DIFF );
        }
        if ( diffPref.get().equals( ExternalProgs.WIN_DIFF )) {
            diffPref.set( ExternalProgs.STANDARD_DIFF );
        }

        if ( diffPref.get().equals( ExternalProgs.STANDARD_DIFF ) ) {
            diffText.setEnabled( false );
            diffButton.setEnabled( false );
            stdDiff.setSelected( true );
        } else {
            diffText.setEnabled( true );
            diffButton.setEnabled( true );
            otherDiff.setSelected( true );
            diffText.setText( diffPref.get() );
        }

        diffText.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased( keyEvent );
                if ( JTextFields.isFile( diffText, Colors.BACKGROUND_NEUTRAL, Colors.BACKGROUND_WARN)) {
                    diffPref.set( diffText.getText() );
                }
            }
        });

        editorText.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased( keyEvent );
                if ( JTextFields.isFile( editorText, Colors.BACKGROUND_NEUTRAL, Colors.BACKGROUND_WARN)) {
                    editorPref.set( editorText.getText() );
                }
            }
        });


    }
}
