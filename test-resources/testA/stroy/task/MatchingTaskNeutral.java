package org.openCage.stroy.task;

import org.openCage.util.lang.Once;
import org.openCage.util.logging.Log;

import java.util.*;


/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

// TODO Unit test
public class MatchingTaskNeutral<T> implements MatchingTask<T>{


    class Quality {
        public T      target;
        public double quality;
        // TODO add merge info ?

        public Quality() {
            target = null;
            quality = 0.0;
        }

        public void init() {
            target  = null;
            quality = 0.0;
    }


    }

    private final Once<T> leftRoot = new Once<T>();
    private final Once<T> rightRoot = new Once<T>();

    private Map<T, Quality> left2Right   = new HashMap<T, Quality>();
    private Map<T, Quality> right2Left   = new HashMap<T, Quality>();


    public MatchingTaskNeutral() {
    }

    public void addLeft(T obj) {
        // the first to add may not be the root
//        if ( leftRoot.isNull() ) {
//            leftRoot.set( obj);
//        }
        left2Right.put( obj, new Quality());
    }

    public void addRight(T obj) {
//        if ( rightRoot.isNull() ) {
//            rightRoot.set( obj );
//        }
        right2Left.put( obj, new Quality());
    }

    public Collection<T> getUnmatchedLeft() {

        List<T> ret = new ArrayList<T>();
        for ( T val : left2Right.keySet() ) {
            if ( left2Right.get(val).quality == 0 ) {
                ret.add( val );
            }
        }

        return ret;
    }

    public Collection<T> getUnmatchedRight() {
        List<T> ret = new ArrayList<T>();
        for ( T val : right2Left.keySet() ) {
            if ( right2Left.get(val).quality == 0 ) {
                ret.add( val );
            }
        }

        return ret;
    }

    public Collection<T> getMatchedLeft() {
        List<T> ret = new ArrayList<T>();
        for ( T val : left2Right.keySet() ) {
            if ( left2Right.get(val).quality > 0 ) {
                ret.add( val );
            }
        }

        return ret;
    }

    public Collection<T> getMatchedRight() {
        List<T> ret = new ArrayList<T>();
        for ( T val : right2Left.keySet() ) {
            if ( right2Left.get(val).quality > 0 ) {
                ret.add( val );
            }
        }

        return ret;
    }

    public boolean isMatched(T obj) {

        Quality qy = left2Right.get( obj );

        if ( qy != null ) {
            return qy.quality > 0; // TODO use target ?
        }

        qy = right2Left.get( obj );

        if ( qy != null ) {
            return qy.quality > 0;
        }

        Log.severe( "prog error" );

        throw new IllegalStateException("prog error");
    }

    public T getMatch(T obj) {
        Quality qy = left2Right.get( obj );

        if ( qy != null ) {
            return qy.target;
        }

        qy = right2Left.get( obj );

        if ( qy != null ) {
            return qy.target;
        }

        Log.severe( "prog error" );

        throw new IllegalStateException("prog error");
    }

    public void match(T src, T tgt, double quality) {

        if ( isMatched( src) || isMatched( tgt )) {
            Log.warning("prog error double match: one argument allready matched, one: " + isMatched(src) + ", two: " + isMatched(tgt));
            throw new IllegalArgumentException( "one argument allready matched, one: " + isMatched(src) + ", two: " + isMatched(tgt));
        }

        Quality qy = left2Right.get(src);
        qy.target  = tgt;
        qy.quality = quality;

        qy         = right2Left.get(tgt);
        qy.target  = src;
        qy.quality = quality;

    }

    public void breakMatch(T src) {
        Quality qy    = left2Right.get(src);
        Quality other;
        if ( qy != null ) {
            other = right2Left.get( qy.target );
        } else {
            qy = right2Left.get(src);
            if ( qy != null ) {
                other = left2Right.get( qy.target );
            } else {
                Log.severe( "prog error" );
                throw new IllegalStateException( "not in list" );
            }
        }

        other.init();
        qy.init();
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
    }

    public void remove(T node) {
        Quality qy = left2Right.get( node );

        if ( qy != null ) {
            if ( qy.target != null ) {
                Quality other = right2Left.get( qy.target );
                other.target  = null;
                other.quality = 0.0;
            }

            left2Right.remove( node );
        } else {

            qy = right2Left.get( node );

            if ( qy != null ) {
                if ( qy.target != null ) {
                    Quality other = left2Right.get( qy.target );
                    other.target  = null;
                    other.quality = 0.0;
                }

                right2Left.remove( node );
            }
        }
    }

    public double getMatchQuality(T obj) {

        Quality qy = left2Right.get(obj);

        if ( qy != null ) {
            return qy.quality;
        }

        qy = right2Left.get(obj);
        if ( qy != null ) {
            return qy.quality;
        }

        return 0;
    }
}
