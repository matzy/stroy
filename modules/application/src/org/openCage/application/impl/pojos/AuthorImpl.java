package org.openCage.application.impl.pojos;

import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.Contact;

public class AuthorImpl implements Author {

	private final String  name;
	private final Contact contact;
	

	public AuthorImpl( final String name, final Contact contact ) {
		this.name = name;
		this.contact = contact;
	}
	
	public String getName() {
		return name;
	}

	public Contact getContact() {
		return contact;
	}

}
