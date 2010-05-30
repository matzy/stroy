package org.openCage.lang.structure;

import org.openCage.lang.Constants;

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
public class T2<A,B> {

    public final A i0;
    public final B i1;

    public T2( final A a, final B b ) {
        this.i0 = a;
        this.i1 = b;
    }

    public static <A,B> T2<A,B> valueOf( A a, B b ) {
        return new T2<A,B>(a,b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof T2)) { return false; }

        T2 t2 = (T2) o;

        if (i0 != null ? !i0.equals(t2.i0) : t2.i0 != null) { return false; }
        if (i1 != null ? !i1.equals(t2.i1) : t2.i1 != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = i0 != null ? i0.hashCode() : 0;
        result = Constants.HASHFACTOR * result + (i1 != null ? i1.hashCode() : 0);
        return result;
    }
}
