package org.openCage.stroy.ui.prefs;

import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;

import org.openCage.util.prefs.TextField;
import org.openCage.util.ui.FileChooser;
import org.openCage.util.io.FileUtils;
import org.openCage.util.external.ExternalProgs;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

public class StandardProgUI extends JPanel {

    public static final String STANDARD_DIFF_KEY = "stroy.standard.diff";
    public static final String STANDARD_DIFF_TEXT = "<standard diff>";
    public static final String STANDARD_TEXT_EDITOR_TEXT = "<standard text editor>";
    public static final String STANDARD_TEXT_EDITOR_KEY = "stroy.standard.texteditor";


    private final JFrame frame;
    private final TextField diffText = new TextField(STANDARD_DIFF_KEY );
    private final JButton diffButton = new JButton( "..");
    private final JButton resetButton = new JButton( "reset");
    private final JTextField openText = new JTextField("opens the file with the assigned program");
    private final TextField editorText = new TextField(STANDARD_TEXT_EDITOR_KEY);
    private final JButton editButton = new JButton("..");

    public StandardProgUI( JFrame frme ) {
        this.frame = frme;

        createLayout();
        addListeners();
    }

    private void addListeners() {
        diffButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                    String path = FileChooser.open( frame, FileUtils.getCurrentDir());

                    if ( path != null ) {
                        diffText.setText( FileUtils.normalizePath( path ));
                    }

            }
        });

        resetButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                diffText.reset();
            }
        });

        editButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                    String path = FileChooser.open( frame, FileUtils.getCurrentDir());

                    if ( path != null ) {
                        editorText.setText( FileUtils.normalizePath( path ));
                    }

            }
        });
    }

    private void createLayout() {
        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        layout.row().label( STANDARD_DIFF_TEXT ).add( diffText, 8 ). add( diffButton );
        layout.row().add( resetButton, 1 ).add( new JLabel(" "),8);
        layout.row().add( "   " );

        openText.setEditable( false );
        layout.row().label( ExternalProgs.open ).add( openText, 8 ).add( new JLabel( "" ), 1);
        layout.row().add( "   " );

        //editorText.setEditable( false );
        layout.row().label(STANDARD_TEXT_EDITOR_TEXT).add( editorText, 8 ).add( editButton, 1);

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER );
    }
}
