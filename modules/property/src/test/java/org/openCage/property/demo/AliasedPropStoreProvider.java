package org.openCage.property.demo;



import com.google.inject.Provider;
import org.openCage.property.PropStore;
import org.openCage.property.PropStoreImpl;

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
        return new PropStoreImpl(
                null,
                null,
                new HashMap<String,Class>() {{
                        put( "Meal", Meal.class );
                    }},
                null );
    }
}
