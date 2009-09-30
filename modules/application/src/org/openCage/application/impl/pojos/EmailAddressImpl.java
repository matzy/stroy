package org.openCage.application.impl.pojos;

import java.io.ObjectStreamException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jetbrains.annotations.NotNull;
import org.openCage.application.protocol.EmailAddress;
import org.openCage.lang.errors.ValidationError;

public class EmailAddressImpl implements EmailAddress {

	private String email;
	
	public EmailAddressImpl( @NotNull String email ) {
		this.email = email;
		gettEmail();
	}
	
 	public URI gettEmail() {
		try {
			URI uri = new URI(email);
			if ( uri.getScheme() == null || !uri.getScheme().equals("mailto")) {
				throw new ValidationError( getClass(), "email address without mailto://" );
			}
			
			return uri;
		} catch (URISyntaxException e) {
			throw new ValidationError( getClass(), "", e);
		} 		
	}

 	private Object readResolve() throws ObjectStreamException {
 		gettEmail();
 		return this;
 	}
 	
}
