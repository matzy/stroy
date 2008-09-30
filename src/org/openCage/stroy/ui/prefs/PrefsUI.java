package org.openCage.stroy.ui.prefs;

import org.openCage.stroy.ui.prefs.ExternalPref;
import org.openCage.stroy.ui.prefs.StandardProgUI;
import org.openCage.stroy.ui.prefs.FilterFrameDetails;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.update.UpdatePrefs;
import org.openCage.stroy.RuntimeModule;
import org.openCage.util.io.FileUtils;

import javax.swing.*;
import java.awt.*;

import com.google.inject.Injector;
import com.google.inject.Guice;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class PrefsUI extends JFrame {

    private static PrefsUI me = null;
    private JTabbedPane tabbed;

    public static PrefsUI create() {
        if ( me == null ) {
            me = new PrefsUI();
        }

        return me;
    }


    private ExternalPref fileTypes   = new ExternalPref( this );
    private UpdatePrefs updatePrefs;

    private PrefsUI() {
        Injector injector = Guice.createInjector( new RuntimeModule() );
        updatePrefs = injector.getInstance( UpdatePrefs.class );        
        
        createLayout();
    }

    private void createLayout() {
        setLayout( new BorderLayout() );

        setTitle( Message.get( "Pref.Title" ));

        tabbed = new JTabbedPane();

        tabbed.addTab( Message.get( "Pref.FileType.title" ), null, fileTypes   );
        tabbed.addTab( Message.get( "Pref.Filter.title" ), null,  new FilterFrameDetails());
        tabbed.addTab( Message.get( "Pref.StandardProgs.title" ), null,  new StandardProgUI( this ));
//        tabbed.addTab( Message.get( "Pref.Logging.title" ), null,  new LogPrefs());
//        tabbed.addTab( Message.get( "Pref.Update.title" ), null,  updatePrefs );
        tabbed.addTab( Message.get( "Pref.More.title" ), null, new MorePrefs()  );

        getContentPane().add( tabbed, BorderLayout.CENTER );


        pack();
    }

    public void showFileType( String fileName ) {

        tabbed.setSelectedComponent( fileTypes );
        fileTypes.showExtension( FileUtils.getExtension( fileName ));
    }

    public void showUpdatePref() {
        tabbed.setSelectedComponent( updatePrefs );
    }
}
