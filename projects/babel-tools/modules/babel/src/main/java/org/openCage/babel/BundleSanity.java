package org.openCage.babel;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Nov 12, 2010
 * Time: 2:32:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class BundleSanity {
    private String bundleLocation;
//    private ResourceBundle bundle;

    public BundleSanity( String bundleLocation ) {
        this.bundleLocation = bundleLocation;
    }

    private ResourceBundle getSingleLocaleBundle( final Locale loc ) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleLocation, new ResourceBundle.Control() {
            @Override
            public List<Locale> getCandidateLocales(String s, Locale locale) {
                List<Locale> withRoot = new ArrayList<Locale>();
                withRoot.add( loc );
                withRoot.add( Locale.ROOT );
                return withRoot;
            }
        });

        return bundle;
    }

    public void check( Locale ... locales) {

		// check fallback
        ResourceBundle fallback = getSingleLocaleBundle( Locale.ENGLISH );

        if ( !fallback.getLocale().equals( Locale.ENGLISH )) {
            throw new IllegalArgumentException( "resource bundles without empty fallback (platform dependent)" );
        }

		// check that all keys in all language files have a value in the fallback
		for ( Locale locale : locales ) {
			ResourceBundle bundle = getSingleLocaleBundle( locale );

            // the locale is really in the bundle ?
            if ( !bundle.getLocale().equals( locale )) {
                throw new IllegalArgumentException( "locale not in bundle " + locale );
            }

            // all keys of that locale are in the fallback
			Enumeration<String> keys = bundle.getKeys();
			while ( keys.hasMoreElements() ) {
				String key = keys.nextElement();
				String fallbackVal = fallback.getString( key );

                String locVal = bundle.getString( key );

                // check args
                for ( int idx = 0; idx < 20; ++idx ) {
                    String arg = "{" + idx + "}";

                    if ( locVal.contains(arg) != fallbackVal.contains(arg)) {
                        throw new IllegalArgumentException("values have different argument style for key " + key + ": " + locVal + " - " + fallbackVal );
                    }
                }
			}
		}    
    }
}
