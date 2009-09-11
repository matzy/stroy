package org.openCage.application.protocol;

public interface AuthorBuilder {
	public Author  build();
	
	public AuthorBuilder name( String name );
}
