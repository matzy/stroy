package org.openCage.stroy.locale;

import org.openCage.util.prefs.PListSelectionString;

import javax.swing.*;
import java.util.*;
import java.util.logging.Logger;

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

public class Message {

    private static Logger LOG = Logger.getLogger( Message.class.getName());

    public final static String localKey = "language.locale";

    public static String get( String key ) {

        return get( getLocale(), key );

    }

    public static Locale getLocale() {
        PListSelectionString  localPref = PListSelectionString.getOrCreate( localKey , "Pref.locale.default", "Pref.locale.default", "Pref.locale.en", "Pref.locale.de", "Pref.locale.ja", "Pref.locale.es" );

        String selection = localPref.get().getSelection().substring( "Pref.locale.".length() );

        if ( selection.equals( "default")) {
            return Locale.getDefault();
        }

        return new Locale( selection );
    }

    /**
     * utility method for designgridlayout.
     * Returns a new JLabel with a localized text
     * @param key
     * @return
     */
    public static JLabel getl( String key ) {
        return new JLabel( get( key ));
    }

    public static boolean hasNewLines( String key ) {
        String txt = get( key );

        return txt.contains( "\n" ) || txt.contains( "\uff3cn"  );
    }

    public static String get( String key, int row ) {
        String txt = get( key );

        if ( txt.contains( "\uff3cn"  ) ) {
            return txt.split( "\uff3cn")[row];
        } else if ( txt.contains( "\n" ) ) {
            return txt.split( "\n")[row];
        } else if ( row == 0 ) {
            return txt;
        }

        throw new Error( "need localization" );
    }

    /**
     * remember not found locales, one warning is enough 
     */
    private static Set<Locale> notFoundLocales = new HashSet<Locale>();

    public static String get( Locale local, String key ) {

        ResourceBundle messages;

        try {
//        String path = Message.class.getResource( "messages.properties" ).getPath();

            // full path for the bundles name
            messages = ResourceBundle.getBundle( "org.openCage.stroy.locale.messages",
                    local);
        } catch ( MissingResourceException exp ) {
            LOG.warning( "no localization for " + local + " found, falling back on English"  );
            local = new Locale( "en", "US" );
            messages = ResourceBundle.getBundle( "messages", local );
        }

        try {
            return messages.getString( key );
        } catch ( MissingResourceException exp ) {

            if ( !notFoundLocales.contains( local )) {
                notFoundLocales.add(  local );
                LOG.warning( "localization not found: key \"" + key + "\" for locale: " +  local );
            }

            return key;
        }
    }


}
