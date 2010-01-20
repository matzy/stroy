package org.motpe.app;

import javax.swing.*;
import java.awt.*;

public class BigLabel extends JFrame {

    public BigLabel( String str ) {

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( new JLabel( str ), BorderLayout.CENTER);
        pack();

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );                
    }
}
