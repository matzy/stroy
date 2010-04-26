package org.openCage.lang.artifact;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Apr 25, 2010
 * Time: 10:55:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class ESet<T> implements Set<T> {

    private Map<T,T> map = new HashMap<T,T>();

    public T get( T t ) {
        return map.get(t);
    }

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
}
