package org.openCage.demo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.property.clazz.AbstractPropertyProvider;
import org.openCage.property.protocol.PropStore;

public class OtherStoreProp extends AbstractPropertyProvider<String> {

    public final static String KEY = "Other";

    @Inject
    public OtherStoreProp( @Named( "trans" ) PropStore store ) {
        super( store, KEY, "totally different");
    }
}
