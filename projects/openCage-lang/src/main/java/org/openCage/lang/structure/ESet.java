package org.openCage.lang.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
 * A set with a get method
 * Sets use equals to determine uniqueness.
 * But if you want to retreive a specific object from a set you can not call get with an equal object
 * thus ESet
 * @param <T>
 */
public class ESet<T> implements Set<T> {

    private Map<T,T> map = new HashMap<T,T>();

    /**
     * Get a object equal to the input
     * i.e. not the same object
     * @param t the key
     * @return an object equal to the key or null
     */
    public T get( T t ) {
        return map.get(t);
    }

    /**
     * Get an object equal to the key or store the key in the eset
     * @param t
     * @return
     */
    public T getAdd( T t ) {
        if ( map.containsKey( t )) {
            return map.get(t);
        }

        map.put( t, t);

        return t;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey( o );
    }

    @Override
    public Iterator<T> iterator() {
        return map.values().iterator();
    }

    @Override
    public Object[] toArray() {
        return map.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return map.values().toArray( ts );
    }

    @Override
    public boolean add(T t) {
        if ( map.containsKey(t)) {
            map.put(t,t);
            return true;
        }

        map.put(t,t);
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if ( map.containsKey(o)) {
            map.remove( o );
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return map.values().containsAll( objects );
    }

    @Override
    public boolean addAll(Collection<? extends T> ts) {
        boolean ret = false;
        for ( T t : ts ) {
            ret |= add( t );
        }

        return ret;
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public String toString() {

        String ret = "Eset{";

        for ( T t : map.keySet() ) {
            ret += "   " + t.toString() + "\n";
        }


        return ret + "}";
    }
}
