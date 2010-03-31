package org.openCage.property.protocol;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.openCage.lang.protocol.LangWiring;
import org.openCage.property.protocol.NonPersistingPropStore;

public class PropertyWiring implements Module {

    @Override public void configure(Binder binder) {
        binder.install( new LangWiring());

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
