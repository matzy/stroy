package org.openCage.stroy.ui.menu;

import org.openCage.util.ui.BrowserLauncher;
import org.openCage.application.protocol.Application;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.stroy.ui.help.HelpLauncher;
import org.openCage.stroy.ui.menu.Menu;
import org.openCage.stroy.locale.Message;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.muchsoft.util.Sys;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class PortableMenu implements Menu {

    private Application appInfo;
    private JFrame  frame;
    private static JFrame  logView;

    private JMenu menuWinFile = new JMenu( Message.get( "Menu.File" ) );
    private JMenu menuView    = new JMenu( Message.get("org.openCage.localization.dict.view") );
    private JMenu menuHelp    = new JMenu( Message.get( "Menu.Help" ) );

    public PortableMenu() {
        int i = 0;
    }


    public void create() {
        JMenuBar mbar = new JMenuBar();

        if ( Sys.isWindows() ) {
            mbar.add( menuWinFile );

            createFile();
        }

        mbar.add( menuView );
        mbar.add( menuHelp );
        frame.setJMenuBar( mbar );

        createHelp();
        createView();
    }

    private void createFile() {
        JMenuItem prefs = new JMenuItem( Message.get(  "Menu.Preference" ));
        JMenuItem ex = new JMenuItem( Message.get("org.openCage.localization.dict.exit"));
        menuWinFile.add( prefs );

        prefs.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                PrefsUI.create().setVisible( true );
            }
        });

        JMenuItem exi = new JMenuItem( Message.get("org.openCage.localization.dict.exit"));
        menuWinFile.add( exi );

        exi.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
      }

    private void createView() {
        JMenuItem showLog = new JMenuItem( Message.get(  "Menu.logMessages" ));
        menuView.add( showLog );
        showLog.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if ( logView != null ) {
                    logView.setVisible( true );
                }
            }
        });

        menuView.addSeparator();

        // TODO
//        JMenuItem toggleThinnig = new JMenuItem( "thin" );
//        menuView.add( toggleThinnig );
//        toggleThinnig.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
////                new ThinningWorker( null, Central.diffPane, Central.tasks, new Method1<Boolean, TreeNode<FileContent>>() {
////                    public Boolean call(TreeNode<FileContent> treeNode) {
////                        return treeNode.isLeaf();
////                    }
////                }).execute();
//            }
//        });
    }

    private void createHelp() {
        if ( Sys.isWindows() ) {
            JMenuItem about = new JMenuItem( Message.get(  "Menu.About" ));
            menuHelp.add( about );
            final Application ai = appInfo;
            about.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                		throw new Error( "Impl me" );
//                    About about = new AboutImpl( appInfo);
//                    about.setVisible( true );
                }
            });
        }

        JMenuItem sendBugReport = new JMenuItem( Message.get(  "Menu.bugReport" ));
        menuHelp.add( sendBugReport );
        final Application ai = appInfo;
        sendBugReport.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
        		throw new Error( "Impl me" );
//                new Mailto(ai.getContactEmail())
//                        .subject( "Bug Report: " )
//                        .body( ai.getName() + " " + ai.getVersion())
//                        .send();
            }
        });

        JMenuItem sendFeatureRequest = new JMenuItem( Message.get(  "Menu.featureRequest" ));
        menuHelp.add( sendFeatureRequest );
        sendFeatureRequest.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
        		throw new Error( "Impl me" );
//                new Mailto(ai.getContactEmail())
//                        .subject( "Feature Request: " )
//                        .body( ai.getProgName() + " " + ai.getVersion())
//                        .send();
            }
        });

        JMenuItem gotoforum = new JMenuItem( Message.get(  "Menu.forum" ));
        menuHelp.add( gotoforum );
        gotoforum.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    BrowserLauncher.displayURL( "http://stroy.wikidot.com/forum/c-43010/bugs-and-features" );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        menuHelp.addSeparator();

//        JMenuItem changeLog = new JMenuItem( "show changeLog" );
//        menuHelp.add( changeLog );
//        changeLog.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                new ShowText( "ChangeLog", ai.getChangeLog()).go();
//            }
//        });


        // in help menu now
//        JMenuItem refExt = new JMenuItem( "show references" );
//        menuHelp.add( refExt );
//        refExt.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                new ShowRefs( ai ).go();
//            }
//        });

//        menuHelp.addSeparator();

        JMenuItem help = new JMenuItem( Message.get(  "Menu.Help" ));
        menuHelp.add( help );
        help.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                HelpLauncher.showHelp();
            }
        });
    }


    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setLogView(JFrame logView) {
        this.logView = logView;
    }


    public void setAppInfo( Application appInfo) {
        this.appInfo = appInfo;
    }

    public JMenu getMenuView() {
        return menuView;
    }

    public JMenu getMenuHelp() {
        return menuHelp;
    }
}
