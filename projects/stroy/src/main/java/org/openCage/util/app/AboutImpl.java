package org.openCage.util.app;

import com.google.inject.Inject;
import org.openCage.util.ui.BrowserLauncher;
import org.openCage.stroy.ui.help.HelpLauncher;
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

public class AboutImpl extends JDialog implements About {

    private final AppInfo appInfo;

    @Inject
    public AboutImpl( AppInfo appInfo ) {
        this.appInfo = appInfo;
    }



    public void go() {
        setTitle( appInfo.getProgName() );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setSize( 400, 200 );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );

//        layout.row().grid().add( new JLabel(  ));
        layout.row().grid().add( new JLabel( appInfo.getProgName() ));
        layout.row().grid( new JLabel( Message.get( "About.version" ))).add( new JLabel( appInfo.getVersion().toString() ));
        layout.row().grid( new JLabel( Message.get( "About.copyright" ))).add( new JLabel( appInfo.getCopyright() ), 3 );
        layout.row().grid( new JLabel( Message.get( "About.short" ))).add( new JLabel( Message.get( "About.description" )), 6 );
        layout.row().grid( new JLabel( Message.get( "About.licence" ))).add( new JLabel( "MPL1.1"), 6 );

        JButton help = new JButton( Message.get( "Menu.Help" ) );
        layout.row().grid( new JLabel( Message.get( "Menu.Help" )))/*.add( new JLabel(""), 3 )*/.add( help ).add( new JLabel(""), 5 );

        boolean first = true;
        for ( final String author : appInfo.getAuthors() ) {

            if ( first ) {
                first = false;
                layout.row().grid( new JLabel( Message.get( "About.author" ))).add( new JLabel( author ),2 );
            } else {
                layout.row().grid().add( new JLabel( author ), 2 );
            }
        }
        layout.row().grid( new JLabel( Message.get( "About.contributors" ))).add( new JLabel( "Misa Inabe, Miguel Cuadron Marion" ),6 );

        if ( appInfo.getContactEmail() != null ) {
            JButton contact = new JButton( appInfo.getContactEmail());
            final String mailto = "mailto:" + appInfo.getContactEmail();
            contact.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        BrowserLauncher.displayURL( mailto );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            layout.row().grid( new JLabel( Message.get( "About.contact" )))/*.add( new JLabel(""),5)*/.add( contact, 3 ).add( new JLabel(""),3);
        }

        JButton web = new JButton( "http://stroy.wikidot.com" );
        layout.row().grid( new JLabel( Message.get( "About.web" ))).add( web, 3 ).add( new JLabel(""), 3);

        web.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                try {
                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/start" );
                } catch (Exception exp ) {
                    exp.printStackTrace();
                }
            }
        } );

        help.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                HelpLauncher.showHelp();
            }
        });

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );

        pack();

        setVisible( true );

    }
}
