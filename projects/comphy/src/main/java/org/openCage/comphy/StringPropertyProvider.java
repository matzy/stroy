package org.openCage.comphy;

import com.google.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/22/12
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class StringPropertyProvider extends AbstractPropertyProvider<StringProperty>{

    @Inject
    public StringPropertyProvider( PropertyStore store, Key key, String deflt ) {
        super(store, new StringPropertyDereader(), new StringProperty(deflt), key );
    }


}
