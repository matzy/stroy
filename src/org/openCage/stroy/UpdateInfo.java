package org.openCage.stroy;

import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;

public class UpdateInfo extends JFrame {


    public UpdateInfo() {
        JPanel top = new JPanel();

//        setSize( 400, 400 );

        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        // TODO localize
        layout.row().add( new JLabel("There is a new version" ), 3).add( "1.1.1");
        layout.row().add( new JLabel("Go to download page" ), 3);
        layout.row().add( new JLabel("" ), 3);
        layout.row().add( new JLabel("Never show this again" ), 3);

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.CENTER  );
        pack();
    }

}
