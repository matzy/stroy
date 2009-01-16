package org.openCage.utils.log;

import org.junit.Test;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Jan 15, 2009
 * Time: 1:53:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultLogTest {

    /**
     * Loghandler accepting all logs
     */
    public class AcceptAllHandler extends Handler {
        private LogRecord last;

        public void publish( LogRecord record ) {
            last = record;
        }

        public void flush() {}
        public void close() throws SecurityException {}

        public LogRecord getLast() {
            return last;
        }
    }

    /**
     * A demonstration how logging can be done
     * Each class can use any logger they want.
     * The messages go all to the root logger which has a custom handler.
     * This handler accept all logs.
     * Then the controlling factor is the root logger.
     *
     * Q: if the child loggers have a finer setting than rootlogger is that slow (unnecassary message sending)? 
     */
    @Test
    public void testFinest() {
        Logger log = Logger.getLogger( "foo.foo" );

        Level lev = log.getLevel();


        Logger rootLog = Logger.getLogger("");
        Level rootLevel = rootLog.getLevel();
        AcceptAllHandler handler = new AcceptAllHandler();
        rootLog.addHandler( handler );

        log.fine("nah");

        assertNull( handler.getLast());

        rootLog.setLevel( Level.ALL );
        log.fine("yes");

        assertEquals( "yes", handler.getLast().getMessage() );

    }

}
