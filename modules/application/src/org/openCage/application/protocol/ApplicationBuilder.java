package org.openCage.application.protocol;

import org.openCage.lang.protocol.Builder;

public interface ApplicationBuilder extends Builder<Application>{
	
	public ApplicationBuilder with( Author author );
	public ApplicationBuilder name( String author );
	
	
	public AuthorBuilder      author();
	public ContactBuilder     contact();
	
}
