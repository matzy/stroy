/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openCage.vfs.impl;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.openCage.vfs.protocol.VNode;

/**
 *
 * @author spfab
 */
public class TreeNodeModel implements TreeModel {

    private VNode root;

    public TreeNodeModel(VNode root) {
        this.root = root;
    }

    public Object getRoot() {
        return root;
    }

    public Object getChild(Object parent, int index) {

        Collection<? extends VNode> childs = ((VNode) parent).getChildren();

        if (childs == null) {
            return null;
        }

        ArrayList ll = new ArrayList(childs);

        return ll.get(index);
    }

    public int getChildCount(Object parent) {
        Collection<? extends VNode> childs = ((VNode) parent).getChildren();

        if (childs == null) {
            return 0;
        }

        return ((VNode) parent).getChildren().size();
    }

    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    public int getIndexOfChild(Object parent, Object child) {

        int idx = 0;
        for (VNode node : ((VNode) parent).getChildren()) {
            if (node.equals(child)) {
                return idx;
            }
            idx++;
        }

        return -1;


    }

    public void addTreeModelListener(TreeModelListener l) {
        System.out.println("WOO");
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }
}
