package org.openCage.other;

import com.google.inject.Provider;
import org.openCage.property.PropStore;
import org.openCage.property.PropStoreImpl;


/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 23, 2009
 * Time: 10:13:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropStoreProvider implements Provider<PropStore> {

    @Override
    public PropStore get(){
        return new PropStoreImpl( null, null, null, null );
    }
}
