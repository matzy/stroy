/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage;

/**
 * InfiniteBinaryTree
 * @author Daniel Green
 * Superliminal Software
 *
 * A little example program to show the power of the TreeModel interface.
 * Running it displays a binary tree with numbers on each node.
 * Every positive number can be found somewhere in the tree.
 * See if you can find the node labeled "1000".
 * Hint: The base 2 representation of any node value can be read out from
 * the path leading to that node where opening the first child of a node
 * represents a 0 and opening the second child represents a 1.
 * Enjoy!
 */


import javax.swing.*;
import javax.swing.tree.*;

public class InfiniteBinaryTree implements TreeModel {

    public Object getRoot()  {
        return new Integer(1); // start at node number 1
    }

    public int getChildCount(Object parent)  {
        return 2; // because its a binary tree
    }

    public Object getChild(Object parent, int index) {
        return new Integer(2 * valueOf(parent) + index); // the magic formula
    }

    public int getIndexOfChild(Object parent, Object child) {
        return valueOf(child) % 2; // odd number == first child, even == second
    }

    public boolean isLeaf(Object node) {
        return false; // an infinite number of internal nodes and no leaves!
    }

    // stubbed out methods not needed for display though for any real
    // application at least the listener methods should be implemented.
    public void addTreeModelListener(javax.swing.event.TreeModelListener l) {}
    public void removeTreeModelListener(javax.swing.event.TreeModelListener l) {}
    public void valueForPathChanged(TreePath path, Object newValue) {}

    // helper function
    private int valueOf(Object obj)  {
        return ((Integer)obj).intValue();
    }


    /**
     * Creates a JTree from an InfiniteBinaryTree model and displays it.
     */
    public static void main(String args[])  {
        JTree binTree = new JTree(new InfiniteBinaryTree());
        binTree.setShowsRootHandles(true);
        JFrame frame = new JFrame("Infinite Binary Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(binTree));
        frame.setSize(new java.awt.Dimension(400, 400));
        frame.setVisible(true);
    }
}