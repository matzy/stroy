package org.openCage.jmidgard.core;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 12.07.11
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class Log {

    private String level;
    private Logger logger;
    private static LogFormatter lf = new LogFormatter();

    public Log one;
    public Log two;
    public Log three;
    public Log four;


    public static Log getLogger( String name ) {
        Logger logger = Logger.getLogger( name );
        logger.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        handler.setFormatter(lf);
        logger.addHandler(handler);
        return new Log( logger );
    }


    public Log( Logger logger ) {
        this.level = "";
        this.logger = logger;

        this.one   = new Log( "   ", logger );
        this.two   = new Log( "      ", logger );
        this.three = new Log( "          ", logger );
        this.four  = new Log( "            ", logger );
    }

    private Log(String level, Logger logger) {
        this.level = level;
        this.logger = logger;
    }

    public void severe( String msg ) {
        logger.severe(level + msg);
    }

    public void warn( String msg ) {
        logger.warning( level  + msg );
    }

    public void info( String msg ) {
        logger.info(level + msg);
    }
    public void fine( String msg ) {
        logger.fine(level + msg);
    }
    public void finest( String msg ) {
        logger.finest(level + msg);
    }
}
