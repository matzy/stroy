package org.openCage.localization.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.openCage.localization.protocol.Localize;
import org.openCage.localization.protocol.TheLocale;

public class LocalizeImpl implements Localize {

	private final String         bundleName;
	private final List<Localize> parents    = new ArrayList<Localize>();
	private final TheLocale      theLocale;
	
	
	public LocalizeImpl( String name, List<Localize> parents, TheLocale theLocale ) {
		this.parents.addAll( parents );
		this.bundleName = name;
		this.theLocale = theLocale;
	}
	
	public String localize(String key) {		
		return localize( theLocale.getLocale(), key );
	}

	public String localize(Locale locale, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle( bundleName, locale );
		try {
			return bundle.getString(key);
		} catch ( MissingResourceException exp ) {
			for ( Localize parent : parents ) {
				try {
					return parent.localize( locale, key);
				} catch ( MissingResourceException parentExp ) {					
				}
			}
			
			throw exp;
		}
	}


}
