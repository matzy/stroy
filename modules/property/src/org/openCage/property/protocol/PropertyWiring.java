package org.openCage.property.protocol;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.openCage.lang.protocol.LangWiring;

public class PropertyWiring implements Module {
    @Override
    public void configure(Binder binder) {
        binder.install( new LangWiring());
    }

    @Override
    public int hashCode() {
        return PropertyWiring.class.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (o instanceof PropertyWiring);
    }
}
