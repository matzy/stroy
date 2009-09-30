package org.openCage.withResource.wiring;

import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.With;

import com.google.inject.Binder;
import com.google.inject.Module;

public class WithResourceWiring implements Module {

	public void configure(Binder binder ) {
		binder.bind( With.class ).to( WithImpl.class );
	}

}