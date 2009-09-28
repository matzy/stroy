package org.openCage.stroy.ui;

import com.google.inject.Inject;
import com.muchsoft.util.mac.Java14Adapter;
import com.muchsoft.util.mac.Java14Handler;
import com.muchsoft.util.Sys;
import org.openCage.util.io.FileUtils;
import org.openCage.application.protocol.AboutSheet;
import org.openCage.application.protocol.Application;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.stroy.ui.menu.PortableMenu;
import org.openCage.stroy.update.UpdateChecker;
import org.openCage.stroy.locale.Message;
import org.openCage.util.ui.FileChooser;
import org.openCage.util.logging.LogHandlerPanel;
import org.openCage.util.prefs.PreferencesChangeListener;
import org.openCage.util.prefs.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.EventObject;
import java.io.File;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class DirSelectorImpl extends JFrame
        implements DirSelector, Java14Handler{


    private final JProgressBar progressBar = new JProgressBar();

    private final TextField oneTxt      =  new TextField( "dir.first" );
    private final JButton   oneButton   = new JButton("..");
    private final TextField twoTxt      = new TextField( "dir.second" );
    private final JButton   twoButton   = new JButton("..");
    private final TextField threeTxt    = new TextField( "dir.third" );
    private final JButton   threeButton = new JButton("..");

    private final JButton go                = new JButton( Message.get( "DirSelector.Compare" ) );

    private final JTextField ignorePathTxt      = new JTextField();

//    private final Application appInfo;
    private final AboutSheet aboutSheet;

    private final LogHandlerPanel logHandlerPanel = new LogHandlerPanel();

    private org.openCage.stroy.ui.menu.Menu menu;
//    private PComboBox strategyCombo = new PComboBox( "stroy.first.strategy" );

    private MatchStrategy<FileContent> matchStrategy;

    private final UpdateChecker updateChecker;


    @Inject
    public DirSelectorImpl( Application application, AboutSheet about, UpdateChecker updateChecker ) {
        super( "stroy");

        this.aboutSheet = about;
        this.updateChecker = updateChecker;

        Java14Adapter.registerJava14Handler( this );
        Java14Adapter.setEnabledPrefs( true );

        createLayout();
        createMenu( application );

        addListeners();

        checkGo();

    }

    private void addListeners() {
        final DirSelector thisUI = this;
        go.setEnabled( false );
        go.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent) {
                new CompareBuilder( oneTxt.getText(), twoTxt.getText(), threeTxt.getText() ).execute();
                dispose();
            }
        });


        addFileChooser( oneButton, oneTxt, twoTxt);
        addFileChooser( twoButton, twoTxt, oneTxt );
        addFileChooser( threeButton, threeTxt, oneTxt );


        oneTxt.getItem().addListener( new PreferencesChangeListener<String>() {
            public void changed(String txt) {
                checkGo();
            }
        });

        twoTxt.getItem().addListener( new PreferencesChangeListener<String>() {
            public void changed(String txt) {
                checkGo();
            }
        });

