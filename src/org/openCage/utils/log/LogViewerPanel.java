package org.openCage.utils.log;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 16, 2009
 * Time: 6:57:01 PM
 * To change this template use File | Settings | File Templates.
 */
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
