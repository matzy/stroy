package org.openCage.stroy.find;

import java.util.logging.Logger;
import java.io.File;


import org.apache.commons.cli.*;

import java.io.File;
import java.util.logging.Logger;

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
public class Args {

    private static Logger log = Logger.getLogger( Args.class.getName() );

    private Options options = new Options();
    private static final String HELP = "h";
    private CommandLine line = null;

    private boolean ok = false;
    private String what = null;
    private String where;


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


        what  = line.getArgs()[0];
        where = line.getArgs()[1];

    }

    public String getWhat() {
        return what;
    }


    public String getWhere() {
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
