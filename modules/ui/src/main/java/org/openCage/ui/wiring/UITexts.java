package org.openCage.ui.wiring;

import org.openCage.babel.BabelBundle;
import org.openCage.babel.LocalePreference;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 1/3/11
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class UITexts {

    private final BabelBundle bundle;

    public UITexts( LocalePreference pref ) {
        bundle = new BabelBundle( "org.openCage.babel.ui", pref );
    }

    public String get( String key ) {
        return bundle.get(key);
    }
}
