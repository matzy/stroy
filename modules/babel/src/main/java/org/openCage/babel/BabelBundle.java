package org.openCage.babel;

import org.openCage.lang.functions.F1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class BabelBundle {

    private ResourceBundle bundle;
    final private String bundleLocation;

    public BabelBundle( final String bundleLocation, LocalePreference lpref ) {
        this.bundleLocation = bundleLocation;
        lpref.listeners.add( new F1<Void, List<Locale>>() {
            @Override public Void call(List<Locale> locales) {
                change( locales );
                return null;
            }
        });

        change( lpref.getLocales() );
    }

    private void change( final List<Locale> locales) {
        bundle =  ResourceBundle.getBundle( bundleLocation, Locale.ENGLISH, new ResourceBundle.Control() {
            @Override
            public List<Locale> getCandidateLocales(String s, Locale locale) {
                List<Locale> withRoot = new ArrayList<Locale>();
                withRoot.addAll( locales );
                withRoot.add( Locale.ROOT );
                return withRoot;
            }
        });
    }

    public String get( String key ) {
        return bundle.getString( key );
    }
}
