package org.openCage.localization;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.localization.impl.LocalePropertyProvider;
import org.openCage.property.Property;

import java.util.Locale;

public class LocalizeFactory {

    private final Property<Locale> localeProp;

    @Inject
    public LocalizeFactory( @Named(LocalePropertyProvider.THE_LOCALE) Property<Locale> loc ) {
        localeProp = loc;
    }

    public Localize get( String fullyQualifiedName, Localize ... other ) {
        return new CombinedLocalize( fullyQualifiedName, localeProp, other );
    }
}
