package org.openCage.ruleofthree.jtothree;

import org.openCage.kleinod.collection.Ref;
import org.openCage.kleinod.lambda.F1;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.ThreeHashMap;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.ThreeMap;
import org.openCage.ruleofthree.Threeable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.openCage.ruleofthree.Threes.THREE;


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

public class JToThree {

    private Basics basics = Basics.get();

    public JToThree() {
    }


    public <T> Three toThree( T obj ) {

        if ( obj instanceof Threeable) {
            return ((Threeable)obj).toThree();
        }

        Class clazz = obj.getClass();

        if ( clazz.isEnum() ) {
            return THREE(obj.toString());
        }

        if (List.class.isAssignableFrom( clazz )) {
            return toThreeList((List) obj);
        }


        if (ThreeMap.class.isAssignableFrom( clazz )) {
            return toThreeThreeMap((ThreeMap<Object>) obj);
        }

        if ( Map.class.isAssignableFrom( clazz )) {
            return toThreeVanillaMap((Map<Object, Object>) obj);

        }

        if ( Ref.class.isAssignableFrom( clazz )) {
            return toThree( ((Ref)obj).get());
        }

        F1<Three, T> fct = (F1<Three, T>) basics.getToThree( clazz);

        if ( fct != null ) {
            return fct.call(obj);
        }

        throw new IllegalArgumentException( "not known to be threeable " + clazz + " obj " + obj );

    }

    private <T> Three toThreeVanillaMap(Map<Object, Object> obj) {
        List<Three> ret = new ArrayList<Three>();

        for ( Map.Entry<Object,Object> entry : obj.entrySet() ) {
            List<Three> pair = new ArrayList<Three>();
            pair.add( toThree(entry.getKey()));
            pair.add( toThree( entry.getValue()));

            ret.add( THREE( pair ));
        }

        return THREE(ret);
    }

    private <T> Three toThreeThreeMap(ThreeMap<Object> obj) {
        Map<ThreeKey,Three> ret = new ThreeHashMap<Three>();

        for ( ThreeMap.Entry<ThreeKey, Object> entry : obj.entrySet() ) {
            ret.put( entry.getKey(), toThree( entry.getValue()));
        }

        return  THREE(ret);
    }

    private <T> Three toThreeList(List obj) {
        List<Three> rl = new ArrayList<Three>();
        for ( Object elem : obj) {
            rl.add( toThree(elem));
        }

        return THREE(rl);
    }

}
