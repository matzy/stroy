package org.openCage.utils.persistence;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.HashMap;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class InMemoryPeristence<T extends Persistable> implements Persistence<T> {

    private Map<String, T> mem = new HashMap<String,T>();

    public T load( T init, @NotNull String name ) {
        if ( mem.containsKey( name )) {
            return mem.get( name );
        }

        mem.put( name, init );
        
        return mem.get( name );
    }

    public void save( T prefs, @NotNull String name ) {
    }
}
