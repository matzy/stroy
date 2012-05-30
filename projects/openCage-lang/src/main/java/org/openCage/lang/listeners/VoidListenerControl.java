package org.openCage.lang.listeners;

import org.openCage.lang.functions.VF0;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/29/12
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VoidListenerControl {

    void add( VF0 listerner );
    void remove( VF0 listerner );
    void removeCompletely( VF0 listerner );
}
