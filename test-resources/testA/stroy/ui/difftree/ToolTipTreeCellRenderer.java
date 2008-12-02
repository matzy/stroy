package org.openCage.stroy.ui.difftree;

import org.openCage.stroy.ui.difftree.UINode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

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