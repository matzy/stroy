package org.openCage.comphy;

import com.google.inject.Inject;

public class StringPropertyProvider extends AbstractPropertyProvider<StringProperty>{

    @Inject
    public StringPropertyProvider( PropertyStore5 store ) {
        super(store, new StringPropertyDereader(), new StringProperty("foo"), "bar ");
    }
}
