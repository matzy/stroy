package org.openCage.stroy.ui.difftree;

import org.openCage.util.ui.skvTree.JudgeBlock;
import org.openCage.util.iterator.Count;
import org.openCage.util.iterator.Iterators;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTaskNeutral;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.DiffReporter;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.ui.Colors;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.diff.ContentDiff;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;

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

public class TreeNodeJudge<T extends Content> implements JudgeBlock {

    private final TreeMatchingTask<T> matching;

    public TreeNodeJudge( TreeMatchingTask<T> matching ) {
        this.matching = matching;
    }


    public DefaultMutableTreeNode getInteresting(List<DefaultMutableTreeNode> block) {
        for (Count<DefaultMutableTreeNode> node : Iterators.count( block )) {

            TreeNode<T> nn = ((UINode<T>)node.o.getUserObject()).get();
                                        
            final ChangeVector cv = matching.getChangeVector( nn );

            if ( cv.isAny()) {
                return node.o;
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

