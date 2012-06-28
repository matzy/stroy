package org.openCage.generj;

import java.util.regex.Pattern;

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
