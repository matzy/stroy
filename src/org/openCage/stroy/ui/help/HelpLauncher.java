package org.openCage.stroy.ui.help;

import com.muchsoft.util.Sys;
import org.openCage.util.logging.Log;
import org.openCage.util.io.FileUtils;
import org.openCage.util.ui.BrowserLauncher;

import java.io.File;

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
 * Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
 * All Rights Reserved.
 *
 * Contributor(s):
 ***** END LICENSE BLOCK *****/

public class HelpLauncher {

    public static void showHelp() {
        if ( Sys.isMacOSX() ) {
            try {
                HelpBookLauncher.launch();
                return;
            } catch( UnsatisfiedLinkError err ) {
            } catch ( NoClassDefFoundError err ) {
            }

            viewHomepage();

            return;
        }

        if ( Sys.isWindows() ) {
            if ( !new File( FileUtils.getCurrentDir() + "\\help\\index.html").exists() ) {
                viewHomepage();
                return;
            }
            try {
                // in windows the currentdir is the location of the program
                BrowserLauncher.displayURL( "file://" + FileUtils.getCurrentDir() + "\\help\\index.html");
            } catch (Exception e) {
                Log.warning( "show Help:" + e );
            }
            return;
        }

        // TODO linux
        viewHomepage();

    }

    private static void viewHomepage() {
        try {
            BrowserLauncher.displayURL( "http://stroy.wikidot.com");
        } catch (Exception e) {
            Log.warning( "show Help:" + e );
        }
    }

}
