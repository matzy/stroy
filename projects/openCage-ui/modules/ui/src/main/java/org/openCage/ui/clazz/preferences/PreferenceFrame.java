package org.openCage.ui.clazz.preferences;

import com.bric.swing.PreferencePanel;
import com.google.inject.Inject;
import org.openCage.ui.protocol.GlobalKeyEventHandler;
import org.openCage.ui.protocol.PrefBuilder;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

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

public class PreferenceFrame extends JFrame {

    private final PreferencePanel prefPanel = new PreferencePanel();

    @Inject
    public PreferenceFrame( GlobalKeyEventHandler keyEventHandler ) {
        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( prefPanel, BorderLayout.CENTER  );
        pack();

        keyEventHandler.addCloseWindow( this );
    }

    public void addButtonRow(AbstractButton[] buttons, JComponent[] components,String title) {
        prefPanel.addButtonRow( buttons, components, title );
    }

    public PreferenceRow addRow( String title ) {
        return new PreferenceRow( prefPanel, title );
    }

    public class PreferenceRow {

        private final PreferencePanel pref;
        private final String title;
        private final List<AbstractButton> buttons = new ArrayList<AbstractButton>();
        private final List<JComponent>     compos  = new ArrayList<JComponent>();

        public PreferenceRow( PreferencePanel frame, String title ) {
            this.title = title;
            this.pref = frame;
        }

        public PreferenceRow add( AbstractButton button, JComponent compo ) {
            buttons.add( button );
            compos.add( compo );
            return this;
        }

        public PreferenceRow add( PrefBuilder prefBuilder) {
            buttons.add( prefBuilder.getPrefButton() );
            compos.add( prefBuilder.getPrefPanel() );
            return this;
        }

        public void build() {

            pref.addButtonRow( buttons.toArray( new AbstractButton[ buttons.size()] ),
                               compos.toArray( new JComponent[ compos.size()]),
                               title );
        }

    }
}
