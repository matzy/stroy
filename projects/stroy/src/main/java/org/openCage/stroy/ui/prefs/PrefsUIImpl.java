package org.openCage.stroy.ui.prefs;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.comphy.ImmuProp;
import org.openCage.comphy.StringProperty;
import org.openCage.lang.inc.Str;
import org.openCage.stroy.file.FileTypes5;
import org.openCage.stroy.filter.IgnoreCentral5;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.update.UpdatePrefs;
import org.openCage.util.io.FileUtils;
import org.openCage.util.prefs.LocaleSelectionProperty5;
import org.openCage.util.prefs.LogLevelSelectionProperty5;

import javax.swing.*;
import java.awt.*;

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


    private ExternalPref fileTypes;
    private UpdatePrefs updatePrefs;
    private final LocaleSelectionProperty5 localeSelection;
    private final LogLevelSelectionProperty5 loglevelSelection;
    private final ImmuProp<Str> editorPref;
    private final ImmuProp<Str> diffPref;
    private final IgnoreCentral5 central;

    @Inject
    public PrefsUIImpl(final UpdatePrefs updatePrefs,
                       LocaleSelectionProperty5 localeSelection,
                       LogLevelSelectionProperty5 loglevelSelection,
                       @Named(value = "Editor") ImmuProp<Str> editorPref,
                       @Named(value = "DiffProg") ImmuProp<Str> diffPref,
                       FileTypes5 fileTypes, IgnoreCentral5 central) {
        this.updatePrefs = updatePrefs;
        this.localeSelection = localeSelection;
        this.loglevelSelection = loglevelSelection;
        this.editorPref = editorPref;
        this.diffPref = diffPref;
        this.central = central;
        this.fileTypes = new ExternalPref( this, fileTypes );

        createLayout();
    }

    @Override
    protected void createLayout() {
        setLayout( new BorderLayout() );

        setTitle( Message.get( "Pref.Title" ));

        tabbed = new JTabbedPane();

        tabbed.addTab( Message.get( "Pref.FileType.title" ), null, fileTypes   );
        tabbed.addTab( Message.get( "Pref.Filter.title" ), null,  new FilterFrameDetails(central));
        tabbed.addTab( Message.get( "Pref.StandardProgs.title" ), null,  new StandardProgUI( this, editorPref, diffPref));
//        tabbed.addTab( Message.get( "Pref.Logging.title" ), null,  new LogPrefs());
        tabbed.addTab( Message.get( "Pref.More.title" ), null, new MorePrefs( updatePrefs, localeSelection, loglevelSelection )  );

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
