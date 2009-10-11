package org.openCage.util.collection;

import java.util.Map;
import java.util.HashMap;

public class MapF<S,T> {

    private Map<S,T> map = new HashMap<S,T>();

    public static <S,T> MapF<S,T> c( S s, T t ) {
        MapF<S,T> mb = new MapF<S,T>();
        return mb.a( s, t);
    }

    public static <S,T> MapF<S,T> c( Map<S,T> map ) {
        MapF<S,T> mapf = new MapF<S,T>();
        mapf.map = map;
        return mapf;        
    }

    public MapF<S,T> a( S s, T t ) {
        map.put( s, t );
        return this;
    }

    public Map<S,T> get() {
        return map;
    }

    public T get( S s ) {
        return map.get( s );
    }

}
