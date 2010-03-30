package org.openCage.property.demo;

import org.openCage.property.protocol.AbstractPropStoreProvider;


import java.util.HashMap;


public class AliasedPropStoreProvider extends AbstractPropStoreProvider {

    public AliasedPropStoreProvider() {

        super( null, null, new HashMap<String,Class>() {{
            put( "Meal", Meal.class );
        }});
    }
}
