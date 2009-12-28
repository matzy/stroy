package org.openCage.demo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.property.impl.PropStoreImpl;
import org.openCage.property.protocol.PropStore;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 23, 2009
 * Time: 7:43:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropStoreProvider implements Provider<PropStore> {

    @Inject
    private BackgroundExecutor executor;

    @Override
    public PropStore get() {
        return new PropStoreImpl( executor, null );
    }
}
