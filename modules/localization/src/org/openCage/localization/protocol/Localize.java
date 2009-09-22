package org.openCage.localization.protocol;

import java.util.Locale;

public interface Localize {
	
	public String localize( String key );
	public String localize( Locale locale, String key );

}
