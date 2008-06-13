package org.openCage.util.collection;

import java.util.Set;

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

/**
 * Set utils
 */
public class Sets {

    /**
     * Get the intersection of 2 sets.
     * @param a A Set.
     * @param b An other Set.
     * @return the intersection
     */
    public static <T>int intersectionSize( Set<T> a, Set<T> b ) {
        int same = 0;

        for ( T elem : a ) {
            if ( b.contains( elem )) {
                same++;
            }
        }

        return same;        
    }

    /**
     * Equality of 2 sets (order independent)
     * @param a A Set.
     * @param b An other Set.
     * @return true iff the sets are equal
     */
    public static <T>boolean equal( Set<T> a, Set<T> b ) {
        return a.size() == b.size() && intersectionSize( a, b ) == a.size();
    }
}
