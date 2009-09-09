package org.openCage.stroy.ui.popup;

import org.openCage.stroy.graph.node.TreeNode;
import org.openCage.stroy.content.Content;
import org.openCage.stroy.file.FileTypes;
import org.openCage.util.platform.Platform;
import org.openCage.util.logging.Log;
import org.openCage.util.io.FileUtils;
import com.muchsoft.util.Sys;

import java.util.regex.Pattern;

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

/**
 * Methods to decide which menu items to show in the diff popup dialog
 */
public class DiffPopupDecider {

    FileTypes fileTypes = FileTypes.create();

    public boolean showOpen( TreeNode node ) {

        if ( node.isLeaf() ) {
            return node.getContent() != null && hasOpenApp( node );
        }

        if ( Platform.isBundle(( (Content)node.getContent()).getName())) {
            return hasOpenApp( node );
        }

        return false;
    }

    public boolean showOpenWith( TreeNode node ) {

        if ( node.isLeaf() ) {
            return node.getContent() != null && !hasOpenApp( node );
        }

        if ( Platform.isBundle(( (Content)node.getContent()).getName())) {
            return !hasOpenApp( node );
        }

        return false;
    }

    public boolean showOpenAsText( TreeNode node ) {
        return node.isLeaf() && node.getContent() != null;
    }

    private boolean hasOpenApp( TreeNode node ) {
        if ( node.getContent() == null ) {
            throw Log.log( new IllegalArgumentException( "node has no content" ));
        }

        return fileTypes.hasOpen( FileUtils.getExtension(((Content)node.getContent()).getName() ));
    }


    public boolean showDiff( TreeNode node ) {
        boolean leaf  =  node.isLeaf();
        boolean content = node.getContent() != null;
        boolean diffType = fileTypes.hasDiffType( FileUtils.getExtension(((Content)node.getContent()).getName() ));

        return node.isLeaf() &&
               node.getContent() != null &&
               fileTypes.hasDiffType( FileUtils.getExtension(((Content)node.getContent()).getName() ));
    }

    public boolean showDiffWith( TreeNode node ) {
        return node.isLeaf() &&
               node.getContent() != null &&
               !fileTypes.hasDiffType( FileUtils.getExtension(((Content)node.getContent()).getName() ));
    }
}
