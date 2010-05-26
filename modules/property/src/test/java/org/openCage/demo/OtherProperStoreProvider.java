package org.openCage.demo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.lang.BackgroundExecutor;
import org.openCage.property.PropStore;
import org.openCage.property.PersistingPropStore;

public class OtherProperStoreProvider  implements Provider<PropStore> {

    @Inject
    private BackgroundExecutor executor;

    @Override
    public PropStore get() {
        return new PersistingPropStore( executor, null, null, null );
    }
}
