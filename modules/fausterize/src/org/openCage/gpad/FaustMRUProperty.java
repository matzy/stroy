package org.openCage.gpad;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.fspath.clazz.FSPathBuilder;
import org.openCage.lang.clazz.MRU;
import org.openCage.property.clazz.AbstractPropertyProvider;
import org.openCage.property.protocol.PropStore;

import java.io.File;

import static org.openCage.gpad.Constants.PROJECT_DIR;

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

/**
 * A persisted MRU for opened files for fausterize
 */
public class FaustMRUProperty extends AbstractPropertyProvider<MRU<String>> {

    public static final String KEY = "FaustMRU";

    @Inject
    public FaustMRUProperty( @Named("trans") PropStore store ) {
        super( store, KEY, getDefault() );

    }

    private static MRU<String> getDefault() {
        MRU<String> mru = new MRU<String>();

        String message = FSPathBuilder.getHome().add(PROJECT_DIR, "1.fst1").toString();

        // add legacy message (0.6 and before) if it exists
        if ( new File( message ).exists() ) {
            mru.use(message);
        } else {
            // new default location is documents
            mru.use( FSPathBuilder.getDocuments().add( Constants.FAUSTERIZE, "1.fst1").toString());
        }

        return mru;
    }
}
