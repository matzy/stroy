package org.openCage.stroy.ui;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.muchsoft.util.Sys;
import com.muchsoft.util.mac.Java14Adapter;
import com.muchsoft.util.mac.Java14Handler;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.kleinod.io.FileUtils;
import org.openCage.kleinod.observe.ObservableRef;
import org.openCage.kleinod.observe.ObservableReference;
import org.openCage.kleinod.observe.Observer;
import org.openCage.kleinod.ui.Binding;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.ui.menu.PortableMenu;
import org.openCage.stroy.ui.menu.PortableMenuFactory;
import org.openCage.stroy.ui.prefs.PrefsUI;
import org.openCage.stroy.update.UpdateChecker;
import org.openCage.stroy.todo.app.About;
import org.openCage.stroy.todo.app.AboutImpl;
import org.openCage.stroy.todo.app.AppInfo;
import org.openCage.util.logging.Log;
import org.openCage.util.logging.LogHandlerPanel;
import org.openCage.util.ui.FileChooser;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.EventObject;

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

public class DirSelectorImpl extends JFrame
        implements DirSelector, Java14Handler{


    private final JProgressBar progressBar = new JProgressBar();

    private final JTextField oneTxt = new JTextField();
    private final JButton   oneButton   = new JButton("..");
    private final JTextField twoTxt = new JTextField();
    private final JButton   twoButton   = new JButton("..");
    //private final TextField threeTxt    = new TextField( "dir.third" );
    private final JButton   threeButton = new JButton("..");

    private final JButton go                = new JButton( Message.get( "DirSelector.Compare" ) );

    private final JTextField ignorePathTxt      = new JTextField();

    private final AppInfo appInfo;

    private final LogHandlerPanel logHandlerPanel;

    private org.openCage.stroy.ui.menu.Menu menu;

    private final UpdateChecker updateChecker;
    private final CompareBuilderFactory compareBuilderFactory;
    private PortableMenuFactory portableMenuFactory;
    private PrefsUI prefsUI;
    private final ObservableReference<String> dirFirst;
    private final ObservableReference<String> dirSecond;


    @Inject
    public DirSelectorImpl( final PrefsUI prefsUI,
                            final PortableMenuFactory portableMenuFactory,
                            final AppInfo appInfo,
                            final UpdateChecker updateChecker,
                            final CompareBuilderFactory cbf,
                            @Named( value = "dir.first")  final ObservableRef<String> dirFirst,
                            @Named( value = "dir.second") final ObservableRef<String> dirSecond,
                            LogHandlerPanel logHandlerPanel ) {
        super( "stroy");

        Binding.bind( oneTxt, dirFirst );
        Binding.bind( twoTxt, dirSecond );

        this.dirFirst = dirFirst;
        this.dirSecond = dirSecond;

        this.logHandlerPanel = logHandlerPanel;

        this.appInfo       = appInfo;
        this.updateChecker = updateChecker;
        this.compareBuilderFactory = cbf;
        this.portableMenuFactory = portableMenuFactory;
        this.prefsUI = prefsUI;

        Java14Adapter.registerJava14Handler( this );
        Java14Adapter.setEnabledPrefs( true );

        createLayout();
        createMenu(appInfo);

        addListeners();

        checkGo();

    }

    private void addListeners() {
        final DirSelector thisUI = this;
        go.setEnabled( false );
        go.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent) {
                Log.fine( "comparing: " + oneTxt.getText() + " with " + twoTxt.getText());
                try {
                    compareBuilderFactory.get( oneTxt.getText(), twoTxt.getText(), "" ).execute();
                } catch ( Throwable exp ) {
                    Log.warning( exp.getMessage());
                    return;
                }
                dispose();
            }
        });


        addFileChooser( oneButton, oneTxt, twoTxt);
        addFileChooser( twoButton, twoTxt, oneTxt );
        //addFileChooser( threeButton, threeTxt, oneTxt );


        dirFirst.getListenerControl().add(new Observer() {
            @Override
            public void call() {
                checkGo();
            }
        });

        dirSecond.getListenerControl().add( new Observer() {
            @Override
            public void call() {
                checkGo();
            }
        });

//        strategyCombo.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                String strategy = (String) strategyCombo.getSelectedItem();
//
//
//                if ( strategy.equals( "full" )) {
//                    matchStrategy = injector.getInstance( StandardFirst.class );
//                } else if ( strategy.equals( "structure only" )) {
//                    matchStrategy = injector.getInstance( StructureOnly.class );
//                }
//            }
//        });

    }

    private void createMenu(AppInfo appInfo) {

        for ( JFrame frame : Arrays.asList( this, logHandlerPanel/*, presetsPanel */)) {
            PortableMenu menu = portableMenuFactory.get();
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
                    if ( refFile.exists() ){
                        if ( refFile.isDirectory() ) {
                            baseDir = refFile.getAbsolutePath();
                        } else {
                            baseDir = refFile.getParentFile().getAbsolutePath();
                        }
                    }
                }

//                String dir = FileChooser.getDir( frameRef, baseDir);
                String dir = FileChooser.getAnyFile( frameRef, baseDir);

                if ( dir != null ) {
                    // TODO set prop
                    text.setText( FileUtils.normalizePath( dir ));

                    checkGo();
                }
            }
        });

    }

    private void checkGo() {


        boolean oneOk   = checkTextField( oneTxt );
        boolean twoOk   = checkTextField( twoTxt );
        //checkTextField( threeTxt );


        go.setEnabled( oneOk && twoOk );
    }

    private boolean checkTextField( JTextField txt ) {

        boolean textOk = false;

        if ( txt.getText().length() > 0 ) {
            File left = new File(txt.getText() );

            if ( left.exists() /*&& left.isDirectory() */) {
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

        // TODO modes
//        layout.row().label( "Strategy" ).add( strategyCombo );
        layout.row().grid( Message.getl( "DirSelector.first" ) ).add(oneTxt, 8 ). add(oneButton);
        layout.row().grid( Message.getl( "DirSelector.second" ) ).add(twoTxt, 8 ).add(twoButton);

        // TODO 3
//        layout.row().label( "Third").add( threeTxt, 8 ).add( threeButton );

         layout.row().grid().add( new JLabel(""),4).add( go ).add(new JLabel(""),4);

        // fix layout problem in no macs
        // TODO check newer designgridlayout
        if ( !Sys.isMacOSX() ) {
            layout.row().grid().empty();
        }


        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );

        pack();
    }

    public void progress(boolean b) {
        progressBar.setIndeterminate( b );
    }

    public void handleAbout(EventObject eventObject) {
        Java14Adapter.setHandled( eventObject, true );
        About about = new AboutImpl( appInfo);
        about.go();
    }

    public void handlePrefs(EventObject eventObject) {
        Java14Adapter.setHandled( eventObject, true );
        prefsUI.setVisible( true );
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
