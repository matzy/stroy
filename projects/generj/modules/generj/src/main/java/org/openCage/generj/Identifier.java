package org.openCage.generj;

import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: spf
 * Date: 10.01.11
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class Identifier {

    // http://java.sun.com/docs/books/jls/third_edition/html/lexical.html#3.9

    public static Pattern keyword = Pattern.compile(
            "abstract|continue|for|new|switch|" +
            "assert|default|if|package|synchronized|" +
            "boolean|do|goto|private|this|" +
            "break|double|implements|protected|throw|" +
            "byte|else|import|public|throws|" +
            "case|enum|instanceof|return|transient|" +
            "catch|extends|int|short|try|" +
            "char|final|interface|static|void|" +
            "class|finally|long||strictfp|volatile|" +
            "const|float|native|super|while");

    public static Pattern pseudoKeyword = Pattern.compile("true|false|null");

    // Returns true if id is a legal Java identifier.
    public static boolean isJavaIdentifier( String id) {
        if (id.length() == 0 || !Character.isJavaIdentifierStart(id.charAt(0))) {
            return false;
        }

        for (int i = 1; i < id.length(); i++) {
            if (!Character.isJavaIdentifierPart(id.charAt(i))) {
                return false;
            }
        }

        if ( keyword.matcher( id ).matches() ) {
            return false;
        }

        if ( pseudoKeyword.matcher( id ).matches() ) {
            return false;
        }

        return true;
    }
}
