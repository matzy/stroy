package org.openCage.withResource.protocol;

import org.openCage.withResource.impl.StringIterator;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Oct 17, 2009
 * Time: 12:25:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Iterators {

    public static String[] words( String str ) {
        return str.split(" ");
    }

    public static Iterable<Character> chars( String str ) {
        return new StringIterator(str);
    }

    public static String[] lines( String str ) {
        return str.split( "\n|\r");
    }
}
