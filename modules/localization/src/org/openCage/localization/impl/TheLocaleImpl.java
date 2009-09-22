package org.openCage.localization.impl;

import java.util.Locale;

import org.openCage.localization.protocol.TheLocale;

public class TheLocaleImpl implements TheLocale {

	private Locale locale = Locale.US;
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
