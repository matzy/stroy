package org.openCage.utils.log;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.logging.Level;
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
