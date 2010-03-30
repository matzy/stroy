package org.openCage.demo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.property.protocol.AbstractPropertyProvider;
import org.openCage.property.protocol.PropStore;

public class DuhProp extends AbstractPropertyProvider<Integer>{

    public static final String HICKER = "hicker";

    @Inject
    public DuhProp( @Named( "std" ) PropStore store) {
        super( store, HICKER, 42, "duh" );
    }
}
