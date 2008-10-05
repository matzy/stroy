package org.openCage.stroy.ui.popup;

import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.locale.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

/**
 * Dialog to filter out one file by pattern or extension or ...
 */
public class IgnoreOneUI extends JFrame {
//    private JTextField patternField = new JTextField("");
//    private JLabel matches = new JLabel( "-m-");
//    private JLabel legal = new JLabel( "-l-" );
//    private JButton patternButton = new JButton( "Filter by Pattern" );
    private JButton nameButton = new JButton( Message.get( "SingleIgnore.nameButton" ));
    private JButton extButton;
    private JButton pathButton;

    private final String extension;
    private final String name;
    private final String path;
    private final String realName;


    public IgnoreOneUI( final String path, final String nme, final String extension ) {

        this.path = path;
        this.extension = extension;
        String nameTmp = nme;
        name = ".*/" + nameTmp.replaceAll( "\\.", "\\\\.");
        realName = nme;

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

        setTitle( Message.get("SingleIgnore.windowTitle"));

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        extButton = new JButton( Message.get("SingleIgnore.extensionButton" ));
        pathButton = new JButton( Message.get( "SingleIgnore.pathButton" ));



        JLabel pathLabel = new JLabel( path );
        pathLabel.setBorder( BorderFactory.createLoweredBevelBorder() );

        JLabel nameLabel = new JLabel(name);
        nameLabel.setBorder( BorderFactory.createLoweredBevelBorder() );

        JLabel extLabel = new JLabel(extension);
        extLabel.setBorder( BorderFactory.createLoweredBevelBorder() );
        if ( extension == null || extension.length() == 0  ) {
            extLabel.setText( " ");
        }

//        patternButton.setEnabled( false );
//        patternField.setEnabled( false );

        layout.row().label( new JLabel( Message.get( "SingleIgnore.title" ))).add( new JLabel( realName ),3).add( new JLabel(""));
        layout.row().add( new JLabel(""));
        layout.row().label( new JLabel( Message.get( "SingleIgnore.path" ))).add( pathLabel, 3 ).add( pathButton );
        layout.row().label( new JLabel( Message.get( "SingleIgnore.name" ))).add(nameLabel, 3 ).add( nameButton );
        layout.row().label( new JLabel( Message.get( "SingleIgnore.extension" ))).add(extLabel, 3 ).add( extButton );


        // TODO fill with live
//        layout.row().add("");
//        layout.row().label( "All files with pattern" ).add( patternField, 3 ).add( patternButton );
//        layout.row().add( legal ).add( matches);

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );
    
        pack();


    }
}
