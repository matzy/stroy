package org.openCage.application.impl.update;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Version;
import org.openCage.localization.protocol.Localize;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import net.java.dev.designgridlayout.DesignGridLayout;

public class UpdateInfo extends JFrame {

    private final Application appInfo;
	private final Localize    localize; 

    private JLabel latests = new JLabel( "" );

    private JLabel infoText;
    private JLabel currentText;
    private JLabel availableText;
    private JButton configure;
    private JLabel configureText;
    private JButton close;
    private JButton page;
    private JLabel pageText;

    @Inject
    public UpdateInfo( final Application appInfo, @Named( "application" )  Localize localize ) {

        this.appInfo = appInfo;
        this.localize = localize;

        infoText = new JLabel( localize.localize(  "Update.info" ));
        currentText = new JLabel( localize.localize(  "Update.current" ));
        availableText = new JLabel( localize.localize(  "Update.available" ));
        configure = new JButton( localize.localize(  "Update.configure" ));
        configureText = new JLabel( localize.localize(  "Update.configureText" ));
        close = new JButton( localize.localize(  "Update.close" ));
        page = new JButton( localize.localize(  "Update.page" ));
        pageText = new JLabel( localize.localize(  "Update.pageText" ));

        infoText.setForeground( Color.BLUE );
        latests.setForeground( Color.BLUE );

        setTitle( localize.localize(  "Update.title" ));

        JPanel top = new JPanel();

        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        layout.row().add( infoText );
        layout.row().add( new JLabel("" ), 3);
        layout.row().add( new JLabel("" ), 3);
        layout.row().add( currentText, 3).add( new JLabel( appInfo.getVersion().toString()) );
        layout.row().add( availableText, 3).add( latests );
        layout.row().add( new JLabel("" ), 3);
        layout.row().add( new JLabel("" ), 3);
        layout.row().add( configureText, 3).add(configure );
        layout.row().add( pageText, 3).add(page );
        layout.row().add( new JLabel("" ), 3);
        layout.row().add( new JLabel("" ), 3).add(close );

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
//                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/start" );
                } catch (Exception exp ) {
                    exp.printStackTrace();
  //                  Log.warning( "no connection to stroy home page");
                }
            }
        } );

        configure.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
//                PrefsUI.create().showUpdatePref();
//                PrefsUI.create().setVisible( true );
            }
        } );


    }

    public UpdateInfo setLatest( Version latest ) {
        latests.setText( latest.toString() );

        return this;
    }

}
