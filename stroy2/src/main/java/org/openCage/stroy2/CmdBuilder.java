package org.openCage.stroy2;

import org.apache.commons.cli.*;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jun 28, 2009
 * Time: 4:40:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class CmdBuilder {

    private static Logger log = Logger.getLogger( CmdBuilder.class.getName() );

    private Options     options  = new Options();
    private CommandLine line = null;
    private boolean     isOk = false;

    public CmdBuilder(String[] args) {

        options.addOption( "h", "help", false, "show this message" ); // TODO localize: show ...

        parseArgs( args );

        if ( line.hasOption( "h" )) {
            return;
        }

        if ( line.getArgs().length != 2 ) {
            return;
        }

        isOk = true;
    }

    public boolean isOk() {
        return isOk;
    }

    public void printUsage() {
        System.out.println("Usage:");
    }

    public StroyCmd build() {
        if ( !isOk ) {
            throw new Error( "usage" );
        }

        return new StroyCmd( line.getArgs()[0], line.getArgs()[1]);
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

}
