package org.openCage.util.external;

import com.muchsoft.util.Sys;
import org.openCage.lang.inc.Str;
import org.openCage.util.logging.Log;

import java.awt.Desktop;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;

import static org.openCage.lang.inc.Strng.S;

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

public class ExternalProgs {

    public static final Str fileMerge = S("/usr/bin/opendiff");
    public static final Str open      = S("--system open--");
    public static final Str unknown   = S("--unknown--");
    public static final Str openAsText = S("open -e");
    public static final Str STANDARD_DIFF = S("--standard diff--");

    public static final Str OS_TEXT_EDIT = S("--os text edit--");
    public static final Str WIN_DIFF = S("windiff");
    public static final Str WIN_TEXT_EDIT = S("notepad");

    private static String xtermPath;


    public static void execute( final String prog, final String ... args ) {
        Thread th = new Thread() {
            @Override
            public void run() {
                executeImplP1( prog, args );
            }
        };
        th.start();
    }

    private static void executeImplP1( String prog, String[] args ) {
        if ( prog.contains( "%")) {

            String[] cmd = prog.split( " ");

            for ( int idx = 0; idx < cmd.length; ++idx ) {
                if ( cmd[idx].equals( "%1" )) {
                    cmd[ idx ] = args[0];
                } else if ( cmd[idx].equals( "%2" )) {
                    cmd[ idx ] = args[1];
                } else if ( cmd[idx].equals( "%3" )) {
                    cmd[ idx ] = args[2];
                } else if ( cmd[idx].equals( "%4" )) {
                    cmd[ idx ] = args[3];
                }
            }

            execProg1( cmd );
        } else {
            executeImpl( prog, args );
        }
    }


    private static void executeImpl( String prog, String ... args ) {

        // some systems have a build in open command
        if ( prog.equals( open.get() )) {
            if ( Sys.isMacOSX() ) {
                // OSX has open
                execOSXopen( args[0 ]);
                return;
            } else if ( Sys.isWindows() ) {
                // windows has cmd
//                execWinOpen( args[0] );
                jdicOpen( args[0]);
                return;
            }

            Log.info( "no known open prog" );

            return;

        } else if ( prog.equals( OS_TEXT_EDIT.get() )) {
            if ( Sys.isMacOSX() ) {
                execOSXopenAsText( args[0]);
                return;
            } else if ( Sys.isWindows() ) {
                execProg( WIN_TEXT_EDIT.get(), args );
                return;
            } else if ( Sys.isLinux() ) {
                execProg( findxterm(), "-e", "vi", args[0] );
                return;
            }
        } else if ( prog.equals( STANDARD_DIFF.get() ) ) {
            if ( Sys.isMacOSX() ) {
                execProg( fileMerge.get(), args );
                return;
            } else if ( Sys.isWindows() ) {
                execProg( WIN_DIFF.get(), args );
                return;
            } else if ( Sys.isLinux() ) {
                execProg( findxterm(), "-hold", "-e", "diff", args[0], args[1] );
                return;
            }
        } else if ( Sys.isMacOSX() && prog.equals( openAsText.get() )) {
            execOSXopenAsText( args[0]);
        } else if ( prog.equals( unknown.get() )) {
            Log.info( "execute 'unknown' called");
            return;
        }


        if ( prog.endsWith( ".app" )) {
            execApp( prog, args );
            return;
        }

        execProg( prog, args );
        return;
    }

    private static String findxterm() {

        // todo syncro ??
        if ( xtermPath == null ) {
            for ( String pre : Arrays.asList( "/bin", "/usr/bin/", "/usr/local/bin/","/usr/sbin/" , "/usr/X11/bin/", "/usr/local/X11/bin/" ) )  {
                String testing = pre + "xterm";
                if ( new File(testing).exists() ) {
                    xtermPath = testing;
                    break;
                }
            }
        }

        return xtermPath;
    }

    private static void linuxOpenWithText(String arg ) {
        execProg( findxterm(), "-e", "vi", arg); //"\"" + arg + "\"");
    }


    private static void jdicOpen( String file ) {
        try {
            Desktop.getDesktop().open(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
            Log.warning( "could not open " + file );
        }
    }

    // windows system open
    private static void execWinOpen(String arg) {
        try {
            String[] cmd = new String[] { "cmd", "/q", "\"/c start " + arg + "\""};

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e1) {
            Log.warning( "start " + arg + "threw " + e1  );
        }
    }

    // OSX system open
    private static void execOSXopen(String arg) {
        try {

            String[] cmd = new String[2];
            cmd[0] = "/usr/bin/open";
            cmd[1] = arg;

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e1) {
            Log.warning( "open " + arg + "threw " + e1  );
        }
    }

    private static void execOSXopenAsText( String arg ) {
        try {

            String[] cmd = new String[3];
            cmd[0] = "/usr/bin/open";
            cmd[1] = "-e";
            cmd[2] = arg;

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e1) {
            Log.warning( "open -e" + arg + "threw " + e1  );
        }
    }


    // OSX has special commands ending in .app
    private static void execApp(String prog, String ... args ) {
        try {
            String[] cmd = new String[ args.length + 3 ];

            cmd[0] = "open";
            cmd[1] = "-a";
            cmd[2] = prog;

            int idx = 3;
            for ( String arg : args ) {
                cmd[idx] = arg;
                idx++;

            }

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e1) {
            Log.warning( "external prog " + prog + " threw " + e1  );
        }
    }

    private static void execProg(String prog, String ... args ) {
        try {
            String[] cmd = new String[ args.length + 1 ];

            cmd[0] = prog ;

            int idx = 1;
            for ( String arg : args ) {
                cmd[idx] = arg;
                idx++;

            }

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e1) {
            Log.warning( "external prog " + prog + " threw " + e1  );
        }
    }

    private static void execProg1( String ... cmd ) {
        try {

            Process proc = Runtime.getRuntime().exec( cmd ) ;
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e1) {
            Log.warning( "external prog " + cmd[0] + " threw " + e1  );
        }
    }

}
