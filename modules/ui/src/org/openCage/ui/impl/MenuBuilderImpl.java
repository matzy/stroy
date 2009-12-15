package org.openCage.ui.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.muchsoft.util.Sys;
import org.openCage.application.protocol.Application;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.MenuBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 14, 2009
 * Time: 5:59:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuBuilderImpl implements MenuBuilder {

    private final Localize loca;
    private final Application app;
    private final AboutSheet aboutSheet;

    private JMenuBar mbar = new JMenuBar();
    private boolean showPrefs = true;
    private JMenu menuFile;
    private JMenu menuHelp;
    private boolean showExit = true;
    private boolean showHelp;
    private JMenu menuView;

    private boolean isMac = false; //Sys.isMacOSX();



    @Inject
    public MenuBuilderImpl( Application app, @Named( "ui" ) Localize loca, 	AboutSheet aboutSheet ) {
        this.app = app;
        this.loca = loca;
        this.aboutSheet = aboutSheet;

        menuFile = new JMenu( loca.localize( "org.openCage.localization.dict.file" ) );
        menuView = new JMenu( loca.localize( "org.openCage.localization.dict.view" ) );
        menuHelp = new JMenu( loca.localize( "org.openCage.localization.dict.help" ) );
    }

    public void setMenuOnFrame(JFrame frame) {
        createFile();
        createHelp();
        frame.setJMenuBar( mbar );        
    }

    public MenuBuilder withExit() {
        this.showExit = true;
        return this;
    }

    public MenuBuilder withView() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public MenuBuilder withAbout() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public MenuBuilder withHelp() {
        this.showHelp = true;
        return this;
    }

    public MenuBuilder withPreferences() {
        this.showPrefs = true;
        return this;
    }

    private void createFile() {

        mbar.add( menuFile );

        if (showPrefs) {
            if ( isMac ) {
                JMenuItem prefs = new JMenuItem( loca.localize( "org.openCage.localization.dict.preference" ));
                menuFile.add( prefs );
            }
        }

        if ( showExit ) {
            JMenuItem ex = new JMenuItem( loca.localize("org.openCage.localization.dict.exit"));
            menuFile.add( ex );

            ex.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });

        }
    }

    private void createHelp() {

        mbar.add( menuHelp );

        if ( !isMac ) {
            JMenuItem about = new JMenuItem( loca.localize("org.openCage.localization.dict.about" ));
            menuHelp.add( about );
            about.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    aboutSheet.setVisible( true );
                }
            });
        }

//        JMenuItem sendBugReport = new JMenuItem( Message.get(  "Menu.bugReport" ));
//        menuHelp.add( sendBugReport );
//        final Application ai = appInfo;
//        sendBugReport.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//        		throw new Error( "Impl me" );
////                new Mailto(ai.getContactEmail())
////                        .subject( "Bug Report: " )
////                        .body( ai.getName() + " " + ai.getVersion())
////                        .send();
//            }
//        });
//
//        JMenuItem sendFeatureRequest = new JMenuItem( Message.get(  "Menu.featureRequest" ));
//        menuHelp.add( sendFeatureRequest );
//        sendFeatureRequest.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//        		throw new Error( "Impl me" );
////                new Mailto(ai.getContactEmail())
////                        .subject( "Feature Request: " )
////                        .body( ai.getProgName() + " " + ai.getVersion())
////                        .send();
//            }
//        });
//
//        JMenuItem gotoforum = new JMenuItem( Message.get(  "Menu.forum" ));
//        menuHelp.add( gotoforum );
//        gotoforum.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                try {
//                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/forum/c-43010/bugs-and-features" );
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        menuHelp.addSeparator();
//
////        JMenuItem changeLog = new JMenuItem( "show changeLog" );
////        menuHelp.add( changeLog );
////        changeLog.addActionListener( new ActionListener() {
////            public void actionPerformed(ActionEvent actionEvent) {
////                new ShowText( "ChangeLog", ai.getChangeLog()).go();
////            }
////        });
//
//
//        // in help menu now
////        JMenuItem refExt = new JMenuItem( "show references" );
////        menuHelp.add( refExt );
////        refExt.addActionListener( new ActionListener() {
////            public void actionPerformed(ActionEvent actionEvent) {
////                new ShowRefs( ai ).go();
////            }
////        });
//
////        menuHelp.addSeparator();
//
//        JMenuItem help = new JMenuItem( Message.get(  "Menu.Help" ));
//        menuHelp.add( help );
//        help.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                HelpLauncher.showHelp();
//            }
//        });

    }



}
