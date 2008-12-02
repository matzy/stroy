package org.openCage.stroy.ui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.openCage.stroy.RuntimeModule;
import org.openCage.stroy.ui.prefs.StandardProgUI;
import org.openCage.util.prefs.Preferences;
import org.openCage.util.prefs.PreferenceString;
import org.openCage.util.prefs.PListSelectionString;
import org.openCage.util.prefs.ListSelection;
import org.openCage.util.logging.LogHandlerPanel;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class Main  {

    public static void main(String[] args) {

        // TODO platform dependent stuff
        // TODO e.g. where are the help files
        // TODO param ?



        Preferences.setName( "stroy");
        // TODO windiws windiff ?
        PreferenceString.create( StandardProgUI.STANDARD_DIFF_KEY, "/usr/bin/opendiff" );

        String[] levelNames = { "ALL", "FINEST", "FINER", "FINE", "CONFIG", "INFO", "WARNING", "SEVERE", "OFF" };
        PListSelectionString.create( LogHandlerPanel.STROY_LOG_OUT, new ListSelection( levelNames, "INFO" ));
        PListSelectionString.create( LogHandlerPanel.STROY_LOG_IN, new ListSelection( levelNames, "INFO" ));

        String[] strategyNames = { "full", "structure only" };
        PListSelectionString.create( "stroy.first.strategy", new ListSelection( strategyNames, "full" ));


        Injector injector = Guice.createInjector( new RuntimeModule() );

        DirSelector dirSelector = injector.getInstance( DirSelector.class);
        dirSelector.setVisible( true );

    }

}
