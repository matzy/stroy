package org.openCage.lang.inc;

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
public class Strng implements Str {

    private final String str;

    public Strng(String str) {
        this.str = str;
        if ( str == null ) {
            throw new IllegalArgumentException( "no null values allowed in Strng" );
        }
    }

    @Override
    public String get() {
        return str;
    }

    @Override
    public int size() {
        return str.length();
    }

    @Override
    public Character charAt(int idx) {
        return str.charAt(idx);
    }

    @Override
    public boolean endsWith(String postfix) {
        return str.endsWith(postfix);
    }

    @Override
    public Str substring(int from, int len) {
        return S(str.substring(from,len));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Strng strng = (Strng) o;

        if (str != null ? !str.equals(strng.str) : strng.str != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return str != null ? str.hashCode() : 0;
    }

    public static Strng valueOf( String str ) {
        return new Strng( str );
    }

    public static Strng S( String str ) {
        return new Strng(str);
    }

    @Override
    public Iterator<Character> iterator() {
        return new StrngIterator(this);
    }

    @Override
    public int compareTo(Str o) {
        return str.compareTo(o.get());
    }

    @Override
    public String toString() {
        return str;
    }

    public static class StrngIterator implements Iterator<Character> {
        private final Str str;
        private int    idx;


        public StrngIterator( Str str ) {
            this.str = str;
            this.idx = 0;
        }

        public boolean hasNext() {
            return str.size() > idx;
        }

        public Character next() {
            Character ch = str.charAt(idx);
            ++idx;
            return ch;
        }

        public void remove() {
            throw new Error( "strings are imutable" );
        }
    }

}
