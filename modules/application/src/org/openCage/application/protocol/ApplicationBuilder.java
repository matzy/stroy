package org.openCage.application.protocol;

public interface ApplicationBuilder {
	public Application        build();
	
	public ApplicationBuilder with( Author author );
	public ApplicationBuilder name( String author );
	
	
	public AuthorBuilder      author();
	
}
