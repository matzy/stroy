package org.openCage.demo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.property.impl.PropStoreImpl;
import org.openCage.property.protocol.PropStore;

public class OtherProperStoreProvider  implements Provider<PropStore> {

    @Inject
    private BackgroundExecutor executor;

    @Override
    public PropStore get() {
        return new PropStoreImpl( executor, null, null );
    }
}
