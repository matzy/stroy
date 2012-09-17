//package org.openCage.stroy.graph.matching;
//
//import org.openCage.lindwurm.content.Content;
//import org.openCage.lindwurm.content.FileContent;
//import org.openCage.lindwurm.Ignore;
//import org.openCage.stroy.graph.node.LindenNode;
//import org.openCage.lindwurm.LindenNode;
//import org.openCage.lindwurm.LindenDirNode;
//import org.openCage.stroy.graph.matching.TreeMatchingTask;
//import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutral;
//import org.openCage.lindwurm.content.FileContentFactory;
//import org.openCage.util.logging.Log;
//
//import java.io.File;
//import java.util.List;
//import java.util.ArrayList;
//
//import com.google.inject.Inject;
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
// ***** END LICENSE BLOCK *****/
//
//public class GenericTreeMatchingTaskBuilder { //implements FileTreeMatchingTaskBuilder {
//
//    @Inject
//    public GenericTreeMatchingTaskBuilder() {
//    }
//
//    public TreeMatchingTask<Content> build( Ignore ignore, TreeMatchingTask<Content> task, File file ) {
//
//        TreeMatchingTask<Content> pool = new TreeMatchingTaskNeutral<Content>();
//
//        if ( ignore.match( "/" + file.getName())) {
//            throw new Error( "top level dir is in ignore pattern " + file.getAbsolutePath() );
//        }
//
//        LindenNode<Content> oneRoot = add( pool, ignore, "", file, true );
////        LindenNode<FileContent> twoRoot = add( pool, ignore, "", two, false );
//
////        if ( oneRoot.isLeaf() || twoRoot.isLeaf() ) {
////            Log.severe( "oops can't handle leafs yet" );
////        }
//
//        pool.getDirs().setRoots( oneRoot, null );
//
//        return pool;
//    }
//
//    public TreeMatchingTask<FileContent> build(Ignore ignore, TreeMatchingTask<FileContent> leftTask, File right) {
////        TreeMatchingTaskImpl pool = new TreeMatchingTaskImpl();
//        TreeMatchingTask pool = new TreeMatchingTaskNeutral();
//
//        if ( ignore.match( "/" + right.getName() )) {
//            throw new Error( "top level dir is in ignore pattern" );
//        }
//
//
//        for ( LindenNode<FileContent> nd : leftTask.getLeaves().getUnmatchedLeft() ) {
//            pool.getLeaves().addLeft( nd );
//        }
//        for ( LindenDirNode<FileContent> nd : leftTask.getDirs().getUnmatchedLeft() ) {
//            pool.getDirs().addLeft( nd );
//        }
//
//        LindenNode<FileContent> rightRoot = add( pool, ignore, "", right, false );
//
//        pool.getDirs().setRoots( leftTask.getLeftRoot(), (LindenDirNode<FileContent>)rightRoot );
//
//        return pool;
//    }
//
//
//    private LindenNode<Content> add( TreeMatchingTask<Content> pool, Ignore ignore, String pathPart, File file, boolean isSrc) {
//
//        pathPart += "/" + file.getName();
//
//        if ( ignore.match( pathPart )) { // || ignore.ignoreByPath( file.getPath() )) {
//            return null;
//        }
//
//        if ( file.isDirectory() ) {
//            return addDir( pool, ignore, pathPart, file, isSrc );
//        } else {
//            return addLeaf( pool, file, isSrc );
//        }
//    }
//
//    private LindenDirNode<FileContent> addDir( TreeMatchingTask pool, Ignore ignore, String pathPart, File file, boolean isSrc ) {
//
////        if ( ignore.ignore( file.getName() ) || ignore.ignoreByPath( file.getPath())) {
////            return null;
////        }
//
//        List<LindenNode<FileContent>> kids = new ArrayList<LindenNode<FileContent>>();
//
//        for ( File child : file.listFiles() ) {
//
//            LindenNode<FileContent> kid = add( pool, ignore, pathPart, child, isSrc );
//
//            if ( kid != null ) {
//                kids.add( kid );
//            }
//        }
//
//        LindenDirNode<FileContent> dir = new DirTreeNodeImpl( fileContentFactory, file, kids, isSrc );
//
//        if ( isSrc ) {
//            pool.getDirs().addLeft( dir );
//        } else {
//            pool.getDirs().addRight( dir );
//        }
//
//        return dir;
//    }
//
//    private LindenNode<FileContent> addLeaf( TreeMatchingTask pool, File file, boolean isSrc) {
//
//        assert( !file.isDirectory() );
//
//        LindenNode<FileContent> lfm = new LeafTreeNodeImpl( fileContentFactory, file, isSrc );
//
//        if ( isSrc ) {
//            pool.getLeaves().addLeft( lfm );
//        } else {
//            pool.getLeaves().addRight( lfm );
//        }
//
//        return lfm;
//    }
//
//
//}
