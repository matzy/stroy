package org.openCage.ui.clazz;

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
* Version: MPL 1.1
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
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
