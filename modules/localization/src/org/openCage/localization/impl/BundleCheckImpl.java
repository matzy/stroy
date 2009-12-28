package org.openCage.localization.impl;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import org.openCage.localization.protocol.BundleCheck;

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

public class BundleCheckImpl implements BundleCheck {
	
	private static final Locale unknownLocale = new Locale("xx", "URGH");
	
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
