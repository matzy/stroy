package org.openCage.utils.log;

import org.junit.Test;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Level;
import java.util.logging.Logger;

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
