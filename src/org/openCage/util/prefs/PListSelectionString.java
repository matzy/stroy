package org.openCage.util.prefs;

import org.openCage.stroy.file.Action;

import java.util.Map;
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
public class PListSelectionString extends PreferenceBase<ListSelection<String>> {


    public static PListSelectionString create( String key, ListSelection<String> val ) {

        PreferenceItem item = Preferences.getItem( key );

        if ( item == null ) {

            PListSelectionString newItem = new PListSelectionString(val);
            Preferences.add( key, newItem );

            return newItem;
        }

        if ( item instanceof PListSelectionString ) {
            PListSelectionString newItem = (PListSelectionString)item;
            newItem.setInitial(val);
            return newItem;
        }

        throw new IllegalArgumentException( "type mismatch" );
    }

    public static PListSelectionString create( String key ) {
        PreferenceItem item = Preferences.getItem( key );

        if ( item == null ) {

            String[] tmp = {"1"};
            PListSelectionString newItem = new PListSelectionString( new ListSelection<String>(tmp,"1"));
            Preferences.add( key, newItem );

            return newItem;
        }

        if ( item instanceof PListSelectionString ) {
            PListSelectionString newItem = (PListSelectionString)item;
            return newItem;
        }

        throw new IllegalArgumentException( "type mismatch" );
    }

    private PListSelectionString( ListSelection<String> val ) {
        super(val);
    }

}
