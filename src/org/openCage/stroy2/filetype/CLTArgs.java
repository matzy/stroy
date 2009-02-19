package org.openCage.stroy2.filetype;

import org.apache.commons.cli.*;

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
public class CLTArgs {

    private static Logger log = Logger.getLogger( CLTArgs.class.getName() );

    private Options options = new Options();
    private static final String HELP = "h";
    private CommandLine line = null;

    private boolean ok = true;

    public CLTArgs( String[] args ) {
        options.addOption( HELP, "help", false, "show this message" );
        options.addOption("v", "verbose", false, "verbose output" );
        options.addOption("e", "ext", false, "extension only" );
        options.addOption("c", "content", false, "content only" );
        options.addOption("r", "recursive", false, "recursive: check all file and folders" );

        parseArgs( args );

        if ( !ok || line.hasOption( HELP )) {
            usage();
            ok = false;
            return;
        }

        if ( line.getArgs().length != 1 ) {
            usage();
            ok = false;
            return;
        }

        if ( line.hasOption( "e" ) && line.hasOption("c")) {
            usage();
            ok = false;
            return;
        }

        ok = true;
    }

    private void usage() {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();

        //formatter.printUsage();
        formatter.printHelp( "file <file|dir>\n    determine the file type by extension and/or content\n    Version 1.0 stroy.wikidot.com\n     Most power is from: mime-util 1.2, sourceforge.net/projects/mime-util",
                options );

    }

    private void parseArgs(String[] args) {

        CommandLineParser parser = new GnuParser();
        try {
            line = parser.parse( options, args );

        } catch( ParseException exp ) {
            // oops, something went wrong
            ok = false;
            log.severe( "argument parse exception" );            
        }

    }


    public boolean isOk() {
        return ok;
    }

    public String getFile() {
        return line.getArgs()[0];
    }

    public boolean isVerbose() {
        return line.hasOption( "v");
    }

    public boolean contentOnly() {
        return line.hasOption("c");
    }

    public boolean extensionOnly() {
        return line.hasOption("e");
    }

    public boolean isRecursive() {
        return line.hasOption("r");
    }
}
