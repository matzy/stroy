package org.openCage.comphy;

import org.openCage.lang.Listeners;

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
    private Listeners<Object> observers = new Listeners<Object>();
    private String elementKey;
    private Dereadalizer<T> elemDeread;

    public ListProperty(String elementKey, Dereadalizer<T> elemDeread) {
        this.elementKey = elementKey;
        this.elemDeread = elemDeread;
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
        s.addObserver(this);
        return ret;
    }

    private void announceChange() {
        observers.shout( null );
    }

    @Override
    public boolean remove(Object o) {
        boolean ret = list.remove(o);
        announceChange();
        return ret;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean ret = list.addAll(c);
        announceChange();
        return ret;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean ret = list.addAll(index,c);
        announceChange();
        return ret;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ret = list.removeAll(c);
        announceChange();
        return ret;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addObserver(Observer ob) {
        observers.add( ob );
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
    public Void call(Object o) {
        observers.shout(null);
        return null;
    }
}
