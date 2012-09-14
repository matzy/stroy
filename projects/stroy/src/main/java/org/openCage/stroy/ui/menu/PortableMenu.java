package org.openCage.stroy.ui.menu;

import com.muchsoft.util.Sys;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.todo.app.About;
import org.openCage.stroy.todo.app.AboutImpl;
import org.openCage.stroy.todo.app.AppInfo;
import org.openCage.stroy.ui.help.HelpLauncher;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.util.ui.BrowserLauncher;
import org.openCage.util.www.Mailto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PortableMenu implements Menu {

    private AppInfo appInfo;
    private JFrame  frame;
    private static JFrame  logView;

    private JMenu menuWinFile = new JMenu( Message.get( "Menu.File" ) );
    private JMenu menuView    = new JMenu( Message.get( "Menu.View" ) );
    private JMenu menuHelp    = new JMenu( Message.get( "Menu.Help" ) );
    private PrefsUI prefsUI;

    public PortableMenu( final PrefsUI prefsUI) {
        this.prefsUI = prefsUI;
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
        JMenuItem ex = new JMenuItem( Message.get(  "Menu.Exit" ));
        menuWinFile.add( prefs );

        prefs.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                prefsUI.setVisible(true);
            }
        });

        JMenuItem exi = new JMenuItem( Message.get(  "Menu.Exit" ));
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
////                new ThinningWorker( null, Central.diffPane, Central.tasks, new Method1<Boolean, LindenNode<FileContent>>() {
////                    public Boolean call(LindenNode<FileContent> treeNode) {
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
            final AppInfo ai = appInfo;
            about.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    About about = new AboutImpl( appInfo);
                    about.go();
                }
            });
        }

        JMenuItem sendBugReport = new JMenuItem( Message.get(  "Menu.bugReport" ));
        menuHelp.add( sendBugReport );
        final AppInfo ai = appInfo;
        sendBugReport.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new Mailto(ai.getContactEmail())
                        .subject( "Bug Report: " )
                        .body( ai.getProgName() + " " + ai.getVersion())
                        .send();
            }
        });

        JMenuItem sendFeatureRequest = new JMenuItem( Message.get(  "Menu.featureRequest" ));
        menuHelp.add( sendFeatureRequest );
        sendFeatureRequest.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new Mailto(ai.getContactEmail())
                        .subject( "Feature Request: " )
                        .body( ai.getProgName() + " " + ai.getVersion())
                        .send();
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


    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public JMenu getMenuView() {
        return menuView;
    }

    public JMenu getMenuHelp() {
        return menuHelp;
    }
}
