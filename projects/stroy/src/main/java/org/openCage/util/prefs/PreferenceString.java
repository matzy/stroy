package org.openCage.util.prefs;

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
public class PreferenceString extends PreferenceBase<String> {


//    public PreferenceString() {
//        super(null);
//    }

    /**
     * get or getOrCreate a PreferenceString
     * if it does not exist yet getOrCreate one with the given value
     * if it exits return that without changing the value
     * @param key The key.
     * @param val The initial value.
     * @return A Persisted string associated to the key
     */
    public static PreferenceString getOrCreate( String key, String val ) {
        PreferenceItem item = Preferences.getItem( key );

        if ( item == null ) {

            PreferenceString newItem = new PreferenceString(val);
            Preferences.add( key, newItem );

            return newItem;
        }

        if ( item instanceof PreferenceString ) {
            PreferenceString newItem = (PreferenceString)item;
            newItem.setInitial(val);
            return newItem;
        }

        throw new IllegalArgumentException( "type mismatch" );
    }

    /**
     * Get an exiting PreferenceString
     * @param key
     * @return
     */
    public static PreferenceString get( String key ) {
        PreferenceItem item = Preferences.getItem( key );

        if ( item == null ) {

            throw new IllegalStateException( "prefstring does not exist yet" );
//
//            PreferenceString newItem = new PreferenceString("");
//            Preferences.add( key, newItem );
//
//            return newItem;
        }

        if ( item instanceof PreferenceString ) {
            PreferenceString newItem = (PreferenceString)item;
            return newItem;
        }

        throw new IllegalArgumentException( "type mismatch" );
    }

    private PreferenceString( String val ) {
        super(val);
    }

}
