//package org.openCage.stroy.algo.matching.strategies.base;
//
//import org.openCage.lang.inc.Null;
//import org.openCage.stroy.algo.matching.strategies.TreeStrategy;
//import org.openCage.stroy.algo.matching.TreeTask;
//import org.openCage.stroy.algo.matching.Task;
//
///***** BEGIN LICENSE BLOCK *****
// * BSD License (2 clause)
// * Copyright (c) 2006 - 2012, Stephan Pfab
// * All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *     * Redistributions of source code must retain the above copyright
// *       notice, this list of conditions and the following disclaimer.
// *     * Redistributions in binary form must reproduce the above copyright
// *       notice, this list of conditions and the following disclaimer in the
// *       documentation and/or other materials provided with the distribution.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
// * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//***** END LICENSE BLOCK *****/
//
///**
// * match dirs and leaves based on same path
// */
//
///**
// * A tree matching strategy following the standard logic
// * i.e. just use the name path.
// * not great but fast and a good first step
// */
//public class StandardTreeMatching implements TreeStrategy {
//
//    public void match( TreeTask task ) {
//        matchInChildList( task, task.getLeftRoot(), task.getRightRoot() );
//    }
//
//    public void match( Task<Noed> task ) {
//        // would need to search for root
//        throw new Error( "not impl" );
//    }
//
//
//
//    public void matchInChildList( Task<Noed> task,
////                                  Reporter reporter,
//                                  Noed leftNode,
//                                  Noed toParent ) {
//
//        if ( ! task.isMatched( leftNode )  ) {
//
//            String name = leftNode.getName();
//
//            if (Null.is( toParent ) ) {
//                throw new IllegalStateException( "prog error" );
//            }
//
//            // TODO
//            //reporter.detail( Message.get( "testing"), leftNode.toString() );
//
//            for ( Noed tgtKid : toParent.getChildren() ) {
//
//                if ( (leftNode.isLeaf() == tgtKid.isLeaf() ) &&
//                     !task.isMatched( tgtKid ) &&
//                     tgtKid.getName().equals( name )) {
//
//                    task.match( leftNode, tgtKid );
//                    break;
//                }
//            }
//
//        }
//
//        if ( leftNode.isLeaf() ) {
//            return;
//        }
//
//        Noed newParent = task.getMatch( leftNode );
//
//        if ( Null.is( newParent)) {
//            // no match
//            return;
//        }
//
//        for ( Noed fm : leftNode.getChildren() ) {
//            matchInChildList(task, fm, newParent  );
//        }
//
//    }
//
//}
//
