package org.openCage.stroy.matching;

import org.openCage.util.logging.Log;
import org.openCage.util.lang.P2;
import org.openCage.stroy.algo.matching.Task;

import java.util.*;

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

    public Collection<T> getLeft( P2<Task<T>, T> filter ) {
        List<T> ret = new ArrayList<T>();

        for ( T obj : left2right.keySet() ) {
            if ( filter.c( this, obj )) {
                ret.add( obj );
            }
        }

        return ret;
    }

    public Collection<T> getRight( P2<Task<T>, T> filter ) {
        List<T> ret = new ArrayList<T>();

        for ( T obj : right2left.keySet() ) {
            if ( filter.c( this, obj )) {
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
