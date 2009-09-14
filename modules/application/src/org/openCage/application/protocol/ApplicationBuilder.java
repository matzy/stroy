package org.openCage.application.protocol;

public interface ApplicationBuilder extends Builder<Application>{
	
	public ApplicationBuilder with( Author author );
	public ApplicationBuilder name( String author );
	
	
	public AuthorBuilder      author();
	public ContactBuilder     contact();
	
}
