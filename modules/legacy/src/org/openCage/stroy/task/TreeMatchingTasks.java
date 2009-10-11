package org.openCage.stroy.task;

import org.openCage.vfs.protocol.TreeNode;

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

public class TreeMatchingTasks {

    public static boolean isParentMatch( MatchingTask<TreeNode> task, TreeNode a, TreeNode b ) {

        if ( a.getParent() == null ) {
            return b.getParent() == null;
        } else if ( b.getParent() == null ) {
            return false;
        }

        TreeNode matchedParent = task.getMatch( a.getParent() );

        if ( matchedParent == null ) {
            return false;
        }


        return matchedParent == b.getParent();
    }
}
