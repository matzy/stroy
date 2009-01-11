package org.openCage.stroy.ui.difftree;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

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


public class ToolTipTreeCellRenderer implements TreeCellRenderer {

    DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

    public ToolTipTreeCellRenderer() {
    }

    public Component getTreeCellRendererComponent( JTree   tree,
                                                   Object  value,
                                                   boolean selected,
                                                   boolean expanded,
                                                   boolean leaf,
                                                   int     row,
                                                   boolean hasFocus) {

        renderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        if (value != null) {

            if (value instanceof DefaultMutableTreeNode) {

                UINode uin = (UINode)((DefaultMutableTreeNode) value).getUserObject();

                renderer.setToolTipText( uin.getToolTip() );
            } else {
                //tipKey = tree.convertValueToText(value, selected, expanded, leaf, row, hasFocus);
            }
        }
        return renderer;
    }
}
