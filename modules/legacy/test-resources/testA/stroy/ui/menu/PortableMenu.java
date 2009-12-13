package org.openCage.stroy.ui.menu;

import org.openCage.util.app.AppInfo;
import org.openCage.util.app.ShowText;
import org.openCage.util.app.ShowRefs;
import org.openCage.util.www.Mailto;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.stroy.ui.help.HelpBookLauncher;
import org.openCage.stroy.ui.menu.Menu;
import org.openCage.util.ui.BrowserLauncher;
import org.openCage.util.io.FileUtils;
import org.openCage.util.logging.Log;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.muchsoft.util.Sys;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class PortableMenu implements Menu {

    private AppInfo appInfo;
    private JFrame  frame;
    private JFrame  logView;
    private JFrame  filterView;

    private JMenu menuWinFile = new JMenu( "File" );
    private JMenu menuView    = new JMenu( "View" );
    private JMenu menuHelp    = new JMenu( "Help" );


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
        JMenuItem prefs = new JMenuItem( "Preferences" );
        menuWinFile.add( prefs );

        prefs.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                PrefsUI.create().setVisible( true );
            }
        });
      }

    private void createView() {
        JMenuItem showLog = new JMenuItem( "log messages" );
        menuView.add( showLog );
        showLog.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                logView.setVisible( true );
            }
        });

    }

    private void createHelp() {
        JMenuItem sendBugReport = new JMenuItem( "send bug report" );
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

        JMenuItem sendFeatureRequest = new JMenuItem( "send feature request" );
        menuHelp.add( sendFeatureRequest );
        sendFeatureRequest.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new Mailto(ai.getContactEmail())
                        .subject( "Feature Request: " )
                        .body( ai.getProgName() + " " + ai.getVersion())
                        .send();
            }
        });

        menuHelp.addSeparator();

        JMenuItem changeLog = new JMenuItem( "show changeLog" );
        menuHelp.add( changeLog );
        changeLog.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                new ShowText( "ChangeLog", ai.getChangeLog()).go();
            }
        });


        // in help menu now
//        JMenuItem refExt = new JMenuItem( "show references" );
//        menuHelp.add( refExt );
//        refExt.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                new ShowRefs( ai ).go();
//            }
//        });

        menuHelp.addSeparator();

        JMenuItem help = new JMenuItem("org.openCage.localization.dict.help");
        menuHelp.add( help );
        help.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                if ( Sys.isMacOSX() ) {
                    HelpBookLauncher.launch();
                } else {
                    try {
                        // TODO make that work for linux
                        Log.warning( "pwd = " + FileUtils.getCurrentDir() );
                        BrowserLauncher.displayURL( "file://" + FileUtils.getCurrentDir() + "\\help\\index.html");
                    } catch (Exception e) {
                        Log.warning( "" + e );
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setLogView(JFrame logView) {
        this.logView = logView;
    }

    public void setFilterView(JFrame filterView) {
        this.filterView = filterView;
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
