package org.openCage.demo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.openCage.property.Property;
import org.openCage.property.PropertyProvider;
import org.openCage.property.PropStore;

public class DuhProp extends PropertyProvider<Integer> implements Provider<Property<Integer>> {

    public static final String HICKER = "hicker";

    @Inject
    public DuhProp( @Named( "std" ) PropStore store) {
        super( store, HICKER, 42, "duh" );
    }
}
