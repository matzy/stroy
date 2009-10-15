package org.openCage.stroy.graph;

import org.openCage.vfs.protocol.Content;
import org.openCage.vfs.protocol.VNode;

import java.util.ArrayList;
import java.util.List;

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

public class SameContent {

    private final List<VNode> src;
    private final List<VNode> tgt;

    public SameContent() {
        src = new ArrayList<VNode>();
        tgt = new ArrayList<VNode>();
    }

    public List<VNode> getSources() {
        return src;
    }

    public List<VNode> getTargets() {
        return tgt;
    }

    public void add( VNode lfm, boolean isSrc) {
        if ( isSrc ) {
            src.add( lfm );
        } else {
            tgt.add( lfm );
        }
    }


    public boolean isOk() {
        return src.size() == 1 && tgt.size() == 1;
    }


    public boolean isNewOnly() {
        return src.size() == 0;
    }

    public boolean isOldOnly() {
        return tgt.size() == 0;
    }


    public String toString() {
        String str = "same hash:\n";

        for ( VNode lfm : src ) {
            str += "   " + lfm + "\n";
        }

        str += "--\n";

        for ( VNode lfm : tgt ) {
            str += "   " + lfm + "\n";
        }

        return str;
    }

    public boolean isSingle() {
        return src.size() < 2 && tgt.size() < 2;
    }


//    public void setMatch(IdMap idMap) {
//
//        assert( isOk() );
//
//        idMap.connect( src.get(0), tgt.get(0));
//    }

    public boolean isSingleNew() {

        return ( src.size() == 0 && tgt.size() == 1 );
    }

    public boolean isSingleOld() {
        return ( src.size() == 1 && tgt.size() == 0 );
    }

    public VNode getSingle() {
        if ( isSingleNew() ) {
            return tgt.get(0);
        } else if ( isSingleOld()) {
            return src.get(0);
        }

        throw new Error( "not single" );
    }



//    public void match( final IdMap idMap, List<T> dels, List<T> news ) {
//        if ( src.size() == 0 ) {
//            for ( T lf : tgt ) {
//                news.add( lf );
//            }
//        } else if ( tgt.size() == 0 ) {
//            for ( T lf : src ) {
//                dels.add( lf );
//            }
//        } else {
//            Match<T> match = new MatchBestConnections<T>( new Distance<T>() {
//            //Match<T> match = new MatchByLevels<T>( new Distance<T>() {
//            //Match<T> match = new MatchSimple<T>( new Distance<T>() {
//                public double distance(T a, T b) {
//                    double dist = 1.0;
//                    // parent
//                    if ( idMap.isConnected( a.getParent())) {
//                        FileMeta aparentMatch  = idMap.find(a.getParent());
//                        if ( aparentMatch == b.getParent() ) {
//                            dist *= 0.25;
//                        }
//                    } else if ( b.getParent().getId() == null ) {
//                        dist *= 0.3;
//                    }
//
//                    if ( a.getContent().getName().equals( b.getContent().getName() )) {
//                        dist *= 0.2;
//                    }
//
//                    return dist;
//                }
//            });
//
//            match.match( idMap, src, tgt );
//
//            news.addAll( match.getUnmatchedTos() );
//            dels.addAll( match.getUnmatchedFroms());
//
//        }
//
//
//    }

    public VNode getSingleTgt() {
        if ( tgt.size() == 1  ) {
            return tgt.get(0);
        }

        throw new Error( "not single" );
    }

    public VNode getSingleSrc() {
        if ( src.size() == 1 )  {
            return src.get(0);
        }

        throw new Error( "not single" );
    }
}
