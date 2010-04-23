package org.openCage.lang.artifact;

import org.jetbrains.annotations.NotNull;
import org.openCage.lang.Once;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Apr 17, 2010
 * Time: 12:13:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Author {

    private final String name;
    private final Once<EmailAddress> email = new Once<EmailAddress>( new EmailAddress( "mailto:404" ));
    private String tName;

    public Author( String name ) {
        this.name = name;
    }

    public Author email( @NotNull String addr ) {
        email.set( new EmailAddress( addr ));
        return this;
    }


    public String getName() {
        return name;
    }

    public static Author c( String name ) {
        return new Author( name );
    }

}
