package org.openCage.kleinod.collection;

import org.openCage.kleinod.type.TypeLit;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/31/12
 * Time: 8:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReiHashMap<K,V> extends HashMap<K,V> {

    private final TypeLit<K> keyTL;
    private static final Logger LOG = Logger.getLogger( ReiHashMap.class.getName() );

    public ReiHashMap(TypeLit<K> keyTL ) {
        this.keyTL = keyTL;
    }

    @Override
    public V get(Object o) {
        if ( !keyTL.getRawType().isAssignableFrom( o.getClass() )) {
            throw new IllegalArgumentException( "object " + o + " (class "+ o.getClass() +")ist not of type " + keyTL);
        }
        return super.get(o);
    }

    @Override
    public boolean containsKey(Object o) {
        if ( !keyTL.getRawType().isAssignableFrom( o.getClass() )) {
            throw new IllegalArgumentException( "object " + o + " (class "+ o.getClass() +")ist not of type " + keyTL);
        }
        return super.containsKey(o);
    }

    @Override
    public V remove(Object o) {
        if ( !keyTL.getRawType().isAssignableFrom( o.getClass() )) {
            throw new IllegalArgumentException( "object " + o + " (class "+ o.getClass() +")ist not of type " + keyTL);
        }
        return super.remove(o);
    }

    @Override
    public V put(K k, V v) {
        if ( v == null ) {
            LOG.warning( "putting " + k + "->null into a ReiHashMap" );
        }
        return super.put(k, v);
    }
}
