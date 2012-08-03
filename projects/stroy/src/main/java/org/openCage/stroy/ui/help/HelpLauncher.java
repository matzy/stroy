package org.openCage.stroy.ui.help;

import org.openCage.lang.inc.Null;
import org.openCage.util.logging.Log;
import org.openCage.util.prefs.LocaleSelectionProperty;
import org.openCage.util.ui.BrowserLauncher;

import java.net.URL;

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

    public static LocaleSelectionProperty localeSelection;

//    public HelpLauncher(LocaleSelectionProperty localeSelection) {
//        this.localeSelection = localeSelection;
//    }


    public static void showHelp() {

        String loc = localeSelection.getSelection().toString(); //toLanguageTag();

        URL url = HelpLauncher.class.getResource("/org/openCage/stroy/ui/help/" + loc + "/index.html");
        if (!Null.is(url)) {
            BrowserLauncher.displayURL( url );
        } else {
            viewHomepage();
        }

//        if ( Sys.isMacOSX() ) {
//            try {
//                HelpBookLauncher.launch();
//                return;
//            } catch( UnsatisfiedLinkError err ) {
//            } catch ( NoClassDefFoundError err ) {
//            }
//
//            viewHomepage();
//
//            return;
//        }
//
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
//
//        // TODO linux
//        viewHomepage();

    }

    private static void viewHomepage() {
        try {
            BrowserLauncher.displayURL( "http://stroy.wikidot.com");
        } catch (Exception e) {
            Log.warning( "show Help:" + e );
        }
    }

}
