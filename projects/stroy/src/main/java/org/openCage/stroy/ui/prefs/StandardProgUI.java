package org.openCage.stroy.ui.prefs;

import javax.swing.*;

import com.google.inject.name.Named;
import org.openCage.lang.structure.ObservableRef;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.property.MapProperty;
import org.openCage.util.external.DesktopX;
import org.openCage.util.external.DesktopXs;
import org.openCage.util.prefs.MComboBox;
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

public class StandardProgUI extends JPanel {

    public static final String STANDARD_DIFF_KEY = "stroy.standard.diff";
    public static final String STANDARD_TEXT_EDITOR_KEY = "stroy.standard.texteditor";


    private final JFrame frame;
    private final JTextField diffText = new JTextField();
    private final JButton diffButton = new JButton( "..");
    private final JButton resetButton = new JButton( "reset");
    private final JTextField editorText = new JTextField();
    private final ObservableRef<String> editorPref;
    private final ObservableRef<String> diffPref;

    private final JButton editButton = new JButton("..");
    private final JRadioButton stdEdit   = new JRadioButton( Message.get( "Pref.StandardProgs.osText" ));
    private final JRadioButton otherText = new JRadioButton( Message.get( "Pref.StandardProgs.textOther" ));
    private final JRadioButton stdDiff   = new JRadioButton( Message.get( "Pref.StandardProgs.osDiff" ));
    private final JRadioButton otherDiff = new JRadioButton( Message.get( "Pref.StandardProgs.DiffOther" ));
    private MComboBox mbox;
    private final JTextField newProg = new JTextField();
    private JButton progOk = new JButton("OK");
    private JButton progProg = new JButton("..");


    public StandardProgUI(JFrame frme,
                          @Named("Editor") ObservableRef<String> editorPref,
                          @Named("DiffProg") ObservableRef<String> diffPref,
                          DesktopX desktop,
                          ObservableRef<String> sel,
                          final MapProperty<ObservableRef<String>> progList) {
        this.frame = frme;
        this.editorPref = editorPref;
        this.diffPref = diffPref;

        this.mbox = new MComboBox( progList, sel);

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );

        layout.row().grid().add( new JLabel("")).add( new JLabel( Message.get( "Pref.StandardProgs.intro" )) ).add( new JLabel(""));
        layout.row().grid().add( new JLabel( "" ));


        ButtonGroup diffGroup = new ButtonGroup();
        diffGroup.add( stdDiff );
        diffGroup.add( otherDiff );

        String stdDiffProg = desktop.getStandardDiffProgName();

        layout.row().grid().add( mbox );
        layout.row().grid().add(new JLabel(Message.get("Pref.StandardProgs.diff")), 2).add(stdDiff, 2).add( new JLabel(stdDiffProg), 5);
        layout.row().grid().add( new JLabel(" "), 2).add( otherDiff,2 ).add( diffText, 4).add( diffButton, 1);
        layout.row().grid().add( new JLabel( ""));

//        openText.setEditable( false );
//        layout.row().grid( ExternalProgs.open ).add( openText, 8 ).add( new JLabel( "" ), 1);
//        layout.row().grid().add( "   " );

        //editorText.setEditable( false );
        ButtonGroup textGroup = new ButtonGroup();
        textGroup.add( stdEdit );
        textGroup.add( otherText );

        String stdText = desktop.getStandardEditorName();

        layout.row().grid().add( new JLabel(Message.get( "Pref.StandardProgs.text" )),2).add( stdEdit, 2 ).add( new JLabel(stdText), 5);
        layout.row().grid().add(new JLabel(" "), 2).add( otherText,2 ).add( editorText, 4).add( editButton, 1);
        layout.row();
        layout.row().grid().add( new JLabel( Message.get("Add a new Program")), 3).add( newProg,4 ).add(progProg, 1).add(progOk, 1);

        progProg.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = newProg.getText();
                String key = extract( cmd );
                progList.put( ThreeKey.valueOf(key), new ObservableRef<String>((newProg.getText())));
            }
        });

        setLayout(new BorderLayout());
        add( top, BorderLayout.CENTER );

        diffButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                    String path = FileChooser.getAnyFile(frame, FileUtils.getCurrentDir());

                    if ( path != null ) {
                        String norm = FileUtils.normalizePath( path );
                        diffText.setText( norm );
                        StandardProgUI.this.diffPref.set((norm));
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
                    String path = FileChooser.getAnyFile(frame, FileUtils.getCurrentDir());

                    if ( path != null ) {
                        String norm = FileUtils.normalizePath( path );
                        editorText.setText( norm );
                        StandardProgUI.this.editorPref.set((norm));
                    }

            }
        });

        stdEdit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                editorText.setEnabled( false );
                editButton.setEnabled( false );
                StandardProgUI.this.editorPref.set(ExternalProgs.OS_TEXT_EDIT);
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
                StandardProgUI.this.diffPref.set(ExternalProgs.OS_TEXT_EDIT);
            }
        });

        otherDiff.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                diffText.setEnabled( true );
                diffButton.setEnabled( true );
            }
        });


        // to fix preference setttings
        if ( this.editorPref.get().equals( "" )) {
            this.editorPref.set(DesktopXs.OS_STANDARD_TEXT_EDITOR);
        }
        if ( this.editorPref.get().equals( ExternalProgs.WIN_TEXT_EDIT )) {
            this.editorPref.set(ExternalProgs.OS_TEXT_EDIT);
        }
        if ( this.editorPref.get().equals( ExternalProgs.openAsText )) {
            this.editorPref.set(ExternalProgs.OS_TEXT_EDIT);
        }




        // to fix preference setttings
        if ( this.diffPref.get().equals( ("") )) {
            this.diffPref.set(DesktopXs.STANDARD_DIFF);
        }
        if ( this.diffPref.get().equals( ExternalProgs.fileMerge )) {
            this.diffPref.set(ExternalProgs.STANDARD_DIFF);
        }
        if ( this.diffPref.get().equals( ExternalProgs.WIN_DIFF )) {
            this.diffPref.set(ExternalProgs.STANDARD_DIFF);
        }

        selectionBox( editorText, editButton, stdEdit, otherText, editorPref, DesktopXs.OS_STANDARD_TEXT_EDITOR);
        selectionBox( diffText, diffButton, stdDiff, otherDiff, diffPref, DesktopXs.STANDARD_DIFF  );



    }

    private String extract(String cmd) {
        int lastSlash = cmd.lastIndexOf("/");
        int firstSpace = cmd.indexOf(" ");
        firstSpace = (firstSpace == -1) ? cmd.length() : firstSpace;
        return cmd.substring( lastSlash, firstSpace - lastSlash );
    }

    public static void selectionBox( final JTextField textField,
                                     JButton button,
                                     JRadioButton stdButton,
                                     JRadioButton other,
                                     final ObservableRef<String> prop,
                                     String std ) {


        //
        //enable disable

        if ( prop.get().equals( std ) ) {
            textField.setEnabled( false );
            button.setEnabled( false );
            stdButton.setSelected( true );
        } else {
            textField.setEnabled( true );
            button.setEnabled( true );
            other.setSelected( true );
            textField.setText( prop.get());
        }



        // react to keyboard input

        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                if ( JTextFields.isFileOrApp(textField, Colors.BACKGROUND_NEUTRAL, Colors.BACKGROUND_WARN)) {
                    prop.set( (textField.getText()));
                }
            }
        });

    }

}
