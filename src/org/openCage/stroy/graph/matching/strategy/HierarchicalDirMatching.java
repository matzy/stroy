package org.openCage.stroy.graph.matching.strategy;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.stroy.graph.matching.strategy.SimpleDirMatching;
import org.openCage.stroy.locale.Message;
import org.openCage.util.logging.Log;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class HierarchicalDirMatching<T extends Content> implements MatchStrategy<T> {

    private final SimpleDirMatching<T> simpleMatcher = new SimpleDirMatching<T>();

    public void match( TreeMatchingTask<T> treeMatchingTask, Reporter reporter) {

        Log.fine( "match dir hierarchical");
        reporter.title( Message.get( "Strategy.HierarchicalDir" ));


        // TODO more reports

        matchHir(treeMatchingTask, treeMatchingTask.getDirs().getLeftRoot(), reporter);
    }

    private void matchHir( TreeMatchingTask<T> treeMatchingTask, TreeDirNode<T> src, Reporter reporter) {

        if ( ! treeMatchingTask.isMatched( src )) {
            TreeDirNode<T> bestMatch = matchHirBest(treeMatchingTask, src );

            if ( bestMatch != null ) {

                // TODO: test
                // TODO: store quality
                treeMatchingTask.getDirs().match( src, bestMatch, 0.99 );


                for ( TreeNode<T> fm : src.getChildren() ) {
                    if ( !fm.isLeaf() ) {
                        simpleMatcher.matchInChildList(treeMatchingTask, reporter, (TreeDirNode<T>)fm, bestMatch );
                    }
                }
            } else {
                Log.fine( "deleted :" + src );
            }
        }

        for ( TreeNode<T> fm : src.getChildren() ) {
            if ( !fm.isLeaf() ) {
                matchHir(treeMatchingTask, (TreeDirNode<T>)fm, reporter);
            }
        }
    }

    private TreeDirNode<T> matchHirBest( TreeMatchingTask<T> treeMatchingTask, TreeDirNode<T> src) {

        double         bestDist  = 2.0;
        TreeDirNode<T> bestMatch = null;

        for ( TreeDirNode<T> tgt : treeMatchingTask.getDirs().getUnmatchedRight() ) {
            double dist = distance(treeMatchingTask, src, tgt );
            if ( dist < bestDist && dist < 0.31) {
                bestDist = dist;
                bestMatch = tgt;
            }
        }

        return bestMatch;
    }

    private double distance( TreeMatchingTask<T> treeMatchingTaskImpl, TreeDirNode<T> a, TreeDirNode<T> b) {

        int deleted       = 0;
        int same          = 0;
        int sameName      = 0;

        for ( TreeNode<T> kid : a.getChildren() ) {
            if ( !treeMatchingTaskImpl.isMatched( kid ) ) {
                boolean findName = false;
                for ( TreeNode<T> bkid : b.getChildren() ) {

                    if ( ! treeMatchingTaskImpl.isMatched( bkid ) ) {
                        if ( bkid.getContent().getName().equals( kid.getContent().getName() )) {
                            findName = true;
                            sameName++;
                            break;
                        }
                    }
                }

                if ( !findName ) {
                    deleted++;
                }

            } else {
                if ( treeMatchingTaskImpl.getMatch( kid ).getParent() == b ) {
                    same++;
                } else {
                    deleted++;
                }
            }
        }

        double val = Math.min( 1.0, 1 - (((double)same + 0.5 * sameName )/ ((double)a.getChildren().size() )));

        if ( a.getContent().getName().equals( b.getContent().getName() ) ) {
            val *= 0.5;
        }

        return val;
    }



}
