package org.openCage.ui.impl.help;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.fspath.clazz.FSPathBuilder;
import org.openCage.fspath.protocol.FSPath;
import org.openCage.io.clazz.Location;
import org.openCage.localization.protocol.Localize;
import org.openCage.ui.impl.BrowseTmp;
import org.openCage.ui.protocol.HelpViewer;

import javax.swing.*;
import java.io.File;
import java.util.Locale;
import java.util.logging.Logger;

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

public class HelpViewerOSX implements HelpViewer {

    private static final Logger LOG = Logger.getLogger( HelpViewerOSX.class.getName());
    @Inject
    @Named( "ui") private Localize localize;

    @Override
    public void viewHelp() {

        String jarPath = Location.getJarLocation( HelpViewerOSX.class );

        FSPath helpBookIndex = getHelpbookInApp( jarPath );

        if ( !helpBookIndex.toFile().exists()) {
            LOG.info( "not an app, no: " + helpBookIndex );
            // TODO else ?

            System.out.println( "helpfile location:  " + helpBookIndex );
            JOptionPane.showMessageDialog(null, "no exists: " + helpBookIndex, "Error", JOptionPane.ERROR_MESSAGE);

            return;
        }

            new BrowseTmp().browse( helpBookIndex.toURI());
//        } catch (URISyntaxException e) {
//            LOG.warning( "helpbook exists but can't be shown: " + helpBookIndex );
//            JOptionPane.showMessageDialog(null, "throw " + helpBookIndex, "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }

    private FSPath getHelpbookInApp( String jarpath ) {
        return FSPathBuilder.getPath( new File( jarpath ).getParentFile().getParentFile() ).
                  add( localize.getLocale().getDisplayLanguage( Locale.US ) + ".lproj" ).
                  add( "HelpBook" ).
                  add( "index.html");
    }

}
