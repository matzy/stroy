package org.openCage.gpad;

import com.explodingpixels.macwidgets.*;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.application.protocol.Application;
import org.openCage.fspath.clazz.FSPathBuilder;
import org.openCage.lang.clazz.Count;
import org.openCage.lang.clazz.MRU;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.lang.protocol.F0;
import org.openCage.lang.protocol.F1;
import org.openCage.localization.protocol.Localize;
import org.openCage.property.protocol.Property;
import org.openCage.ui.clazz.HUDWarning;
import org.openCage.ui.clazz.MenuBuilder;
import org.openCage.ui.clazz.MenuHelper;
import org.openCage.ui.clazz.PreferenceFrame;
import org.openCage.ui.clazz.TextEditorBuilder;
import org.openCage.ui.protocol.*;
import org.openCage.withResource.protocol.With;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static org.openCage.gpad.Constants.*;
import static org.openCage.ui.Constants.*;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class FaustUI extends JFrame {

    private final Application             application;
    private final With                    with;
    private final FileChooser             fileChooser;
    private final AboutSheet              about;
    private final OSXStandardEventHandler osxEventHandler;
    private final Localize                localize;
    private final BackgroundExecutor      executor;
    private final MenuHelper              menuHelper;
    private final TextEditorBuilder       textEditorBuilder;
    private final Property<MRU<String>> mru;

    private final UI2File ui2file;

    private String                  message;
    private JTextArea               textUI = new JTextArea();
    private TextEncoderIdx<String>  textEncoder;
    final private JButton           padButton = new JButton( new ImageIcon( getClass().getResource(LOCK_CLOSED_PNG)));//"faust-small.png")));
    private LabeledComponentGroup padGroup;
    private JLabel infoLabel;
    private MenuBuilder.MenuIM recent;
    private MenuBuilder menuBuilder;
    private static final String LOCK_OPEN_PNG = "lock_open.png";
    private static final String LOCK_CLOSED_PNG = "lock_closed.png";
    private final HUDWarning warning;

    @Inject
    public FaustUI(Application application,
                   With wth,
                   FileChooser chooser,
                   AboutSheet about,
                   OSXStandardEventHandler osxEventHandler,
                   BackgroundExecutor executor,
                   @Named(FAUSTERIZE) Localize localize,
                   MenuBuilder menubuilder,
                   @Named(LOCALE) PrefBuilder localePrefBuilder,
                   @Named(FAUSTERIZE) PrefBuilder codePref,
                   final PreferenceFrame prefFrame,
                   GlobalKeyEventHandler keyHandler,
                   MenuHelper menuHelper,
                   TextEditorBuilder textEditorBuilder,
                   Property<MRU<String>> mru,
                   HUDWarning warning ) {

        this.application     = application;
        this.with            = wth;
        this.fileChooser     = chooser;
        this.about           = about;
        this.osxEventHandler = osxEventHandler;
        this.executor        = executor;
        this.localize        = localize;
        this.menuHelper = menuHelper;
        this.textEditorBuilder = textEditorBuilder;
        this.mru = mru;
        this.menuBuilder = menubuilder;
        this.warning = warning;

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );        


        message = mru.get().getAll().iterator().next();

        this.ui2file = new UI2File( textUI, executor, localize,  new File(message));

        final JFrame theFrame = this;
        padButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                toggleEncryption(theFrame);
            }

        });

        JScrollPane scroll =  new JScrollPane(textUI);
        scroll.setSize( 800, 600 );
        scroll.setMinimumSize( new Dimension(400,400));
        textUI.setMinimumSize( new Dimension( 400, 400 ));
        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( scroll, BorderLayout.CENTER  );


        // For some versions of Mac OS X, Java will handle painting the Unified Tool Bar.
        // Calling this method ensures that this painting is turned on if necessary.
        MacUtils.makeWindowLeopardStyle( getRootPane());

        UnifiedToolBar toolBar = new UnifiedToolBar();
        toolBar.installWindowDraggerOnWindow( this );
//        padButton.putClientProperty("JButton.buttonType", "textured");
//        toolBar.addComponentToLeft(save);

        padGroup = new LabeledComponentGroup( localize.localize( "org.openCage.fausterize.toggleCode"),
                                                               padButton);
        toolBar.addComponentToLeft( padGroup.getComponent() );

        //padButton.putClientProperty("JButton.buttonType", "textured");
        final JTextField textField = new JTextField(10);
        textField.putClientProperty("JTextField.variant", "search");
        toolBar.addComponentToRight( new LabeledComponentGroup( localize.localize( "org.openCage.localization.dict.search"),
                                                                textField).getComponent());
        

        getContentPane().add( toolBar.getComponent(), BorderLayout.NORTH );

        BottomBar bottomBar = new BottomBar(BottomBarSize.SMALL);

        infoLabel = MacWidgetFactory.createEmphasizedLabel( message );

        bottomBar.addComponentToCenter( infoLabel );
        getContentPane().add( bottomBar.getComponent(), BorderLayout.SOUTH );

        setTitle( application.gettName());
        setSize( 800, 600 );

        // TODO
        textUI.setEditable(false);
        textUI.setBackground( Color.LIGHT_GRAY);

