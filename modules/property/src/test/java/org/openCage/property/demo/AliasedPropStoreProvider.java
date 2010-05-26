package org.openCage.property.demo;



import com.google.inject.Provider;
import org.openCage.property.PersistingPropStore;
import org.openCage.property.PropStore;

import java.util.HashMap;


public class AliasedPropStoreProvider implements Provider<PropStore> {

//    public AliasedPropStoreProvider() {
//
//        super( null, null, new HashMap<String,Class>() {{
//            put( "Meal", Meal.class );
//        }});
//    }

    @Override
    public PropStore get() {
        return new PersistingPropStore(
                null,
                null,
                new HashMap<String,Class>() {{
                        put( "Meal", Meal.class );
                    }},
                null );
    }
}
