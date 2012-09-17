package org.openCage.stroy.task;

import org.openCage.kleinod.collection.Once;
import org.openCage.kleinod.collection.T3;
import org.openCage.stroy.diff.ContentDiff;
import org.openCage.util.logging.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

public class MatchingTaskNeutral<T> implements MatchingTask<T>{

    private List<NodeChangeListener> listeners = new ArrayList<NodeChangeListener>();


    class Quality {
        public T           target;
        public double      quality;
        public ContentDiff diff;

        public Quality() {
            target  = null;
            quality = 0.0;
            diff    = ContentDiff.unknown;
        }

        public void init() {
            target  = null;
            quality = 0.0;
            diff    = ContentDiff.unknown;
    }


    }

    private final Once<T> leftRoot  = new Once<T>( null );
    private final Once<T> rightRoot = new Once<T>( null );

    private Map<T, Quality> left2Right   = new HashMap<T, Quality>();
    private Map<T, T>       right2Left   = new HashMap<T, T>();


    public MatchingTaskNeutral() {
    }

    public void addLeft(T obj) {
        if ( !left2Right.containsKey( obj )) {
            left2Right.put( obj, null );
        }
    }

    public void addRight(T obj) {
        if ( !right2Left.containsKey( obj )) {
            right2Left.put( obj, null);
        }
    }

    public Collection<T> getUnmatchedLeft() {

        List<T> ret = new ArrayList<T>();
        for ( T val : left2Right.keySet() ) {
            if ( left2Right.get(val) == null ) {
                ret.add( val );
            }
        }

        return ret;
    }

    public Collection<T> getUnmatchedRight() {
        List<T> ret = new ArrayList<T>();
        for ( T val : right2Left.keySet() ) {
            if ( right2Left.get(val) == null ) {
                ret.add( val );
            }
        }

        return ret;
    }

    public Collection<T> getMatchedLeft() {
        List<T> ret = new ArrayList<T>();
        for ( T val : left2Right.keySet() ) {
            if ( left2Right.get(val) != null ) {
                ret.add( val );
            }
        }

        return ret;
    }

    public Collection<T> getMatchedRight() {
        List<T> ret = new ArrayList<T>();
        for ( T val : right2Left.keySet() ) {
            if ( right2Left.get(val) != null ) {
                ret.add( val );
            }
        }

        return ret;
    }

    public boolean isMatched(T obj) {

        if ( left2Right.containsKey( obj )) {
            return left2Right.get(obj) != null;
        }

        return right2Left.get( obj ) != null;
    }

    public T getMatch(T obj) {
        if ( left2Right.containsKey( obj )) {
            Quality qy = left2Right.get(obj);
            if ( qy == null ) {
                return null;
            }
            return qy.target;
        }

        return right2Left.get( obj );
    }

    public void match( T src, T tgt, double quality) {

        if ( isMatched( src) || isMatched( tgt )) {
            Log.warning("prog error double match: one argument allready matched, one: " + isMatched(src) + ", two: " + isMatched(tgt));
            throw new IllegalArgumentException( "one argument allready matched, one: " + isMatched(src) + ", two: " + isMatched(tgt));
        }

        for ( NodeChangeListener listener : listeners ) {
            listener.beforeMatched( src, tgt );
        }


        Quality qy = new Quality();
        qy.target  = tgt;
        qy.quality = quality;
        qy.diff = ContentDiff.unknown;

        left2Right.put( src, qy );
        right2Left.put( tgt, src);

        for ( NodeChangeListener listener : listeners ) {
            listener.matched( src, tgt );
        }

    }

    public void breakMatch( T left) {

        if ( !isMatched(left)) {
            return;
        }

        if ( !left2Right.containsKey( left )) {
            breakMatch( right2Left.get(left));
            return;
        }

        Quality qy = left2Right.get(left);
        T right    = qy.target;
        right2Left.put( right, null );
        left2Right.put( left, null );

        for ( NodeChangeListener listener : listeners ) {
            listener.matchRemoved( left, right );
        }
    }

    public T getLeftRoot() {
        return leftRoot.get();
    }

    public T getRightRoot() {
        return rightRoot.get();
    }

    public void setRoots(T leftRoot, T rightRoot) {
        this.leftRoot.set( leftRoot);
        this.rightRoot.set( rightRoot );

        match( leftRoot, rightRoot, 1.0 );
    }

    @Override
    public void setRoot( T oneRoot, boolean left ) {
        if ( left ) {
            leftRoot.set( oneRoot );
        } else {
            rightRoot.set( oneRoot );
        }

        if ( leftRoot.isSet() && rightRoot.isSet() ) {
            match( leftRoot.get(), rightRoot.get(), 1.0 ); // TODO
        }
    }

    public void remove( T obj ) {
        breakMatch( obj );
        left2Right.remove( obj );
        right2Left.remove( obj );

        for ( NodeChangeListener listener : listeners ) {
            listener.removed( obj );
        }
    }

    public double getMatchQuality(T obj) {
        if ( isMatched(obj)) {
            return getQuality(obj).quality;
        }

        return 0;
    }

    public void addNodeChangeListener( NodeChangeListener listener ) {
        listeners.add( listener );
    }

    public void removeNodeChangeListener(NodeChangeListener listener) {
        listeners.remove( listener );
    }

    public ContentDiff getDifference(T obj) {
        return getQuality(obj).diff;
    }

    public void setDifference( T obj, ContentDiff diff) {

        T3<T,T,Quality> ordered = getOrdered( obj );

        ordered.i2.diff = diff;

        for ( NodeChangeListener listener : listeners ) {
            listener.diffChanged( ordered.i0, ordered.i1 );
        }
    }


    private Quality getQuality( T obj ) {

        if ( !isMatched(obj)) {
            throw new IllegalArgumentException( "not matched" );
        }

        Quality qy = left2Right.get( obj );

        if ( qy != null ) {
            return qy;
        }

        return left2Right.get( right2Left.get( obj ));
    }

    private T3<T,T,Quality> getOrdered( T obj ) {
        if ( !isMatched(obj)) {
            throw new IllegalArgumentException( "not matched" );
        }

        Quality qy = left2Right.get( obj );

        if ( qy != null ) {
            return new T3( obj, qy.target, qy );
        }

        T left = right2Left.get( obj );
        return new T3( left, obj, left2Right.get( left ));
    }

}
