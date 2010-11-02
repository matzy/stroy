package org.openCage.babel;

import org.openCage.lang.Listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.List;

public class LocalePreference {

    Listeners<List<Locale>> listeners = new Listeners<List<Locale>>();

    private List<Locale> locales = new ArrayList<Locale>();

    public LocalePreference() {
        locales.add( Locale.getDefault());
        locales.add( Locale.ENGLISH );
    }

    public List<Locale> getLocales() {
        return locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;

        listeners.shout( locales );
    }

    public void setLocales(Locale ... locales ) {
        setLocales( Arrays.asList( locales ));
    }
}