//        setTextEnabled( false );
//        setInitial( message );
        


        textEditorBuilder.setTextArea( textUI );
        textEditorBuilder.setFindField( textField );
        //textEditorBuilder.setConfCaret();

        pack();


        buildMenu( menubuilder, prefFrame, textEditorBuilder );

        // TODO
        prefFrame.addRow( "woo" ).add( codePref).add( localePrefBuilder ).build();

        F0<Void> showPrefs = new F0<Void>() {
            @Override
            public Void call() {
                prefFrame.setVisible( true );
                return null;
            }
        };

        osxEventHandler.addPrefsDelegate( showPrefs );



    }

    private String newMessage() {

        // exists check might not find this otherwise
        ui2file.write();

        int i = 1;

        File file = null;

        do {
            file = FSPathBuilder.getDocuments().add( Constants.FAUSTERIZE, "" + i + ".fst1").toFile();
            i++;
        } while ( file.exists());

        file.getParentFile().mkdirs();

        return file.getAbsolutePath();
    }

    private void toggleEncryption(JFrame theFrame) {
        if ( ui2file.isPadSet() && !ui2file.isEncoded()) {
            ui2file.codeToggle();
            setTextEnabled( !ui2file.isEncoded() );
        } else {

            String path = fileChooser.open( theFrame, FSPathBuilder.getARoot().toString());
            if ( path != null ) {

                try {
                    ui2file.setPad( new File( path ).toURI() );
                    if ( ui2file.isEncoded() ) {
                        ui2file.codeToggle();
                    }
                    setTextEnabled( true );
                } catch ( Unchecked exp ) {
                    if ( exp.getSource() instanceof IOException ) {
                        warning.show( "Warning", localize.localize( "org.openCage.fausterize.fileReadWarning", path));
                    } else {
                        warning.show( "Not a valid Pad Error", localize.localize( "org.openCage.fausterize.padWarning", path, exp.getSource().getMessage()) );
                    }
                }
            }
        }
    }


    private void setTextEnabled( boolean enable ) {
        if ( enable ) {
            textUI.setEditable( ui2file.isWritable() );
            textUI.setBackground( new Color( 220, 220, 255 ));
            padButton.setIcon( new ImageIcon( getClass().getResource(LOCK_OPEN_PNG)) );
        } else {
            textUI.setEditable(false);
            textUI.setBackground( Color.LIGHT_GRAY);
            padButton.setIcon( new ImageIcon( getClass().getResource(LOCK_CLOSED_PNG)) );
        }
    }

    private void buildMenu(  MenuBuilder mb, final PreferenceFrame prefFrame, TextEditorBuilder teBuilder ) {

        final JFrame that = this;

        mb.setOnFrame( this );

        recent = mb.menuOpenRecent();
        buildOpenRecentMenu();

        mb.addFile().
                add( mb.itemNew().action( new F0<Void>() {
                    @Override
                    public Void call() {
                        newOpenFile( newMessage() );
                        return null;
                    }
                }) ).
                add( mb.itemOpen().action( new F0<Void>() {
                    @Override
                    public Void call() {
                        newOpenFile(fileChooser.open( that, FSPathBuilder.getARoot().toString()));
                        return null;
                    }
                }) ).
                add( recent ).
                add( mb.itemSaveAs().action( new F0<Void>() {
                    @Override
                    public Void call() {
                        final String path = fileChooser.saveas( that, FSPathBuilder.getARoot().toString());
                        if ( path != null ) {
                            try {
                                ui2file.changeFile( new File(path));
                            } catch ( Unchecked exp ) {
                                warning.show( "foo", localize.localize( "org.openCage.fausterize.fileWriteWarning", path ) );
                                return null;
                            }
                            
                            infoLabel.setText( path );
                            mru.modify( new F1<Void, MRU<String>>() {
                                @Override
                                public Void call(MRU<String> mru) {
                                    mru.use( path );
                                    return null;
                                }
                            });
                            buildOpenRecentMenu();
                        }
                        return null;
                    }
                }) ).
                separator().
                add( mb.itemPrefs().action( new F0<Void>() {
                    @Override
                    public Void call() {
                        prefFrame.setVisible( true );
                        return null;
                    }
                }) ).
                add( mb.itemExit() );

        mb.addEdit().
                add( mb.item( localize.localize( "org.openCage.fausterize.toggleCode")).
                        action( new F0<Void>() {
                    @Override
                    public Void call() {
                        toggleEncryption( that );
                        return null;
                    }
                })).
                separator().
                add( teBuilder.itemCopy( mb )).
                add( teBuilder.itemPaste( mb ));

        mb.addSearch().
                add( teBuilder.itemFind( mb )).
                add( teBuilder.itemFindNext( mb ));

        mb.addHelp().
                add( mb.itemAbout()).
                add( mb.itemHelp());

    }

    private void newOpenFile( final String path) {
        if ( path != null ) {
            try {
                ui2file.setFile( new File(path));
            } catch ( Unchecked exp ) {
                warning.show( "Warning", localize.localize( "org.openCage.fausterize.fileReadWarning", path  ));
                return;
            }
            setTextEnabled(false);
            infoLabel.setText( path );
            mru.modify( new F1<Void, MRU<String>>() {
                @Override
                public Void call(MRU<String> mru) {
                    mru.use( path );
                    return null;
                }
            });
            buildOpenRecentMenu();
        }
    }

    private void buildOpenRecentMenu() {
        // rebuild
        recent.getMenu().removeAll();

        // add all but the current visible file
        for ( final Count<String> file : Count.count(mru.get().getAll())) {
            if ( !file.isFirst()) {
                final String path = file.obj();
                recent.add( menuBuilder.item( path ).
                    action( new F0<Void>() {
                        @Override
                        public Void call() {
                            newOpenFile( path );
                            return null;
                        }
                }));
            }
        }
    }


}
