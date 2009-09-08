package org.openCage.util.external;

import com.muchsoft.util.Sys;
import org.openCage.util.logging.Log;
import org.openCage.util.iterator.Iterators;
import org.openCage.util.iterator.Count;
import org.jdesktop.jdic.desktop.DesktopException;
import org.jdesktop.jdic.desktop.Desktop;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;

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

public class ExternalProgs {

    public static final String fileMerge = "/usr/bin/opendiff";
    public static final String open      = "<system open>";
    public static final String unknown   = "<unknown>";
    public static final String openAsText = "open -e";
    public static final String STANDARD_DIFF = "<standard diff>";

    public static final String OS_TEXT_EDIT = "<os text edit>";
    public static final String WIN_DIFF = "windiff";
    public static final String WIN_TEXT_EDIT = "notepad";

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
        if ( prog.equals( open )) {
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

        } else if ( prog.equals( OS_TEXT_EDIT )) {
            if ( Sys.isMacOSX() ) {
                execOSXopenAsText( args[0]);
                return;
            } else if ( Sys.isWindows() ) {
                execProg( WIN_TEXT_EDIT, args );
                return;
            } else if ( Sys.isLinux() ) {
                execProg( findxterm(), "-e", "vi", args[0] );
                return;
            }
        } else if ( prog.equals( STANDARD_DIFF ) ) {
            if ( Sys.isMacOSX() ) {
                execProg( fileMerge, args );
                return;
            } else if ( Sys.isWindows() ) {
                execProg( WIN_DIFF, args );
                return;
            } else if ( Sys.isLinux() ) {
                execProg( findxterm(), "-hold", "-e", "diff", args[0], args[1] );
                return;
            }
        } else if ( Sys.isMacOSX() && prog.equals( openAsText )) {
            execOSXopenAsText( args[0]);
        } else if ( prog.equals( unknown )) {
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
            Desktop.open(new File(file));
        } catch ( DesktopException e) {
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
