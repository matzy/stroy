package org.openCage.stroy.find;

import org.apache.commons.cli.*;

import java.util.logging.Logger;

/**
 * A builder for Find Argements
 */
public class ArgsBuilderImpl implements ArgsBuilder {

    private static Logger log = Logger.getLogger( Args.class.getName() );

    private Options options = new Options();
    private static final String HELP = "h";
    private CommandLine line = null;

    private boolean ok = false;
//    private String what = null;
//    private String where;


    public ArgsBuilderImpl( String[] args ) {
        options.addOption(HELP, "help", false, "show this message" );

        parseArgs( args );

        if ( line.hasOption( HELP )) {
            return;
        }

        if ( line.getArgs().length != 2 ) {
            return;
        }

        ok = true;
    }




    public FindArgs getArgs() {
        if ( !ok ) {
            throw new IllegalStateException("Arguments are not complete");
        }

        return new FindArgs( line.getArgs()[0], line.getArgs()[1] );
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

    public void printUsage() {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();

        //formatter.printUsage();
        formatter.printHelp( "stroy [] (file|dir) (file|dir)\nstroy find the first arg in the second",
                options );
    }



}
