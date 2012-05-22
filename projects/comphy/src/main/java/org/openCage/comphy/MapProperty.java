package org.openCage.comphy;

import org.openCage.lang.Listeners;

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
public class MapProperty<T extends Property> implements Property, Map<String,T> {

    private Map<String,T> map = new HashMap<String, T>();
    private Listeners listeners = new Listeners();

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
    public T put(String key, T value) {
        T ret = map.put(key, value);
        listeners.shout(null);
        return ret;
    }

    @Override
    public T remove(Object key) {
        T ret = map.remove(key);
        listeners.shout(null);
        return ret;
    }

    @Override
    public void putAll(Map<? extends String, ? extends T> m) {
        map.putAll( m );
        listeners.shout(null);
    }

    @Override
    public void clear() {
        map.clear();
        listeners.shout(null);
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<T> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, T>> entrySet() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addObserver(Observer ob) {
        listeners.add(ob);
    }

    @Override
    public Readable toReadable() {
        RMap ret = new RMap();
        for ( Map.Entry<String,T> pair : map.entrySet() ) {
            ret.put(pair.getKey(), pair.getValue().toReadable());
        }
        return ret;
    }
}
