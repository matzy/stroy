package org.openCage.property.impl;

import net.jcip.annotations.ThreadSafe;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.protocol.F1;
import org.openCage.property.protocol.PropStore;
import org.openCage.property.protocol.Property;

import java.util.ArrayList;
import java.util.List;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

@ThreadSafe
public class PropertyImpl<T> implements Property<T> {

    private T                          obj;
    private final transient T          dflt;
    private final String               description;

    private transient PropStore          store;
    private transient List<F1<Void, T>>  listeners;


    public PropertyImpl( PropStore store, T deflt, String description ) {
        this.store = store;
        dflt = deflt;
        this.description = description;
        setDefault();
    }

    @Override
    public synchronized T get() {
        return obj;
    }

    private void setDirty() {
        if ( store != null ) {
            store.setDirty();
        }

        for (F1<Void,T> listener : listeners ) {
            listener.call( obj );
        }
    }

    @Override
    public synchronized void setDefault() {
        obj = dflt;
        setDirty();
    }

    @Override
    public synchronized void modify(F1<T, T> modi) {
        // try to modify the object
        // if it fails return to the only object we know, i.e. the default
        try {
            obj = modi.call( obj );
        } catch ( Exception exp ) {
            setDefault();
            throw Unchecked.wrap( exp );
        } catch ( Error err ) {
            setDefault();
            throw err;            
        } finally {
            setDirty();
        }
    }

    @Override
    public void addPropertyChangeListener(F1<Void, T> listener) {
        // initialize here because transient means it might not be initialized after deserialization
        if ( listeners == null ) {
             listeners = new ArrayList<F1<Void, T>>();
        }
        listeners.add( listener  );
    }



    @Override
    public String toString() {
        return "PropertyImpl{" +
                "obj=" + obj +
                ", dflt=" + dflt +
                '}';
    }

    public synchronized void setStore(PropStore store) {
        this.store = store;
    }
}
