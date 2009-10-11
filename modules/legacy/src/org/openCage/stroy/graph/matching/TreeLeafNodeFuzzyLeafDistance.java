package org.openCage.stroy.graph.matching;

import com.google.inject.Inject;
import org.openCage.stroy.TreeLeafDistance;
import org.openCage.vfs.protocol.Content;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.task.TreeMatchingTasks;

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

public class TreeLeafNodeFuzzyLeafDistance implements TreeLeafDistance {

    private final TreeLeafDistanceMetric metric;

    @Inject
    public TreeLeafNodeFuzzyLeafDistance( final TreeLeafDistanceMetric metric ) {
        this.metric = metric;
    }


    public double distance( MatchingTask<TreeNode> parents,
                            TreeNode a,
                            TreeNode b) {
        
        if ( ! a.getContent().getType().equals( b.getContent().getType())  ) {
            return 1.0;
        }

        double contentDist = Math.max( .001, a.getContent().getFuzzyHash().distance( b.getContent().getFuzzyHash() ));


        boolean sameName   = a.getContent().getName().equals( b.getContent().getName() );

        boolean sameParent = TreeMatchingTasks.isParentMatch( parents, a, b);

        return metric.distance( contentDist, sameName, sameParent );
    }

}
