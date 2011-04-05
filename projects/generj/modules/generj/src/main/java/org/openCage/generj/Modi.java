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

public class Modi {

    private String mod;
    private Class_ aClass;

    public Modi( String typ, Class_ aClass) {
        this.mod = typ;
        this.aClass = aClass;
    }

    public Method_ method( Typ retType, String name ) {
        Method_ mes = new Method_(aClass, mod, retType, name );

        aClass.methods.add( mes );

        return mes;
    }

    public Method_ method( String name ) {
        Method_ mes = new Method_(aClass, mod, name );

        aClass.methods.add( mes );

        return mes;
    }


    public Field<Class_> field( Typ typ, NameExpr name ) {
        Field<Class_> field = new Field<Class_>(aClass, mod, typ, name );
        aClass.fields.add(field);
        return field;
    }

    public Method_ cnstr() {
        Method_ mes = new Method_(aClass, mod, null, aClass.name.getName() );

        aClass.methods.add( mes );

        return mes;
    }

    public Class_ clazz(Typ typ) {
        Class_ subClass = new Class_(aClass, typ );
        subClass.setModi( mod );
        aClass.innerClazzes.add(subClass);
        return subClass;
    }


    public Modi _static() {
        mod += " static ";
        return this;
    }

    public Modi override() {
        mod = "@Override " + mod;
        return this;
    }
}
