package org.openCage.lang.iterators;

import java.io.BufferedReader;
import java.util.Arrays;


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

public final class Iterators {

    private Iterators() {};

    /**
     * Split on standard word boundary, i.e. space tab line ends
     * @param str Any string
     * @return An Array of Strings
     */
    public static String[] words( String str ) {
        return str.split("( |\t|\r|\n)+");
    }

    /**
     * Allows a for-loop iteration over the characters of a string
     * @param str
     * @return A Character Iterable
     */
    public static Iterable<Character> chars( String str ) {
        return new StringIterator(str);
    }

    /**
     * Allows for-loop iteration over the lines of a text
     * @param str Any String
     * @return An Array of Strings
     */
    public static String[] lines( String str ) {
        return str.split( "(\n|\r)+");
    }

    /**
     * loops over lines from a BufferedReader
     * @param reader Some text input
     * @return A Text Line Iterator
     */
    public static LineReaderIterator lines( BufferedReader reader ) {
        return new LineReaderIterator( reader );
    }

    /**
     * combined iteration over to iterables
     * @param ia Any Iterable
     * @param ib Any Iterable
     * @param <A> Any Type
     * @param <B> Any Type
     * @return An Iterator over both Iterables
     */
    public static <A,B> Parallel<A,B> parallel( Iterable<A> ia, Iterable<B> ib ) {
        return new Parallel<A,B>(ia,ib);
    }

    /**
     * combined iteration over to iterables
     * @param ia Any Array
     * @param ib Any Iterable
     * @param <A> Any Type
     * @param <B> Any Type
     * @return An Iterator over both Iterables
     */
    public static <A,B> Parallel<A,B> parallel( A[] ia, Iterable<B> ib ) {
        return new Parallel<A,B>( Arrays.asList(ia),ib);
    }

    /**
     * Adds a counter to a new for loop
     * @param it Any Iterable
     * @param <A> Any Type
     * @return A Counter
     */
    public static <A> Count<A> count( Iterable<A> it ) {
        return Count.count( it );
    }

    /**
     * Adds a counter to a new for loop
     * @param it Any Array
     * @param <A> Any Type
     * @return An ArrayCounter
     */
    public static <A> ArrayCount<A> count( A[] it ) {
        return new ArrayCount( it );
    }

}
