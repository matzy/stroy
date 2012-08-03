package org.openCage.stroy.zip;

import org.openCage.lang.inc.Null;
import org.openCage.stroy.algo.fuzzyHash.FuzzyHash;
import org.openCage.stroy.algo.tree.Noed;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.content.FileContent;
import org.openCage.stroy.content.ReducedContent;
import org.openCage.stroy.dir.DirTreeNodeImpl;
import org.openCage.stroy.dir.FileTreeMatchingTaskBuilder;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.fuzzyHash.FuzzyHashNever;
import org.openCage.stroy.graph.node.*;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutral;
import org.openCage.stroy.content.FileContentFactory;
import org.openCage.util.io.FileUtils;
import org.openCage.util.logging.Log;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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

public class ZipTreeMatchingTaskBuilder implements FileTreeMatchingTaskBuilder {

    @Inject
    public ZipTreeMatchingTaskBuilder() {
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

        TreeNode<Content> oneRoot = add( pool, ignore, one, Null.is(task) );

        if ( oneRoot.isLeaf()  ) {
            Log.severe( "oops can't handle leafs yet" );
        }

        pool.getDirs().setRoot(oneRoot, Null.is(task));

        return pool;
    }


    private TreeNode<Content> add( TreeMatchingTask<Content> pool, Ignore ignore, File file, boolean isSrc) {

        TreeNode<Content> root = null;

        Map<String, TreeNode<Content>> nodes = new HashMap<String, TreeNode<Content>>();

        ZipFile zf = null;
        try {
            zf = new ZipFile( file );

            for ( Enumeration<? extends ZipEntry> e = zf.entries(); e.hasMoreElements(); )
            {
                ZipEntry entry = e.nextElement();
                String   elemPath  = FileUtils.normalizePath(entry.getName());

                String   parentPath = new File( elemPath ).getParent();
                String   name       = new File( elemPath ).getName();

                if ( ignore.match( elemPath )) {
                    // filter
                    continue;
                }

                TreeNode<Content> node = null;

                // TODO windows ?
                if ( parentPath.equals( "/" )) {
                    if ( root != null ) {
                        throw new Error( "zip with more than one top entry (i.e. the content of s dir without the dir NOT Supported yet)" );
                    }
                    root = SimpleTreeNode.makeDir((Content)new ReducedContent(name, "", new FuzzyHashNever(), ""));
                    node = root;
                    if ( isSrc ) {
                        pool.getDirs().addLeft((TreeDirNode<Content>) node);
                    } else {
                        pool.getDirs().addRight((TreeDirNode<Content>) node);
                    }
                } else {
                    TreeNode<Content> parent = nodes.get( parentPath );

                    if ( parent == null ) {
                        // assume filtered
                        continue;
                        //throw new Error( "strange order zip not supported yet" );
                    }

                    if ( entry.isDirectory() ) {
                        node = SimpleTreeNode.makeDir((Content)new ReducedContent(name, "", new FuzzyHashNever(), ""));
                        if ( isSrc ) {
                            pool.getDirs().addLeft((TreeDirNode<Content>) node);
                        } else {
                            pool.getDirs().addRight((TreeDirNode<Content>) node);
                        }

                    } else {

                        node = new SimpleTreeNode<Content>( new ZipContent( file.getAbsolutePath(), entry, name) );

                        if ( isSrc ) {
                            pool.getLeaves().addLeft((TreeLeafNode<Content>) node);
                        } else {
                            pool.getLeaves().addRight((TreeLeafNode<Content>) node);
                        }
                    }

                    SimpleTreeNode.addChild((TreeDirNode<Content>) parent, node);

                }

                nodes.put(elemPath, node);
            }
        } catch( IOException e ) {
//            Log.warning( "could not open zipfile " + path );
            throw new IllegalArgumentException( e );
        } finally {

            if ( zf != null ) {
                try {
                    zf.close();
                } catch ( IOException e ) {
//                    Log.warning( "could not close zipfile " + path );
                }
            }

        }


        return root;
    }
}
