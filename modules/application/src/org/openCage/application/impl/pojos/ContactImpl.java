package org.openCage.application.impl.pojos;

import java.net.URI;

import org.openCage.application.protocol.Contact;

public class ContactImpl implements Contact {

	private URI email;
	private URI webPage;
	
	public URI getDefaultURI() {
		if( email != null ) {
			return email;
		}
		
		if( webPage != null ) {
			return webPage;
		}
		
		return null;
	}
	
}
