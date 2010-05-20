package org.openCage.demo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.property.PropStore;
import org.openCage.property.protocol.AbstractPropertyProvider;

public class OtherStoreProp extends AbstractPropertyProvider<String> {

    public final static String KEY = "Other";

    @Inject
    public OtherStoreProp( @Named( "trans" ) PropStore store ) {
        super( store, KEY, "totally different", "other");
    }
}
