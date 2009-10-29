package org.openCage.application.wiring;

import org.openCage.application.impl.ApplicationFromConfigXStream;
import org.openCage.application.impl.ApplicationLocalizeProvider;
import org.openCage.application.protocol.ApplicationFromConfig;
import org.openCage.localization.protocol.Localize;
import org.openCage.localization.wiring.LocalizeWiring;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.openCage.withResource.wiring.WithResourceWiring;

public class ApplicationWiring implements Module{

	public void configure(Binder binder ) {

		binder.install( new WithResourceWiring() );
		binder.install( new LocalizeWiring());
		binder.bind( ApplicationFromConfig.class).
			to( ApplicationFromConfigXStream.class );
		binder.bind( Localize.class ).
			annotatedWith( Names.named("application" )).toProvider( ApplicationLocalizeProvider.class );
//                binder.bind( UpdateChecker.class ).to( UpdateChecker.class );
	}
}
