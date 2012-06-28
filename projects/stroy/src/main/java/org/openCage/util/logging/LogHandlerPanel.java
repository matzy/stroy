package org.openCage.util.logging;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openCage.util.prefs.*;
import org.openCage.stroy.locale.Message;
import net.java.dev.designgridlayout.DesignGridLayout;

/***** BEGIN LICENSE BLOCK *****
 * BSD License (2 clause)
 * Copyright (c) 2006 - 2012, Stephan Pfab
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***** END LICENSE BLOCK *****/

public class LogHandlerPanel extends JFrame {


    public static final String STROY_LOG_OUT = "log.out";
    public static final String STROY_LOG_IN = "log.in";

//    private final String PRESET_OUTLEVEL = "outlevel";
//    private final String PRESET_INLEVEL = "inlevel";

    private Handler           logHandler         = new GraphicalHandler( this );
    private final PComboBox2 selectLogLevel;//     = new PComboBox( STROY_LOG_OUT );
//    private final PComboBox   selectHandlerLevel = new PComboBox( STROY_LOG_IN );
    private JTextArea         messages           = new JTextArea();
    private JScrollPane       messagesScoll      = new JScrollPane( messages );
    private final JButton     refreshButton      = new JButton( "refresh" );
    private final JTextField  infoTextField      = new JTextField();
    private final JTextField  warnTextField      = new JTextField();
    private final JTextField  severeTextField    = new JTextField();

    private final Formatter fullFormater = new SimpleFormatter();
    private final Formatter slimFormater = new SimpleFormatter();

    @Inject
    public LogHandlerPanel( LogLevelSelectionProperty5 loglevels) {

        selectLogLevel = new PComboBox2( loglevels );

        setTitle( "Logs" );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setSize( 400, 200 );

        // next line makes scroll areafield bigger
        messagesScoll.setPreferredSize( new Dimension( 400, 180 ));

        createLayout();
        addListeners();

        Log.addHandler( logHandler );

    }

    private void addListeners() {
        refreshButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                refresh();
            }
        });

//        selectLogLevel.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                String levelName = (String) selectLogLevel.getSelectedItem();
//                if ( levelName == null ) {
//                    Log.warning( "null as log level" );
//                    return;
//                }
//                Level level = Level.parse( levelName );
//                Log.setLevel( level );
//            }
//        });

//        selectHandlerLevel.addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                String levelName = (String) selectHandlerLevel.getSelectedItem();
//                if ( levelName == null ) {
//                    Log.warning( "null as log level" );
//                    return;
//                }
//                Level level = Level.parse(levelName);
//                logHandler.setLevel( level );
//            }
//        });

//        Preset.addListener( this );
//        Preset.inform( StroyConstants.PRESET_LOGS, PRESET_OUTLEVEL);
//        Preset.inform( StroyConstants.PRESET_LOGS, PRESET_INLEVEL );
    }

    private void createLayout() {
        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );

        layout.row().grid( new JLabel( Message.get( "Pref.Logging.display" ))).add( selectLogLevel);
                //.add( new JLabel("")).add( refreshButton );

        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( top, BorderLayout.NORTH  );
        getContentPane().add( messagesScoll, BorderLayout.CENTER  );

        JPanel bottom = new JPanel();
        DesignGridLayout layout2 = new DesignGridLayout( bottom );
        layout2.row().grid( new JLabel( "severe"/*Message.get( "Log.level.severe" ))*/)).add( severeTextField );
        layout2.row().grid( new JLabel( "warn" /*Message.get( "Log.level.warn" )*/ )).add( warnTextField );
        layout2.row().grid( new JLabel( "info" /*Message.get( "Log.level.info" )*/)).add( infoTextField );

        getContentPane().add( bottom, BorderLayout.SOUTH  );

        pack();

    }

    private void refresh() {
        selectLogLevel.setSelectedItem( Log.getLevel().getName());
//        selectHandlerLevel.setSelectedItem( logHandler.getLevel().getName() );
    }

    public void add( LogRecord logRecord ) {

        String txt  = fullFormater.format( logRecord  ).toString();
        String full = String.format( "%-10s %s", logRecord.getLevel().getName(), txt );

        if ( messages.getLineCount() >= 1000 ) {
            messages.setText( "" );
        }

        messages.append( full );
        messages.setCaretPosition( messages.getText().length());

        if ( logRecord.getLevel().equals( Level.INFO )) {
            infoTextField.setText( txt );
        } else if ( logRecord.getLevel().equals( Level.WARNING )) {
            warnTextField.setText( txt );
        } else if ( logRecord.getLevel().equals( Level.SEVERE )) {
            severeTextField.setText( txt );
        }

    }

    public void presetChanged() {}

    public void newPreset() {}


//    public void presetItemChanged(String group, String item, String val) {
//        if ( group.equals( StroyConstants.PRESET_LOGS )) {
//
//            if ( item.equals(PRESET_OUTLEVEL )) {
//
//                if ( val.equals( Log.getLevel().getName() )) {
//                    return;
//                }
//
//                try {
//                    Level level = Level.parse( val );
//                    Log.setLevel( level );
//                } catch( Exception exp ) {
//                    Log.warning( "not a loglevel " + val );
//
//                }
//            } else if ( item.equals(PRESET_INLEVEL )) {
//                if ( val.equals( logHandler.getLevel().getName() )) {
//                    return;
//                }
//
//                try {
//                    Level level = Level.parse( val );
//                    logHandler.setLevel( level );
//                } catch( Exception exp ) {
//                    Log.warning( "not a loglevel " + val );
//
//                }
//            }
//        }
//    }
}
