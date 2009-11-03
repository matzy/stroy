package org.openCage.ui.impl;

import com.google.inject.Inject;
import com.muchsoft.util.mac.Java14Adapter;
import com.muchsoft.util.mac.Java14Handler;
import org.openCage.lang.protocol.F0;
import org.openCage.ui.protocol.AboutSheet;
import org.openCage.ui.protocol.OSXStandardEventHandler;

import java.util.EventObject;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 2, 2009
 * Time: 10:20:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class OSXStandardEventHandlerImpl implements OSXStandardEventHandler, Java14Handler{


    final private AboutSheet about;
    private F0<Void> prefs;

    @Inject
    public OSXStandardEventHandlerImpl( AboutSheet about ) {
        this.about = about;

        Java14Adapter.registerJava14Handler( this );
        Java14Adapter.setEnabledPrefs( true );
    }

    @Override
    public void handleAbout(EventObject eventObject) {
        Java14Adapter.setHandled( eventObject, true );
        about.setVisible( true );
    }

    @Override
    public void handlePrefs(EventObject eventObject) {
        if ( prefs != null ) {
            Java14Adapter.setHandled( eventObject, true );
            prefs.call();
        }
    }

    @Override
    public void handleQuit(EventObject eventObject) {
        Java14Adapter.setHandled( eventObject, true );
        System.exit(0);
    }

    @Override
    public void handleOpenApplication(EventObject eventObject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleReOpenApplication(EventObject eventObject) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handleOpenFile(EventObject eventObject, String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void handlePrintFile(EventObject eventObject, String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addPrefsDelegate(F0<Void> prefs) {
        this.prefs = prefs;
    }
}
