package org.openCage.stroy.dir;

import org.openCage.stroy.content.FileContent;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.node.ContentTreeNodeBaseImpl;
import org.openCage.stroy.content.FileContentFactory;

import java.io.File;
import java.util.List;
import java.util.Collection;

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
public class DirTreeNodeImpl extends ContentTreeNodeBaseImpl<FileContent> implements TreeDirNode<FileContent> {

    private List<TreeNode<FileContent>> kids;

    public DirTreeNodeImpl( final FileContentFactory factory, final File file, List<TreeNode<FileContent>> kids, boolean generateId  ) {
        super( factory.create(  file ), generateId );
        this.kids   = kids;

        for ( TreeNode<FileContent> kid : this.kids ) {
            kid.setParent( this );
        }

    }


    public Collection<TreeNode<FileContent>> getChildren() {
        return kids;
    }

    public void removeChild(TreeNode<FileContent> child) {
        kids.remove( child );
        child.setParent( null );
    }

    public boolean isLeaf() {
        return false;
    }

    public void unsetId() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
