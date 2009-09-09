package org.openCage.util.www;

import org.openCage.util.ui.BrowserLauncher;
import org.openCage.util.logging.Log;
import org.w3.www.International.URLUTF8Encoder;

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


/**
 * creates a new email in the local email client
 */
public class Mailto {

    private final String address;
    private String       subject;
    private String       body;

    public Mailto( String address ) {
        this.address = address;
        subject      = null;
        body         = null;
    }

    public Mailto subject( String subject ) {
        this.subject = URLUTF8Encoder.encode(subject);
        return this;
    }

    public Mailto body( String body ) {
        this.body = URLUTF8Encoder.encode( body );
        return this;
    }


    /*
     * returns a correctly formed mailto string
     */
    public String toString() {

        String ret = "mailto:" + address;

        if ( subject != null ) {
            ret += "?subject=" + subject;
        }

        if ( body != null ) {
            ret += "&body=" + body;
        }

        return ret;
    }

    /*
     * new email in local email client with set address, subject ...
     */
    public void send() {
        try {
            BrowserLauncher.displayURL( toString() );
        } catch (Exception e) {
            Log.severe( "can't open browser" + e.toString() );
        }
    }
}
