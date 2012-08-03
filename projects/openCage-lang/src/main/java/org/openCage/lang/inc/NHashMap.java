package org.openCage.lang.inc;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/31/12
 * Time: 7:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class NHashMap<K,V> extends HashMap<K,V> {

    private Class<K> keyClass;
    private Class<V> valClass;

    public NHashMap( Class<K> keyClass, Class<V> valClass ) {
        this.keyClass = keyClass;
        this.valClass = valClass;
    }

    @Override
    public V get(Object o) {
        if ( !keyClass.isAssignableFrom( o.getClass() ))  {

            throw new IllegalArgumentException( "not the correkt type for a key: " + o.getClass() );
        }

        return super.get(o);
    }
}
