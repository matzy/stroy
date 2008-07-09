package org.openCage.stroy.update;

import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;

public class UpdatePrefs extends JPanel {

    private JButton                 checkNow        = new JButton( );
//    private final LocalizedComboBox updateInterval;


    public UpdatePrefs() {

        String[] levelNames = { "Pref.Update.Every", "Pref.Update.Weekly", "Pref.Update.Monthly", "Pref.Update.Never" };
        
//        updateInterval  = new LocalizedComboBox( "update.interval",
//                PListSelectionString.getOrCreate( "update.interval", new ListSelection( levelNames, "Pref.Update.Weekly" )).get());


        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );
//        layout.row().add( new JLabel( "How often should stroy check for updates? " ),4).add( updateInterval );
        layout.row().add( "");
        layout.row().add( new JLabel("Check now: "),4 ).add( checkNow );

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER );

    }

    
}
