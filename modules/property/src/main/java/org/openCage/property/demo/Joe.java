package org.openCage.property.demo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.lang.protocol.F1;
import org.openCage.property.protocol.Property;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 28-mar-2010
 * Time: 11:59:17
 * To change this template use File | Settings | File Templates.
 */
public class Joe {

    @Inject @Named( RestaurantProp.Key) private Property<String> dinner;

    public Joe() {
    }

    public void status() {
        System.out.println("Joe has dinner at: " + dinner.get());
    }

    public void suggestNewRestaurant( final String place ) {
        dinner.modify( new F1<String, String>() {
            @Override public String call(String s) {
                return place;
            }
        });
    }


}
