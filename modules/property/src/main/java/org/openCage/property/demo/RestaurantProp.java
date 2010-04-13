package org.openCage.property.demo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.property.protocol.NonPersistingPropStore;
import org.openCage.property.protocol.AbstractPropertyProvider;
import org.openCage.property.protocol.PropStore;

/**
 * A restaurant property / preference
 */
public class RestaurantProp extends AbstractPropertyProvider<String> {
    public static final String Key = "Restaurant";

    @Inject
    public RestaurantProp( @Named(NonPersistingPropStore.NAME) PropStore store) {
        super( store, Key, "Lisa's Lunchbox", "Restaurant Preferences" );
    }
}
