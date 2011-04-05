package org.openCage.generj;


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

public class Typ {
    private String name;
    private Typ of;
    public static Typ STRING = TYP("String");
    public static Typ VOID = new Typ("void", false );
    public static Typ INT = new Typ("int", false );
    public static Typ BOOLEAN = new Typ("boolean", false );

    public Typ(String name) {
        this.name = name;

        if ( !Identifier.isJavaIdentifier( name )) {
            throw new IllegalArgumentException("not legal java type: " + name);
        }
    }

    private Typ(String name, boolean foo ) {
        this.name = name;
    }

    public Typ(String name, Typ of) {
        this( name );
        this.of = of;
    }

    public static Typ TYP( String name ) {
        return new Typ( name );
    }

    public static Typ array( String name ) {
        return new Typ( name + " ...");  // TODO legal name
    }

    public static Typ of( String name, Typ of ) {
        return new Typ( name, of );
    }

    public static Typ TYPOF( String name, Typ of ) {
        return new Typ( name, of );
    }

    public static Typ LIST( Typ of ) {
        return new Typ( "List", of );
    }

    public static Typ ARRAYLIST( Typ of ) {
        return new Typ( "ArrayList", of );
    }

    public String toString() {
        if ( of == null ) {
            return name;
        }

        return name + "<" + of.toString() + ">";
    }

    public String getName() {
        return name;
    }


}
