package org.openCage.localization.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.localization.DictLocalize;
import org.openCage.localization.Localization;
import org.openCage.localization.Localize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

public class TheLocaleLocalized {

    private final Localize loca;
    private String locale;

    private final Map<String, Locale> strToLocale = new HashMap<String, Locale>();

    @Inject
    public TheLocaleLocalized( @Named(Localization.DICT) Localize loca ) {
        this.loca = loca;

        Map<Locale,String> locale2Str = new HashMap<Locale, String>();

        locale2Str.put( Locale.getDefault(), loca.localize( "org.openCage.localization.pref.locale.default" ) );
        locale2Str.put( Locale.ENGLISH, loca.localize( "org.openCage.localization.pref.locale.en" ));
        locale2Str.put( Locale.GERMAN, loca.localize( "org.openCage.localization.pref.locale.de" ));
        locale2Str.put( Locale.JAPANESE, loca.localize( "org.openCage.localization.pref.locale.ja" ) );
        locale2Str.put( new Locale( "es", "ES" ), loca.localize( "org.openCage.localization.pref.locale.es" ));


        locale = locale2Str.get(loca.getLocale());
    }


    public void setLocale( String locale ) {
        loca.setLocale( strToLocale.get(locale));
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public List<String> getLocalizedLocales() {
        List<String> loc = new ArrayList<String>();

        loc.add( loca.localize( "org.openCage.localization.pref.locale.default" ) );
        loc.add( loca.localize( "org.openCage.localization.pref.locale.en" ) );
        loc.add( loca.localize( "org.openCage.localization.pref.locale.de" ) );
        loc.add( loca.localize( "org.openCage.localization.pref.locale.ja" ) );
        loc.add( loca.localize( "org.openCage.localization.pref.locale.es" ) );

        strToLocale.put( loca.localize( "org.openCage.localization.pref.locale.default" ), Locale.getDefault() );
        strToLocale.put( loca.localize( "org.openCage.localization.pref.locale.en" ), Locale.ENGLISH );
        strToLocale.put( loca.localize( "org.openCage.localization.pref.locale.de" ), Locale.GERMAN );
        strToLocale.put( loca.localize( "org.openCage.localization.pref.locale.ja" ), Locale.JAPANESE );
        strToLocale.put( loca.localize( "org.openCage.localization.pref.locale.es" ), new Locale( "es", "ES" ));

        return loc;
    }
}
