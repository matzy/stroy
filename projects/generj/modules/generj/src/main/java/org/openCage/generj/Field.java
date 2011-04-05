package org.openCage.generj;

import static org.openCage.generj.NewExpr.NEW;

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

public class Field<T> implements Statement {
    private NameExpr name;
    private T clazz;
    private String mod;
    private Typ typ;
    private Expr init;

    public Field(T clazz, String mod, Typ typ, NameExpr name) {
        this.name = name;
        this.clazz = clazz;
        this.mod = mod;
        this.typ = typ;
    }

    public T c() {
        return clazz;
    }

    public String toString() {
        String ret =  (mod.equals("") ? "" : (mod + " ")) + typ + " " + name;

        if ( init != null ) {
            ret += " = " + init;
        }

        ret += ";";

        return ret;
    }

    public T init( Expr ini ) {
        this.init = ini;
        return clazz;
    }

    @Override
    public String toString(String prefix) {
        return prefix + toString();
    }

    public T defaultInit() {
        return init( NEW(typ));
    }
}
