package org.openCage.util.collection;

import org.openCage.lang.structure.T2;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

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


public class Maps {

    public static class MapHelper<S,T> {
        Map<S,T> map;

        public MapHelper( Map<S,T> map ) {
            this.map = map;
        }

        public Map<S,T> get() {
            return map;
        }

        public MapHelper<S,T> put( S s, T t ) {
            map.put( s, t);
            return this;
        }
    }

    public static <S,T> Map<S,T> create( List<T2<S,T>> listofpairs ) {
        Map<S,T> ret = new HashMap<S,T>();

        for ( T2<S,T> pair : listofpairs ) {
            ret.put(  pair.i0, pair.i1 );
        }

        return ret;
    }

    public static <S,T> MapHelper<S,T> create( S s, T t ) {
        Map<S,T> map = new HashMap<S,T>();
        map.put(s,t);
        return new MapHelper<S,T>(map);
    }
}
