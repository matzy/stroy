//package org.openCage.stroy.ui.popup;
//
//import org.junit.Test;
//import org.openCage.stroy.graph.node.SimpleContentTreeBuilder;
//import org.openCage.lindwurm.LindenNode;
//import org.openCage.lindwurm.TreeNodes;
//import org.openCage.stroy.graph.matching.TreeMatchingTask;
//import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutralBuilder;
//import org.openCage.stroy.graph.matching.strategy.StandardMatching;
//import org.openCage.stroy.graph.matching.strategy.NullReporter;
//import org.openCage.lindwurm.content.ReducedContent;
//import org.openCage.lang.structure.T2;
//
//import static org.junit.Assert.*;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//public class DiffPopupTest {
//
//    /**
//     * bug id: 228
//     */
//    @Test
//    public void testLeftRight_right() {
//        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();
//
//        LindenNode<ReducedContent> treeLeft = b.d( "left", b.l( "a"),
//                                                      b.d( "g", b.l("b"),
//                                                                b.l("c")));
//
//        LindenNode<ReducedContent> treeRight = b.d( "right", b.l( "a"),
//                                                       b.d( "g", b.l("b"),
//                                                                 b.l("c")));
//
//        TreeMatchingTask<ReducedContent> task =
//                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );
//
//        new StandardMatching<ReducedContent>().match( task, Null.of(Reporter.class) );
//
//        DiffPopup<ReducedContent> popup =
//                new DiffPopup<ReducedContent>(standard_diff, null, task, null, editor);
//
//        T2<LindenNode<ReducedContent>, LindenNode<ReducedContent>> lr =
//                popup.getLeftAndRightNode( TreeNodes.getNode( task.getRightRoot() ));
//
//        assertEquals( TreeNodes.getNode( task.getLeftRoot()),
//                      lr.i0 );
//        assertEquals( TreeNodes.getNode( task.getRightRoot()),
//                      lr.i1 );
//    }
//
//    @Test
//    public void testLeftRight_left() {
//        SimpleContentTreeBuilder b = new SimpleContentTreeBuilder();
//
//        LindenNode<ReducedContent> treeLeft = b.d( "left", b.l( "a"),
//                                                      b.d( "g", b.l("b"),
//                                                                b.l("c")));
//
//        LindenNode<ReducedContent> treeRight = b.d( "right", b.l( "a"),
//                                                       b.d( "g", b.l("b"),
//                                                                 b.l("c")));
//
//        TreeMatchingTask<ReducedContent> task =
//                TreeMatchingTaskNeutralBuilder.build( treeLeft, treeRight );
//
//        new StandardMatching<ReducedContent>().match( task, Null.of(Reporter.class) );
//
//        DiffPopup<ReducedContent> popup =
//                new DiffPopup<ReducedContent>(standard_diff, null, null, task, editor);
//
//        T2<LindenNode<ReducedContent>, LindenNode<ReducedContent>> lr =
//                popup.getLeftAndRightNode( TreeNodes.getNode( task.getLeftRoot() ));
//
//        assertEquals( TreeNodes.getNode( task.getLeftRoot()),
//                      lr.i0 );
//        assertEquals( TreeNodes.getNode( task.getRightRoot()),
//                      lr.i1 );
//    }
//}
