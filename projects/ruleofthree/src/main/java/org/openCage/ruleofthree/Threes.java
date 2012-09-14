package org.openCage.ruleofthree;

import org.openCage.ruleofthree.jtothree.JToThree;

import java.util.List;
import java.util.Map;


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

public class Threes implements Three {


    private final Object obj;

    @Override
    public boolean isString() {
        return obj instanceof String;
    }

    @Override
    public String getString() {
        if ( !isString()) {
            throw new IllegalArgumentException( "not a string " + obj );
        }
        return (String)obj;
    }

    public boolean isMap() {
        return obj instanceof Map;
    }

    @Override
    public ThreeMap<Three> getMap() {
        if ( !isMap()) {
            throw new IllegalArgumentException( "not a map " + obj );
        }
        return (ThreeMap<Three>) obj;
    }

    @Override
    public boolean isList() {
        return obj instanceof List;

    }

    @Override
    public List<Three> getList() {
        if ( !isList()) {
            throw new IllegalArgumentException( "not a list " + obj );
        }
        return (List<Three>) obj;
    }

    @Override
    public Three put(ThreeKey key, Object val) {
        if ( val instanceof Three ) {
            getMap().put( key, (Three) val);
        } else {
            getMap().put( key, new JToThree().toThree(val));
        }
        return this;
    }

    @Override
    public Three put(String key, Object val) {
        return put( new ThreeKey(key), val );
    }


    private Threes(Object obj) {
        this.obj = obj;
    }

    public static Threes THREE( String str ) {
        return new Threes(str);
    }


    public static Threes THREE( Map<ThreeKey,Three> map ) {
        return new Threes(map);
    }

    public static Threes THREE( List<? extends Three> lst ) {
        return new Threes(lst);
    }

    public static Three newMap() {
        return THREE(new ThreeHashMap<Three>());
    }

    @Override
    public String toString() {
        return obj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Threes readables = (Threes) o;

        if (obj != null ? !obj.equals(readables.obj) : readables.obj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return obj != null ? obj.hashCode() : 0;
    }
}
