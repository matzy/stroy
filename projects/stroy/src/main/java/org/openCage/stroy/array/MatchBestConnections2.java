package org.openCage.stroy.array;

import org.openCage.kleinod.collection.T3;
import org.openCage.stroy.InformedDistance;
import org.openCage.stroy.graph.matching.strategy.Reporter;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.util.compare.TrippleProj1Comparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

public class MatchBestConnections2<S,T> {

    private final InformedDistance<S,T> dist;
    private final boolean               perfect;

    private final List<T3<Double,T,T>> edges = new ArrayList<T3<Double,T,T>>();
    private Reporter reporter;


    public MatchBestConnections2( InformedDistance<S,T> dist, boolean perfect, Reporter reporter ) {
        this.dist = dist;
        this.perfect = perfect;
        this.reporter = reporter;
    }

    public void match( S info, MatchingTask<T> task, Collection<T> lefts, Collection<T> rights ) {

        for ( T elemFrom : lefts ) {
            reporter.detail( Message.get( "Progress.matchsearch" ), elemFrom.toString() );
            for ( T elemTo : rights ) {
                if ( !task.isMatched( elemTo )) {
                    double distval = dist.distance( info, elemFrom, elemTo );

                    if ( distval < 0.31  ) {
                        edges.add( new T3<Double,T,T>( distval, elemFrom, elemTo ));
                    }
                }
            }
        }

        Collections.sort( edges, new TrippleProj1Comparator());

        for ( T3<Double,T,T> edge : edges ) {
            if ( !task.isMatched( edge.i1) && !task.isMatched( edge.i2 )) {
                if ( perfect ) {
                    task.match( edge.i1,  edge.i2, 1.0 );
                } else {
                    task.match( edge.i1,  edge.i2, 1 - edge.i0 );
                }
            }
        }

    }
}

