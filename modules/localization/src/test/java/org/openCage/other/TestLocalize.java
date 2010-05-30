package org.openCage.other;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.Ignore;
import org.openCage.localization.CombinedLocalize;
import org.openCage.localization.DictLocalize;
import org.openCage.localization.Localize;
import org.openCage.localization.impl.LocaleProperty;
import org.openCage.property.Property;

import java.util.Locale;

@Ignore
public class TestLocalize extends DictLocalize {
    @Inject
    public TestLocalize( @Named(LocaleProperty.THE_LOCALE) Property<Locale> theLocale) {
        super(theLocale);
    }
}
