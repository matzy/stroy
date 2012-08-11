package org.openCage.ruleofthree.property;

import org.openCage.lang.listeners.Observer;
import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;
import org.openCage.ruleofthree.Property;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.ThreeHashMap;
import org.openCage.ruleofthree.ThreeKey;
import org.openCage.ruleofthree.ThreeMap;
import org.openCage.ruleofthree.Threes;
import org.openCage.ruleofthree.jtothree.JToThree;

import java.util.Map;

import static org.openCage.ruleofthree.property.ListenerControls.listenerControl;

//import static org.openCage.ruleofthree.property.ListenerControls.listenerControl;

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

public class HashMapProperty<T> extends ThreeHashMap<T> implements MapProperty<T> {

    private VoidListeners listeners = new VoidListeners();


    @Override
    public T put( ThreeKey key, T val ) {
        T ret = super.put(key, val);
        listenerControl(val).add( this );
        if ( ret != null ) {
            listenerControl(ret).remove(this);
        }
        listeners.shout();
        return ret;
    }

    @Override
    public T remove(Object key) {
        T ret = super.remove(key);
        listeners.shout();
        return ret;
    }


    @Override
    public void putAll(Map<? extends ThreeKey, ? extends T> m) {
        for ( Map.Entry<? extends ThreeKey, ? extends T> pair : m.entrySet()  ) {
            put( pair.getKey(), pair.getValue());
        }
    }

    @Override
    public void clear() {
        for ( T val : super.values()) {
            listenerControl(val).remove(this);
        }
        super.clear();
        listeners.shout();
    }


    @Override
    public Three toThree() {
        JToThree JToThree = new JToThree();
        Three ret = Threes.newMap();
        for ( Map.Entry<ThreeKey,T> pair : super.entrySet() ) {
            ret.getMap().put(pair.getKey(), JToThree.toThree(pair.getValue()));
        }
        return ret;
    }

    @Override
    public void call() {
        listeners.shout();
    }

    @Override
    public VoidListenerControl getListenerControl() {
        return listeners;
    }

}
