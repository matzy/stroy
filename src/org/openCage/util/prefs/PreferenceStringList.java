package org.openCage.util.prefs;

import java.util.List;
import java.util.Collections;

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
public class PreferenceStringList extends PreferenceBase<List<String>> {

    public static PreferenceStringList create( String key, List<String> val ) {
          PreferenceItem item = Preferences.getItem( key );

          if ( item == null ) {

              PreferenceStringList newItem = new PreferenceStringList(val);
              Preferences.add( key, newItem );

              return newItem;
          }

          if ( item instanceof PreferenceStringList ) {
              PreferenceStringList newItem = (PreferenceStringList)item;
              newItem.setInitial(val);
              return newItem;
          }

          throw new IllegalArgumentException( "type mismatch" );
      }

      public static PreferenceStringList create( String key ) {
          PreferenceItem item = Preferences.getItem( key );

          if ( item == null ) {

              PreferenceStringList newItem = new PreferenceStringList( Collections.EMPTY_LIST );
              Preferences.add( key, newItem );

              return newItem;
          }

          if ( item instanceof PreferenceStringList ) {
              PreferenceStringList newItem = (PreferenceStringList)item;
              return newItem;
          }

          throw new IllegalArgumentException( "type mismatch" );
      }

    private PreferenceStringList(List<String> val) {
        super(val);
    }
}
