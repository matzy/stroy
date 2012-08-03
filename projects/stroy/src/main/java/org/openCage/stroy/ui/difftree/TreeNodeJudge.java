package org.openCage.stroy.ui.difftree;

import org.openCage.lang.iterators.Count;
import org.openCage.lang.iterators.Iterators;
import org.openCage.util.ui.skvTree.JudgeBlock;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.ui.Colors;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.diff.ContentDiff;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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

public class TreeNodeJudge<T extends Content> implements JudgeBlock {

    private final TreeMatchingTask<T> matching;

    public TreeNodeJudge( TreeMatchingTask<T> matching ) {
        this.matching = matching;
    }


    public DefaultMutableTreeNode getInteresting(List<DefaultMutableTreeNode> block) {
        for (Count<DefaultMutableTreeNode> node : Iterators.count(block)) {

            if ( node.obj().getUserObject() instanceof String ) {
                int i = 0;
            }

            TreeNode<T> nn = ((UINode<T>)node.obj().getUserObject()).get();
                                        
            final ChangeVector cv = matching.getChangeVector( nn );

            if ( cv.isAny()) {
                return node.obj();
            }

        }

        return null;
    }


    public List<Color> getColor(List<DefaultMutableTreeNode> block) {
        if ( null == getInteresting( block )) {
            return Collections.EMPTY_LIST;
        }

        List<Color> colors = new ArrayList<Color>();


        boolean only      = false;
        boolean structure = false;
        boolean content   = false;
        boolean contentUnknown   = false;

        for ( DefaultMutableTreeNode dmtn : block ) {
            TreeNode<T> node = ((UINode)dmtn.getUserObject()).get();
            final ChangeVector cv = matching.getChangeVector( node );

            if ( cv.only ) {
                if ( !only ) {
                    only = true;
                    colors.add( Colors.ONLYHERE );
                }
            } else if ( cv.name || cv.parent ) {
                if ( !structure) {
                    structure = true;
                    colors.add( Colors.STRUCTUR );
                }
            }

            ContentDiff diff = ((UINode)dmtn.getUserObject()).getContentChangedLeft();

            if ( diff != null ) {

                if ( !content && (diff == ContentDiff.different || diff == ContentDiff.sameEnough )) {
                    content = true;
                    colors.add( Colors.CONTENT );
                }

                if ( !contentUnknown && (diff == ContentDiff.unknown )) {
                    contentUnknown = true;
                    colors.add( Colors.UNKNOWN );
                }

            }

            diff = ((UINode)dmtn.getUserObject()).getContentChangedRight();

            if ( diff != null ) {

                if ( !content && (diff == ContentDiff.different || diff == ContentDiff.sameEnough )) {
                    content = true;
                    colors.add( Colors.CONTENT );
                }

                if ( !contentUnknown && (diff == ContentDiff.unknown )) {
                    contentUnknown = true;
                    colors.add( Colors.UNKNOWN );
                }

            }
        }


        return colors;
    }
}

