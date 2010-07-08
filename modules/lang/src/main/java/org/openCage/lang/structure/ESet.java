package org.openCage.lang.structure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
