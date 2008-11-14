package org.openCage.stroy.matching;

import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.task.NodeChangeListener;
import org.openCage.stroy.diff.ContentDiff;
import org.openCage.util.logging.Log;
import org.openCage.util.lang.F1;

import java.util.*;

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

    public Collection<T> getLeft( F1<Boolean, T> filter ) {
        List<T> ret = new ArrayList<T>();

        for ( T obj : left2right.keySet() ) {
            if ( filter.call( obj )) {
                ret.add( obj );
            }
        }

        return ret;
    }

    public Collection<T> getRight( F1<Boolean, T> filter ) {
        List<T> ret = new ArrayList<T>();

        for ( T obj : right2left.keySet() ) {
            if ( filter.call( obj )) {
                ret.add( obj );
            }
        }

        return ret;
    }

    public boolean isMatched( T obj ) {
        return left2right.get(  obj ) == null && right2left.get( obj ) == null;
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
