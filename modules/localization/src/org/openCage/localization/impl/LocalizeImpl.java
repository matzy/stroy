package org.openCage.localization.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.openCage.lang.protocol.F1;
import org.openCage.localization.protocol.Localize;
import org.openCage.property.protocol.Property;

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

public class LocalizeImpl implements Localize {

	private final String           bundleName;
	private final List<Localize>   parents    = new ArrayList<Localize>();
	private final Property<Locale> theLocale;
	
	
	public LocalizeImpl( String name, List<Localize> parents, Property<Locale> theLocale ) {
		this.parents.addAll( parents );
		this.bundleName = name;
		this.theLocale = theLocale;
	}
	
	@Override
    public String localize(String key) {
		return localize( theLocale.get(), key );
	}

	@Override
    public String localize(Locale locale, String key) {
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
        theLocale.modify( new F1<Locale, Locale>() {
            @Override public Locale call(Locale locale) {
                return newLocale;
            }
        });
    }


}
