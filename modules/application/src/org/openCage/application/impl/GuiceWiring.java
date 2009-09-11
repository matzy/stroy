package org.openCage.application.impl;

import org.openCage.application.protocol.ApplicationBuilder;
import org.openCage.application.protocol.AuthorBuilder;

import com.google.inject.Binder;
import com.google.inject.Module;

public class GuiceWiring implements Module{

	public void configure(Binder binder ) {
        binder.bind( AuthorBuilder.class ).to( AuthorBuilderImpl.class );	
        binder.bind( ApplicationBuilder.class ).to( AppBuilder2.class );	
	}

}
