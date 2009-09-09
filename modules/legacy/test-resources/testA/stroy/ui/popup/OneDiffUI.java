package org.openCage.stroy.ui.popup;

import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;

/**
 */
public class OneDiffUI extends JFrame {
    private final JTextField cmdField = new JTextField();
    private final JButton typeButton = new JButton( "for all ..." );
    private final JButton thisButton = new JButton( "for this file" );
    private final JButton cmdButton = new JButton( ".." );

    public OneDiffUI() {
        setTitle( "Open With" );

        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        layout.row().label( "command" ).add( cmdField, 5).add( cmdButton);
        layout.row().label( "remember" ).add( typeButton ).add( thisButton);

        setLayout( new BorderLayout());
        add(top, BorderLayout.CENTER);

        pack();
    }
}
