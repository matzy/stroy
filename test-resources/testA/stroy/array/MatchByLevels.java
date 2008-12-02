//package org.openCage.stroy.array;
//
//import com.google.inject.Inject;
//import org.openCage.stroy.Connect;
//import org.openCage.stroy.Distance;
//import org.openCage.util.collection.SortedTreeSet;
//import org.openCage.util.compare.FirstProjectionComparator;
//import org.openCage.util.iterator.T2;
//
//import java.util.*;
//
///**
// * stroy, a differencing tool
// * Copyright (C) May 17, 2007 Stephan Pfab
// * <p/>
// * This library is free software; you can redistribute it and/or
// * modify it under the terms of the GNU Lesser General Public
// * License as published by the Free Software Foundation; either
// * version 2.1 of the License, or (at your option) any later version.
// * <p/>
// * This library is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// * Lesser General Public License for more details.
// * <p/>
// * You should have received a copy of the GNU Lesser General Public
// * License along with this library; if not, write to the Free Software
// * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
// */
//public class MatchByLevels<T> implements Match<T> {
//
//    private final Distance<T> dist;
//    private final List<T>     unmatchedFroms;
//    private final List<T>     unmatchedTos;
//    private final Map<T, SortedTreeSet<T2<Double,T>>> possibilities;
//
//
//    @Inject
//    public MatchByLevels( Distance<T> dist ) {
//        this.dist = dist;
//        unmatchedFroms = new ArrayList<T>();
//        unmatchedTos   = new ArrayList<T>();
//        possibilities  = new HashMap<T, SortedTreeSet<T2<Double,T>>>();
//    }
//
//
//    public void match(Connect<? super T> connect, Collection<T> from, Collection<T> to ) {
//        unmatchedFroms.clear();
//        unmatchedTos.clear();
//
//        for ( T elemFrom : from ) {
//
//            SortedTreeSet<T2<Double,T>> best =
//                    new SortedTreeSet<T2<Double,T>>( new FirstProjectionComparator<Double,T>());
//
//            for ( T elemTo : to ) {
//
//
//                if ( !connect.isConnected( elemTo )) {
//                    double distval = dist.distance( elemFrom, elemTo );
//
//                    if ( distval < 0.31  ) {
//                        best.add( new T2<Double,T>( distval, elemTo ));
//                    }
//                }
//            }
//
//            possibilities.put( elemFrom, best);
//        }
//
//
//        decide( 0.1, from, connect);
//        decide( 0.2, from, connect);
//
//        for ( T elemFrom : from ) {
//            if ( !connect.isConnected( elemFrom ) ) {
//                unmatchedFroms.add( elemFrom );
//            }
//        }
//
//        for ( T elemTo : to ) {
//            if ( !connect.isConnected( elemTo )) {
//                unmatchedTos.add( elemTo );
//            }
//        }
//
//        possibilities.clear();
//
//    }
//
//    private void decide( double level, Collection<T> from, Connect<? super T> connect) {
//        for ( T elemFrom : from ) {
//
//            SortedTreeSet<T2<Double,T>> best = possibilities.get( elemFrom );
//
//            if ( best.isEmpty() ) {
//                // unmatchedFroms.add( elemFrom );
//            } else  {
//                boolean found = false;
//
//                for ( T2<Double,T> pp : best ) {
//
//                    if ( pp.i0 >= level ) {
//                        return;
//                    }
//
//                    if ( !connect.isConnected( pp.i1 ) ) {
//                        connect.connect( elemFrom,  pp.i1 );
//                        found = true;
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//
//
//    public Collection<T> getUnmatchedFroms() {
//        return unmatchedFroms;
//    }
//
//    public Collection<T> getUnmatchedTos() {
//        return unmatchedTos;
//    }
//}
