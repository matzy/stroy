package org.openCage.stroy.ui.prefs;

import com.google.inject.name.Named;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.openCage.kleinod.observe.ObservableRef;
import org.openCage.kleinod.ui.Binding;
import org.openCage.stroy.locale.Message;
import org.openCage.util.logging.Log;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.logging.Level;

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

public class LogPrefs extends JPanel {

    public LogPrefs( @Named(value = "loglevel") ObservableRef<Level> level ) {


        JPanel           top    = new JPanel();
        JLabel           txt    = new JLabel(Message.get("Pref.Logging.display"));
        DesignGridLayout layout = new DesignGridLayout( top );
        JComboBox        logs   = new JComboBox();

        Binding.bind(logs,
                Log.getLevels(),
                level );


        layout.row().grid(   ).empty(    );
        layout.row().grid(txt).add( logs );

        top.setBorder( new TitledBorder( Message.get( "Pref.Logging.title" ) ));

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER );
    }
}
