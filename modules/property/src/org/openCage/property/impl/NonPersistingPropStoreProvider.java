package org.openCage.property.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.property.impl.PropStoreImpl;
import org.openCage.property.protocol.PropStore;

import java.io.File;

/**
 * propstore without a backing file, i.e. non persisted
 */
public class NonPersistingPropStoreProvider implements Provider<PropStore> {

    @Inject
    private BackgroundExecutor executor;

    @Override
    public PropStore get() {
        return new PropStoreImpl( executor, null, null, null );
    }
}
