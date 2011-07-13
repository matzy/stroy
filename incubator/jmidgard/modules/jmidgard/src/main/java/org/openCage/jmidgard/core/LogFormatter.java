package org.openCage.jmidgard.core;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by IntelliJ IDEA.
 * User: SPF
 * Date: 12.07.11
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
public class LogFormatter extends Formatter {


    private DecimalFormat df = new DecimalFormat("00000000");
    private long start = 0;

    @Override
    public String format(LogRecord logRecord) {
        return showTime(logRecord) + shortLevel( logRecord.getLevel()) + logRecord.getMessage() + "\n";
    }

    public String shortLevel( Level level ) {
        if ( level.equals( Level.FINE )) {
            return "f   ";
        }
        if ( level.equals( Level.FINER )) {
            return "ff  ";
        }
        if ( level.equals( Level.FINEST )) {
            return "fff ";
        }
        if ( level.equals( Level.WARNING )) {
            return "w   ";
        }
        if ( level.equals( Level.INFO )) {
            return "i   ";
        }

        return "s   ";
    }

    public String showTime( LogRecord rec ) {
        if ( start == 0 ) {
            start = rec.getMillis();
        }

        return df.format( rec.getMillis() - start ) + " ";

    }


}
