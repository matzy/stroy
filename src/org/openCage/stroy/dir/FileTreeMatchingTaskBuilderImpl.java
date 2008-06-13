package org.openCage.stroy.dir;

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
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class FileTreeMatchingTaskBuilderImpl implements FileTreeMatchingTaskBuilder {

    private final FileContentFactory fileContentFactory;

    @Inject
    public FileTreeMatchingTaskBuilderImpl( FileContentFactory fileContentFactory ) {
        this.fileContentFactory = fileContentFactory;
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
