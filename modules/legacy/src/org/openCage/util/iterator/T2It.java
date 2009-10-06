package org.openCage.util.iterator;

import java.util.Iterator;
import org.openCage.lang.protocol.tuple.T2;

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

public class T2It<S,T> implements Iterable<T2<S,T>>, Iterator<T2<S, T>> {

    private final Iterator<S> it0;
    private final Iterator<T> it1;

    public T2It( final Iterable<S> s, final Iterable<T> t) {
        it0 = s.iterator();
        it1 = t.iterator();
    }

    public Iterator<T2<S, T>> iterator() {
        return this;
    }

    public boolean hasNext() {
        return it0.hasNext() && it1.hasNext();
    }

    public T2<S, T> next() {
        return new T2<S,T>( it0.next(), it1.next());
    }

    public void remove() {
        throw new Error( "not impl" );
    }
}
