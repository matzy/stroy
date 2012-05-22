package org.openCage.stroy.app;

import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeDirNode;
import org.openCage.stroy.content.Content;

import java.util.List;
import java.util.ArrayList;

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

public class Tasks<T extends Content> {

    private List<TreeMatchingTask<T>> tasks;
    private List<TreeDirNode<T>>      roots = new ArrayList<TreeDirNode<T>>();

    public Tasks( List<TreeMatchingTask<T>> tasks ) {
        this.tasks = tasks;

        for ( TreeMatchingTask<T> task : tasks ) {
            if ( roots.size() == 0 ) {
                roots.add( task.getLeftRoot() );
            } else {
                if ( roots.get( roots.size() - 1) != task.getLeftRoot() ) {
                    throw new IllegalArgumentException( "roots don't match" );
                }
            }

            roots.add( task.getRightRoot() );
        }

    }

    public List<TreeMatchingTask<T>> getTasks() {
        return tasks;
    }

    public List<TreeDirNode<T>> getRoots() {
        return roots;
    }
}
