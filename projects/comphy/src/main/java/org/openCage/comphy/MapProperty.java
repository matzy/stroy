package org.openCage.comphy;

import org.openCage.lang.listeners.Listeners;
import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapProperty<T extends Property> implements Property, Map<Key,T>, Observer  {

    private Map<Key,T> map = new HashMap<Key, T>();
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
        return map.get(key);
    }

    @Override
    public T put(Key key, T value) {
        T ret = map.put(key, value);
        value.getListenerControl().add(this);
        if ( ret != null ) {
            ret.getListenerControl().remove(this);
        }
        listeners.shout();
        return ret;
    }

    public MapProperty<T> put( String key, T val ) {
        map.put( new Key(key), val);
        listeners.shout();
        return this;

    }

    @Override
    public T remove(Object key) {
        T ret = map.remove(key);
        listeners.shout();
        return ret;
    }

    @Override
    public void putAll(Map<? extends Key, ? extends T> m) {
        for ( Map.Entry<? extends Key, ? extends T> pair : m.entrySet()  ) {
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
    public Set<Key> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<T> values() {
        return map.values();
    }

    @Override
    public Set<Entry<Key, T>> entrySet() {
        return map.entrySet();
    }

    @Override
    public Readable toReadable() {
        RMap ret = new RMap();
        for ( Map.Entry<Key,T> pair : map.entrySet() ) {
            ret.put(pair.getKey(), pair.getValue().toReadable());
        }
        return ret;
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
