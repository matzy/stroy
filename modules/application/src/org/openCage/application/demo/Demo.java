package org.openCage.application.demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.openCage.application.protocol.Application;
import org.openCage.application.protocol.Author;
import org.openCage.localization.wiring.LocalizeWiring;
import org.openCage.withResource.wiring.IoWiring;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 1, 2009
 * Time: 6:04:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Demo {

    public static void main(String[] args) {

        System.out.println("woooo");

        new IoWiring();

        System.out.println("woooo");

        new LocalizeWiring();
        System.out.println("woooo");

        Injector injector = Guice.createInjector( new DemoWiring() );

        Application app = injector.getInstance( Application.class );

        for ( Author author : app.getAuthors()) {
            System.out.println( author );
        }

        System.out.println("duuuh");
    }
}
