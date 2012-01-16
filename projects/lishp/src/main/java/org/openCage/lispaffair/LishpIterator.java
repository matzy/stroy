//package org.openCage.lispaffair;
//
//import java.util.ListIterator;
//
///**
// * Created by IntelliJ IDEA.
// * User: stephan
// * Date: 12/14/11
// * Time: 12:14 PM
// * To change this template use File | Settings | File Templates.
// */
//public class LishpIterator implements ListIterator<Object> {
//
//    private LishpList list;
//    private int idx;
//
//    public LishpIterator( LishpList list ) {
//        this.list = list;
//        this.idx = 0;
//    }
//
//    public boolean hasNext() {
//        return idx < list.size();
//    }
//
//    public Object next() {
//        return list.get(idx);  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public boolean hasPrevious() {
//        return idx > 0;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public Object previous() {
//        return list.get( idx - 1 );  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public int nextIndex() {
//        return idx;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public int previousIndex() {
//        return idx - 1;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void remove() {
//        throw new Error( "Immutable List" );
//    }
//
//    public void set(Object o) {
//        throw new Error( "Immutable List" );
//    }
//
//    public void add(Object o) {
//        throw new Error( "Immutable List" );
//    }
//}
