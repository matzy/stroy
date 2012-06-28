package org.openCage.comphy;

import org.openCage.lang.inc.GHashMap;
import org.openCage.lang.inc.GMap;
import org.openCage.lang.inc.Str;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openCage.lang.inc.Strng.S;

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

public class Readables implements Readable {


    // TODO howto extend
    private static ToAndFro toAndFro = new ToAndFro();

    private final Object obj;

    @Override
    public boolean isStr() {
        return obj instanceof Str;
    }

    @Override
    public Str getStr() {
        if ( !isStr()) {
            throw new IllegalArgumentException( "not a str " + obj );
        }
        return (Str)obj;
    }

    public boolean isMap() {
        return obj instanceof GMap;
    }

    @Override
    public GMap<Str, Readable> getMap() {
        if ( !isMap()) {
            throw new IllegalArgumentException( "not a map " + obj );
        }
        return (GMap<Str, Readable>) obj;
    }

    @Override
    public boolean isList() {
        return obj instanceof List;

    }

    @Override
    public List<Readable> getList() {
        if ( !isList()) {
            throw new IllegalArgumentException( "not a list " + obj );
        }
        return (List<Readable>) obj;
    }

    @Override
    public Readable put(Str key, Object val) {
        getMap().put( key, toAndFro.toReadable( val ));
        return this;
    }


    private Readables(Object obj) {
        this.obj = obj;
    }

    public static Readables R( Str str ) {
        return new Readables(str);
    }

    public static Readables R( String str ) {
        return R(S(str));
    }

    public static Readables R(GMap<Str,Readable> map ) {
        return new Readables(map);
    }

    public static Readables R( List<Readable> lst ) {
        return new Readables(lst);
    }

    public static Readable C( Str key, Readable val ) {
        GMap<Str,Readable> ret = new GHashMap<Str, Readable>();
        ret.put( key,val);
        return R(ret);
    }

    public static Readable Map() {
        return R(new GHashMap<Str,Readable>());
    }

    public Readable put( Str key, Readable val ) {
        getMap().put(key,val);
        return this;
    }

    @Override
    public String toString() {
        return obj.toString();
    }

    public static Readable toReadable( Object obj ) {
        return toAndFro.toReadable( obj );
    }
}
