package org.openCage.stroy.update;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.openCage.lang.functions.F1;
import org.openCage.stroy.locale.LocalizedComboBox;
import org.openCage.stroy.locale.Message;
import com.google.inject.Inject;
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

public class UpdatePrefs extends JPanel {

    private JButton                         checkNow        = new JButton( Message.get("Update.checknow"));
    private LocalizedComboBox<UpdateTime> updateInterval;

//    private final Interval      interval;
//    private final UpdateChecker checker;
    private JLabel uptodate = new JLabel( "          ");

    @Inject
    public UpdatePrefs(final Interval interval, final UpdateChecker checker, UpdateSelectionProperty updateSelectionProperty) {
//        this.interval = interval;
//        this.checker  = checker;

        updateInterval  = new LocalizedComboBox<UpdateTime>( new F1<String, UpdateTime>() {
            @Override
            public String call(UpdateTime updateTime) {
                return "update-time." + updateTime;
            }
        }, updateSelectionProperty  );


        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );

        layout.row().grid().add( new JLabel(""));
        layout.row().grid().add( new JLabel( Message.get("Update.howoften") ),2).add( updateInterval );
        layout.row().grid().add( new JLabel(""));
        layout.row().grid().add( new JLabel( Message.get( "Update.checknowlabel")) ,2 ).add( checkNow );
        layout.row().grid().add( new JLabel(""),2 ).add( uptodate );

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
