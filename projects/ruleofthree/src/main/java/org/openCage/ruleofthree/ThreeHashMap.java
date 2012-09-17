package org.openCage.ruleofthree;

import org.openCage.kleinod.collection.ReiHashMap;
import org.openCage.kleinod.type.TypeLit;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/31/12
 * Time: 11:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class ThreeHashMap<T> extends ReiHashMap<ThreeKey,T> implements ThreeMap<T> {
    public ThreeHashMap() {
        super( new TypeLit<ThreeKey>() {});
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ( !(o instanceof ThreeHashMap )) {
            return false;
        }

        ThreeHashMap other = (ThreeHashMap)o;

        if ( size() != other.size()) {
            return false;
        }

        for ( ThreeKey key : keySet() ) {
            if ( !get( key).equals(other.get(key))) {
                return false;
            }
        }

        return true;
    }
}
