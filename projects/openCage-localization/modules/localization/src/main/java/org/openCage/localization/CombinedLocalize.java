package org.openCage.localization;

import java.text.MessageFormat;
import java.util.*;

import org.openCage.lang.functions.F1;
import org.openCage.localization.Localize;
import org.openCage.property.Property;

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

public class CombinedLocalize implements Localize {

	private final String           bundleName;
	private final List<Localize>   parents    = new ArrayList<Localize>();
	private final Property<Locale> theLocale;
	
//    public CombinedLocalize( String fullyQualifiedName, Property<Locale> theLocale, Localize ... parents ) {
//        this.parents.addAll( Arrays.asList( parents ));
//        this.bundleName = fullyQualifiedName;
//        this.theLocale = theLocale;
//    }
//


    public CombinedLocalize( String fullyQualifiedName, Property<Locale> theLocale, Localize ... parents ) {
        this.parents.addAll( Arrays.asList( parents ));
        this.bundleName = fullyQualifiedName;
        this.theLocale = theLocale;
    }

    public CombinedLocalize( String fullyQualifiedName, Property<Locale> theLocale, List<Localize> parents ) {
        this.parents.addAll( parents );
        this.bundleName = fullyQualifiedName;
        this.theLocale = theLocale;
    }

    @Override public String localize(String key) {
        if ( theLocale != null ) {
            return localize( theLocale.get(), key );
        }

        return localize( Locale.getDefault(), key );
	}

	@Override public String localize(Locale locale, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle( bundleName, locale );
		try {
			return bundle.getString(key);
		} catch ( MissingResourceException exp ) {
			for ( Localize parent : parents ) {
				try {
					return parent.localize( locale, key);           
				} catch ( MissingResourceException ignored) {					
				}
			}
			
			throw exp;
		}
	}

    @Override
    public String localize(String key, Object... args) {
        return localize( theLocale.get(), key, args );    
    }

    public String localize(Locale locale, String key, Object ... args ) {
        ResourceBundle bundle = ResourceBundle.getBundle( bundleName, locale );
        try {
            String trans =  bundle.getString(key);
            return MessageFormat.format( trans, args );

        } catch ( MissingResourceException exp ) {
            for ( Localize parent : parents ) {
                try {
                    return parent.localize( locale, key);
                } catch ( MissingResourceException ignored) {
                }
            }

            throw exp;
        }
    }

    @Override
    public Locale getLocale() {
        return theLocale.get();
    }

    @Override
    public void setLocale( final Locale newLocale) {

        if ( theLocale != null ) {
            theLocale.set( newLocale );
            return;
        }

        Locale.setDefault( newLocale );
    }


}
