package org.openCage.localization.impl;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import org.openCage.localization.protocol.BundleCheck;

public class BundleCheckImpl implements BundleCheck {
	
	private static Locale unknownLocale = new Locale("xx", "URGH");
	
	public void checkBundle( String bundleLocation, Locale ... locales  ) {
		
		// check fallback
		ResourceBundle fallback = ResourceBundle.getBundle( bundleLocation, unknownLocale );
		
		// check that all keys in all language files have a value in the fallback 
		for ( Locale locale : locales ) {
			ResourceBundle bundle = ResourceBundle.getBundle( bundleLocation, locale );
			
			Enumeration<String> keys = bundle.getKeys();
			while ( keys.hasMoreElements() ) {
				String key = keys.nextElement();
				
				fallback.getObject( key );
				
			}
		}

	}
	
}
