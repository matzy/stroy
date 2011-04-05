package org.openCage.generj;

import org.openCage.lang.Strings;

import java.util.regex.Pattern;

import static org.openCage.generj.Dot.DOT;


/**
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
 *     * Neither the name of the <organization> nor the
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
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.public interface Statement {
 */

public class NameExpr implements Callble {

    private final String str;

    public NameExpr( String str ) {

        if ( !Identifier.isJavaIdentifier(str) ) {
            throw new IllegalArgumentException( "not a valid java name: <" + str + ">" );
        }

        this.str = str;
    }

    private NameExpr( String str, int i ) {

        this.str = str;
    }

    public Dot dot( NameExpr name ) {
        return DOT( this, name );
    }

    public Dot dot( String name ) {
        return dot( NAME(name));
    }


    public String toString() {
        return str;
    }

    public static NameExpr n( String str ) {
        return new NameExpr( str );
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public static NameExpr NAME( String name ) {
        return new NameExpr( name );
    }

    public static NameExpr SETTER( String name ) {
        return new NameExpr( "set" + Strings.toFirstUpper( name ));
    }

    public static NameExpr ADDER( String name ) {
        return new NameExpr( "add" + Strings.toFirstUpper( name ));
    }

    public static NameExpr GETTER( String name ) {
        return new NameExpr( "get" + Strings.toFirstUpper( name ));
    }

    public static NameExpr NULL = new NameExpr( "null", 0 );
    public static NameExpr TRUE = new NameExpr( "true", 0 );
    public static NameExpr FALSE = new NameExpr( "false", 0 );
    public static NameExpr THIS = new NameExpr( "this", 0 );

}
