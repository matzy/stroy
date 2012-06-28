package org.openCage.stroy.matching;

import org.openCage.lang.functions.F2;
import org.openCage.util.logging.Log;
import org.openCage.stroy.algo.matching.Task;

import java.util.*;

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

public class TaskNeutral<T> implements Task<T> {

    private Map<T,T>              left2right = new HashMap<T,T>();
    private Map<T,T>              right2left = new HashMap<T,T>();
    private Map<T,EdgeAttributes> attributes = new HashMap<T, EdgeAttributes>();

    public void addLeft( T left ) {
        left2right.put( left, null );
    }

    public void addRight( T right ) {
        right2left.put( right, null );
    }

    public void remove( T obj ) {
        breakMatch( obj );
        left2right.remove( obj );
        right2left.remove( obj );

        // TODO
//        for ( NodeChangeListener listener : listeners ) {
//            listener.removed( obj );
//        }

    }

    public Collection<T> getLeft( F2<Boolean, Task<T>, T> filter ) {
        List<T> ret = new ArrayList<T>();

        for ( T obj : left2right.keySet() ) {
            if ( filter.call( this, obj )) {
                ret.add( obj );
            }
        }

        return ret;
    }

    public Collection<T> getRight( F2<Boolean, Task<T>, T> filter ) {
        List<T> ret = new ArrayList<T>();

        for ( T obj : right2left.keySet() ) {
            if ( filter.call( this, obj )) {
                ret.add( obj );
            }
        }

        return ret;
    }

    public boolean isMatched( T obj ) {
        return left2right.get(  obj ) != null || right2left.get( obj ) != null;
    }

    public T getMatch( T obj ) {
        T match = left2right.get( obj);
        if ( match != null ) {
            return match;
        }


        return right2left.get(obj);
    }

    public void match( T left, T right ) {
        if ( isMatched( left ) || isMatched( right )) {
            Log.warning("prog error double match: one argument allready matched, one: " + isMatched(left) + ", two: " + isMatched( right));
            throw new IllegalArgumentException( "one argument allready matched, one: " + isMatched(left) + ", two: " + isMatched( right));
        }

        // TODO
//        for ( NodeChangeListener listener : listeners ) {
//            listener.beforeMatched( src, tgt );
//        }


        left2right.put( left, right );
        right2left.put( right, left );

        EdgeAttributes ea = new EdgeAttributesImpl();
        attributes.put( left, ea );
        attributes.put( right, ea);

        // TODO
//        for ( NodeChangeListener listener : listeners ) {
//            listener.matched( src, tgt );
//        }
    }

    public void breakMatch( T obj ) {

        T match = getMatch( obj );

        if ( match == null ) {
            return;
        }

        attributes.remove( obj );
        attributes.remove( match );

        if ( left2right.containsKey( obj )) {
            left2right.put( obj, null );
            right2left.put( match, null );
        } else {
            left2right.put( match, null );
            right2left.put( obj, null );
        }

        // TODO
//        for ( NodeChangeListener listener : listeners ) {
//            listener.matchRemoved( obj, right );
//        }
    }

    public EdgeAttributes getEdgeAttributes( T node ) {
        return attributes.get( node );
    }
}
