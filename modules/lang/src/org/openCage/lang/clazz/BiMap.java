package org.openCage.lang.clazz;

import java.util.Collection;
import java.util.List;
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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class BiMap<A,B> {

    private Map<A, B> forward = new HashMap<A,B>();
    private Map<B, A> backward = new HashMap<B,A>();

    public void put( A a, B b ) {
        forward.put(a,b);
        backward.put(b,a);
    }

    public B get( A a ) {
        return forward.get(a);
    }

    public A getReverse( B b ) {
        return backward.get(b);
    }

    public Collection<A> keys() {
        return forward.keySet();
    }

    public Collection<B> vals() {
        return forward.values();
    }
}
