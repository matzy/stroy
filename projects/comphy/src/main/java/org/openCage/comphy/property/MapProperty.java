package org.openCage.comphy.property;

import org.openCage.comphy.*;
import org.openCage.comphy.Readable;
import org.openCage.lang.inc.GMap;
import org.openCage.lang.inc.Str;
import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.openCage.comphy.Readables.R;

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

public class MapProperty<T extends Property> implements Property, Map<Str,T>, Observer {

    private Map<Str,T> map = new HashMap<Str, T>();
    private VoidListeners listeners = new VoidListeners();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public T get(Object key) {
        if ( !(key instanceof Str)) {
            throw new ClassCastException("object is no Str " + key );
        }
        return map.get(key);
    }

    @Override
    public T put( Str key, T val ) {
        T ret = map.put(key, val);
        val.getListenerControl().add(this);
        if ( ret != null ) {
            ret.getListenerControl().remove(this);
        }
        listeners.shout();
        return ret;
    }

    @Override
    public T remove(Object key) {
        T ret = map.remove(key);
        listeners.shout();
        return ret;
    }

    @Override
    public void putAll(Map<? extends Str, ? extends T> m) {
        for ( Map.Entry<? extends Str, ? extends T> pair : m.entrySet()  ) {
            put( pair.getKey(), pair.getValue());
        }
    }

    @Override
    public void clear() {
        for ( T val : map.values()) {
            val.getListenerControl().remove(this);
        }
        map.clear();
        listeners.shout();
    }

    @Override
    public Set<Str> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<T> values() {
        return map.values();
    }

    @Override
    public Set<Entry<Str, T>> entrySet() {
        return map.entrySet();
    }

    @Override
    public org.openCage.comphy.Readable toReadable() {
        GMap<Str, Readable> ret = Readables.Map().getMap();
        for ( Map.Entry<Str,T> pair : map.entrySet() ) {
            ret.put(pair.getKey(), pair.getValue().toReadable());
        }
        return R(ret);
    }

    @Override
    public void call() {
        listeners.shout();
    }

    @Override
    public VoidListenerControl getListenerControl() {
        return listeners;
    }
}
