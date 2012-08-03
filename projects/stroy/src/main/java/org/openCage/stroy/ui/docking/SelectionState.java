package org.openCage.stroy.ui.docking;

import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.content.Content;

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

public class SelectionState<T extends Content> {

    private final TreeNode<T> node;
    private final boolean               match;
    private final boolean               parentMatch;
    private final boolean               master;

//    private final boolean content;
//    private final boolean name;
//    private final boolean parent;
//    private final boolean parentRenamed;

    public SelectionState( final TreeNode<T> node,
                           final boolean               match,
                           final boolean               parentMatch,
                           final boolean               master ) {
//                           final boolean content,
//                           final boolean name,
//                           final boolean parent,
//                           final boolean parentRenamed
//                           ) {
        this.node = node;
        this.match = match;
        this.master = master;
        this.parentMatch = parentMatch;
//        this.content = content;
//        this.name = name;
//        this.parent = parent;
//        this.parentRenamed = parentRenamed;
        
        assert( !(parentMatch && match) );
    }


    public TreeNode<T> getNode() {
        return node;
    }

    public boolean isMatch() {
        return match;
    }

    public boolean isMaster() {
        return master;
    }


    public boolean isParentMatch() {
        return parentMatch;
    }

    public String toString() {
        return "(sel " + node + " match:" + match + "/" + parentMatch + " master:"  + master + ")";
    }
}
