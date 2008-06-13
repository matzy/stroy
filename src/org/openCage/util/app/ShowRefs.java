package org.openCage.util.app;

import com.google.inject.Inject;
import org.openCage.util.ui.BrowserLauncher;
import org.openCage.util.logging.Log;
import zappini.designgridlayout.DesignGridLayout;
import zappini.designgridlayout.Row;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class ShowRefs extends JDialog {

    private final AppInfo appInfo;

    @Inject
    public ShowRefs( AppInfo appInfo ) {
        this.appInfo = appInfo;
    }



    public void go() {
        setTitle( "References" );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setSize( 400, 200 );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );


        boolean first = true;

        for ( final Reference ref : appInfo.getReferences() ) {

            String comment = null;

            if ( first) {
                comment =  "Used in App/Delivered:   ";
                first = false;
            }

            displayRef( layout, ref, comment );
        }

        first = true;

        for ( final Reference ref : appInfo.getBuildRefs() ) {

            String comment = null;

            if ( first) {
                comment = "Used to Produce:   ";
                first = false;
            }

            displayRef( layout, ref, comment );              
        }


        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );

        pack();

        setVisible( true );

    }

    private void displayRef( DesignGridLayout layout, final Reference ref, String comment ) {
        Row row = layout.row();

        if ( comment != null ) {
            row.label( comment );
        }

        row = row.add( new JLabel( ref.getProg()), 2 );
        if ( ref.getLicence() != null ) {
            JButton licence = new JButton( ref.getShortLicence() );
            licence.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        BrowserLauncher.displayURL( ref.getAddress() );
                    } catch (Exception e) {
                        Log.warning( "BrowserLauncher threw " + e.getCause() );
                    }
                }
            });

            row.add( licence, 1);
        }

        if ( ref.getAddress() != null ) {
            JButton contact = new JButton( ref.getAddress() );
            contact.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        BrowserLauncher.displayURL( ref.getAddress() );
                    } catch (Exception e) {
                        Log.warning( "BrowserLauncher threw " + e.getCause() );
                    }
                }
            });

            row.add( contact, 2 );
        }

        layout.row().add( new JLabel(ref.getDescr()));

    }
}
