package org.openCage.lang.structure;

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

public class Lexicographical {

    private Lexicographical() {}

    public static <A extends Comparable<A>, B extends Comparable<B>> int compareTo( A a1, A a2, B b1, B b2 ) {

        int aa = a1.compareTo(a2);
        if ( aa < 0 ) {
            return -1;
        }

        if ( aa > 0 ) {
            return 1;
        }

        return b1.compareTo(b2);
    }

    public static <A extends Comparable<A>,B extends Comparable<B>, C extends Comparable<C>> int compareTo( A a1, A a2, B b1, B b2, C c1, C c2 ) {

        int aa = a1.compareTo(a2);
        if ( aa < 0 ) {
            return -1;
        }

        if ( aa > 0 ) {
            return 1;
        }

        int bb = b1.compareTo(b2);
        if ( bb < 0 ) {
            return -1;
        }

        if ( bb > 0 ) {
            return 1;
        }

        return c1.compareTo(c2);
    }
}
