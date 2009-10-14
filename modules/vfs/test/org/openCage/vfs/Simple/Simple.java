/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.vfs.Simple;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import org.openCage.vfs.impl.SimpleStringTreeBuilder;
import org.openCage.vfs.impl.TreeNodeModel;
import org.openCage.vfs.protocol.TreeNode;

/**
 *
 * @author spfab
 */
public class Simple {
    public static void main(String args[])  {

        SimpleStringTreeBuilder b = new SimpleStringTreeBuilder();

        TreeNode tree = b.d( "f", b.l("a"),
                                  b.d( "g", b.l("b"),
                                            b.l("c")));
        


        JTree binTree = new JTree(new TreeNodeModel(tree));
        binTree.setShowsRootHandles(true);
        JFrame frame = new JFrame("Infinite Binary Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(binTree));
        frame.setSize(new java.awt.Dimension(400, 400));
        frame.setVisible(true);
    }

}
