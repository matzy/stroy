package org.openCage.util.prefs;

import java.util.List;
import java.util.ArrayList;

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
public class GlobalItem implements PrefItem<String> {

    private String       val;
    private String       initial;
    private List<String> possibles = new ArrayList<String>();
    
    private transient List<PreferencesChangeListener<String>> listeners = new ArrayList<PreferencesChangeListener<String>>();

    public String get() {
        return val;
    }

    public void set(String val) {

        if ( val.equals( this.val )) {
            return;
        }

        if ( possibles.size() > 0 ) {
            if ( ! possibles.contains( val )) {
                throw new IllegalArgumentException( "not in the list of possibles: " + val );
            }
        }

        this.val = val;
        Prefs.setDirty();

        checkTransients();
        for ( PreferencesChangeListener listr : listeners ) {
            listr.changed( val );
        }
    }

    public void addListener(PreferencesChangeListener listener) {
        checkTransients();
        listeners.add( listener );
    }


    public boolean isGlobal() {
        return true;
    }

    public void setInitials(String ini) {
        initial = ini;
    }

    public void reset() {
        val = initial;
    }

    private void checkTransients() {
        if ( listeners == null ) {
            listeners = new ArrayList<PreferencesChangeListener<String>>();
        }
    }

}
