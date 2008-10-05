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
        top.setLayout( layout );

//        layout.row().add( new JLabel(  ));
        layout.row().add( new JLabel( appInfo.getProgName() ));
        layout.row().label( new JLabel( Message.get( "About.version" ))).add( new JLabel( appInfo.getVersion().toString() ));
        layout.row().label( new JLabel( Message.get( "About.copyright" ))).add( new JLabel( appInfo.getCopyright() ), 3 );
        layout.row().label( new JLabel( Message.get( "About.short" ))).add( new JLabel( Message.get( "About.description" )), 6 );
        layout.row().label( new JLabel( Message.get( "About.licence" ))).add( new JLabel( "MPL1.1"), 6 );

        JButton help = new JButton( Message.get( "Menu.Help" ) );
        layout.row().label( new JLabel( Message.get( "Menu.Help" )))/*.add( new JLabel(""), 3 )*/.add( help ).add( new JLabel(""), 5 );

        boolean first = true;
        for ( final String author : appInfo.getAuthors() ) {

            if ( first ) {
                first = false;
                layout.row().label( new JLabel( Message.get( "About.author" ))).add( new JLabel( author ),2 );
            } else {
                layout.row().add( new JLabel( author ), 2 );
            }
        }
        layout.row().label( new JLabel( Message.get( "About.contributors" ))).add( new JLabel( "Misa Inabe, Miguel Cuadron Marion" ),6 );

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
            layout.row().label( new JLabel( Message.get( "About.contact" )))/*.add( new JLabel(""),5)*/.add( contact, 3 ).add( new JLabel(""),3);
        }

        JButton web = new JButton( "http://stroy.wikidot.com" );
        layout.row().label( new JLabel( Message.get( "About.web" ))).add( web, 3 ).add( new JLabel(""), 3);

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
