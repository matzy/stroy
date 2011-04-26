package org.openCage.babel;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 1/3/11
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class StdUIText {
    private final BabelBundle bundle;

    public StdUIText( LocalePreference pref ) {
        bundle = new BabelBundle( "org.openCage.babel.ui", pref );
    }

    public String get( String key ) {
        return bundle.get(key);
    }
}
