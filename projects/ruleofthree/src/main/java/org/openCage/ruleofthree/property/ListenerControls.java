package org.openCage.ruleofthree.property;

import org.openCage.kleinod.type.Null;
import org.openCage.kleinod.observe.Observable;
import org.openCage.kleinod.observe.VoidListenerControl;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/6/12
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListenerControls {

    private static VoidListenerControl nullLC = Null.of(VoidListenerControl.class);

    public static VoidListenerControl listenerControl( Object obj ) {
        if ( obj instanceof Observable) {
            return ((Observable) obj).getListenerControl();
        }

        return nullLC;
    }

}
