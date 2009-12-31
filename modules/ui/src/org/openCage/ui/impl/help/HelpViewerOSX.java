package org.openCage.ui.impl.help;

import org.openCage.io.clazz.Location;
import org.openCage.ui.impl.BrowseTmp;
import org.openCage.ui.protocol.HelpViewer;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
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

    @Override
    public void viewHelp() {

        String jarPath = Location.getJarLocation( HelpViewerOSX.class );

        String helpBookIndex = getHelpbookInApp( jarPath );

        if ( !new File(helpBookIndex).exists()) {
            LOG.info( "not an app, no: " + helpBookIndex );
            // TODO else ?
            return;
        }

        try {
            new BrowseTmp().browse( new URI( "file:" + helpBookIndex ));
        } catch (URISyntaxException e) {
            LOG.warning( "helpbook exists but can't be shown: " + helpBookIndex );
        }
    }

    private String getHelpbookInApp( String jarpath ) {
        String resourcepath = new File( jarpath ).getParentFile().getParentFile().getAbsolutePath();
        return resourcepath + "/" + Locale.getDefault().getDisplayLanguage() + ".lproj/HelpBook/index.html";
    }
}
