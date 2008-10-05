package org.openCage.stroy.ui.prefs;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import org.openCage.util.prefs.PComboBox;
import org.openCage.util.logging.LogHandlerPanel;
import org.openCage.stroy.locale.Message;
import net.java.dev.designgridlayout.DesignGridLayout;

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
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/

public class LogPrefs extends JPanel {

    private final PComboBox selectLogLevel     = new PComboBox(LogHandlerPanel.STROY_LOG_OUT );
    private final PComboBox selectHandlerLevel = new PComboBox(LogHandlerPanel.STROY_LOG_IN );


    public LogPrefs() {


        JPanel top = new JPanel();
        DesignGridLayout layout = new DesignGridLayout( top );
        top.setLayout( layout );
        layout.row().add( new JLabel(""));
        layout.row().label( new JLabel( Message.get( "Pref.Logging.display" ))).add( selectLogLevel );
//        layout.row().label( "display loglevel level: ").add( selectHandlerLevel );

        top.setBorder( new TitledBorder( Message.get( "Pref.Logging.title" ) ));

        setLayout( new BorderLayout());
        add( top, BorderLayout.CENTER );


    }
}
