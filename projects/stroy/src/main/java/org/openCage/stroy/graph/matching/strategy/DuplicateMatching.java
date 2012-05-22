package org.openCage.stroy.graph.matching.strategy;

import org.openCage.stroy.TreeLeafDistance;
import org.openCage.stroy.locale.Message;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.array.MatchBestConnections2;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.SameContent;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.matching.strategy.MatchStrategy;
import org.openCage.util.logging.Log;

import java.util.List;

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

@SuppressWarnings({"HardCodedStringLiteral"})
public class DuplicateMatching<T extends Content> implements MatchStrategy<T> {
    public void match(TreeMatchingTask<T> treeMatchingTask, Reporter reporter) {

        Log.fine( "duplicate matching" );
        reporter.title( Message.get( "Strategy.Duplicates" ));

        // TODO reporting

        for (SameContent<T> sh : treeMatchingTask.getDuplicates() ) {
            match(treeMatchingTask, sh.getSources(), sh.getTargets() );
        }
    }


    // TODO: refactor with other 

    private void match( final TreeMatchingTask<T> matchingTask, List<TreeLeafNode<T>> src, List<TreeLeafNode<T>> tgt ) {
        if ( src.size() != 0 && tgt.size() != 0 ) {

            MatchBestConnections2<MatchingTask<TreeDirNode<T>>,TreeLeafNode<T>> match =
                    new MatchBestConnections2<MatchingTask<TreeDirNode<T>>,TreeLeafNode<T>>( new TreeLeafDistance<T>() {

                public double distance(MatchingTask<TreeDirNode<T>> info, TreeLeafNode<T> a, TreeLeafNode<T> b) {
                    double dist = 1.0;
                    // parent
                    if ( matchingTask.isMatched( a.getParent())) {
                        TreeDirNode aparentMatch = matchingTask.getDirs().getMatch( a.getParent());
                        if ( aparentMatch == b.getParent() ) {
                            dist *= 0.25;
                        }
                    }
                    // TODO ???
//                    else if ( b.getParent().getId() == null ) {
//                        dist *= 0.3;
//                    }

                    if ( a.getContent().getName().equals( b.getContent().getName() )) {
                        dist *= 0.2;
                    }

                    return dist;
                }
            },
                    true, new NullReporter() );

            match.match( matchingTask.getDirs(), matchingTask.getLeaves(), src, tgt  );
        }
    }

}
