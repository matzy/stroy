package org.openCage.stroy.cli;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.cli.*;
import org.openCage.lang.structure.Once;
import org.openCage.stroy.RuntimeModule;
import org.openCage.stroy.graph.matching.TreeMatchingTask;
import org.openCage.stroy.dir.FileContent;
import org.openCage.stroy.filter.Ignore;
import org.openCage.stroy.dir.doubles2.CompareDirs2;
import org.openCage.util.logging.Log;

import java.io.File;
import java.util.logging.Level;


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

/**
 * a cli main
 */
public class Main {

    private final Options           optionsPublic;
    private final Options           options;
    private final Once<CommandLine> line;
    private static final String DEBUG_HELP = "dh";
    private static final String HELP = "h";
    private static final String IGNORE = "ignore";


    public static void main(String[] args) {

        Main main = new Main();

        main.createOptions();
        main.parseArgs( args );
        main.eval();

    }

    public Main() {

        options       = new Options();
        optionsPublic = new Options();
        line          = new Once<CommandLine>( null );
    }

    private void eval() {
        if ( line.get().hasOption( DEBUG_HELP ) ) {
            usageReal();
            return;
        }

        if ( line.get().hasOption( HELP ) || line.get().getArgs().length != 2 ) {
            usage();
            return;
        }


        if ( line.get().hasOption( "loglevel")) {
            setLogLevel( line.get().getOptionValue( "loglevel" ));
        }

        if ( line.get().hasOption( "s")) {
            setLogLevel( "0" );            
        }

        String pattern = null;
        if ( line.get().hasOption( IGNORE )) {
            pattern = line.get().getOptionValue(IGNORE);
        }


        File from = new File( line.get().getArgs()[0] );
        File to   = new File( line.get().getArgs()[1] );

        if ( ! from.exists() ) {
            System.err.println("does not exits: " + from.getPath() );
            usage();
            return;
        }

        if ( ! to.exists() ) {
            System.err.println("does not exits: " + to.getPath() );
            usage();
            return;
        }

        if ( from.isDirectory() ) {
            if ( to.isDirectory() ) {
                Injector injector = Guice.createInjector( new RuntimeModule() );

                // FIRST GOOD
//                CompareDirs cd = injector.getInstance( CompareDirsImpl.class );
//
//                System.out.println(pattern);
//                if ( pattern != null ) {
//                    cd.addIgnore( pattern );
//                }
//                cd.go( from.getAbsolutePath(), to.getAbsolutePath());


                CompareDirs2 cd     = injector.getInstance(CompareDirs2.class );
                Ignore       ignore = injector.getInstance( Ignore.class );

//                IgnoreHelper.addStd( ignore );
//                if ( pattern != null ) {
//                    ignore.add( pattern );
//                }

                TreeMatchingTask<FileContent> matching = cd.compare( ignore, from, to );
                matching.status();


                return;
            } else {
                throw new Error( "not impl" );
            }
        } else if ( to.isDirectory() ) {
            throw new Error( "not impl" );
        } else {
//            new CompareFiles( from.getAbsolutePath(), to.getAbsolutePath()).go();
            return;
        }

    }

    private void usage() {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        //formatter.printUsage();
        formatter.printHelp( "stroy [] (file|dir) (file|dir)\nstroy compares 2 files/dirs.\nIt finds files which print changed, renamed and moved",
                optionsPublic );
    }

    private void usageReal() {
        // automatically generate the help statement
        HelpFormatter formatter = new HelpFormatter();
        //formatter.printUsage();
        formatter.printHelp( "stroy [] (file|dir) (file|dir)\nstroy compares 2 files/dirs.\nIt finds files which print changed, renamed and moved",
                options );
    }

    private void parseArgs(String[] args) {

        CommandLineParser parser = new GnuParser();
        try {
            line.set( parser.parse( options, args ));
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }

    }

    private void createOptions() {

        options.addOption(HELP, "help", false, "show this message" );
        optionsPublic.addOption(HELP, "help", false, "show this message" );

        Option loglevel   = OptionBuilder.withArgName( "loglevel" )
                .hasArg()
                .withDescription(  "set the loglevel" )
                .create( "loglevel" );
//
//        OptionBuilder.withLongOpt( "block-size" )
//                                .withDescription( "use SIZE-byte blocks" )
//                                .withValueSeparator( '=' )
//                                .hasArg()
//                                .getOrCreate() );

        options.addOption( loglevel );

        options.addOption( "dbg", "debug", false, "print debugging info" );
        options.addOption(DEBUG_HELP, "debugHelp", false, "show this message" );

        options.addOption( "s", "siltent", false, "no messages" );
        optionsPublic.addOption( "s", "siltent", false, "no messages" );

        Option filter   = OptionBuilder.withArgName(IGNORE)
                .hasArg()
                .withDescription(  "files matching this pattern are ignored" )
                .create(IGNORE);

        options.addOption( filter );
        optionsPublic.addOption( filter );

    }


    private void setLogLevel( String levelStr ) {

        Log.setLevel( Level.parse( levelStr ));
    }



}
