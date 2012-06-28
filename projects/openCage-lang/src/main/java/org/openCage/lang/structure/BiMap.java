package org.openCage.lang.structure;

import java.util.*;

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


/**
 * A bidirectional Hashmap
 * @param <A> Any type
 * @param <B> Any type
 */
public class BiMap<A,B> implements Map<A,B>{

    private Map<A, B> forward  = new HashMap<A,B>();
    private Map<B, A> backward = new HashMap<B,A>();

    @Override public int size() {
        return forward.size();
    }

    @Override public boolean isEmpty() {
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
