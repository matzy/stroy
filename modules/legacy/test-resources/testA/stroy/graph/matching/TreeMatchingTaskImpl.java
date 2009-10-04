//package org.openCage.stroy.graph.matching;
//
//import org.openCage.util.generics.Generics;
//import org.openCage.stroy.content.Content;
//import org.openCage.stroy.graph.SameContent;
//import org.openCage.stroy.graph.node.TreeNode;
//import org.openCage.stroy.graph.node.TreeNode;
//import org.openCage.stroy.graph.node.TreeNode;
//import org.openCage.stroy.graph.iterator.DepthFirstIterable;
//import org.openCage.stroy.task.MatchingTask;
//import org.openCage.stroy.task.MatchingTaskImpl;
//import org.openCage.stroy.task.MatchingTaskNeutral;
//import org.openCage.stroy.ui.ChangeVector;
//import org.openCage.util.logging.Log;
//
//import java.util.*;
//import java.util.logging.Level;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1/GPL 2.0
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
//*
//* Alternatively, the contents of this file may be used under the terms of
//* either the GNU General Public License Version 2 or later (the "GPL"),
//* in which case the provisions of the GPL are applicable instead
//* of those above. If you wish to allow use of your version of this file only
//* under the terms of either the GPL, and not to allow others to
//* use your version of this file under the terms of the MPL, indicate your
//* decision by deleting the provisions above and replace them with the notice
//* and other provisions required by the GPL. If you do not delete
//* the provisions above, a recipient may use your version of this file under
//* the terms of any one of the MPL, the GPL.
//*
//***** END LICENSE BLOCK *****/
//
//public class TreeMatchingTaskImpl<T extends Content> implements TreeMatchingTask<T> {
//
//    private List<SameContent<T>>           dups;
//    private MatchingTask<TreeNode<T>>   dirTask;
//    private MatchingTask<TreeNode<T>>  fileTask;
//
//    public TreeMatchingTaskImpl() {
//        dups         = new ArrayList<SameContent<T>>();
////        dirTask      = new MatchingTaskImpl<TreeNode<T>>();
////        fileTask     = new MatchingTaskImpl<TreeNode<T>>();
//        dirTask      = new MatchingTaskNeutral<TreeNode<T>>();
//        fileTask     = new MatchingTaskNeutral<TreeNode<T>>();
//    }
//
//
//    public void addDup(final SameContent<T> sh) {
//        dups.add( sh );
//    }
//
//    public MatchingTask<TreeNode<T>> getLeaves() {
//        return fileTask;
//    }
//
//    public MatchingTask<TreeNode<T>> getDirs() {
//        return dirTask;
//    }
//
//    public boolean isMatched( final TreeNode<T> obj ) {
//        if ( obj.isLeaf() ) {
//            return fileTask.isMatched( (TreeNode<T>)obj );
//        }
//
//        return dirTask.isMatched( (TreeNode<T>)obj );
//    }
//
//    public TreeNode<T> getMatch( final TreeNode<T> obj ) {
//
//        if ( obj == null ) {
//            return null;
//        }
//
//        if ( obj.isLeaf() ) {
//            return fileTask.getMatch( (TreeNode<T>)obj );
//        }
//
//        return dirTask.getMatch( (TreeNode<T>)obj );
//    }
//
//
//
//    public Collection<SameContent<T>> getDuplicates() {
//        return dups;
//    }
//
//
//    public void shortStatus() {
//
//        if ( Log.isAtLeast( Level.FINE )) {
//            System.out.println("unmatched source dirs  " + dirTask.getUnmatchedLeft().size() );
//            System.out.println("unmatched source files " + fileTask.getUnmatchedLeft().size() );
//            System.out.println("unmatched target dirs  " + dirTask.getUnmatchedRight().size() );
//            System.out.println("unmatched target files " + fileTask.getUnmatchedRight().size() );
//        }
//    }
//
//    public void status() {
//        System.out.println("files deleted");
//
//        for ( final TreeNode<T> lfm : fileTask.getUnmatchedLeft() ) {
//            System.out.println("  " + lfm );
//        }
//
//        System.out.println("dirs deleted");
//        for ( final TreeNode<T> fm : dirTask.getUnmatchedLeft() ) {
//            System.out.println("  " + fm );
//        }
//
//        System.out.println("files moved");
//        for ( final TreeNode<T> lfm : getMovedLeaves() ) {
//            System.out.println("  " + lfm.toString() + "->" + fileTask.getMatch( lfm ).getParent() );
//        }
//
//        System.out.println("files renamed");
//        for ( TreeNode<T> lfm : getRenamedLeaves() ) {
//            System.out.println("  " + lfm.toString() + "->" + fileTask.getMatch( lfm ).getContent().getName() );
//        }
//
//        System.out.println("dirs moved");
//        for ( final TreeNode<T> lfm : getMovedDirs() ) {
//            System.out.println("  " + lfm.toString() + "->" + getMatch( lfm ).getParent() );
//        }
//
//        System.out.println("dirs renamed");
//        for ( TreeNode<T> lfm : getRenamedDirs() ) {
//            System.out.println("  " + lfm.toString() + "->" + getMatch( lfm ).getContent().getName() );
//        }
//
//        System.out.println("files changed");
//        for ( TreeNode<T> lfm : getModifiedLeaves() ) {
//            System.out.println("  " + lfm );
//        }
//
//        System.out.println("new dirs");
//        for ( TreeNode<T> lfm : dirTask.getUnmatchedRight() ) {
//            System.out.println("  " + lfm );
//        }
//
//        System.out.println("new files");
//        for ( TreeNode<T> lfm : fileTask.getUnmatchedRight() ) {
//            System.out.println("  " + lfm );
//        }
//
//        System.out.println("dirs changed");
//        for ( TreeNode<T> lfm : getModifiedDirs() ) {
//            System.out.println("  " + lfm );
//        }
//
//    }
//
//    public int getLeftLeaveCount() {
//        int count = 0;
//        for ( TreeNode<T> node : new DepthFirstIterable<T>( getLeftRoot() )) {
//            if ( node.isLeaf() ) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public int getLeftDirCount() {
//        int count = 0;
//        for ( TreeNode<T> node : new DepthFirstIterable<T>( getLeftRoot() )) {
//            if ( !node.isLeaf() ) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public int getRightLeaveCount() {
//        int count = 0;
//        for ( TreeNode<T> node : new DepthFirstIterable<T>( getRightRoot() )) {
//            if ( node.isLeaf() ) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public int getRightDirCount() {
//        int count = 0;
//        for ( TreeNode<T> node : new DepthFirstIterable<T>( getRightRoot() )) {
//            if ( !node.isLeaf() ) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public Collection<TreeNode<T>> getModifiedLeaves() {
//        final List<TreeNode<T>> modified = new ArrayList<TreeNode<T>>();
//
//        for ( TreeNode<T> lfm : fileTask.getMatchedLeft() ) {
//
//            if ( fileTask.getMatch( lfm ).getQuality() < 1.0 ) {
//                modified.add( lfm );
//            }
//        }
//
//        return modified;
//    }
//
//    public Collection<TreeNode<T>> getRenamedLeaves() {
//        //noinspection unchecked
//        return filterRenamed( (Collection) fileTask.getMatchedLeft() );
//    }
//
//    public Collection<TreeNode<T>> getRenamedDirs() {
//        //noinspection unchecked
//        return filterRenamed( (Collection) dirTask.getMatchedLeft() );
//    }
//
//    public TreeNode<T> getLeftRoot() {
//        return dirTask.getLeftRoot();
//    }
//
//    public TreeNode<T> getRightRoot() {
//        return dirTask.getRightRoot();
//    }
//
//    public boolean isContentChanged(TreeNode<T> obj) {
//
//        if ( !obj.isLeaf() || !isMatched( obj ) ) {
//            return false;
//        }
//
//
//        return fileTask.getMatch( (TreeNode)obj ).getQuality() < 1.0;
//    }
//
//    public double getMatchQuality(TreeNode<T> obj) {
//        if ( !isMatched( obj )) {
//            return 0;
//        }
//
//        if ( !obj.isLeaf() ) {
//            return 0.3;
//        }
//
//
//
//
//        return fileTask.getMatch( (TreeNode)obj ).getQuality();
//    }
//
//
//    public Collection filterRenamed( Collection<TreeNode<T>> matched ) {
//        List renamed = new ArrayList();
//
//        for ( TreeNode<T> lfm : matched ) {
//            if ( ! lfm.getContent().getName().equals( getMatch( lfm ).getContent().getName() )){
//                //noinspection unchecked
//                renamed.add( lfm );
//            }
//        }
//
//        return renamed;
//    }
//
//    public Collection<TreeNode<T>> getMovedLeaves() {
//
//        //noinspection unchecked
//        return getMoved( new Generics<TreeNode<T>,TreeNode<T>>().cast( fileTask.getMatchedLeft() ));
//    }
//
//    public Collection<TreeNode<T>> getMovedDirs() {
//
//        //noinspection unchecked
//        return getMoved( new Generics<TreeNode<T>,TreeNode<T>>().cast( dirTask.getMatchedLeft()));
//    }
//
//
//    public Collection<TreeNode<T>> getUnmatchedLeftFiles() {
//        return fileTask.getUnmatchedLeft();
//    }
//
//    public Collection<TreeNode<T>> getUnmatchedLeftDirs() {
//        return dirTask.getUnmatchedLeft();
//    }
//
//    public Collection<TreeNode<T>> getUnmatchedRightFiles() {
//        return fileTask.getUnmatchedRight();
//    }
//
//    public Collection<TreeNode<T>> getUnmatchedRightDirs() {
//        return dirTask.getUnmatchedRight();
//    }
//
//    private Collection getMoved( Collection< TreeNode<T>> matched ) {
//        List moved = new ArrayList();
//
//        for ( final TreeNode<T> node : matched ) {
//            TreeNode<T> parent     = node.getParent();
//            TreeNode<T>    match      = getMatch( node );
//
//            if ( parent != null ) {
//                if ( !dirTask.isMatched( parent )) {
//                    moved.add( node );
//                } else if ( dirTask.getMatch( parent ) != match.getParent() ) {
//                    moved.add( node );
//                }
//            }
//        }
//
//        return moved;
//    }
//
//    public Collection<TreeNode<T>> getModifiedDirs() {
//        Set<TreeNode<T>> modified = new HashSet<TreeNode<T>>();
//
//        for ( TreeNode<T> node : getRenamedDirs() ) {
//            if ( node.getParent() != null ) {
//                modified.add( node.getParent() );
//            }
//        }
//
//        for ( TreeNode<T> node : getRenamedLeaves() ) {
//            if ( node.getParent() != null ) {
//                modified.add( node.getParent() );
//            }
//        }
//
//        for ( TreeNode<T> node : getMovedDirs() ) {
//            modified.add( node.getParent() );
//
//            TreeNode<T> newParentSrc = dirTask.getMatch( dirTask.getMatch( node ).getParent());
//
//            if ( newParentSrc != null ) {
//                modified.add( newParentSrc );
//            }
//        }
//
//        for ( TreeNode<T> node : getMovedLeaves() ) {
//            modified.add( node.getParent() );
//
//            TreeNode<T> newParentSrc = dirTask.getMatch( fileTask.getMatch( node ).getParent());
//
//            if ( newParentSrc != null ) {
//                modified.add( newParentSrc );
//            }
//        }
//
//        for (TreeNode node : dirTask.getUnmatchedLeft() ) {
//            modified.add( node.getParent() );
//        }
//
//        for (TreeNode node : fileTask.getUnmatchedLeft() ) {
//            modified.add( node.getParent() );
//        }
//
//        for (TreeNode node : dirTask.getUnmatchedRight() ) {
//            TreeNode<T> parentSrc = dirTask.getMatch( node.getParent());
//
//            if ( parentSrc != null ) {
//                modified.add( parentSrc );
//            }
//        }
//
//        for (TreeNode node : fileTask.getUnmatchedRight() ) {
//            TreeNode<T> parentSrc = dirTask.getMatch( node.getParent());
//
//            if ( parentSrc != null ) {
//                modified.add( parentSrc );
//            }
//        }
//
//        return modified;
//    }
//
//    public ChangeVector getChangeVector(TreeNode<T> obj) {
//
//        ChangeVector cv = new ChangeVector();
//
//        TreeNode<T> match = getMatch( obj );
//
//        if ( match == null ) {
//            cv.only = true;
//
//            return cv;
//        }
//
//        if ( obj.isLeaf() ) {
//            if ( obj.isOriginal() ) {
//                cv.content = ((TreeNode)match ).getQuality() < 1.0;
//            } else {
//                cv.content = ((TreeNode)obj ).getQuality() < 1.0;
//            }
//        }
//
//        cv.name = ! match.getContent().getName().equals( obj.getContent().getName());
//
//        TreeNode<T> parent     = obj.getParent();
//
//        if ( parent != null ) {
//            if ( !dirTask.isMatched( parent )) {
//                cv.parent = true;
//            } else if ( dirTask.getMatch( parent ) != match.getParent() ) {
//                cv.parent = true;
//            } else if ( !parent.getContent().getName().equals( match.getParent().getContent().getName() ) ) {
//                cv.parentRenamed = true;
//            }
//        }
//
//
//        return cv;
//    }
//
//    public void remove(TreeNode<T> treeNode) {
//        if ( treeNode.isLeaf() ) {
//            fileTask.remove( (TreeNode<T>)treeNode );
//        } else {
//            dirTask.remove( (TreeNode<T>)treeNode );
//        }
//    }
//
//    public void merge() {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public Collection<TreeNode<T>> getComplexModifiedLeaves() {
//        Collection<TreeNode<T>> struct = getMovedLeaves();
//
//        struct.addAll( getRenamedLeaves() );
//
//        List<TreeNode<T>> ret = new ArrayList<TreeNode<T>>();
//
//        for ( TreeNode<T> lfm : struct ) {
//            if ( fileTask.getMatch( lfm ).getQuality() < 1.0 ) {
//                ret.add( lfm );
//            }
//        }
//
//        return ret;
//    }
//
//
//}
