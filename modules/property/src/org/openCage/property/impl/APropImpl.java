/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.property.impl;

import java.util.ArrayList;
import java.util.List;
import org.openCage.property.protocol.Prop;
import org.openCage.property.protocol.PropChangeListener;

/**
 *
 * @author stephan
 */
public abstract class APropImpl<T> implements Prop<T>{

    private T                           prop;
    private List<PropChangeListener<T>> listeners = new ArrayList<PropChangeListener<T>>();

    public abstract T getDefault();

    public T get() {
        if ( prop == null ) {
            prop = getDefault();
        }
        return prop;
    }

    public void set( T val ) {
        if ( prop == null ) {
            prop = getDefault();
        }

        if ( prop.equals(val)) {
            // nothing to do. no event
            return;
        }

        prop = val;

        for ( PropChangeListener listener : listeners ) {
            listener.propChanged( prop  );
        }
    }

    public void addListener( PropChangeListener<T> listener ) {
        listeners.add( listener );
    }

    public void setDefault() {
        set( getDefault() );
    }

}
