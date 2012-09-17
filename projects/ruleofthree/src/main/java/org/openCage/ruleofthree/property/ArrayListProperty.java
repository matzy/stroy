package org.openCage.ruleofthree.property;

import org.openCage.kleinod.collection.Forall;
import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.lambda.F2;
import org.openCage.kleinod.observe.VoidListenerControl;
import org.openCage.kleinod.observe.VoidListeners;
import org.openCage.ruleofthree.Three;
import org.openCage.ruleofthree.jtothree.JToThree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.openCage.ruleofthree.Threes.THREE;
import static org.openCage.ruleofthree.property.ListenerControls.listenerControl;

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

public class ArrayListProperty<T> extends ArrayList<T> implements ListProperty<T> {

    private VoidListeners observers = new VoidListeners();


    // TODO better iterator
//    @Override
//    public Iterator<T> iterator() {
//        return list.iterator();
//    }


    @Override
    public boolean add(T s) {
        boolean ret = super.add(s);
        announceChange();
        listenerControl(s).add(this);
        return ret;
    }

    private void announceChange() {
        observers.shout();
    }

    @Override
    public boolean remove(Object o) {
        boolean ret = super.remove(o);
        if ( ret ) {
            listenerControl(o).remove(this);
            announceChange();
        }
        return ret;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean ret = super.addAll(c);
        for( T elem : c ) {
            listenerControl(elem).add(this);
        }
        announceChange();
        return ret;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean ret = super.addAll(index,c);
        for( T elem : c ) {
            listenerControl(elem).add(this);
        }
        announceChange();
        return ret;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ret = super.removeAll(c);
        for( T elem : (Collection<T>)c ) {
            listenerControl(elem).removeCompletely(this);
        }
        announceChange();
        return ret;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for ( T elem : this ) {
            if ( !c.contains(elem)) {
                listenerControl(elem).remove(this);
            }
        }
        boolean ret = super.retainAll(c);
        observers.shout();
        return ret;
    }

    @Override
    public void clear() {
        for ( T elem : this ) {
            listenerControl(elem).remove(this);
        }
        super.clear();
        announceChange();
    }


    @Override
    public T set(int index, T element) {
        T ret = super.set(index, element);
        announceChange();
        return ret;
    }

    @Override
    public void add(int index, T element) {
        super.add(index, element);
        announceChange();
    }

    @Override
    public T remove(int index) {
        T ret = super.remove(index);
        announceChange();
        return ret;
    }

    // TODO
//    @Override
//    public ListIterator<T> listIterator() {
//        return list.listIterator();
//    }

    // TODO
//    @Override
//    public ListIterator<T> listIterator(int index) {
//        return list.listIterator(index);
//    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new Error("impl me");
    }


    @Override
    public Three toThree() {
        JToThree jtothree = new JToThree();
        List<Three> ret = new ArrayList<Three>();
        for( T elem : this ) {
            ret.add(jtothree.toThree(elem));
        }
        return THREE(ret);
    }


    @Override
    public void call() {
        observers.shout();
    }

    @Override
    public VoidListenerControl getListenerControl() {
        return observers;
    }

    @Override
    public String toString() {
        return Forall.forall(this).join("[",new F2<String, String, T>() {
            @Override
            public String call(String s, T t) {
                return s + t.toString();
            }
        }).end(new F1<String, String>() {
            @Override
            public String call(String s) {
                return s+"]";
            }
        }).sep( new F1<String, String>() {
            @Override
            public String call(String s) {
                return s+", ";
            }
        }).
                go();
    }

}
