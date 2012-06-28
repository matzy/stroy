package org.openCage.babel;

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

public class BundleSanity {
    private String bundleLocation;
//    private ResourceBundle bundle;

    public BundleSanity( String bundleLocation ) {
        this.bundleLocation = bundleLocation;
    }

    private ResourceBundle getSingleLocaleBundle( final Locale loc ) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleLocation, new ResourceBundle.Control() {
            @Override
            public List<Locale> getCandidateLocales(String s, Locale locale) {
                List<Locale> withRoot = new ArrayList<Locale>();
                withRoot.add( loc );
                withRoot.add( Locale.ROOT );
                return withRoot;
            }
        });

        return bundle;
    }

    public void check( Locale ... locales) {

		// check fallback
        ResourceBundle fallback = getSingleLocaleBundle( Locale.ENGLISH );

        if ( !fallback.getLocale().equals( Locale.ENGLISH )) {
            throw new IllegalArgumentException( "resource bundles without empty fallback (platform dependent)" );
        }

		// check that all keys in all language files have a value in the fallback
		for ( Locale locale : locales ) {
			ResourceBundle bundle = getSingleLocaleBundle( locale );

            // the locale is really in the bundle ?
            if ( !bundle.getLocale().equals( locale )) {
                throw new IllegalArgumentException( "locale not in bundle " + locale );
            }

            // all keys of that locale are in the fallback
			Enumeration<String> keys = bundle.getKeys();
			while ( keys.hasMoreElements() ) {
				String key = keys.nextElement();
				String fallbackVal = fallback.getString( key );

                String locVal = bundle.getString( key );

                // check args
                for ( int idx = 0; idx < 20; ++idx ) {
                    String arg = "{" + idx + "}";

                    if ( locVal.contains(arg) != fallbackVal.contains(arg)) {
                        throw new IllegalArgumentException("values have different argument style for key " + key + ": " + locVal + " - " + fallbackVal );
                    }
                }
			}
		}    
    }
}
