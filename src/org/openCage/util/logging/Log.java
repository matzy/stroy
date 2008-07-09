package org.openCage.util.logging;

import org.openCage.util.prefs.PListSelectionString;
import org.openCage.util.prefs.ListSelection;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Handler;

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

public class Log {

    // one logger for all openCage apps
    private static Logger logger = Logger.getLogger( Log.class.getName() );

    static {
        String[] levelNames = { "ALL", "FINEST", "FINER", "FINE", "CONFIG", "INFO", "WARNING", "SEVERE", "OFF" };
        String lev = PListSelectionString.getOrCreate( LogHandlerPanel.STROY_LOG_OUT, new ListSelection( "INFO", levelNames )).get().getSelection();
        PListSelectionString.getOrCreate( LogHandlerPanel.STROY_LOG_IN, new ListSelection( "INFO", levelNames ));

        logger.setLevel( Level.parse( lev) );
//        logger.setLevel( Level.INFO );
//        setLogLevelOnAllHandles( Level.ALL );
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

    public static Error log( Error err ) {
        logger.severe( err.getMessage());
        return err;
    }

    public static boolean isAtLeast( Level level ) {
        if ( logger.getLevel() == null )  {
            return true;
        }
        return logger.getLevel().intValue() <= level.intValue();
    }

}
