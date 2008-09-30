package org.openCage.stroy.update;

import zappini.designgridlayout.DesignGridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

import org.openCage.stroy.locale.LocalizedComboBox;
import org.openCage.stroy.locale.Message;
import com.google.inject.Inject;

public class UpdatePrefs extends JPanel {

    private JButton                 checkNow        = new JButton( Message.get("Update.checknow"));
    private LocalizedComboBox       updateInterval;

    private final Interval      interval;
    private final UpdateChecker checker;
    private JLabel uptodate = new JLabel( "          ");

    @Inject
    public UpdatePrefs( final Interval interval, final UpdateChecker checker ) {
        this.interval = interval;
        this.checker  = checker;

        updateInterval  = new LocalizedComboBox( interval.getKey() );


        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );

        layout.row().add( new JLabel(""));
        layout.row().add( new JLabel( Message.get("Update.howoften") ),2).add( updateInterval );
        layout.row().add( "");
        layout.row().add( new JLabel( Message.get( "Update.checknowlabel")) ,2 ).add( checkNow );
        layout.row().add( new JLabel(""),2 ).add( uptodate );

        top.setBorder( new TitledBorder( Message.get( "Pref.Update.title" ) ));


        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER );

        checkNow.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if ( !checker.isConnected() ) {
                    uptodate.setText( Message.get( "Prefs.update.nonet" ));
                    return;
                }

                uptodate.setText( "" );

                if ( !checker.checkAnyway() ) {
                    uptodate.setText( Message.get( "Prefs.update.isuptodate" ));
                }
            }
        } );

    }

    
}
