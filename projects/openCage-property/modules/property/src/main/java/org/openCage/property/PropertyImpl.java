package org.openCage.property;

import net.jcip.annotations.ThreadSafe;
import org.openCage.lang.errors.Unchecked;
import org.openCage.lang.functions.F1;
import org.openCage.property.PropStore;
import org.openCage.property.Property;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.List;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

@ThreadSafe
public abstract class PropertyImpl<T> implements Property<T> {

    private T                          obj;
    private final transient T          dflt;
    private final String               description;

    private transient PropStore          store;
    private transient List<F1<Void, T>>  listeners = new ArrayList<F1<Void, T>>();


    PropertyImpl( PropStore store, T deflt, String description ) {
        this.store = store;
        dflt = deflt;
        this.description = description;
        setDefault();
    }

    @Override public final synchronized T get() {
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

    @Override public final synchronized void setDefault() {
        obj = dflt;
        setDirty();
    }

    @Override public final synchronized void modify(F1<T, T> modi) {
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

    private Object readResolve() throws ObjectStreamException {
        listeners = new ArrayList<F1<Void, T>>();
        return this;
    }

    @Override public void addPropertyChangeListener(F1<Void, T> listener) {
        listeners.add( listener  );
    }

    @Override public void set(T val) {
        obj = val;
        setDirty();
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

    public String getDescription() {
        return description;
    }
}
