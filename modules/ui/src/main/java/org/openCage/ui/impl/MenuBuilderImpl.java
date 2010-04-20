//package org.openCage.ui.impl;
//
//import com.google.inject.Inject;
//import com.google.inject.name.Named;
//import com.muchsoft.util.Sys;
//import org.openCage.application.protocol.Application;
//import org.openCage.lang.protocol.F0;
//import org.openCage.localization.protocol.Localize;
//import org.openCage.ui.protocol.AboutSheet;
//import org.openCage.ui.protocol.HelpViewer;
//import org.openCage.ui.protocol.MenuBuilder;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//
//public class MenuBuilderImpl {
//
//    public static Color BAR_BACKGROUND = new Color(30, 30, 30);
//
//    private final Localize loca;
//    private final Application app;
//    private final AboutSheet aboutSheet;
//    private final HelpViewer helpViewer;
//
//    private JMenuBar mbar = new JMenuBar();
//    private boolean showPrefs = true;
//    private JMenu menuFile;
//    private JMenu menuHelp;
//    private boolean showExit = true;
//    private boolean showHelp;
//    private JMenu menuView;
//
//    private boolean isMac = Sys.isMacOSX();
//    private JMenuItem menuItemPrefs;
//
//
//    @Inject
//    public MenuBuilderImpl( Application app, @Named( "ui" ) Localize loca, 	AboutSheet aboutSheet, HelpViewer helpViewer ) {
//        this.app = app;
//        this.loca = loca;
//        this.aboutSheet = aboutSheet;
//        this.helpViewer = helpViewer;
//
//        menuFile  = new JMenu( loca.localize( "org.openCage.ui.menu.file" ) );
//        menuView  = new JMenu( loca.localize( "org.openCage.localization.dict.view" ) );
//        menuHelp  = new JMenu( loca.localize( "org.openCage.localization.dict.help" ) );
//        menuItemPrefs = new JMenuItem( loca.localize( "org.openCage.localization.dict.preference" ));
//
//        mbar.setBackground( BAR_BACKGROUND );
//        menuFile.setBackground( BAR_BACKGROUND );
//        menuView.setBackground( BAR_BACKGROUND );
//        menuHelp.setBackground( BAR_BACKGROUND );
//        menuItemPrefs.setBackground( BAR_BACKGROUND );
//    }
//
//    public void setMenuOnFrame(JFrame frame) {
//        createFile();
//        createHelp();
//        frame.setJMenuBar( mbar );
//    }
//
//    public MenuBuilder withExit() {
//        this.showExit = true;
//        return this;
//    }
//
//    public MenuBuilder withView() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public MenuBuilder withAbout() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public MenuBuilder withHelp() {
//        this.showHelp = true;
//        return this;
//    }
//
//    public MenuBuilder withPreferences() {
//        this.showPrefs = true;
//        return this;
//    }
//
//    @Override
//    public void addPrefsDelegate( final F0<Void> prefs) {
//        menuItemPrefs.addActionListener( new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                prefs.call();
//            }
//        });
//    }
//
//    private void createFile() {
//
//        mbar.add( menuFile );
//
//        if (showPrefs) {
//            if ( !isMac ) {
//                menuFile.add(menuItemPrefs);
//            }
//        }
//
//        if ( showExit ) {
//            JMenuItem ex = new JMenuItem( loca.localize("org.openCage.localization.dict.exit"));
//            menuFile.add( ex );
//            //ex.setMnemonic( 'Q');
//            KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
//            ex.setAccelerator( stroke );
//
//            ex.addActionListener( new ActionListener() {
//                public void actionPerformed(ActionEvent actionEvent) {
//                    System.exit(0);
//                }
//            });
//
//        }
//    }
//
//    private void createHelp() {
//
//        mbar.add( menuHelp );
//
//        if ( !isMac ) {
//            JMenuItem about = new JMenuItem( loca.localize("org.openCage.ui.about_prog", app.gettName() ));
//            menuHelp.add( about );
//            about.addActionListener( new ActionListener() {
//                public void actionPerformed(ActionEvent actionEvent) {
//                    aboutSheet.setVisible( true );
//                }
//            });
//        }
//
////        JMenuItem sendBugReport = new JMenuItem( Message.get(  "Menu.bugReport" ));
////        menuHelp.add( sendBugReport );
////        final Application ai = appInfo;
////        sendBugReport.addActionListener( new ActionListener() {
////            public void actionPerformed(ActionEvent actionEvent) {
////        		throw new Error( "Impl me" );
//////                new Mailto(ai.getContactEmail())
//////                        .subject( "Bug Report: " )
//////                        .body( ai.getName() + " " + ai.getVersion())
//////                        .send();
////            }
////        });
////
////        JMenuItem sendFeatureRequest = new JMenuItem( Message.get(  "Menu.featureRequest" ));
////        menuHelp.add( sendFeatureRequest );
////        sendFeatureRequest.addActionListener( new ActionListener() {
////            public void actionPerformed(ActionEvent actionEvent) {
////        		throw new Error( "Impl me" );
//////                new Mailto(ai.getContactEmail())
//////                        .subject( "Feature Request: " )
//////                        .body( ai.getProgName() + " " + ai.getVersion())
//////                        .send();
////            }
////        });
////
////        JMenuItem gotoforum = new JMenuItem( Message.get(  "Menu.forum" ));
////        menuHelp.add( gotoforum );
////        gotoforum.addActionListener( new ActionListener() {
////            public void actionPerformed(ActionEvent actionEvent) {
////                try {
////                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/forum/c-43010/bugs-and-features" );
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        });
////
////        menuHelp.addSeparator();
////
//////        JMenuItem changeLog = new JMenuItem( "show changeLog" );
//////        menuHelp.add( changeLog );
//////        changeLog.addActionListener( new ActionListener() {
//////            public void actionPerformed(ActionEvent actionEvent) {
//////                new ShowText( "ChangeLog", ai.getChangeLog()).go();
//////            }
//////        });
////
////
////        // in help menu now
//////        JMenuItem refExt = new JMenuItem( "show references" );
//////        menuHelp.add( refExt );
//////        refExt.addActionListener( new ActionListener() {
//////            public void actionPerformed(ActionEvent actionEvent) {
//////                new ShowRefs( ai ).go();
//////            }
//////        });
////
//////        menuHelp.addSeparator();
////
//        JMenuItem help = new JMenuItem( loca.localize("org.openCage.localization.dict.help" ));
//        menuHelp.add( help );
//        help.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                helpViewer.viewHelp();
//            }
//        });
//
//    }
//
//
//
//}
