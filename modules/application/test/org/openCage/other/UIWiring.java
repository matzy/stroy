package org.openCage.other;

import org.openCage.application.protocol.Application;
import org.openCage.application.wiring.ApplicationWiring;
import org.openCage.localization.protocol.Localize;

import com.google.inject.Binder;
import com.google.inject.Module;

public class UIWiring implements Module{

	public void configure(Binder binder ) {
		binder.install( new ApplicationWiring());
		binder.bind( Application.class ).toProvider( AppProviderXML.class );
	}

}
