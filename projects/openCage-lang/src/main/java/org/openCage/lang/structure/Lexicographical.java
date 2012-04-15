package org.openCage.lang.structure;

/***** BEGIN LICENSE BLOCK *****
 * New BSD License
 * Copyright (c) 2006 - 2010, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Stephan Pfab nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
