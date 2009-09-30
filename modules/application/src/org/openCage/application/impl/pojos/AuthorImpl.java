package org.openCage.application.impl.pojos;

import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.EmailAddress;

public class AuthorImpl implements Author {

	private final String  name;
	private final EmailAddress email;
	

	public AuthorImpl( final String name ) {
		this.name = name;
                email = null;
	}

        public AuthorImpl( final String name, EmailAddress email ) {
		this.name = name;
		this.email = email;
	}
	
	public String gettName() {
		return name;
	}

	public EmailAddress getEmail() {
		return email;
	}
}
