package org.openCage.ui.impl.help;

import com.muchsoft.util.Sys;
import org.openCage.ui.impl.BrowseTmp;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
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

public class HelpLauncher {
    private static final Logger LOG = Logger.getLogger( HelpLauncher.class.getName());

    public static void showHelp() {
        if ( Sys.isMacOSX() ) {
            try {
                  HelpBookLauncher.launch();
                return;
            } catch( UnsatisfiedLinkError err ) {
                LOG.warning("jni help library not found " + err);
            } catch ( NoClassDefFoundError err ) {
                LOG.warning("class not found in help lib " + err);
            }

            openHelpBook();

//            viewHomepage();

            return;
        }

//        if ( Sys.isWindows() ) {
//            if ( !new File( FileUtils.getCurrentDir() + "\\help\\index.html").exists() ) {
//                viewHomepage();
//                return;
//            }
//            try {
//                // in windows the currentdir is the location of the program
//                BrowserLauncher.displayURL( "file://" + FileUtils.getCurrentDir() + "\\help\\index.html");
//            } catch (Exception e) {
//                Log.warning( "show Help:" + e );
//            }
//            return;
//        }

        // TODO linux
        viewHomepage();

    }

    private static void viewHomepage() {
//        try {
//            BrowserLauncher.displayURL( "http://stroy.wikidot.com");
//        } catch (Exception e) {
//            Log.warning( "show Help:" + e );
//        }
    }

    private static void openHelpBook(){
        System.out.println( Locale.getDefault().getDisplayLanguage());

        File ff = new File(HelpLauncher.class.getProtectionDomain().getCodeSource().getLocation().getFile());
        ff = ff.getParentFile().getParentFile();
        String str =  "file:" + ff.getAbsolutePath() + "/" + Locale.getDefault().getDisplayLanguage() + ".lproj/org.openCage.gpad.HelpBook/index.html";

        // TODO check existing

        try {
            new BrowseTmp().browse( new URI( str ));
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

}
