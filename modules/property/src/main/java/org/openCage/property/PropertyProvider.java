package org.openCage.property;

import org.jetbrains.annotations.NotNull;

public abstract class PropertyProvider<T> {
    private final T val;
    private final String key;
    private final PropStore store;
    private final String description;

    public PropertyProvider( @NotNull PropStore store, @NotNull String key, T val, String description ) {
        this.key = key;
        this.val = val;
        this.store = store;
        this.description = description;
    }

    public Property<T> get() {

        // this should only get a property with a different T
        // if the xstream does not match this implementation
        @SuppressWarnings({"unchecked"}) PropertyImpl<T> prop = (PropertyImpl<T>)store.get(key);

        if ( prop != null ) {
            prop.setStore( store );
            return prop;
        }

        prop = new PropertyImpl<T>( store, val, description );
        store.setProperty( key, prop );
        return prop;
    }

}
