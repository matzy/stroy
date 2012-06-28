package org.openCage.util.iterator;

import org.openCage.lang.structure.T2;
import org.openCage.lang.structure.T3;
import org.openCage.util.string.IterableString;

import java.util.Iterator;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/
public class Iterators {

//    public static <T,S> Iterable<Pair<T,S>> both( Iterable<T> t, Iterable<S> s ) {
//        return new It2<T, S>( t, s );
//    }

    public static <T,S> Iterable<T2<T,S>> together( Iterable<T> t, Iterable<S> s ) {
        return new T2It<T, S>( t, s );
    }

    public static <T,S,C> Iterable<T3<T,S,C>> together( Iterable<T> t, Iterable<S> s, Iterable<C> c ) {
        return new T3It<T,S,C>( t, s, c );
    }


    public static <T> Iterable<Count<T>> count( Iterable<T> t ) {
        return new CountedIt<T>( t );
    }

//    public static Iterable<String> lines( File file ) {
//        return new IterableFile( file );
//    }


    public static Iterable<Character> chars( String str ) {
        return new IterableString( str );
    }

    public static <T> T get( Iterable<T> iterable, int idx ) {
        if ( idx < 0 ) {
            throw new IllegalArgumentException( "index < 0");
        }

        Iterator<T> it  = iterable.iterator();
        T           res = null;

        for ( ; idx >= 0; --idx ) {
            if ( it.hasNext() ) {
                res = it.next();
            } else {
                throw new IllegalArgumentException( "index " + idx + " too large");
            }
        }

        return res;
    }

    public static <T> Iterable<T> loop( final Iterator<T> iterator ) {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return iterator;
            }
        };
    }

    
}
