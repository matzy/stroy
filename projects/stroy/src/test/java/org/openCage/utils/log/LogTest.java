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
