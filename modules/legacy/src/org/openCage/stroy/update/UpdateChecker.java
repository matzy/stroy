package org.openCage.stroy.update;

import org.openCage.ui.impl.UpdateInfo;
import org.openCage.util.logging.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;

import com.google.inject.Inject;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Version;

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

public class UpdateChecker {


    private final Application     appInfo;
    private final UpdateInfo  updateView;
    private final Interval    interval;

    @Inject
    public UpdateChecker( final Application appInfo, final UpdateInfo updateView, final Interval interval ) {
        this.appInfo    = appInfo;
        this.updateView = updateView;
        this.interval   = interval;
    }

    public Version getLatestVersion() {
        try {
            BufferedReader reader =  new BufferedReader(
                    new InputStreamReader(
                            new URL( "http://stroy.wikidot.com/current" ).openStream()));

            String line = reader.readLine();

            String key = "current version is";

            while (line != null) {
                if ( line.contains( key )) {
                    String vv = line.substring( line.indexOf( key ) + key.length() + 1);
                    vv = vv.substring( 0, vv.indexOf( "</"));
//                    Version ret = versionParser.parseVersion( vv );
                    return null;// ret;
                }
                line = reader.readLine(); }

        } catch ( IOException e ) {
            Log.warning( "can not read update page" );
        } catch ( NumberFormatException exp ) {
            Log.warning( "update page has unexpected formatting" );
        }

        return appInfo.gettVersion();
    }

    public void check() {

        if ( interval.isTime() ) {
            checkAnyway();
        }
    }

    public boolean checkAnyway() {
        interval.done();

        Version latest = getLatestVersion();

        if ( latest.compareTo( appInfo.gettVersion()) > 0 ) {
        		throw new Error( "impl me" );
//            updateView.setLatest( latest ).setVisible( true );
            //return true;
        } else {
            return false;
        }
    }

    public boolean isConnected() {
        try {
            new InputStreamReader( new URL( "http://stroy.wikidot.com/current" ).openStream());

        } catch ( IOException e ) {
            return false;
        }

        return true;

    }


//    public static void main( String[] args ) {
//
//        System.out.println( new UpdateChecker().getLatestVersion() );
//
//    }




}
