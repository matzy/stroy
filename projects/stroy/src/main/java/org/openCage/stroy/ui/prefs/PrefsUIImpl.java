package org.openCage.stroy.ui.prefs;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.kleinod.io.FileUtils;
import org.openCage.kleinod.observe.ObservableRef;
import org.openCage.kleinod.ui.GlobalKeyEventHandlerImpl;
import org.openCage.ruleofthree.property.MapProperty;
import org.openCage.stroy.file.FileTypes;
import org.openCage.stroy.filter.IgnoreCentral;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.update.UpdatePrefs;
import org.openCage.util.external.DesktopX;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.logging.Level;

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

public class PrefsUIImpl extends PrefsUI {

//    private static PrefsUI me = null;
    private JTabbedPane tabbed;

//    public static PrefsUI create() {
//        if ( me == null ) {
//            me = new PrefsUI();
//        }
//
//        return me;
//    }


    private ExternalPref2 fileTypes;
    private UpdatePrefs updatePrefs;
    private final ObservableRef<String> editorPref;
    private final ObservableRef<String> diffPref;
    private final IgnoreCentral central;
    private final DesktopX desktop;
    private final MapProperty<ObservableRef<String>> progList;
    private final ObservableRef<String> sel1;
    private final ObservableRef<Level> level;
    private final ObservableRef<Locale> localeProp;

    @Inject
    public PrefsUIImpl(final UpdatePrefs updatePrefs,
                       @Named("Editor") ObservableRef<String> editorPref,
                       @Named("DiffProg") ObservableRef<String> diffPref,
                       FileTypes fileTypes,
                       IgnoreCentral central,
                       DesktopX desktop,
                       @Named("progList") MapProperty<ObservableRef<String>> progList,
                       @Named("progSel")  ObservableRef<String> sel1,
                       @Named("loglevel") ObservableRef<Level> level,
                       @Named("locale")   ObservableRef<Locale> localeProp) {
        this.updatePrefs = updatePrefs;
        this.localeProp = localeProp;
        this.editorPref = editorPref;
        this.diffPref = diffPref;
        this.central = central;
        this.desktop = desktop;
        this.progList = progList;
        this.sel1 = sel1;
        this.level = level;
        this.fileTypes = new ExternalPref2( this, fileTypes, progList);

        createLayout();

        new GlobalKeyEventHandlerImpl().addCloseWindow( this );
    }

    @Override
    protected void createLayout() {
        setLayout( new BorderLayout() );

        setTitle( Message.get( "Pref.Title" ));

        tabbed = new JTabbedPane();

        tabbed.addTab( Message.get( "Pref.FileType.title" ), null, fileTypes   );
        tabbed.addTab( Message.get( "Pref.Filter.title" ), null,  new FilterFrameDetails(central));
        tabbed.addTab( Message.get( "Pref.StandardProgs.title" ), null,  new StandardProgUI( this, editorPref, diffPref, desktop, sel1, progList));
//        tabbed.addTab( Message.get( "Pref.Logging.title" ), null,  new LogPrefs());
        tabbed.addTab( Message.get( "Pref.More.title" ), null, new MorePrefs( updatePrefs, localeProp, level )  );

        getContentPane().add( tabbed, BorderLayout.CENTER );


        pack();
    }

    @Override
    public void showFileType( String fileName ) {

        tabbed.setSelectedComponent( fileTypes );
        fileTypes.showExtension( FileUtils.getExtension( fileName ));
    }

    @Override
    public void showUpdatePref() {
        tabbed.setSelectedComponent( updatePrefs );
    }
}
