package org.openCage.comphy;

import static org.openCage.comphy.DereaderUtil.deread;

public class MapPropertyDereader<T extends Property> implements Dereadalizer<MapProperty<T>>{

    private Dereadalizer<T> valueTypeDereader;

    public MapPropertyDereader( Dereadalizer<T> valueTypeDereader ) {
        this.valueTypeDereader = valueTypeDereader;
    }

    @Override
    public MapProperty<T> fromString(RString str) {
        if ( str.get().trim().isEmpty() ) {
            return new MapProperty<T>();
        }
        return null;
    }

    @Override
    public MapProperty<T> fromList(RList lst) {
        throw new IllegalArgumentException("can't build MapProperty from RList");
    }

    @Override
    public MapProperty<T> fromMap(RMap map) {
        MapProperty<T> ret = new MapProperty<T>();
        for ( Key key : map.keySet() ) {
            ret.put(key, deread( valueTypeDereader, map.get(key)));
        }
        return ret;
    }
}
