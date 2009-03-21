package org.openCage.stroy.find;

import java.util.logging.Logger;
import java.io.File;


import org.apache.commons.cli.*;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Feb 8, 2009
 * Time: 11:27:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Args {

    private static Logger log = Logger.getLogger( Args.class.getName() );

    private Options options = new Options();
    private static final String HELP = "h";
    private CommandLine line = null;

    private boolean ok = false;
    private File what = null;
    private File where;


    public Args( String[] args ) {
        options.addOption(HELP, "help", false, "show this message" );

        parseArgs( args );

        if ( line.hasOption( HELP )) {
            usage();
            return;
        }

        if ( line.getArgs().length != 2 ) {
            usage();
            return;
        }


        what  = new File( line.getArgs()[0] );
        where = new File( line.getArgs()[1] );

    }

    public File getWhat() {
        return what;
    }


    public File getWhere() {
        return where;
    }


    public boolean isOk() {
        return ok;
    }

    private void parseArgs(String[] args) {

        CommandLineParser parser = new GnuParser();
        try {
            line = parser.parse( options, args );

        } catch( ParseException exp ) {
            // oops, something went wrong

            log.severe( "argument parse exception" );
        }

    }

    private void usage() {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();

        //formatter.printUsage();
        formatter.printHelp( "stroy [] (file|dir) (file|dir)\nstroy find the first arg in the second",
                options );
    }


}
