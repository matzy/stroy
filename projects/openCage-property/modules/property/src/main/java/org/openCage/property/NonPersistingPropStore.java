package org.openCage.property;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class NonPersistingPropStore implements PropStore {

    private final Map<String,Property> store = new HashMap<String, Property>();


    @Override
    public Property get(@NotNull String key) {
        return store.get( key );
    }

    @Override
    public void setProperty(@NotNull String key, @NotNull Property prop) {

        if ( store.containsKey( key )) {
            throw new IllegalArgumentException( "prop already  in store" );
        }

        store.put( key, prop );
    }

    @Override
    public void setDirty() {
    }
}
