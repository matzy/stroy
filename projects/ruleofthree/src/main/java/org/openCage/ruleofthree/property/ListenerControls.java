package org.openCage.ruleofthree.property;

import org.openCage.lang.inc.Null;
import org.openCage.lang.listeners.Observable;
import org.openCage.lang.listeners.VoidListenerControl;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/6/12
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListenerControls {

    private static VoidListenerControl nullLC = Null.get( VoidListenerControl.class );

    public static VoidListenerControl listenerControl( Object obj ) {
        if ( obj instanceof Observable) {
            return ((Observable) obj).getListenerControl();
        }

        return nullLC;
    }

}
