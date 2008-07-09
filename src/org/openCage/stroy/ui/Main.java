package org.openCage.stroy.ui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.openCage.stroy.RuntimeModule;
import org.openCage.util.prefs.Preferences;

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

public class Main  {

    public static void main(String[] args) {

        // TODO param ?

        Preferences.setName( "stroy");


//        // TODO
//        String[] strategyNames = { "full", "structure only" };
//        PListSelectionString.getOrCreate( "stroy.first.strategy", new ListSelection( "full", strategyNames ));

        Injector injector = Guice.createInjector( new RuntimeModule() );

        DirSelector dirSelector = injector.getInstance( DirSelector.class);
        dirSelector.setVisible( true );

    }

}
