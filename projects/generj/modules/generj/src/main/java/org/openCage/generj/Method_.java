package org.openCage.generj;


import org.openCage.lang.Strings;
import org.openCage.lang.functions.F1;
import org.openCage.lang.structure.T2;

import java.util.ArrayList;
import java.util.List;

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

public class Method_ {
    private Class_ aClass;
    private Typ retType;
    private String name;
    private String mod;

    private List<T2<Typ,NameExpr>> args = new ArrayList<T2<Typ, NameExpr>>();

    private Block<Method_> body;
    private List<String> typeNames = new ArrayList<String>();
    private List<Typ> thrws = new ArrayList<Typ>();

    public Method_(Class_ aClass, String mod, Typ retType, String name) {
        this.aClass = aClass;
        this.retType = retType;
        this.name = name;
        this.mod = mod;
    }

    public Method_(Class_ aClass, String mod, String name) {
        this.aClass = aClass;
        this.retType = Typ.VOID;
        this.name = name;
        this.mod = mod;
    }

    public String toString() {
        return toString("");
    }

    public String toString( String prefix ) {
        String ret = prefix + mod + " ";

        ret += Strings.join( typeNames ).prefix( "<").postfix("> ");

        ret += (retType != null ? retType : "" )+ " " + name + "( ";


        ret += Strings.join( args ).trans( new F1<String, T2<Typ, NameExpr>>() {
                    public String call(T2<Typ, NameExpr> arg) {
                        return arg.i0 + " " + arg.i1;
                    }
                });

        ret += " )";

        ret += Strings.join( thrws ).prefix( " throws ").postfix(" ");

        ret += body.toString( prefix  );

        return ret;
    }


    public Class_ c() {
        return aClass;
    }

    public Method_ arg( Typ typ, NameExpr name ) {
        this.args.add( T2.valueOf(typ,name));
        return this;
    }



    public Block<Method_> body() {
        if ( body == null ) {
            body = new Block<Method_>( this );
        }
        return body;
    }

    public Method_ gen(String typeName) {
        typeNames.add( typeName );
        return this;
    }

    public Method_ throws_(Typ typ) {
        this.thrws.add( typ );
        return this;
    }
}
