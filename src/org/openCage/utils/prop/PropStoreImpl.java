package org.openCage.utils.prop;

import org.openCage.util.prefs.PreferencesChangeListener;
import org.openCage.utils.persistence.Persistable;
import org.openCage.utils.persistence.PersistenceImpl;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 30, 2008
 * Time: 1:53:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropStoreImpl implements PropStore, Persistable {

    private Map<String, Prop> store = new HashMap<String, Prop>();
    private boolean           dirty = false;


    public Prop get( String key ) {
        return store.get( key );
    }

    public void init( String key, Prop val ) {
        if ( store.get( key  ) != null ) {
            // exists already
            // TODO check typ
            return;
        }

        // new prop

        store.put( key, val );

        // notify me if it changes in the future
        val.addListener( new PreferencesChangeListener() {
            public void changed( Object txt ) {
                dirty = true;
            }
        } );

        // needs to be saved
        dirty = true;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setClean() {
        dirty = false;
    }
}
