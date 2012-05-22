package org.openCage.utils.prop;

import org.openCage.utils.prop.PropStoreFactory;
import org.openCage.utils.prop.Prop;
import org.openCage.utils.prop.PropImpl;
import com.google.inject.Provider;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class PropProvider<T> implements Provider<Prop<T>> {

    private final PropStoreFactory psfactory;
    private Prop<T>                prop = null;
    private String                 key;
    private T                      val;

    public PropProvider( final PropStoreFactory psfactory, final String key, final T val ) {
        this.psfactory = psfactory;
        this.key = key;
        this.val = val;
    }

    public Prop<T> get() {
        if ( prop == null ) {
            psfactory.get().init( key, new PropImpl<T>( val ) );
            prop = (PropImpl<T>)psfactory.get().get( key );
        }

        return prop;
    }
}
