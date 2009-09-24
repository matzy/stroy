package org.openCage.application.impl.pojos;

import java.net.URI;

import org.opebCage.lang.errors.NotTestedError;
import org.openCage.application.protocol.Contact;

public class ContactImpl implements Contact {

	private URI email;
	private URI webpage;
	
	public ContactImpl( URI email, URI webpage ) {
		this.email = email;
		this.webpage = webpage;
	}
	
	public URI getDefaultURI() {
		if( email != null ) {
			return email;
		}
		
		if( webpage != null ) {
			return webpage;
		}
		
		return null;
	}

	public boolean hasEmail() {
		return email != null;
	}
	
	public URI getEmail() {
		if ( !hasEmail() ) {
			throw new NotTestedError();
		}
		
		return email;
	}
	
	public boolean hasWebpage() {
		return webpage != null;
	}

	public URI getWebpage() {
		if ( !hasWebpage() ) {
			throw new NotTestedError();
		}
		return webpage;
	}

	public void validate() {
		if ( email != null ) {
			String sch = email.getScheme();
			int i = 0;
		}
	}
	
}
