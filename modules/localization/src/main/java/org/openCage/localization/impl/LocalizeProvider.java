package org.openCage.localization.impl;

import java.util.List;
import java.util.Locale;

import com.google.inject.name.Named;
import org.openCage.localization.BundleCheck;
import org.openCage.localization.CombinedLocalize;
import org.openCage.localization.Localize;
import org.openCage.localization.protocol.LocalizeBuilder;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.property.Property;

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

public class LocalizeProvider implements Provider<Localize> {

	private BundleCheck check;
	private Property<Locale> theLocale;

    @Inject
    public LocalizeProvider( BundleCheck check, @Named( LocaleProperty.THE_LOCALE) Property<Locale> theLocale ) {
        this.check = check;
        this.theLocale = theLocale;

    }

    public Localize build(String fullyqualifiedName, List<Localize> parents) {
		check.checkBundle( fullyqualifiedName );
		return new CombinedLocalize( fullyqualifiedName, theLocale, parents );
	}

    public Localize build(String fullyqualifiedName, Localize ... parents) {
		check.checkBundle( fullyqualifiedName );
		return new CombinedLocalize( fullyqualifiedName, theLocale, parents );
	}

	public Localize get() {
		return new CombinedLocalize( "org.openCage.localization.text", theLocale );
	}

}
