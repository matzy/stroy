//package org.openCage.stroy.task;
//
//import org.openCage.util.lang.Once;
//import org.openCage.stroy.id.Id;
//
//import java.util.*;
//
///**
// * stroy, a differencing tool
// * Copyright (C) Dec 2, 2007 Stephan Pfab
// * <p/>
// * This library is free software; you can redistribute it and/or
// * modify it under the terms of the GNU Lesser General Public
// * License as published by the Free Software Foundation; either
// * version 2.1 of the License, or (at your option) any later version.
// * <p/>
// * This library is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// * Lesser General Public License for more details.
// * <p/>
// * You should have received a copy of the GNU Lesser General Public
// * License along with this library; if not, write to the Free Software
// * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
// */
//public class MatchingTaskLists<T extends Id> implements MatchingTask<T>{
//
//    private final Once<T> firstSource      = new Once<T>();
//    private final Once<T> firstTarget      = new Once<T>();
//    private List<T> lefts                = new ArrayList<T>();
//    private List<T> rights                = new ArrayList<T>();
//
//    private Map<T,T> matches  = new HashMap<T,T>();
//
////    private Map<String,T> matchedTargets   = new HashMap<String,T>();
////    private List<T> unmatchedTargets = new ArrayList<T>();
//
//
//    public void addLeft(T obj) {
//        lefts.add( obj );
//    }
//
//    public void addRight(T obj) {
//        rights.add( obj );
//    }
//
//    public Collection<T> getUnmatchedLeft() {
//        List<T> ret = new ArrayList<T>();
//
//        for ( T left : lefts ) {
//            if ( !matches.containsKey( left )) {
//                ret.add( left );
//            }
//        }
//        return ret;
//    }
//
//    public Collection<T> getUnmatchedRight() {
//        List<T> ret = new ArrayList<T>();
//
//        for ( T right : rights ) {
//            if ( !matches.containsValue( right )) {
//                ret.add( right );
//            }
//        }
//        return ret;
//    }
//
//    public Collection<T> getMatchedLeft() {
//        return matches.keySet();
//    }
//
//    public Collection<T> getMatchedRight() {
//        return matches.values();
//    }
//
//    public boolean isMatched(T obj) {
//        return matches.containsKey( obj ) || matches.containsValue( obj );
//    }
//
//    public T getMatch(T obj) {
//        T other = matches.get(obj);
//        if ( other != null ) {
//            return other;
//        }
//
////        for ( T left : matches)
//
//        return null;
//    }
//
//    public void match(T src, T tgt, double quality) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void breakMatch(T src) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public T getLeftRoot() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public T getRightRoot() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void setRoots(T src, T tgt) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void remove(T node) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public double getMatchQuality(T obj) {
//        return 0;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//}
