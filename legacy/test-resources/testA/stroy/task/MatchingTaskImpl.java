//package org.openCage.stroy.task;
//
//import org.openCage.util.lang.Once;
//import org.openCage.stroy.id.Id;
//import org.openCage.util.logging.Log;
//
//import java.util.*;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1/GPL 2.0
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
//* All Rights Reserved.
//*
//* Contributor(s):
//*
//* Alternatively, the contents of this file may be used under the terms of
//* either the GNU General Public License Version 2 or later (the "GPL"),
//* in which case the provisions of the GPL are applicable instead
//* of those above. If you wish to allow use of your version of this file only
//* under the terms of either the GPL, and not to allow others to
//* use your version of this file under the terms of the MPL, indicate your
//* decision by deleting the provisions above and replace them with the notice
//* and other provisions required by the GPL. If you do not delete
//* the provisions above, a recipient may use your version of this file under
//* the terms of any one of the MPL, the GPL.
//*
//***** END LICENSE BLOCK *****/
//
//public class MatchingTaskImpl<T extends Id> implements MatchingTask<T> {
//
//    private final Once<T> firstLeft     = new Once<T>();
//    private final Once<T> firstRight    = new Once<T>();
//    private Map<String,T> lefts         = new HashMap<String,T>();
//    private Map<String,T> matchedRights = new HashMap<String,T>();
//    private List<T>       unmatchedRights     = new ArrayList<T>();
//
//
//    public void addLeft(T obj) {
//
//        if ( obj.getId() == null ) {
//            throw new Error( "a source must have an id" );
//        }
//
//        lefts.put( obj.getId(), obj );
//    }
//
//    public void addRight(T obj) {
//
//        assert( !obj.isOriginal() );
//
//        unmatchedRights.add(obj);
//    }
//
//    public Collection<T> getUnmatchedLeft() {
//        List<T> unmatched = new ArrayList<T>();
//        for ( T src : lefts.values()) {
//            if ( !matchedRights.containsKey( src.getId())) {
//                unmatched.add( src );
//            }
//        }
//        return unmatched;
//    }
//
//    public Collection<T> getUnmatchedRight() {
//        List<T> copy = new ArrayList<T>();
//        copy.addAll(unmatchedRights);
//
//        return copy;
//    }
//
//    public Collection<T> getMatchedLeft() {
//        List<T> matched = new ArrayList<T>();
//
//        for ( T m : matchedRights.values() ) {
//            matched.add( lefts.get( m.getId() ));
//        }
//
//        return matched;
//    }
//
//    public Collection<T> getMatchedRight() {
//        return matchedRights.values();
//    }
//
//    public boolean isMatched(T obj) {
//
//        if ( obj.getId() == null ) {
//            return false;
//        }
//
//        return matchedRights.containsKey( obj.getId() );
//    }
//
//    public T getMatch(T obj) {
//
//        if ( obj.isOriginal() ) {
//            return matchedRights.get( obj.getId() );
//        }
//
//        if ( obj.getId() == null ) {
//            return null;
//        }
//
//        return lefts.get( obj.getId() );
//    }
//
//    public void match(T src, T tgt, double quality ) {
//        assert( tgt.getId() != null );
//        assert( src.isOriginal() );
//        assert( !tgt.isOriginal() );
//
//        tgt.setId( src.getId(), quality );
//        matchedRights.put( src.getId(), tgt );
//
//        unmatchedRights.remove( tgt );
//
//        Log.fine( "match: " + src.toString() + " -> " + tgt.toString()  );
//
//    }
//
//    public void breakMatch(T obj ) {
//
//        throw new Error( "break not impl" );
//
////        if ( !isMatched( obj )) {
////            return;
////        }
////
////        T other = getMatch( obj );
////
////        if ( obj.isOriginal() ) {
////            matchedRights.remove( obj.getId() );
////            unmatchedRights.add( other);
////            other.unsetId();
////            return;
////        }
////
////        obj.unsetId();
////        matchedRights.remove( obj.getId() );
////        unmatchedRights.add( obj );
//    }
//
//    public T getLeftRoot() {
//        return firstLeft.get();
//    }
//
//    public T getRightRoot() {
//        return firstRight.get();
//    }
//
//    public void setRoots(T one, T two) {
//        firstLeft.set( one );
//        firstRight.set( two );
//    }
//
//    public void remove(T node) {
//        // TODO cleanup
//        if ( node.isOriginal() ) {
//            lefts.remove( node.getId() );
//            matchedRights.remove( node.getId() );
//        } else {
//            unmatchedRights.remove( node );
//        }
//    }
//
//    public double getMatchQuality(T obj) {
//        return obj.getQuality();
//    }
//
//}
