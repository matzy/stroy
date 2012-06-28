package org.openCage.ui.impl.help;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.io.IOUtils;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.openCage.localization.Localize;
import org.openCage.ui.impl.BrowseTmp;
import org.openCage.ui.protocol.HelpViewer;

import javax.swing.*;
import java.io.File;
import java.util.Locale;
import java.util.logging.Logger;

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

public class HelpViewerOSX implements HelpViewer {

    private static final Logger LOG = Logger.getLogger( HelpViewerOSX.class.getName());
    private final Localize localize;

    @Inject
    public HelpViewerOSX( @Named( "ui") Localize localize ) {
        this.localize = localize;
    }

    @Override
    public void viewHelp() {

        String jarPath = IOUtils.getJarLocation( HelpViewerOSX.class );

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
