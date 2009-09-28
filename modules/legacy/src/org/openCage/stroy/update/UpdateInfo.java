//package org.openCage.stroy.update;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//import org.openCage.util.app.AppInfo;
//import org.openCage.util.app.Version2;
//import org.openCage.util.ui.BrowserLauncher;
//import org.openCage.util.logging.Log;
//import org.openCage.stroy.locale.Message;
//import org.openCage.stroy.ui.prefs.PrefsUI;
//import com.google.inject.Inject;
//import net.java.dev.designgridlayout.DesignGridLayout;
//
//public class UpdateInfo extends JFrame {
//
//    private final AppInfo appInfo;
//    private JLabel latests = new JLabel( "" );
//
//    private JLabel infoText = new JLabel( Message.get( "Update.info" ));
//    private JLabel currentText = new JLabel( Message.get( "Update.current" ));
//    private JLabel availableText = new JLabel( Message.get( "Update.available" ));
//    private JButton configure = new JButton( Message.get( "Update.configure" ));
//    private JLabel configureText = new JLabel( Message.get( "Update.configureText" ));
//    private JButton close = new JButton( Message.get( "Update.close" ));
//    private JButton page = new JButton( Message.get( "Update.page" ));
//    private JLabel pageText = new JLabel( Message.get( "Update.pageText" ));
//
//    @Inject
//    public UpdateInfo( final AppInfo appInfo ) {
//
//        this.appInfo = appInfo;
//
//        infoText.setForeground( Color.BLUE );
//        latests.setForeground( Color.BLUE );
//
//        setTitle( Message.get( "Update.title" ));
//
//        JPanel top = new JPanel();
//
//        DesignGridLayout layout = new DesignGridLayout( top );
//        top.setLayout( layout );
//
//        layout.row().add( infoText );
//        layout.row().add( new JLabel("" ), 3);
//        layout.row().add( new JLabel("" ), 3);
//        layout.row().add( currentText, 3).add( new JLabel( appInfo.getVersion().toString()) );
//        layout.row().add( availableText, 3).add( latests );
//        layout.row().add( new JLabel("" ), 3);
//        layout.row().add( new JLabel("" ), 3);
//        layout.row().add( configureText, 3).add(configure );
//        layout.row().add( pageText, 3).add(page );
//        layout.row().add( new JLabel("" ), 3);
//        layout.row().add( new JLabel("" ), 3).add(close );
//
//        getContentPane().setLayout( new BorderLayout());
//        getContentPane().add( top, BorderLayout.CENTER  );
//        pack();
//
//        final JFrame me = this;
//
//        close.addActionListener( new ActionListener() {
//            public void actionPerformed( ActionEvent e ) {
//                me.dispose();
//            }
//        } );
//
//        page.addActionListener( new ActionListener() {
//            public void actionPerformed( ActionEvent e ) {
//                try {
//                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/start" );
//                } catch (Exception exp ) {
//                    exp.printStackTrace();
//                    Log.warning( "no connection to stroy home page");
//                }
//            }
//        } );
//
//        configure.addActionListener( new ActionListener() {
//            public void actionPerformed( ActionEvent e ) {
//                PrefsUI.create().showUpdatePref();
//                PrefsUI.create().setVisible( true );
//            }
//        } );
//
//
//    }
//
//    public UpdateInfo setLatest( Version2 latest ) {
//        latests.setText( latest.toString() );
//
//        return this;
//    }
//
//}
