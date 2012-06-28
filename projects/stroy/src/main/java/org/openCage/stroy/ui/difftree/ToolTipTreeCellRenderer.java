package org.openCage.stroy.ui.difftree;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

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
