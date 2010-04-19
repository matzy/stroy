package org.openCage.peleph;

import net.jcip.annotations.Immutable;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.openCage.lang.annotations.HiddenCall;

import java.io.ObjectStreamException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Apr 17, 2010
 * Time: 12:43:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Immutable
public class EmailAddress{

    private final String  email;
    private static final String MAILTO = "mailto";

    public EmailAddress( @NonNls @NotNull String email ) {
        this.email = email;
        getWithValidation();
    }

    public URI gettEmail() {
        return getWithValidation();
    }

    // called by deserialization
    // guaranties correctness
    @SuppressWarnings({"RedundantThrowsDeclaration"})
    @HiddenCall
    private Object readResolve() throws ObjectStreamException {
        getWithValidation();
        return this;
    }

    private URI getWithValidation() {
        try {
            URI emailURI = new URI(email);
            if ( emailURI.getScheme() == null || !emailURI.getScheme().equals(MAILTO)) {
                throw new IllegalStateException( "email address without mailto: " + email );
            }

            return emailURI;

        } catch (URISyntaxException e) {
            throw new IllegalStateException( "not a valid email string " + e);
        }
    }

    @Override
    public String toString() {
        return email;
    }
}
