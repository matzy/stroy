package org.openCage.stroy.ui.difftree.ep;

import javax.swing.*;

public class GradientSelection extends JFrame {

    public static void main( String[] args ) {
        new GradientSelection().setVisible( true );
    }

    public GradientSelection() {
        String[] rows = new String[1000];

        for ( int i = 0; i < 1000; ++i ) {
            rows[i] = "i am row" + i;
        }
        rows[0] += "loooooooooooooooong";

        JTree tree = new EPJTree( rows );
        JScrollPane scroll = new JScrollPane( tree );
        scroll.setSize( 400, 100 );

        add(scroll );

        setSize( 500, 100 );

        pack();
    }
}
