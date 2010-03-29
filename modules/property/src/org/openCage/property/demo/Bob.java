package org.openCage.property.demo;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.property.protocol.Property;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 28-mar-2010
 * Time: 12:04:53
 * To change this template use File | Settings | File Templates.
 */
public class Bob {
    @Inject
    @Named( "Work")
    private Property<String> work;

    public Bob() {
    }

    public void status() {
        System.out.println("Bob works at: " + work.get());
    }

}
