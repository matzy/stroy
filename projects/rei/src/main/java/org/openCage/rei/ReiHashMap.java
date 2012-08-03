package org.openCage.rei;

import com.google.inject.TypeLiteral;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/31/12
 * Time: 8:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReiHashMap<K,V> extends HashMap<K,V> {

    private final TypeLiteral<K> keyTL;

    public ReiHashMap(TypeLiteral<K> keyTL ) {
        this.keyTL = keyTL;
    }

    @Override
    public V get(Object o) {
        if ( !keyTL.getRawType().isAssignableFrom( o.getClass() )) {
            throw new IllegalArgumentException( "object " + o + " ist not of type " + keyTL);
        }
        return super.get(o);
    }

    @Override
    public boolean containsKey(Object o) {
        if ( !keyTL.getRawType().isAssignableFrom( o.getClass() )) {
            throw new IllegalArgumentException( "object ist not of type " + keyTL);
        }
        return super.containsKey(o);
    }

    @Override
    public V remove(Object o) {
        if ( !keyTL.getRawType().isAssignableFrom( o.getClass() )) {
            throw new IllegalArgumentException( "object ist not of type " + keyTL);
        }
        return super.remove(o);
    }
}
