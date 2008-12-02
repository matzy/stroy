//package org.openCage.stroy.array;
//
//import com.google.inject.Inject;
//import org.openCage.stroy.Connect;
//import org.openCage.stroy.Distance;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
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
//public class MatchSimple<T> implements Match<T>{
//    private final Distance<T> dist;
//    private final List<T> unmatchedFroms;
//    private final List<T>     unmatchedTos;
//
//    @Inject
//    public MatchSimple( Distance<T> dist ) {
//        this.dist = dist;
//        unmatchedFroms = new ArrayList<T>();
//        unmatchedTos   = new ArrayList<T>();
//    }
//
//
//    public void match(Connect<? super T> connect, Collection<T> from, Collection<T> to ) {
//        unmatchedFroms.clear();
//        unmatchedTos.clear();
//
//        for ( T elemFrom : from ) {
//
//            double bestDist  = 2.0;
//            T      bestMatch = null;
//
//            for ( T elemTo : to ) {
//
//                if ( !connect.isConnected( elemTo )) {
//                    double distval = dist.distance( elemFrom, elemTo );
//
//                    if ( distval < 0.31 && distval < bestDist ) {
//                        bestDist  = distval;
//                        bestMatch = elemTo;
//                    }
//                }
//            }
//
//            if ( bestMatch != null ) {
//                connect.connect( elemFrom, bestMatch );
//            } else {
//                unmatchedFroms.add( elemFrom );
//            }
//        }
//
//        for ( T elemTo : to ) {
//
//            if ( !connect.isConnected( elemTo )) {
//                unmatchedTos.add( elemTo );
//            }
//        }
//    }
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
