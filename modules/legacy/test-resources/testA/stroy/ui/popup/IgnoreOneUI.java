package org.openCage.stroy.ui.popup;

import org.openCage.stroy.filter.IgnoreCentral;
import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

/**
 * Dialog to filter out one file by pattern or extension or ...
 */
public class IgnoreOneUI extends JFrame {
    private JTextField patternField = new JTextField("");
    private JLabel matches = new JLabel( "-m-");
    private JLabel legal = new JLabel( "-l-" );
    private JButton patternButton = new JButton( "Filter by Pattern" );
    private JButton nameButton = new JButton( "Filter by Name" );
    private JButton extButton;
    private JButton pathButton;

    private final String extension;
    private final String name;
    private final String path;


    public IgnoreOneUI( final String path, final String nme, final String extension ) {

        this.path = path;
        this.extension = extension;
        String nameTmp = nme;
        name = ".*/" + nameTmp.replaceAll( "\\.", "\\\\.");

        createLayout();
        listeners();
    }


    private void listeners() {

        if ( extension == null || extension.length() == 0 ) {
            extButton.setEnabled( false );
        }

        extButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                IgnoreCentral.create().addSingleExtension( extension );
                dispose();
            }
        });

        nameButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                IgnoreCentral.create().addSinglePattern( name );
                dispose();
            }
        });

        pathButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                IgnoreCentral.create().addSinglePath( path );
                dispose();
            }
        });


    }

    private void createLayout() {

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        extButton = new JButton( "Filter by Extension" );
        pathButton = new JButton( "Filter by Path" );



        JLabel pathLabel = new JLabel( path );
        pathLabel.setBorder( BorderFactory.createLoweredBevelBorder() );

        JLabel nameLabel = new JLabel(name);
        nameLabel.setBorder( BorderFactory.createLoweredBevelBorder() );

        JLabel extLabel = new JLabel(extension);
        extLabel.setBorder( BorderFactory.createLoweredBevelBorder() );
        if ( extension == null || extension.length() == 0  ) {
            extLabel.setText( " ");
        }

        patternButton.setEnabled( false );
        patternField.setEnabled( false );

        layout.row().label( "Filter" ).add( new JLabel( "a file" ),3).add("");
        layout.row().add("");
        layout.row().label( "Just that path" ).add( pathLabel, 3 ).add( pathButton );
        layout.row().label( "All files with Name" ).add(nameLabel, 3 ).add( nameButton );
        layout.row().label( "All files with extension" ).add(extLabel, 3 ).add( extButton );


        // TODO fill with live
//        layout.row().add("");
//        layout.row().label( "All files with pattern" ).add( patternField, 3 ).add( patternButton );
//        layout.row().add( legal ).add( matches);

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );
    
        pack();


    }
}
