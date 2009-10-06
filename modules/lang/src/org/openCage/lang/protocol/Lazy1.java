/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.lang.protocol;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stephan
 */
public class Lazy1<T,A> {
    private T              obj;
    private boolean        evaluated = false;
    private final FE1<T,A> func;

    public Lazy1( FE1<T,A> func ) {
        this.func = func;
    }

    // TODO exceptions
    public T get( A a) {
        if ( !evaluated ) {
            try {
                obj = func.call( a );
            } catch (Exception ex) {
                Logger.getLogger(Lazy.class.getName()).log(Level.SEVERE, null, ex);
            }
            evaluated = true;
        }

        return obj;
    }
}
