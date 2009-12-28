package org.openCage.ui.impl;

import com.google.inject.Inject;
import com.muchsoft.util.mac.Java14Adapter;
import com.muchsoft.util.mac.Java14Handler;
import org.openCage.lang.protocol.F0;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.OSXStandardEventHandler;

import java.util.EventObject;

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

public class OSXStandardEventHandlerImpl implements OSXStandardEventHandler, Java14Handler{


    final private AboutSheet about;
    private F0<Void> prefs;

    @Inject
    public OSXStandardEventHandlerImpl( AboutSheet about ) {
        this.about = about;

        Java14Adapter.registerJava14Handler( this );
        Java14Adapter.setEnabledPrefs( true );
    }

    public void handleAbout(EventObject eventObject) {
        Java14Adapter.setHandled( eventObject, true );
        about.setVisible( true );
    }

    public void handlePrefs(EventObject eventObject) {
        if ( prefs != null ) {
            Java14Adapter.setHandled( eventObject, true );
            prefs.call();
        }
    }

    public void handleQuit(EventObject eventObject) {
        Java14Adapter.setHandled( eventObject, true );
        System.exit(0);
    }

    public void handleOpenApplication(EventObject eventObject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void handleReOpenApplication(EventObject eventObject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void handleOpenFile(EventObject eventObject, String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void handlePrintFile(EventObject eventObject, String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addPrefsDelegate(F0<Void> prefs) {
        this.prefs = prefs;
    }
}
