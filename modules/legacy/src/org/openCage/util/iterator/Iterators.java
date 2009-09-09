package org.openCage.util.iterator;

import org.openCage.util.string.IterableString;

import java.io.File;
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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
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
