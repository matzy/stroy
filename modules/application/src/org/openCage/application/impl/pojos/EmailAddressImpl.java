package org.openCage.application.impl.pojos;

import java.io.ObjectStreamException;
import java.net.URI;
import java.net.URISyntaxException;

import net.jcip.annotations.Immutable;
import org.jetbrains.annotations.NotNull;
import org.openCage.application.protocol.EmailAddress;
import org.openCage.lang.errors.ValidationError;

@Immutable
public class EmailAddressImpl implements EmailAddress {

	private String        email;
    private transient URI emailURI;
	
	public EmailAddressImpl( @NotNull String email ) {
		this.email = email;
		setURI();
	}

 	public URI gettEmail() {
         return emailURI;
	}

 	private Object readResolve() throws ObjectStreamException {
 		setURI();
 		return this;
 	}
 	
    private void setURI() {
        try {
            emailURI = new URI(email);
            if ( emailURI.getScheme() == null || !emailURI.getScheme().equals("mailto")) {
                throw new ValidationError( getClass(), "email address without mailto://" );
            }

        } catch (URISyntaxException e) {
            throw new ValidationError( getClass(), "", e);
        } 		        
    }

}
