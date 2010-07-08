package org.openCage.lang.iterators;

import java.io.BufferedReader;
import java.util.Arrays;

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

public final class Iterators {

    private Iterators() {};

    public static String[] words( String str ) {
        return str.split("( |\t|\r|\n)+");
    }

    public static Iterable<Character> chars( String str ) {
        return new StringIterator(str);
    }

    public static String[] lines( String str ) {
        return str.split( "(\n|\r)+");
    }

    public static LineReaderIterator lines( BufferedReader reader ) {
        return new LineReaderIterator( reader );
    }

    public static <A,B> Parallel<A,B> parallel( Iterable<A> ia, Iterable<B> ib ) {
        return new Parallel<A,B>(ia,ib);
    }

    public static <A,B> Parallel<A,B> parallel( A[] ia, Iterable<B> ib ) {
        return new Parallel<A,B>( Arrays.asList(ia),ib);
    }

    public static <A> Count<A> count( Iterable<A> it ) {
        return Count.count( it );
    }

    public static <A> ArrayCount<A> count( A[] it ) {
        return new ArrayCount( it );
    }

}