//        strategyCombo.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                String strategy = (String) strategyCombo.getSelectedItem();
//
//                Injector injector = Guice.createInjector( new RuntimeModule() );
//
//                if ( strategy.equals( "full" )) {
//                    matchStrategy = injector.getInstance( StandardFirst.class );
//                } else if ( strategy.equals( "structure only" )) {
//                    matchStrategy = injector.getInstance( StructureOnly.class );
//                }
//            }
//        });

    }

    private void createMenu(Application appInfo) {

        for ( JFrame frame : Arrays.asList( this, logHandlerPanel/*, presetsPanel */)) {
            PortableMenu menu = new PortableMenu();
            menu.setFrame( frame );
            //menu.setFilterView( filterFrame );
            menu.setLogView( logHandlerPanel );
            menu.setAppInfo( appInfo );

            menu.create();            
        }
    }



    private void addFileChooser( final JButton button, final JTextField text, final JTextField ref ) {

        final JFrame frameRef = this;

        button.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent) {

                String baseDir = FileUtils.getCurrentDir();

                if ( ref.getText().length() > 0 ) {
                    File refFile = new File( ref.getText() );
                    if ( refFile.exists() ) {
                        baseDir = refFile.getParentFile().getAbsolutePath();
                    }
                }

                String dir = FileChooser.getDir( frameRef, baseDir);

                if ( dir != null ) {
                    text.setText( FileUtils.normalizePath( dir ));

                    checkGo();
                }
            }
        });

    }

    private void checkGo() {


        boolean oneOk   = checkTextField( oneTxt );
        boolean twoOk   = checkTextField( twoTxt );
        checkTextField( threeTxt );


        go.setEnabled( oneOk && twoOk );
    }

    private boolean checkTextField( JTextField txt ) {

        boolean textOk = false;

        if ( txt.getText().length() > 0 ) {
            File left = new File(txt.getText() );

            if ( left.exists() && left.isDirectory() ) {
                textOk = true;
                txt.setBackground( Colors.BACKGROUND_NEUTRAL);
            } else {
                txt.setBackground( Colors.BACKGROUND_WARN);
            }
        } else {
            txt.setBackground( Color.WHITE );
        }
        return textOk;
    }

    private void createLayout() {
        setTitle( Message.get( "DirSelector.ChooseDirs" ));
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 400, 200 );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        // TODO modes
//        layout.row().label( "Strategy" ).add( strategyCombo );
        layout.row().label( Message.getl( "DirSelector.first" ) ).add(oneTxt, 8 ). add(oneButton);
        layout.row().label( Message.getl( "DirSelector.second" ) ).add(twoTxt, 8 ).add(twoButton);

        // TODO 3
//        layout.row().label( "Third").add( threeTxt, 8 ).add( threeButton );

         layout.row().add( new JLabel(""),4).add( go ).add(new JLabel(""),4);

        // fix layout problem in no macs
        // TODO check newer designgridlayout
        if ( !Sys.isMacOSX() ) {
            layout.row().empty();
        }


        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );

        pack();
    }

    public void progress(boolean b) {
        progressBar.setIndeterminate( b );
    }

//    public void setMatching( TreeMatchingTask<FileContent> matching1,
//                             TreeMatchingTask<FileContent> matching2,
//                             DefaultMutableTreeNode firstRoot,
//                             DefaultMutableTreeNode secondRoot,
//                             DefaultMutableTreeNode thirdRoot) {
//
//        List<TreeMatchingTask<FileContent>>  matchings = new ArrayList<TreeMatchingTask<FileContent>>();
//        List<DefaultMutableTreeNode>         roots     = new ArrayList<DefaultMutableTreeNode>();
//        matchings.add( matching1 );
//        roots.add(firstRoot);
//        roots.add(secondRoot);
//
//        if ( matching2 != null ) {
//            assert( thirdRoot != null );
//            matchings.add( matching2 );
//            roots.add(thirdRoot);
//        }
////        GraphicalDiff gd = new GraphicalDiff( matching1, matching2, secondRoot, firstRoot, thirdRoot );
////        gd.setVisible( true );
////
////        GraphicalDiffFlex gd3 = new GraphicalDiffFlex( Arrays.asList( matching1, matching2),
////                Arrays.asList( secondRoot, firstRoot, thirdRoot ));
////        gd3.setVisible(true);
//
//        PortableMenu menu = new PortableMenu();
//        //menu.setFilterView( filterFrame );
//        menu.setLogView( logHandlerPanel );
//        menu.setAppInfo( appInfo );
//
//
//
//        try {
////            GraphicalDiffMyDoggy gd4 = new GraphicalDiffMyDoggy( matchings, roots, menu );
////            gd4.setVisible(true);
//        } catch ( Exception exp ) {
//            Log.severe( "no diff tree: " + exp  );
//            progress( false );
//        }
//
//        dispose();
//    }

    public void handleAbout(EventObject eventObject) {
        Java14Adapter.setHandled( eventObject, true );
		aboutSheet.setVisible( true );
    }

    public void handlePrefs(EventObject eventObject) {
        Java14Adapter.setHandled( eventObject, true );
        PrefsUI.create().setVisible( true );
    }

    public void handleQuit(EventObject eventObject) {
        System.exit(0);
    }

    public void handleOpenApplication(EventObject eventObject) {}

    public void handleReOpenApplication(EventObject eventObject) {}

    public void handleOpenFile(EventObject eventObject, String string) {}

    public void handlePrintFile(EventObject eventObject, String string) {}


    @Override
    public void setVisible( boolean b ) {
        super.setVisible( b );

        updateChecker.check();
    }
}
