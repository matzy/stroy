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
