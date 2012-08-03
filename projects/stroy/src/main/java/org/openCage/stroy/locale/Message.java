package org.openCage.stroy.locale;

import org.openCage.util.logging.Log;
import org.openCage.util.prefs.LocaleSelectionProperty;

import javax.swing.*;
import java.util.*;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

public class Message {

    public static LocaleSelectionProperty localeSelection;

    public final static String localKey = "language.locale";

    //private Selection1

    public static String get( String key ) {

        return get( getLocale(), key );

    }

    public static Locale getLocale() {
        return localeSelection.getSelection();
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
            Log.warning( "no localization for " + local + " found, falling back on English"  );
            local = new Locale( "en", "US" );
            messages = ResourceBundle.getBundle( "messages", local );
        }

        try {
            return messages.getString( key );
        } catch ( MissingResourceException exp ) {

            if ( !notFoundLocales.contains( local )) {
                notFoundLocales.add(  local );
                Log.warning( "localization not found: key \"" + key + "\" for locale: " +  local );
            }

            return key;
        }
    }


}
