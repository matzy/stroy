package org.openCage.localization;

import org.openCage.property.Property;

import java.util.Locale;

public class DictLocalize extends CombinedLocalize {

    public DictLocalize( Property<Locale> theLocale) {
        super( "org.openCage.localization.text", theLocale );
    }
}
