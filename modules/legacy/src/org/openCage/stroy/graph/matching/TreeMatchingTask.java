package org.openCage.stroy.graph.matching;

import org.openCage.vfs.protocol.Content;
import org.openCage.stroy.graph.SameContent;
import org.openCage.vfs.protocol.VNode;
import org.openCage.vfs.protocol.VNode;
import org.openCage.vfs.protocol.VNode;
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

    public MatchingTask<VNode>  getLeaves();
    public MatchingTask<VNode>   getDirs();

    public boolean     isMatched(  final VNode obj );
    public VNode    getMatch(   final VNode obj );
    public double      getMatchQuality( final VNode obj );

    public Collection<SameContent> getDuplicates();

    public void shortStatus();

    public void status();

    public int                            getLeftLeaveCount();
    public int getLeftDirCount();
    public int getRightLeaveCount();
    public int getRightDirCount();

    public Collection<VNode>    getModifiedLeaves();
    public Collection<VNode>    getRenamedLeaves();
    public Collection<VNode>    getMovedLeaves();

    public Collection<VNode>     getRenamedDirs();
    public Collection<VNode>     getMovedDirs();

    public Collection<VNode>     getComplexModifiedLeaves();
    
    

    public Collection<VNode> getUnmatchedLeftFiles();
    public Collection<VNode> getUnmatchedLeftDirs();
    public Collection<VNode> getUnmatchedRightFiles();
    public Collection<VNode> getUnmatchedRightDirs();

    public VNode getLeftRoot();
    public VNode getRightRoot();

    public boolean isContentChanged( VNode obj );

    public ChangeVector getChangeVector( VNode obj );

    public void remove(VNode node);

    void merge();

    public MatchingTask<VNode> getDirTask();

    public MatchingTask<VNode> getFileTask();

}
