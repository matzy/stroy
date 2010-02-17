package org.openCage.property.impl;

import net.jcip.annotations.ThreadSafe;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.protocol.F0;
import org.openCage.lang.protocol.F1;
import org.openCage.property.protocol.PropStore;
import org.openCage.property.protocol.Property;

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
    private transient PropStore        store;

    public PropertyImpl( PropStore store, T deflt ) {
        this.store = store;
        dflt = deflt;
        setDefault();
    }

    @Override
    public synchronized T get() {
        return obj;
    }

    @Override
    public synchronized void set(T t) {
        obj = t;
        if ( store != null ) {
            store.setDirty();
        }
    }

    private void setDirty() {
        if ( store != null ) {
            store.setDirty();
        }
    }

    @Override
    public synchronized void setDefault() {
        set( dflt );
    }

    @Override
    public synchronized void modify(F1<Void, T> modi) {
        // try to modify the object
        // if it fails return to the only object we know, i.e. the default
        try {
            modi.call( obj );
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
