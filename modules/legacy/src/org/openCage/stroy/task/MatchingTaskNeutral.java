package org.openCage.stroy.task;

import org.openCage.util.lang.Once;
import org.openCage.stroy.diff.ContentDiff;

import java.util.*;
import java.util.logging.Logger;
import org.openCage.lang.protocol.tuple.T3;


/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class MatchingTaskNeutral<T> implements MatchingTask<T>{

    private static final Logger LOG = Logger.getLogger( MatchingTaskNeutral.class.getName());

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

    private final Once<T> leftRoot  = new Once<T>();
    private final Once<T> rightRoot = new Once<T>();

    private Map<T, Quality> left2Right   = new HashMap<T, Quality>();
    private Map<T, T>       right2Left   = new HashMap<T, T>();


    public MatchingTaskNeutral() {
    }

    public void addLeft(T obj) {
        left2Right.put( obj, null );
    }

    public void addRight(T obj) {
        right2Left.put( obj, null);
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
            LOG.warning("prog error double match: one argument allready matched, one: " + isMatched(src) + ", two: " + isMatched(tgt));
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
