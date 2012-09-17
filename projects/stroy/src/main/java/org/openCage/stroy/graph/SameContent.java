package org.openCage.stroy.graph;

import org.openCage.lindwurm.LindenNode;
import org.openCage.lindwurm.content.Content;

import java.util.ArrayList;
import java.util.List;

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

public class SameContent<T extends Content> {

    private final List<LindenNode> src;
    private final List<LindenNode> tgt;

    public SameContent() {
        src = new ArrayList<LindenNode>();
        tgt = new ArrayList<LindenNode>();
    }

    public List<LindenNode> getSources() {
        return src;
    }

    public List<LindenNode> getTargets() {
        return tgt;
    }

    public void add( LindenNode lfm, boolean isSrc) {
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

        for ( LindenNode lfm : src ) {
            str += "   " + lfm + "\n";
        }

        str += "--\n";

        for ( LindenNode lfm : tgt ) {
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

    public LindenNode getSingle() {
        if ( isSingleNew() ) {
            return tgt.get(0);
        } else if ( isSingleOld()) {
            return src.get(0);
        }

        throw new Error( "not single" );
    }



//    public void match( final IdMap idMap, List dels, List news ) {
//        if ( src.size() == 0 ) {
//            for ( T lf : tgt ) {
//                news.add( lf );
//            }
//        } else if ( tgt.size() == 0 ) {
//            for ( T lf : src ) {
//                dels.add( lf );
//            }
//        } else {
//            Match match = new MatchBestConnections( new Distance() {
//            //Match match = new MatchByLevels( new Distance() {
//            //Match match = new MatchSimple( new Distance() {
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

    public LindenNode getSingleTgt() {
        if ( tgt.size() == 1  ) {
            return tgt.get(0);
        }

        throw new Error( "not single" );
    }

    public LindenNode getSingleSrc() {
        if ( src.size() == 1 )  {
            return src.get(0);
        }

        throw new Error( "not single" );
    }
}
