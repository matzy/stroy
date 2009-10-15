/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.vfs.Simple;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.openCage.vfs.protocol.VNode;

/**
 *
 * @author spfab
 */
public class ToggleModel implements TreeModel {

    private VNode root;
    private boolean full = true;
    private List<TreeModelListener> listners = new ArrayList<TreeModelListener>();

    public ToggleModel( VNode root ) {
        this.root = root;
    }

    public Object getRoot() {
        return root;
    }

    public Object getChild(Object parent, int index) {
        return getChilds((VNode) parent).get(index);
    }

    public int getChildCount(Object parent) {
        return getChilds((VNode)parent).size();
    }

    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    public int getIndexOfChild(Object parent, Object child) {
        int idx = 0;
        for ( VNode node : getChilds((VNode) parent)) {
            if ( node.equals(child)) {
                return idx;
            }
            ++idx;
        }

        return -1;
    }

    public void addTreeModelListener(TreeModelListener l) {
        listners.add(l);
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }

    private List<VNode> getChilds( VNode parent ) {
        List<VNode> kids = new ArrayList<VNode>();

        if ( parent.getChildren() != null ) {
            for ( VNode node : parent.getChildren() ) {
                if ( full || !node.getContent().getName().equals("c")) {
                    kids.add(node);
                }
            }
        }
        
        return kids;
    }

    void toggle() {
        full = !full;
        for ( TreeModelListener listener : listners  ) {
            listener.treeStructureChanged( new TreeModelEvent(root, new TreePath(root)));
        }
    }

}
