package org.openCage.utils.prop;

import com.google.inject.Inject;
import org.openCage.utils.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 30, 2008
 * Time: 2:03:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersistentPropStoreFactory implements PropStoreFactory {

    private final PropStore store;

    @Inject
    public PersistentPropStoreFactory( final PropStore store, final Persistence<PropStore> persi ) {
        this.store = persi.load( store, "foo" );
    }

    public PropStore get() {
        return store;
    }
}
