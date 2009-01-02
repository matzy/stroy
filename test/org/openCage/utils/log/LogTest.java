package org.openCage.utils.log;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.logging.Level;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Dec 31, 2008
 * Time: 5:24:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogTest {

    public class TestHandler extends Handler {
        private LogRecord last;

        public TestHandler() {
            setLevel( Level.ALL );
        }

        public void publish( LogRecord record ) {
            if ( this.isLoggable( record  )) {
                last = record;
            }
        }

        public void flush() {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void close() throws SecurityException {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public LogRecord getLast() {
            return last;
        }
    }

    private TestHandler handler = new TestHandler();

    @Before
    public void setup() {
        Log.addHandler( handler );
    }

    @After
    public void cleanup() {
        Log.setLevel( Level.ALL );
        Log.removeHandler( handler );
    }

    @Test
    public void testFine() {
        Log.setLevel( Level.ALL );
        Log.fine( "foo" );

        LogRecord rec = handler.getLast();
        assertEquals( "foo", rec.getMessage() );

        Log.setLevel( Level.SEVERE );
        Log.fine( "duh" );

        rec = handler.getLast();
        assertNotSame( "foo", rec.getMessage() );
    }
}
