package org.openCage.property;

public class PersistentProp<T> extends PropertyImpl<T>{

    private PersistentProp(PropStore store, T deflt, String description) {
        super(store, deflt, description);
    }


    public static <T> PersistentProp<T> get( PropStore store, String key, T dflt, String descr ) {
        // this should only get a property with a different T
        // if the xstream does not match this implementation
        @SuppressWarnings({"unchecked"}) PersistentProp<T> prop = (PersistentProp<T>)store.get(key);

        if ( prop != null ) {
            prop.setStore( store );
            return prop;
        }

        prop = new PersistentProp<T>( store, dflt, descr );
        store.setProperty( key, prop );
        return prop;
    }

}
