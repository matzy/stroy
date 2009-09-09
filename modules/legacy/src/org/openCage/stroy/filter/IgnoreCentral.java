package org.openCage.stroy.filter;

import org.openCage.util.prefs.PreferenceBase;
import org.openCage.util.prefs.PreferenceItem;
import org.openCage.util.prefs.Preferences;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class IgnoreCentral extends PreferenceBase<IgnoreStore> {

    private static final String key = "stroy.ignore";

    public static IgnoreCentral create() {
        PreferenceItem item = Preferences.getItem( key );

        if ( item == null ) {

            final IgnoreCentral newItem = new IgnoreCentral( getInitial());
            Preferences.add( key, newItem );

            return newItem;
        }

        if ( item instanceof IgnoreCentral) {
            final IgnoreCentral newItem = (IgnoreCentral)item;
            newItem.setInitial( getInitial() );
            return newItem;
        }

        throw new IllegalArgumentException( "type mismatch" );
    }

    private static IgnoreStore getInitial() {

        List<String> exts = new ArrayList<String>();

        exts.add( "svn" );
        exts.add( "DS_Store" );
        exts.add( "class" );
        exts.add( "o" );
        exts.add( "o" );

        List<String> pat = new ArrayList<String>();
        pat.add( ".*/\\.get_date\\.dat" );
        pat.add( ".*/copyarea\\.db" );
        pat.add( ".*/copyarea\\.dat" );
        pat.add( ".*/vssver\\.scc" );
        pat.add( ".*/vssver2\\.scc" );
        pat.add( ".*/CVS" );
        pat.add( ".*/SCCS" );
        pat.add( ".*/RCS" );
        pat.add( ".*/rcs" );

        IgnoreStore store = new IgnoreStore();
        store.extensions = exts;
        store.patterns   = pat;
        store.paths      = new ArrayList<String>();

        return store;
    }


    private IgnoreCentral( IgnoreStore val) {
        super(val);
    }

    private transient List<IgnoreChangedListener> listeners;

    public Ignore getIgnore() {
        return new IgnoreFixed( get() );
    }

    public void addSingleExtension(String extension ) {
        get().extensions.add( extension );
        setDirty();
        callListeners( true );
    }

    public void addSinglePattern(String pat ) {
        get().patterns.add( pat );
        setDirty();
        callListeners( true );
    }

    public void addSinglePath(String path ) {
        get().paths.add( path );
        setDirty();
        callListeners( true );
    }

    private void callListeners( boolean more ) {

        checkTransients();

        Ignore ignore = getIgnore();
        for ( IgnoreChangedListener lis : listeners ) {
            lis.ignoreChanged( ignore, more );
        }
    }

    public void addListener( IgnoreChangedListener listener ) {
        checkTransients();
        listeners.add( listener );
    }

    private void checkTransients() {
        if ( listeners == null ) {
            listeners = new ArrayList<IgnoreChangedListener>();
        }
    }

    public void resetExtensions() {
        get().extensions = getResetVal().extensions;
        setDirty();
        callListeners( false );
    }

    public void resetPaths() {
        get().paths = getResetVal().paths;
        setDirty();
        callListeners( false );
    }

    public void resetPatterns() {
        get().patterns = getResetVal().patterns;
        setDirty();
        callListeners( false );
    }

    public void removeExtension(String ext) {
        get().extensions.remove( ext );
        setDirty();
        callListeners( true );
    }

    public void removePattern(String pat) {
        get().patterns.remove( pat );
        setDirty();
        callListeners( true );
    }

    public void removePath(String path) {
        get().paths.remove( path );
        setDirty();
        callListeners( true );
    }
}
