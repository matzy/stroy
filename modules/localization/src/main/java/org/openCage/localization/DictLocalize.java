package org.openCage.localization;

import org.openCage.localization.impl.LocalizeImpl;
import org.openCage.property.Property;

import java.util.Collections;
import java.util.Locale;

public class DictLocalize extends LocalizeImpl {

    public DictLocalize( Property<Locale> theLocale) {
        super( "org.openCage.localization.text", Collections.<Localize>emptyList(), theLocale);
    }
}
