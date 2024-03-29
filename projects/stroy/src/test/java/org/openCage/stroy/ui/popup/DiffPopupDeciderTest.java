//package org.openCage.stroy.ui.popup;
//
//import junit.framework.TestCase;
//import org.openCage.lindwurm.content.ReducedContent;
//import org.openCage.lindwurm.LindenNode;
//import org.openCage.lindwurm.SimpleTreeNode;
//import org.openCage.stroy.graph.node.SimpleContentTreeBuilder;
//import com.muchsoft.util.Sys;
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
//
//public class DiffPopupDeciderTest extends TestCase {
//
//    public void testShowOpenLeave() {
//
//        LindenNode<ReducedContent> leave = new SimpleContentTreeBuilder().l( "foo.jpg");
//        assertTrue( new DiffPopupDecider(fileTypes).showOpen( leave ));
//    }
//
//    public void testShowOpenLeaveWithoutContetn() {
//
//        LindenNode<ReducedContent> leave = new SimpleTreeNode<ReducedContent>( null );
//        assertFalse( new DiffPopupDecider(fileTypes).showOpen( leave ));
//    }
//
//    public void testShowOpenDir() {
//
//        LindenNode<ReducedContent> leave = new SimpleContentTreeBuilder().l( "foo");
//        LindenNode<ReducedContent> dir = new SimpleContentTreeBuilder().d( "dd", leave);
//
//        assertFalse( new DiffPopupDecider(fileTypes).showOpen( dir ));
//    }
//
//    public void testShowOpenBundle() {
//
//        LindenNode<ReducedContent> leave = new SimpleContentTreeBuilder().l( "foo");
//        LindenNode<ReducedContent> dir = new SimpleContentTreeBuilder().d( "dd.app", leave);
//
//        assertEquals( Sys.isMacOSX(), new DiffPopupDecider(fileTypes).showOpen( dir ));
//    }
//
//    public void testShowOpenAsTextLeave() {
//
//        LindenNode<ReducedContent> leave = new SimpleContentTreeBuilder().l( "foo");
//        assertTrue( new DiffPopupDecider(fileTypes).showOpenAsText( leave ));
//    }
//
//    public void testShowOpenAsTextLeaveWithoutContetn() {
//
//        LindenNode<ReducedContent> leave = new SimpleTreeNode<ReducedContent>( null );
//        assertFalse( new DiffPopupDecider(fileTypes).showOpenAsText( leave ));
//    }
//
//    public void testShowOpenAsTextDir() {
//
//        LindenNode<ReducedContent> leave = new SimpleContentTreeBuilder().l( "foo");
//        LindenNode<ReducedContent> dir = new SimpleContentTreeBuilder().d( "dd", leave);
//
//        assertFalse( new DiffPopupDecider(fileTypes).showOpenAsText( dir ));
//    }
//
//    public void testShowOpenAsTextBundle() {
//
//        LindenNode<ReducedContent> leave = new SimpleContentTreeBuilder().l( "foo");
//        LindenNode<ReducedContent> dir = new SimpleContentTreeBuilder().d( "dd.app", leave);
//
//        assertFalse( new DiffPopupDecider(fileTypes).showOpenAsText( dir ));
//    }
//
//}
