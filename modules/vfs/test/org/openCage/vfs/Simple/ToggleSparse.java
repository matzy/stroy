/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openCage.vfs.Simple;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import org.openCage.vfs.impl.SimpleStringTreeBuilder;
import org.openCage.vfs.protocol.VNode;

/**
 *
 * @author spfab
 */
public class ToggleSparse {
  public static void main(String args[])  {

        SimpleStringTreeBuilder b = new SimpleStringTreeBuilder();

        VNode tree = b.d( "f", b.l("a"), b.l( "c"), b.l("f"),
                               b.d( "g", b.l("b"),
                                         b.l("c")));

        final ToggleModel model  = new ToggleModel(tree);
        JButton toggle = new JButton( "toggle" );
        toggle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model.toggle();
            }
        });

        JTree binTree = new JTree( model );
        binTree.setShowsRootHandles(true);
        JFrame frame = new JFrame("Infinite Binary Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(binTree), BorderLayout.NORTH);
        frame.getContentPane().add( toggle, BorderLayout.SOUTH );
        frame.setSize(new java.awt.Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);
  }
}
