package org.openCage.stroy.graph.matching.strategy;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.diff.ContentDiff;

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

public class ComputeDifference <T extends Content> implements MatchStrategy<T> {
    public void match(TreeMatchingTask<T> treeMatchingTask, Reporter reporter) {

        reporter.title( Message.get( "Strategy.DifferenceCalculation" ));

        leaves(treeMatchingTask, reporter);

        for ( TreeDirNode<T> left : treeMatchingTask.getDirs().getMatchedLeft() ) {
            boolean change = false;
            for ( TreeNode<T> child : left.getChildren() ) {
                if ( !treeMatchingTask.isMatched( child )) {
                    change = true;
                    break;
                }
            }

            if ( !change ) {
                TreeDirNode<T> right = (TreeDirNode<T>)treeMatchingTask.getMatch( left );

                for ( TreeNode<T> child : right.getChildren() ) {
                    if ( !treeMatchingTask.isMatched( child )) {
                        change = true;
                        break;
                    }
                }
            }

            if ( change ) {
                treeMatchingTask.getDirs().setDifference( left, ContentDiff.different );
            } else {
                treeMatchingTask.getDirs().setDifference( left, ContentDiff.same );
            }
        }
    }

    private void leaves(TreeMatchingTask<T> treeMatchingTask, Reporter reporter) {
        for (TreeLeafNode<T> left : treeMatchingTask.getLeaves().getMatchedLeft()) {

            if ( treeMatchingTask.getLeaves().getDifference( left ).equals( ContentDiff.unknown )) {
                reporter.detail( Message.get( "Progress.checking" ), left.toString() );
                String checksumLeft = left.getContent().getChecksum();

                TreeLeafNode<T> right = (TreeLeafNode<T>)treeMatchingTask.getMatch( left );
                reporter.detail( Message.get( "Progress.checking" ), right.toString() );
                String checksumRight = right.getContent().getChecksum();

                if ( checksumLeft.equals( checksumRight )) {
                    treeMatchingTask.getLeaves().setDifference( left, ContentDiff.same );
                } else {
                    treeMatchingTask.getLeaves().setDifference( left, ContentDiff.different );
                }
            }
        }
    }
}
