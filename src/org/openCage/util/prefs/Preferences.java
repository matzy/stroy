package org.openCage.util.prefs;

import java.util.Map;
import java.util.HashMap;

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
public class Preferences implements Persistable {

    // static

    private static Preferences thePref;
    private static String      name;

    public static void setDirty() {
        get().dirty = true;
    }


    public static void add( String key, PreferenceItem item ) {
        get().map.put( key, item );
        setDirty();
    }

    // TODO next
    private static synchronized Preferences get() {
        if ( thePref == null ) {
            thePref = Persistence.load( new Preferences(), name);
        }

        return thePref;
    }

    public static PreferenceItem getItem(String key) {
        return get().map.get( key );
    }

    public static void setName(String nme ) {
        name = nme;
    }

    // object

    private boolean dirty;
    private Map<String, PreferenceItem> map = new HashMap<String, PreferenceItem>();


    public boolean isDirty() {
        return dirty;
    }

    public void setClean() {
        dirty = false;
    }
}
