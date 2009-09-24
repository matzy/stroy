package org.openCage.application.wiring;

import org.openCage.application.impl.AppBuilder2;
import org.openCage.application.impl.ApplicationFromConfigXStream;
import org.openCage.application.impl.ApplicationLocalizeProvider;
import org.openCage.application.impl.AuthorBuilderImpl;
import org.openCage.application.impl.about.AboutSheetFromApplication;
import org.openCage.application.protocol.AboutSheet;
import org.openCage.application.protocol.ApplicationBuilder;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.application.protocol.AuthorBuilder;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.wiring.LocalizeWiring;
import org.openCage.withResource.GuiceWith;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class ApplicationWiring implements Module{

	public void configure(Binder binder ) {
		
		binder.install( new GuiceWith() );
		binder.install( new LocalizeWiring());
		
        binder.bind( AuthorBuilder.class ).
        	to( AuthorBuilderImpl.class );	
        binder.bind( ApplicationBuilder.class ).
        	to( AppBuilder2.class );	
        binder.bind( ApplicationFromConfig.class).
        	to( ApplicationFromConfigXStream.class );
        binder.bind( AboutSheet.class ).
        	to( AboutSheetFromApplication.class );
        binder.bind( Localize.class ).
        	annotatedWith( Names.named("application" )).toProvider( ApplicationLocalizeProvider.class );
	}
}
