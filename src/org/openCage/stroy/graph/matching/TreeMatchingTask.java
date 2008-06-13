package org.openCage.stroy.graph.matching;

import org.openCage.stroy.content.Content;
import org.openCage.stroy.graph.SameContent;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.graph.node.TreeLeafNode;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.task.MatchingTask;
import org.openCage.stroy.ui.ChangeVector;
import org.openCage.stroy.Difference;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public interface TreeMatchingTask<T extends Content> {

    public void addDup(final SameContent<T> sh);

    public MatchingTask<TreeLeafNode<T>>  getLeaves();
    public MatchingTask<TreeDirNode<T>>   getDirs();

    public boolean     isMatched(  final TreeNode<T> obj );
    public TreeNode<T> getMatch(   final TreeNode<T> obj );
    public double      getMatchQuality( final TreeNode<T> obj );

    public Collection<SameContent<T>> getDuplicates();

    public void shortStatus();

    public void status();

    public int                            getLeftLeaveCount();
    public int getLeftDirCount();
    public int getRightLeaveCount();
    public int getRightDirCount();

    public Collection<TreeLeafNode<T>>    getModifiedLeaves();
    public Collection<TreeLeafNode<T>>    getRenamedLeaves();
    public Collection<TreeLeafNode<T>>    getMovedLeaves();

    public Collection<TreeDirNode<T>>     getRenamedDirs();
    public Collection<TreeDirNode<T>>     getMovedDirs();

    public Collection<TreeLeafNode<T>>     getComplexModifiedLeaves();
    
    

    public Collection<TreeLeafNode<T>> getUnmatchedLeftFiles();
    public Collection<TreeDirNode<T>> getUnmatchedLeftDirs();
    public Collection<TreeLeafNode<T>> getUnmatchedRightFiles();
    public Collection<TreeDirNode<T>> getUnmatchedRightDirs();

    public TreeDirNode<T> getLeftRoot();
    public TreeDirNode<T> getRightRoot();

    public boolean isContentChanged( TreeNode<T> obj );

    public ChangeVector getChangeVector( TreeNode<T> obj );

    public void remove(TreeNode<T> node);

    void merge();

    public MatchingTask<TreeDirNode<T>> getDirTask();

    public MatchingTask<TreeLeafNode<T>> getFileTask();

}
