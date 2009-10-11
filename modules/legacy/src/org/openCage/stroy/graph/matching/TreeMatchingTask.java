package org.openCage.stroy.graph.matching;

import org.openCage.vfs.protocol.Content;
import org.openCage.stroy.graph.SameContent;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.vfs.protocol.TreeNode;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.ui.ChangeVector;

import java.util.Collection;


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

public interface TreeMatchingTask {

    public void addDup(final SameContent sh);

    public MatchingTask<TreeNode>  getLeaves();
    public MatchingTask<TreeNode>   getDirs();

    public boolean     isMatched(  final TreeNode obj );
    public TreeNode    getMatch(   final TreeNode obj );
    public double      getMatchQuality( final TreeNode obj );

    public Collection<SameContent> getDuplicates();

    public void shortStatus();

    public void status();

    public int                            getLeftLeaveCount();
    public int getLeftDirCount();
    public int getRightLeaveCount();
    public int getRightDirCount();

    public Collection<TreeNode>    getModifiedLeaves();
    public Collection<TreeNode>    getRenamedLeaves();
    public Collection<TreeNode>    getMovedLeaves();

    public Collection<TreeNode>     getRenamedDirs();
    public Collection<TreeNode>     getMovedDirs();

    public Collection<TreeNode>     getComplexModifiedLeaves();
    
    

    public Collection<TreeNode> getUnmatchedLeftFiles();
    public Collection<TreeNode> getUnmatchedLeftDirs();
    public Collection<TreeNode> getUnmatchedRightFiles();
    public Collection<TreeNode> getUnmatchedRightDirs();

    public TreeNode getLeftRoot();
    public TreeNode getRightRoot();

    public boolean isContentChanged( TreeNode obj );

    public ChangeVector getChangeVector( TreeNode obj );

    public void remove(TreeNode node);

    void merge();

    public MatchingTask<TreeNode> getDirTask();

    public MatchingTask<TreeNode> getFileTask();

}
