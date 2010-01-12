package org.openCage.property.impl;

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
    public T get() {
        return obj;
    }

    @Override
    public void set(T t) {
        obj = t;
        if ( store != null ) {
            store.setDirty();
        }
    }

    @Override
    public void setDefault() {
        set( dflt );
    }

    @Override
    public String toString() {
        return "PropertyImpl{" +
                "obj=" + obj +
                ", dflt=" + dflt +
                '}';
    }

    public void setStore(PropStore store) {
        this.store = store;
    }
}