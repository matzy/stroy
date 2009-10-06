package org.openCage.property.impl;

import org.openCage.property.protocol.*;
import java.util.List;
import java.util.ArrayList;

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
public class PropImpl<T> implements Prop<T>{

    private T                           deflt;
    private T                           prop;
    private List<PropChangeListener<T>> listeners = new ArrayList<PropChangeListener<T>>();

    public PropImpl( T str ) {
        prop = str;
    }

    public T get() {
        return prop;
    }

    public void set( T val ) {
        if ( prop.equals(val)) {
            // nothing to do. no event
            return;
        }

        prop = val;
        
        for ( PropChangeListener listener : listeners ) {
            listener.propChanged( prop  );
        }
    }

    public void addListener( PropChangeListener<T> listener ) {
        listeners.add( listener );
    }

    public void setDefault() {
        set( deflt );
    }
}
