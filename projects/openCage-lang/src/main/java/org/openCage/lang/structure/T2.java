package org.openCage.lang.structure;

import net.jcip.annotations.Immutable;
import org.openCage.lang.Constants;

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

/**
 * A 2-Tuple, i.e. a Pair of to types
 * @param <A> Any Type
 * @param <B> Any Type
 */
@Immutable public class T2<A,B> {

    public final A i0; // NOSONAR
    public final B i1; // NOSONAR

    /**
     * Contruct a tuple out of the 2 arguments
     * @param a Any object of Type A
     * @param b Any object of Type B
     */
    public T2( final A a, final B b ) {
        this.i0 = a;
        this.i1 = b;
    }

    /**
     * Contruct a tuple out of the 2 arguments, derives the correct generics
     * @param a Any object of Type A
     * @param b Any object of Type B
     * @param <A> Any Type
     * @param <B> Any Type
     * @return
     */
    public static <A,B> T2<A,B> valueOf( A a, B b ) {
        return new T2<A,B>(a,b);
    }

    /**
     * Standard equals
     * @param o Any object
     * @return Whether the argument equals the object
     */
    @Override public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof T2)) { return false; }

        T2 t2 = (T2) o;

        if (i0 != null ? !i0.equals(t2.i0) : t2.i0 != null) { return false; }
        if (i1 != null ? !i1.equals(t2.i1) : t2.i1 != null) { return false; }

        return true;
    }

    /**
     * Standard hashCode
     * @return a hash of both elements
     */
    @Override public int hashCode() {
        int result = i0 != null ? i0.hashCode() : 0;
        result = Constants.HASHFACTOR * result + (i1 != null ? i1.hashCode() : 0);
        return result;
    }
}
