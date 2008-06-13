package org.openCage.util.generics;

import java.util.Collection;

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
@SuppressWarnings({"ClassWithoutToString"})
public class Generics<T, S extends T> {

    public Collection<T> cast( Collection<S> collection ) {
        //noinspection unchecked,RedundantCast
        return (Collection)collection;
    }

    public static <T, S extends T> Collection<T> cast( Class<T> dummy,  Collection<S> collection ) {
        //noinspection unchecked,RedundantCast
        return (Collection)collection;
    }
    

    public Collection<S> unsaveCast( Collection<T> collection ) {
        //noinspection unchecked,RedundantCast
        return (Collection)collection;
    }
}
