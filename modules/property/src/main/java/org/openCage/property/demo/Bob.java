package org.openCage.property.demo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.lang.protocol.F1;
import org.openCage.property.protocol.Property;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 28-mar-2010
 * Time: 12:04:53
 * To change this template use File | Settings | File Templates.
 */
public class Bob {

    @Inject @Named( RestaurantProp.Key) private Property<String> lunchPlace;
    @Inject @Named( MealProp.Key) private Property<Meal> lastMeal;

    public Bob() {
    }

    public void status() {
        System.out.println("Bob has lunch at: " + lunchPlace.get() + " and eats " + lastMeal.get() );
    }

    public void chooseMeal( final String starter, final String main ) {
        lastMeal.modify( new F1<Meal, Meal>() {
            @Override public Meal call(Meal meal) {
                return new Meal( starter, main );
            }
        });

    }

}
