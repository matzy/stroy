package org.openCage.stroy.dir;

import org.openCage.lang.inc.Null;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.content.FileContent;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutral;
import org.openCage.stroy.content.FileContentFactory;
import org.openCage.util.logging.Log;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import com.google.inject.Inject;

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

public class FileTreeMatchingTaskBuilderImpl implements FileTreeMatchingTaskBuilder {

    private final FileContentFactory fileContentFactory;

    @Inject
    public FileTreeMatchingTaskBuilderImpl( FileContentFactory fileContentFactory ) {
        this.fileContentFactory = fileContentFactory;
    }

    @Override
    public TreeMatchingTask<Content> build(TreeMatchingTask<Content> task, Ignore ignore, File one) {

        TreeMatchingTask pool = new TreeMatchingTaskNeutral();
        if (!Null.is(task)) {
            pool = task;
        }

        if ( ignore.match( "/" + one.getName() )) {
            throw new Error( "top level dir is in ignore pattern" );
        }

        TreeNode<FileContent> oneRoot = add( pool, ignore, "", one, Null.is(task) );

        if ( oneRoot.isLeaf()  ) {
            Log.severe( "oops can't handle leafs yet" );
        }

        pool.getDirs().setRoot(oneRoot, Null.is(task));

        return pool;
    }

    public TreeMatchingTask<FileContent> build(Ignore ignore, File one, File two) {

        //TreeMatchingTaskImpl pool = new TreeMatchingTaskImpl();
        TreeMatchingTask pool = new TreeMatchingTaskNeutral();

        if ( ignore.match( "/" + one.getName())  || ignore.match( "/" + two.getName() )) {
            throw new Error( "top level dir is in ignore pattern" );
        }

        TreeNode<FileContent> oneRoot = add( pool, ignore, "", one, true );
        TreeNode<FileContent> twoRoot = add( pool, ignore, "", two, false );

        if ( oneRoot.isLeaf() || twoRoot.isLeaf() ) {
            Log.severe( "oops can't handle leafs yet" );
        }

        pool.getDirs().setRoots( (TreeDirNode<FileContent>)oneRoot, (TreeDirNode<FileContent>)twoRoot );

        return pool;
    }

    public TreeMatchingTask<FileContent> build(Ignore ignore, TreeMatchingTask<FileContent> leftTask, File right) {
//        TreeMatchingTaskImpl pool = new TreeMatchingTaskImpl();
        TreeMatchingTask pool = new TreeMatchingTaskNeutral();

        if ( ignore.match( "/" + right.getName() )) {
            throw new Error( "top level dir is in ignore pattern" );
        }


        for ( TreeLeafNode<FileContent> nd : leftTask.getLeaves().getUnmatchedLeft() ) {
            pool.getLeaves().addLeft( nd );
        }
        for ( TreeDirNode<FileContent> nd : leftTask.getDirs().getUnmatchedLeft() ) {
            pool.getDirs().addLeft( nd );
        }

        TreeNode<FileContent> rightRoot = add( pool, ignore, "", right, false );

        pool.getDirs().setRoots( leftTask.getLeftRoot(), (TreeDirNode<FileContent>)rightRoot );

        return pool;
    }


    private TreeNode<FileContent> add( TreeMatchingTask pool, Ignore ignore, String pathPart, File file, boolean isSrc) {

        pathPart += "/" + file.getName();

        if ( ignore.match( pathPart )) { // || ignore.ignoreByPath( file.getPath() )) {
            return null;
        }

        if ( file.isDirectory() ) {
            return addDir( pool, ignore, pathPart, file, isSrc );
        } else {
            return addLeaf( pool, file, isSrc );
        }
    }

    private TreeDirNode<FileContent> addDir( TreeMatchingTask pool, Ignore ignore, String pathPart, File file, boolean isSrc ) {

//        if ( ignore.ignore( file.getName() ) || ignore.ignoreByPath( file.getPath())) {
//            return null;
//        }

        List<TreeNode<FileContent>> kids = new ArrayList<TreeNode<FileContent>>();

        for ( File child : file.listFiles() ) {

            TreeNode<FileContent> kid = add( pool, ignore, pathPart, child, isSrc );

            if ( kid != null ) {
                kids.add( kid );
            }
        }

        TreeDirNode<FileContent> dir = new DirTreeNodeImpl( fileContentFactory, file, kids, isSrc );

        if ( isSrc ) {
            pool.getDirs().addLeft( dir );
        } else {
            pool.getDirs().addRight( dir );
        }

        return dir;
    }

    private TreeLeafNode<FileContent> addLeaf( TreeMatchingTask pool, File file, boolean isSrc) {

        assert( !file.isDirectory() );

        TreeLeafNode<FileContent> lfm = new LeafTreeNodeImpl( fileContentFactory, file, isSrc );

        if ( isSrc ) {
            pool.getLeaves().addLeft( lfm );
        } else {
            pool.getLeaves().addRight( lfm );
        }

        return lfm;
    }


}
