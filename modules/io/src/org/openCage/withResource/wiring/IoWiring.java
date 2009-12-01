package org.openCage.withResource.wiring;

import org.openCage.withResource.impl.WithImpl;
import org.openCage.withResource.protocol.With;

import com.google.inject.Binder;
import com.google.inject.Module;

public class IoWiring implements Module {

	public void configure(Binder binder ) {
		binder.bind( With.class ).to( WithImpl.class );
	}

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof IoWiring);
    }
}