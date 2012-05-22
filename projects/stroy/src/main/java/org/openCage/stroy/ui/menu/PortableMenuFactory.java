package org.openCage.stroy.ui.menu;

import com.google.inject.Inject;
import org.openCage.stroy.ui.prefs.PrefsUI;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/5/12
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
// TODO provider
public class PortableMenuFactory {

    private PrefsUI prefsUI;

    @Inject
    public PortableMenuFactory( PrefsUI prefsUI ) {
        this.prefsUI = prefsUI;
    }

    public PortableMenu get() {
        return new PortableMenu( prefsUI );
    }
}
