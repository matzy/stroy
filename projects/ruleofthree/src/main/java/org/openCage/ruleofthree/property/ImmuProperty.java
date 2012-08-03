package org.openCage.ruleofthree.property;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;
import org.openCage.ruleofthree.Property;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.jtothree.JtoThree;

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

/**
 * Property for Object which can not be changed
 * Note: This is just a convention, Java can not enforce this
 * @param <T>
 */
public class ImmuProperty<T> implements Property {

    private VoidListeners listeners = new VoidListeners();
    private T obj;

    public ImmuProperty( T obj) {
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    public void set(T val) {
        if ( obj.equals( val )) {
            // no change => no shout
            return;
        }
        this.obj = val;
        listeners.shout();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImmuProperty that = (ImmuProperty) o;

        if (obj != null ? !obj.equals(that.obj) : that.obj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return obj != null ? obj.hashCode() : 0;
    }


    @Override
    public VoidListenerControl getListenerControl() {
        return listeners;
    }

    @Override
    public Three toThree() {
        return new JtoThree().toThree(obj);
    }

    @Override
    public String toString() {
        return obj.toString();
    }
}
