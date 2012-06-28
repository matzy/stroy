package org.openCage.utils.log;

import org.jetbrains.annotations.NonNls;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Handler;

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

@NonNls
public class Log {

    // one logger for all openCage apps
    private static Logger logger = Logger.getLogger( Log.class.getName() );

    static {
//        String[] levelNames = { "ALL", "FINEST", "FINER", "FINE", "CONFIG", "INFO", "WARNING", "SEVERE", "OFF" };
//        String lev = PListSelectionString.getOrCreate( LogHandlerPanel.STROY_LOG_OUT, new ListSelection( "INFO", levelNames )).get().getSelection();
//        PListSelectionString.getOrCreate( LogHandlerPanel.STROY_LOG_IN, new ListSelection( "INFO", levelNames ));
//        logger.setLevel( Level.parse( lev) );

        logger.setLevel( Level.ALL );
    }

    public static void tell() {

        Logger logIt = logger;

        while ( logIt != null ) {
            System.out.println("logger: " + logIt.getName() + " " + logIt.getLevel().getName());

            Handler[] handlers = java.util.logging.Logger.getLogger( logIt.getName() ).getHandlers();

            for (Handler handler : handlers) {
                System.out.println("   handler: " + handler.getClass().getName() + " " + handler.getLevel().getName() );
            }

            logIt = logIt.getParent();
        }


    }

    public static void info( String message ) {
        logger.info( message );
    }

    public static void fine( String message ) {
        logger.fine( message );
    }

    public static void finer( String message ) {
        logger.finer( message );
    }

    public static void finest( String message ) {
        logger.finest( message );
    }

    public static void severe( String message ) {
        logger.severe( message );
    }

    public static void warning( String message ) {
        logger.warning( message );
    }

    public static void addHandler( Handler hdlr ) {
        logger.addHandler( hdlr );
    }

    public static void removeHandler( Handler hdlr ) {
        logger.removeHandler( hdlr );
    }

    public static void setLogLevelOnAllHandles( Level level  ) {


        // The root logger's handlers default to INFO. We have to
        // crank them up. We could crank up only some of them
        // if we wanted, but we will turn them all up.

        Handler[] handlers = java.util.logging.Logger.getLogger( "" ).getHandlers();

        for (Handler handler : handlers) {
            handler.setLevel( level );
        }
    }

    public static Level getLevel() {
        return logger.getLevel();
    }


    public static void setLevel(Level level) {
        fine( "setting output log level to " + level.getName() );
        logger.setLevel( level );
    }

    public static <T extends Throwable> T log( T err ) {
        String str = err.toString();
        for ( StackTraceElement el : err.getStackTrace() ) {
            str += "\n             " + el.toString();
        }
        // the last line shows up in the last-severe-error box
        str += "\n    " + err.toString();
        logger.severe( str);
        return err;
    }

    public static <T extends Throwable> T warning( T err ) {
        logger.warning( err.toString());

        for ( StackTraceElement el : err.getStackTrace() ) {
            logger.warning( el.toString());
        }

        return err;
    }

    public static boolean isAtLeast( Level level ) {
        if ( logger.getLevel() == null )  {
            return true;
        }
        return logger.getLevel().intValue() <= level.intValue();
    }

}
