package org.openCage.comphy;

import org.openCage.lang.listeners.VoidListenerControl;
import org.openCage.lang.listeners.VoidListeners;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 1:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListProperty<T extends Property> implements Property, List<T>, Observer {

    private List<T> list = new ArrayList<T>();
    private VoidListeners observers = new VoidListeners();
    private Key elementKey;
    private Dereadalizer<T> elemDeread;

    public ListProperty(Key elementKey, Dereadalizer<T> elemDeread) {
        this.elementKey = elementKey;
        this.elemDeread = elemDeread;
    }

    public ListProperty(String elementKey, Dereadalizer<T> elemDeread) {
        this( new Key(elementKey), elemDeread );
    }
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
        RList ret = new RList( elementKey );
        for( T elem : list ) {
            ret.add( elem.toReadable() );
        }
        return ret;
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
