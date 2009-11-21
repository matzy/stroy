package org.openCage.util.io;

import org.openCage.withResource.impl.LineReaderIterator;
import com.muchsoft.util.Sys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import org.openCage.util.iterator.Iterators;

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

public class FileUtils {

    static private Pattern startWithDriveLetter = Pattern.compile( "([a-zA-Z]):.*");


    /**
     * @param file a file.
     * @return the extension of the filename.
     */
    static public String getExtension( File file ) {
        return getExtension( file.getName() );
    }

    /**
     * @param name a (file) name.
     * @return the extension of the filename, i.e. the substring after the last '.'.
     */
    static public String getExtension( String name ) {
        int    dotpos = name.lastIndexOf('.');

        if ( dotpos < 0 ) {
            return "";
        }

        return name.substring( dotpos + 1).toLowerCase();
    }

    /**
     * @return the current directory
     */
    static public String getCurrentDir() {
        return System.getProperty("user.dir");
    }

    /**
     * return the current users home directory
     * @return the home directory
     */
    static public String getHomeDir() {
        return System.getProperty("user.home");
    }

    /**
     * Normalizes an absolute file path to use only '/' as separator.
     * On windows drive letter plus ':' is used.
     * @param in An absolute path.
     * @return The same path with '/'
     */
    static public String normalizePath( String in ) {

        boolean isUNC = false;
        // TODO fix me
        boolean isDriveLetter = false;

        if ( Sys.isWindows() ) {
            if ( in.startsWith( "\\\\") || in.startsWith( "//")) {
                isUNC = true;
            } else if ( startWithDriveLetter.matcher( in ).matches() ) {
                isDriveLetter = true;
            } else {
                throw new IllegalArgumentException( "not an absolute path" );
            }
        }

        String[] parts = in.split( "(/|:|\\\\|;)" );

        StringBuilder norm = new StringBuilder();

        boolean first = true;
        for ( String part : parts ) {
            if ( part.length() > 0 ) {
                if ( first && Sys.isWindows() ) {
                    if ( isUNC ) {
                        norm.append( "\\\\").append( part );
                    } else {
                        norm.append( part ).append( ":");
                    }
                    first = false;
                } else {
                    norm.append( '/' /*File.pathSeparator*/ ).append( part );
                }
            }
        }

        return norm.toString();
    }

    public static String normalizePathElement( String name ) {
        return name.replace( "/", "" ).replace( "\\", "");
    }

    public static boolean isDriveLetterPath( String path ) {
        return startWithDriveLetter.matcher( path ).matches();
    }

    /**
     * Copy file of source to target/
     * @param source A file path
     * @param target A target path.
     * @throws IOException Rethrow of a io exception.
     */
    public static void copy( String source, String target ) throws IOException {
        File ft = new File( target );
        if ( ft.isDirectory() ) {
            target += File.separatorChar +
                    source.substring( source.lastIndexOf( File.separatorChar ) + 1 );
        }

        new File( target ).getParentFile().mkdirs();

        copy( new FileInputStream( source), new FileOutputStream( target));
    }

    /**
     * helper function for copy
     * @param in input stream
     * @param out output stream
     */
    private static void copy( FileInputStream in, FileOutputStream out ) {
        try{
            byte buffer[] = new byte[ 0xffff];
            int  count;

            while ( (count = in.read( buffer)) != -1 ) {
                out.write( buffer, 0, count );
            }
        } catch ( IOException e ) {
            System.err.println( "copy error " + e);

        } finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException e ) {
                }
            }
            if ( out != null ) {
                try {
                    out.close();
                } catch ( IOException e ) {
                }
            }
        }
    }


    private FileUtils() {
        throw new NoSuchMethodError("utility class");
    }


    public static LineReaderIterator iterator( File file ) {
        if ( !file.canRead() ) {
            throw new IllegalArgumentException("file " + file.getAbsolutePath() + " is not readable");
        }

        try {
            BufferedReader reader = new BufferedReader( new FileReader( file ));
            return new LineReaderIterator( reader );
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("file " + file.getAbsolutePath() + " not found");
        } catch (IOException e) {
            throw new IllegalStateException( "reader has problem now" );
        }
    }


//    public static void loop( File file, V1<LineReaderIterator> func ) {
//        LineReaderIterator it = iterator( file );
//        try {
//            func.call( it );
//        } finally {
//            it.close();
//        }
//    }

//    public static void withIterator( File file, V1<Iterable<String>> func ) {
//        LineReaderIterator it = iterator( file );
//        try {
//            Iterable<String> ita = Iterators.loop( it );
//            func.call( ita );
//        } finally {
//            it.close();
//        }
//    }
//
//    public static void withIterator( InputStream is , V1<Iterable<String>> func ) {
//        LineReaderIterator it = null;
//        try {
//            it = new LineReaderIterator( new BufferedReader( new InputStreamReader( is )));
//            Iterable<String> ita = Iterators.loop( it );
//            func.call( ita );
//        } catch ( IOException e ) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } finally {
//            LineReaderIterator.close( it );
//        }
//    }

//    public static void withOpen( File file, V2<String, LoopState> v2 ) {
//        LineReaderIterator it = iterator( file );
//        try {
//            LoopStateImpl lp = new LoopStateImpl();
//            for ( String str : Iterators.loop( it ) ) {
//                v2.c( str, lp );
//                if ( lp.isBroken()) {
//                    break;
//                }
//
//                lp.incr();
//            }
//
//
//        } finally {
//            it.close();
//        }
//    }



}
