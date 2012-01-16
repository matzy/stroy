//package org.openCage.lispaffair;
//
//import java.util.*;
//
///**
// * Created by IntelliJ IDEA.
// * User: stephan
// * Date: 11/30/11
// * Time: 9:57 PM
// * To change this template use File | Settings | File Templates.
// */
//public class LishpList implements List<Object>{
//
//    private ArrayList<Object> inner = new ArrayList<Object>();
//    private int from;
//    private int len;
//
//    public static LishpList create( Object ... els ) {
//        return new LishpList( Arrays.asList( els ));
//    }
//
//    public LishpList() {
//        inner = new ArrayList<Object>();
//        from = 0;
//        len = 0;
//    }
//
//    public LishpList( List other  ) {
//        this( other, 0, other.size());
//    }
//
//    public LishpList( List other, int from, int len) {
//
//        if ( other instanceof LishpList ) {
//            this.inner = ((LishpList)other).inner;
//            this.from = ((LishpList)other).from + from;
//            this.len = len;
//        } else if ( other instanceof ArrayList ) {
//            this.inner = (ArrayList<Object>) other;
//            this.from = from;
//            this.len = len;
//        } else {
//            this.inner = new ArrayList<Object>();
//            this.from = from;
//            this.len = len;
//
//            inner.addAll( other );
//        }
//    }
//
//
//    public int size() {
//        return len;
//    }
//
//    public boolean isEmpty() {
//        return len == 0;
//    }
//
//    public boolean contains(Object o) {
//        return false;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public Iterator<Object> iterator() {
//        return new LishpIterator( this );
//    }
//
//    public Object[] toArray() {
//        throw new Error( "not impl" );
//    }
//
//    public <T> T[] toArray(T[] ts) {
//        throw new Error( "not impl" );
//    }
//
//    public boolean add(Object o) {
//        throw new Error( "immutable list" );
//    }
//
//    public boolean remove(Object o) {
//        throw new Error( "immutable list" );
//    }
//
//    public boolean containsAll(Collection<?> objects) {
//        return false;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public boolean addAll(Collection<? extends Object> objects) {
//        throw new Error( "immutable list" );
//    }
//
//    public boolean addAll(int i, Collection<? extends Object> objects) {
//        throw new Error( "immutable list" );
//    }
//
//    public boolean removeAll(Collection<?> objects) {
//        throw new Error( "immutable list" );
//    }
//
//    public boolean retainAll(Collection<?> objects) {
//        throw new Error( "immutable list" );
//    }
//
//    public void clear() {
//        throw new Error( "immutable list" );
//    }
//
//    public Object get(int i) {
//        if ( i >= len ) {
//            throw new IndexOutOfBoundsException( "" + i + " > " + len);
//        }
//
//        return inner.get( i + from );
//    }
//
//    public Object set(int i, Object o) {
//        throw new Error( "immutable list" );
//    }
//
//    public void add(int i, Object o) {
//        throw new Error( "immutable list" );
//    }
//
//    public Object remove(int i) {
//        throw new Error( "immutable list" );
//    }
//
//    public int indexOf(Object o) {
//        return 0;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public int lastIndexOf(Object o) {
//        return 0;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public ListIterator<Object> listIterator() {
//        return new LishpIterator( this );
//    }
//
//    public ListIterator<Object> listIterator(int i) {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public List<Object> subList(int i, int i1) {
//        return new LishpList( this, i, i1);
//    }
//}
