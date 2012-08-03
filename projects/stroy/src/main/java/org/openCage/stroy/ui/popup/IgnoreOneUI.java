package org.openCage.stroy.ui.popup;

import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.locale.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private final IgnoreCentral central;


    public IgnoreOneUI(final String path, final String nme, final String extension, IgnoreCentral central) {

        this.path = path;
        this.extension = extension;
        this.central = central;
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
                central.addExtension( extension );
                dispose();
            }
        });

        nameButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                central.addPattern( name );
                dispose();
            }
        });

        pathButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                central.addPath( path );
                dispose();
            }
        });


    }

    private void createLayout() {

        setTitle( Message.get("SingleIgnore.windowTitle"));

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );

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

        layout.row().grid( new JLabel( Message.get( "SingleIgnore.title" ))).add( new JLabel( realName ),3).add( new JLabel(""));
        layout.row().grid().add( new JLabel(""));
        layout.row().grid( new JLabel( Message.get( "SingleIgnore.path" ))).add( pathLabel, 3 ).add( pathButton );
        layout.row().grid( new JLabel( Message.get( "SingleIgnore.name" ))).add(nameLabel, 3 ).add( nameButton );
        layout.row().grid( new JLabel( Message.get( "SingleIgnore.extension" ))).add(extLabel, 3 ).add( extButton );


        // TODO fill with live
//        layout.row().grid().add("");
//        layout.row().grid( "All files with pattern" ).add( patternField, 3 ).add( patternButton );
//        layout.row().grid().add( legal ).add( matches);

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER  );
    
        pack();


    }
}
