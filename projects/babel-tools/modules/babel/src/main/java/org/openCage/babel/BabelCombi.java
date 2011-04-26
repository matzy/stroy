package org.openCage.babel;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 1/4/11
 * Time: 12:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class BabelCombi {

    public final BabelBundle locale;
    public final BabelBundle menu;
    public final BabelBundle ui;

    public BabelCombi( LocalePreference lp ) {
        locale = new BabelBundle( "org.openCage.babel.locale", lp );
        menu = new BabelBundle( "org.openCage.babel.menu", lp );
        ui = new BabelBundle( "org.openCage.babel.ui", lp );
    }
}
