package org.openCage.withResource;

import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.With;

import com.google.inject.Binder;
import com.google.inject.Module;

public class GuiceWith implements Module {

	public void configure(Binder binder ) {
		binder.bind( With.class ).to( WithImpl.class );
	}

}