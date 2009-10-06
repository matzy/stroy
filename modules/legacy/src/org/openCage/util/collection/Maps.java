package org.openCage.util.collection;


import java.util.Map;
import java.util.List;
import java.util.HashMap;
import org.openCage.lang.protocol.tuple.T2;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
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
