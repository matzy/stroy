package org.openCage.lang.structure;

import java.util.*;

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

/**
 * A bidirectional Hashmap
 * @param <A> Any type
 * @param <B> Any type
 */
public class BiMap<A,B> implements Map<A,B>{

    private Map<A, B> forward  = new HashMap<A,B>();
    private Map<B, A> backward = new HashMap<B,A>();

    @Override
    public int size() {
        return forward.size();
    }

    @Override
    public boolean isEmpty() {
        return forward.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return forward.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return backward.containsKey(value);
    }


    @Override
    public B remove(Object key) {
        B val = forward.remove(key);
        backward.remove(val);
        return val;
    }

    @Override
    public synchronized void putAll(Map<? extends A, ? extends B> m) {
        forward.putAll(m);
        for ( Map.Entry<? extends A, ? extends B> entry : m.entrySet() ) {
            backward.put( entry.getValue(), entry.getKey() );
        }
    }

    @Override
    public void clear() {
        forward.clear();
        backward.clear();
    }

    @Override
    public Set<A> keySet() {
        return forward.keySet();
    }

    @Override
    public Collection<B> values() {
        return backward.keySet();
    }

    @Override
    public Set<Entry<A, B>> entrySet() {
        return forward.entrySet();
    }


    @Override
    public B put( A a, B b ) {
        backward.put(b,a);
        return forward.put(a,b);
    }

    @Override
    public B get(Object a) {
        return forward.get(a);
    }


    public A getReverse( B b ) {
        return backward.get(b);
    }

    public Collection<A> keys() {
        return forward.keySet();
    }

    public Collection<B> vals() {
        return forward.values();
    }
}
