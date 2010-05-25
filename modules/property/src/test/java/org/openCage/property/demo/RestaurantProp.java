package org.openCage.property.demo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.openCage.property.Property;
import org.openCage.property.PropertyProvider;
import org.openCage.property.protocol.NonPersistingPropStore;
import org.openCage.property.PropStore;

/**
 * A restaurant property / preference
 */
public class RestaurantProp extends PropertyProvider<String> implements Provider<Property<String>> {
    public static final String Key = "Restaurant";

    @Inject
    public RestaurantProp( @Named(NonPersistingPropStore.NAME) PropStore store) {
        super( store, Key, "Lisa's Lunchbox", "Restaurant Preferences" );
    }
}
