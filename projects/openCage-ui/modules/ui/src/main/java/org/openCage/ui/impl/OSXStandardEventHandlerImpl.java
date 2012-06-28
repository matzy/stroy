package org.openCage.ui.impl;

import com.google.inject.Inject;
import com.muchsoft.util.mac.Java14Adapter;
import com.muchsoft.util.mac.Java14Handler;
import org.openCage.lang.functions.F0;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.OSXStandardEventHandler;

import java.util.EventObject;

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
