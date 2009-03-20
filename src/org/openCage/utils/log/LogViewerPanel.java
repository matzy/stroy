package org.openCage.utils.log;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class LogViewerPanel extends JPanel {

    Logger rootLogger = Logger.getLogger("");
    private JTextArea text = new JTextArea();


    public LogViewerPanel() {
        setSize( 300, 300 );
        DesignGridLayout layout = new DesignGridLayout( this );
        layout.row().add( text );
        setLayout( layout );

        // add a log handler which just passes all messages to us
        rootLogger.addHandler( new Handler() {
            public void publish(LogRecord record) {
                show( record );
            }

            public void flush() {}
            public void close() throws SecurityException {}
        });

    }

    private void show( LogRecord rec ) {
        text.append( rec.getMessage());
    }
}
