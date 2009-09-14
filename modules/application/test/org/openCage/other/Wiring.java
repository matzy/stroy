package org.openCage.other;

import org.openCage.application.protocol.Application;
import org.openCage.application.wiring.GuiceWiring;

import com.google.inject.Binder;
import com.google.inject.Module;

public class Wiring implements Module{

	public void configure(Binder binder ) {
		binder.install( new GuiceWiring());
		binder.bind( Application.class ).toProvider( ApplicationProvider.class );
	}

}
