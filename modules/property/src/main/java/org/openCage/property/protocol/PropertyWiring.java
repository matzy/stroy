package org.openCage.property.protocol;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.openCage.lang.impl.BackgroundExecutorImpl;
import org.openCage.lang.protocol.BackgroundExecutor;

public class PropertyWiring implements Module {

    @Override public void configure(Binder binder) {

        binder.bind(BackgroundExecutor.class ).
                to(BackgroundExecutorImpl.class );
        
        binder.bind( PropStore.class ).
                annotatedWith( Names.named( NonPersistingPropStore.NAME )).
                to( NonPersistingPropStore.class );
    }

    @Override public int hashCode() {
        return PropertyWiring.class.hashCode();
    }

    @Override public boolean equals(Object o) {
        return o != null && (o instanceof PropertyWiring);
    }
}
