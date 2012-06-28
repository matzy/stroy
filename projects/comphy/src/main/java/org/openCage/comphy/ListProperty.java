package org.openCage.comphy;

import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;

import java.util.*;

import static org.openCage.comphy.Readables.R;

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

public class ListProperty<T extends Property> implements Property, List<T>, Observer {

    private List<T> list = new ArrayList<T>();
    private VoidListeners observers = new VoidListeners();
//    private Key elementKey;
//    private Dereadalizer<T> elemDeread;

    public ListProperty() {
//        this.elementKey = elementKey;
//        this.elemDeread = elemDeread;
    }

//    public ListProperty(String elementKey, Dereadalizer<T> elemDeread) {
//        this( new Key(elementKey), elemDeread );
//    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(T s) {
        boolean ret = list.add(s);
        announceChange();
        s.getListenerControl().add(this);
        return ret;
    }

    private void announceChange() {
        observers.shout();
    }

    @Override
    public boolean remove(Object o) {
        boolean ret = list.remove(o);
        if ( ret ) {
            ((T)o).getListenerControl().remove(this);
            announceChange();
        }
        return ret;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean ret = list.addAll(c);
        for( T elem : c ) {
            elem.getListenerControl().add(this);
        }
        announceChange();
        return ret;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean ret = list.addAll(index,c);
        for( T elem : c ) {
            elem.getListenerControl().add(this);
        }
        announceChange();
        return ret;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ret = list.removeAll(c);
        for( T elem : (Collection<T>)c ) {
            elem.getListenerControl().removeCompletely(this);
        }
        announceChange();
        return ret;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for ( T elem : list ) {
            if ( !c.contains(elem)) {
                elem.getListenerControl().remove(this);
            }
        }
        boolean ret = list.retainAll(c);
        observers.shout();
        return ret;
    }

    @Override
    public void clear() {
        for ( T elem : list ) {
            elem.getListenerControl().remove(this);
        }
        list.clear();
        announceChange();
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public T set(int index, T element) {
        T ret = list.set( index, element );
        announceChange();
        return ret;
    }

    @Override
    public void add(int index, T element) {
        list.add( index, element );
        announceChange();
    }

    @Override
    public T remove(int index) {
        T ret = list.remove(index);
        announceChange();
        return ret;
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new Error("impl me");
    }


    @Override
    public Readable toReadable() {
        List<Readable> ret = new ArrayList<Readable>();
        for( T elem : list ) {
            ret.add( elem.toReadable() );
        }
        return R(ret);
    }


    @Override
    public void call() {
        observers.shout();
    }

    @Override
    public VoidListenerControl getListenerControl() {
        return observers;
    }
}
