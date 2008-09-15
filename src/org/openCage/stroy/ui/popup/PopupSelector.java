package org.openCage.stroy.ui.popup;

import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.ui.util.NodeToNode;
import org.openCage.stroy.content.Content;
import org.openCage.util.logging.Log;

import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.regex.Pattern;

import com.muchsoft.util.Sys;

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

/**
 * depending on type open a different popup
 * TODO: popup uses hiding for similar purposes: unify?
 */
public class PopupSelector<T extends Content> {
    private final TreeMatchingTask<T> taskRight;
    private final TreeMatchingTask<T> taskLeft;
    private final DiffPopup unmatchedFilePopup;
    private final DiffPopup filePopup;
    private final DiffPopup bundlePopup;
    private final DiffPopup dirPopup;
    private final Pattern isBundle = Pattern.compile( ".+\\..*" );

    public PopupSelector( final TreeMatchingTask<T> taskLeft, final TreeMatchingTask<T> taskRight) {
        this.taskLeft = taskLeft;
        this.taskRight = taskRight;

        filePopup          = new DiffPopup( taskLeft, taskRight, true, false, true );
        unmatchedFilePopup = new DiffPopup( taskLeft, taskRight, true, false, false );
        bundlePopup        = new DiffPopup( taskLeft, taskRight, false, true, false );
        dirPopup           = new DiffPopup( taskLeft, taskRight, false, false, false );
    }

    public void open( MouseEvent event, TreePath path ) {

        TreeNode tn = NodeToNode.pathToNode( path );

        if ( tn == null ) {
            // tree element without treenode, i.e. a ghost node
            // TODO popup for merge?
            return;
        }

        if ( tn.isLeaf() ) {
            filePopup.open( event, path );
            return;
        }

        if ( Sys.isMacOSX() && isBundle.matcher( ((Content)tn.getContent()).getName() ).matches() ) {
            bundlePopup.open( event, path );
            return;
        }


        dirPopup.open( event, path );
    }


}
