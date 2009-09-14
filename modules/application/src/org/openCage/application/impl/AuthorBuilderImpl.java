package org.openCage.application.impl;

import org.openCage.application.impl.pojos.AuthorImpl;
import org.openCage.application.protocol.Author;
import org.openCage.application.protocol.AuthorBuilder;
import org.openCage.application.protocol.Contact;

public class AuthorBuilderImpl implements AuthorBuilder {

	private String name;
	private Contact contact;
	
	public AuthorBuilder name( String name ) {
		this.name = name;
		return this;
	}
	
	public AuthorBuilder contact( Contact contact ) {
		this.contact = contact;
		return this;
	}
	
	public Author build() {
		return new AuthorImpl( name, null );
	}
	
	

}
