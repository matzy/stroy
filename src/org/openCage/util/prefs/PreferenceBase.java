package org.openCage.util.prefs;

import java.util.ArrayList;
import java.util.List;

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
public class PreferenceBase<T> implements PreferenceItem<T> {

    protected T                                          val;
    protected T                                          resetVal;
    private transient List<PreferencesChangeListener<T>> listeners;
    private transient boolean                            initSet = false;

    public PreferenceBase( T val ) {
        resetVal = val;
        this.val = val;
    }

    public T get() {
        return val;
    }

    public T getResetVal() {
        return resetVal;
    }

    public void set( T val ) {
        this.val = val;
        setDirty();
    }

    public void setDirty() {
        Preferences.setDirty();
        checkTransients();
        for ( PreferencesChangeListener listr : listeners ) {
            listr.changed( val );
        }
    }

    public void reset() {
        set( resetVal );
    }

    public void addListener(PreferencesChangeListener<T> preferencesChangeListener) {
        checkTransients();
        listeners.add( preferencesChangeListener );
    }

    protected void setInitial(T val) {
        if ( initSet ) {
            throw new IllegalStateException( "init val set again" );
        }

        resetVal = val;
    }

    private void checkTransients() {
        if ( listeners == null ) {
            listeners = new ArrayList<PreferencesChangeListener<T>>();
        }
    }
}
