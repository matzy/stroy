package org.openCage.kleinod.collection;


import org.openCage.kleinod.lambda.F1;
import org.openCage.kleinod.lambda.F2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/23/12
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImmuList<T> implements Iterable<T>{

    private final ArrayList<T> list;

    public ImmuList(ArrayList<T> list) {
        this.list = list;
    }

    public ImmuList() {
        this.list = new ArrayList<T>();
    }

    public ImmuList<T> add( T elem ) {
        ImmuList<T> ret = new ImmuList<T>((ArrayList<T>) list.clone());
        ret.list.add( elem );

        return ret;
    }

    @Override
    public Iterator<T> iterator() {
        // todo remove
        return list.iterator();
    }

    @Override
    public String toString() {
        return Forall.forall( this ).join( "[", new F2<String, String, T>() {
            @Override
            public String call(String s, T t) {
                return s + t.toString();
            }} ).
                end(new F1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "]";
                    }
                }).

                sep( new F1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + ",";
                    }
                }).go();
    }

    public T get(int i) {
        return list.get(i);
    }
}
