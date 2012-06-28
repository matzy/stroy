package org.openCage.stroy.update;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.openCage.util.app.AppInfo;
import org.openCage.util.app.Version2;
import org.openCage.util.ui.BrowserLauncher;
import org.openCage.util.logging.Log;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.ui.prefs.PrefsUI;
import com.google.inject.Inject;
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

public class UpdateInfo extends JFrame {

    private JLabel latests = new JLabel( "" );

    private JLabel infoText = new JLabel( Message.get( "Update.info" ));
    private JLabel currentText = new JLabel( Message.get( "Update.current" ));
    private JLabel availableText = new JLabel( Message.get( "Update.available" ));
    private JButton configure = new JButton( Message.get( "Update.configure" ));
    private JLabel configureText = new JLabel( Message.get( "Update.configureText" ));
    private JButton close = new JButton( Message.get( "Update.close" ));
    private JButton page = new JButton( Message.get( "Update.page" ));
    private JLabel pageText = new JLabel( Message.get( "Update.pageText" ));

    @Inject
    public UpdateInfo( /*final PrefsUI prefsUI,*/ final AppInfo appInfo ) {

        infoText.setForeground( Color.BLUE );
        latests.setForeground( Color.BLUE );

        setTitle( Message.get( "Update.title" ));

        JPanel top = new JPanel();

        DesignGridLayout layout = new DesignGridLayout( top );

        layout.row().grid().add( infoText );
        layout.row().grid().add( new JLabel("" ), 3);
        layout.row().grid().add( new JLabel("" ), 3);
        layout.row().grid().add( currentText, 3).add( new JLabel( appInfo.getVersion().toString()) );
        layout.row().grid().add( availableText, 3).add( latests );
        layout.row().grid().add( new JLabel("" ), 3);
        layout.row().grid().add( new JLabel("" ), 3);
        layout.row().grid().add( configureText, 3).add(configure );
        layout.row().grid().add( pageText, 3).add(page );
        layout.row().grid().add( new JLabel("" ), 3);
        layout.row().grid().add( new JLabel("" ), 3).add(close );

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );
        pack();

        final JFrame me = this;

        close.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                me.dispose();
            }
        } );

        page.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                try {
                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/start" );
                } catch (Exception exp ) {
                    exp.printStackTrace();
                    Log.warning( "no connection to stroy home page");
                }
            }
        } );

//        configure.addActionListener( new ActionListener() {
//            public void actionPerformed( ActionEvent e ) {
//                prefsUI.showUpdatePref();
//                prefsUI.setVisible(true);
//            }
//        } );


    }

    public UpdateInfo setLatest( Version2 latest ) {
        latests.setText( latest.toString() );

        return this;
    }

}
