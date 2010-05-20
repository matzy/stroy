package org.openCage.demo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.openCage.property.PropStore;
import org.openCage.property.Property;
import org.openCage.property.PropertyImpl;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 23, 2009
 * Time: 2:29:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class FooPropertyProvider implements Provider<Property<String>> {

    @Inject @Named( "std")
    private PropStore store;
    private static final String DEMO_FOO = "demo.foo";


    @Override
    public Property<String> get() {
        Property prop = store.get(DEMO_FOO);

        if ( prop != null ) {
            return prop;
        }

        prop = new PropertyImpl<String>( store, "yahooo", "foo" );
        store.setProperty( DEMO_FOO, prop );
        return prop;
    }
}
