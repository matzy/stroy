package org.openCage.application.impl;

import org.openCage.application.impl.pojos.ApplicationImpl;
import org.openCage.application.impl.pojos.AuthorImpl;

public class ApplicationBuilderA {

	public ApplicationImpl app( String name ) {
		// TODO Auto-generated method stub
		return new ApplicationImpl(name);
	}

	public AuthorImpl author(String name) {
		// TODO Auto-generated method stub
		return new AuthorImpl(name,null);
	}

}
