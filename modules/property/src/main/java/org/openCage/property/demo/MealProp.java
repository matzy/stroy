package org.openCage.property.demo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.property.protocol.AbstractPropertyProvider;
import org.openCage.property.protocol.PropStore;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Mar 30, 2010
 * Time: 9:33:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class MealProp extends AbstractPropertyProvider<Meal> {

    public static final String Key = "Meal";

    @Inject
    public MealProp( @Named( "trans" ) PropStore store) {
        super( store, Key, new Meal("Tomato Soup", "Cheese Burger"), "remember the last meal"  );
    }
}
