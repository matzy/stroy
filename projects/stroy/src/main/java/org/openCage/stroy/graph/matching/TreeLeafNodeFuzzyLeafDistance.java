package org.openCage.stroy.graph.matching;

import com.google.inject.Inject;
import org.openCage.stroy.TreeLeafDistance;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.task.TreeMatchingTasks;

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

public class TreeLeafNodeFuzzyLeafDistance<T extends Content> implements TreeLeafDistance<T> {

    private final TreeLeafDistanceMetric metric;

    @Inject
    public TreeLeafNodeFuzzyLeafDistance( final TreeLeafDistanceMetric metric ) {
        this.metric = metric;
    }


    public double distance( MatchingTask<TreeDirNode<T>> parents,
                            TreeLeafNode<T> a,
                            TreeLeafNode<T> b) {
        
        if ( ! a.getContent().getType().equals( b.getContent().getType())  ) {
            return 1.0;
        }

        double contentDist = Math.max( .001, 1 - a.getContent().getFuzzyHash().fuzzyEqual( b.getContent().getFuzzyHash() ));


        boolean sameName   = a.getContent().getName().equals( b.getContent().getName() );

        boolean sameParent = TreeMatchingTasks.isParentMatch( parents, a, b);

        return metric.distance( contentDist, sameName, sameParent );
    }
}
