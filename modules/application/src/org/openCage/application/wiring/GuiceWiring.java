package org.openCage.application.wiring;

import org.openCage.application.impl.AppBuilder2;
import org.openCage.application.impl.ApplicationFromConfigXStream;
import org.openCage.application.impl.AuthorBuilderImpl;
import org.openCage.application.protocol.ApplicationBuilder;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.application.protocol.AuthorBuilder;
import org.openCage.withResource.GuiceWith;

import com.google.inject.Binder;
import com.google.inject.Module;

public class GuiceWiring implements Module{

	public void configure(Binder binder ) {
		
		binder.install( new GuiceWith() );
		
        binder.bind( AuthorBuilder.class ).to( AuthorBuilderImpl.class );	
        binder.bind( ApplicationBuilder.class ).to( AppBuilder2.class );	
        binder.bind( ApplicationFromConfig.class).to( ApplicationFromConfigXStream.class );
	}

}
