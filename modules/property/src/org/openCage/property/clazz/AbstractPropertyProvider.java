package org.openCage.property.clazz;

import com.google.inject.Provider;
import org.jetbrains.annotations.NotNull;
import org.openCage.property.impl.PropertyImpl;
import org.openCage.property.protocol.PropStore;
import org.openCage.property.protocol.Property;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public abstract class AbstractPropertyProvider<T> implements Provider<Property<T>> {

    private final T val;
    private final String key;
    private final PropStore store;

    public AbstractPropertyProvider( @NotNull PropStore store, @NotNull String key, T val ) {
        this.key = key;
        this.val = val;
        this.store = store;
    }

    @Override
    public Property<T> get() {

        // this should only get a property with a different T
        // if the xstream does not match this implementation        
        @SuppressWarnings({"unchecked"}) PropertyImpl<T> prop = (PropertyImpl<T>)store.get(key);

        if ( prop != null ) {
            prop.setStore( store );
            return prop;
        }

        prop = new PropertyImpl<T>( store, val );
        store.setProperty( key, prop );
        return prop;
    }
}
