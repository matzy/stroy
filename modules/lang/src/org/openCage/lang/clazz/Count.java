package org.openCage.lang.clazz;

import java.util.Iterator;

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

public class Count<T> implements Iterable<Count<T>>, Iterator<Count<T>> {

    public  T           obj;
    public  int         idx;

    private Iterator<T> orig;

    public Count( final Iterable<T> t) {
        orig = t.iterator();
        idx  = -1;
    }

    public boolean hasNext() {
        return orig.hasNext();
    }

    public Count<T> next() {
        obj = orig.next();
        idx++;
        return this;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Iterator<Count<T>> iterator() {
        return this;
    }

    public boolean isLast() {
        return !hasNext();
    }

    public boolean isFirst() {
        return idx == 0;
    }

    public static <T> Count<T> count( Iterable<T> iter ) {
        return new Count<T>( iter );
    }
}
