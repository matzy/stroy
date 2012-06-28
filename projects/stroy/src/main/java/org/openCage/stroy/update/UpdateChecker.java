package org.openCage.stroy.update;

import org.openCage.util.app.Version2;
import org.openCage.util.app.AppInfo;
import org.openCage.util.logging.Log;
import org.openCage.stroy.update.UpdateInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;

import com.google.inject.Inject;

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

public class UpdateChecker {


    private final AppInfo     appInfo;
    private final UpdateInfo  updateView;
    private final Interval    interval;

    @Inject
    public UpdateChecker( final AppInfo appInfo, final UpdateInfo updateView, final Interval interval ) {
        this.appInfo    = appInfo;
        this.updateView = updateView;
        this.interval   = interval;
    }

    public Version2 getLatestVersion() {
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
                    Version2 ret = Version2.parseVersion( vv );
                    return ret;
                }
                line = reader.readLine(); }

        } catch ( IOException e ) {
            Log.warning( "can not read update page" );
        } catch ( NumberFormatException exp ) {
            Log.warning( "update page has unexpected formatting" );
        }

        return appInfo.getVersion();
    }

    public void check() {

        if ( interval.isTime() ) {
            checkAnyway();
        }
    }

    public boolean checkAnyway() {
        interval.isTime();

        Version2 latest = getLatestVersion();

        if ( latest.compareTo( appInfo.getVersion()) > 0 ) {
            updateView.setLatest( latest ).setVisible( true );
            return true;
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
