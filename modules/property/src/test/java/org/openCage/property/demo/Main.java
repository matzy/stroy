package org.openCage.property.demo;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 28-mar-2010
 * Time: 13:19:07
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector( new DemoWiring() );

        Joe joe = injector.getInstance( Joe.class );
        Bob bob = injector.getInstance( Bob.class );
        injector.getInstance( Sue.class );

        joe.status();
        bob.status();

        joe.suggestNewRestaurant( "El Fuego" );

        joe.status();
        bob.status();

        bob.chooseMeal( "coffee", "bagel" );
        bob.status();

        Bob otherBob = injector.getInstance( Bob.class );
        otherBob.status();


    }
}
