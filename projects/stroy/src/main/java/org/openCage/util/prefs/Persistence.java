//package org.openCage.util.prefs;
//
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;
//import org.openCage.util.io.FileUtils;
//import org.openCage.util.logging.Log;
//
//import java.io.*;
//
///***** BEGIN LICENSE BLOCK *****
// * BSD License (2 clause)
// * Copyright (c) 2006 - 2012, Stephan Pfab
// * All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *     * Redistributions of source code must retain the above copyright
// *       notice, this list of conditions and the following disclaimer.
// *     * Redistributions in binary form must reproduce the above copyright
// *       notice, this list of conditions and the following disclaimer in the
// *       documentation and/or other materials provided with the distribution.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
// * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//***** END LICENSE BLOCK *****/
//public class Persistence {
//
//    private static final XStream xstream = new XStream( new DomDriver());
//
////    static <T> T load( Class<T> clas, final String name ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//      static <T extends Persistable> T load( T init, final String name ) {
//
//        String path = getPath(name);
//
//        // make sure path exits
//        new File( path ).getParentFile().mkdirs();
//
//        T prefs;
//
//        if ( !new File( path ).exists() ) {
//            prefs = init; // clas.getConstructor().newInstance();
//            //theStore.initialize();
//        } else {
//            try {
//                prefs = read( init, path );
//            } catch ( Exception exp ) {
//                Log.warning( "failed to read existing preference file. Using default." );
//                prefs = init;
//            }
//        }
//
//
//        final T prefsCopy = prefs;
//
//        // make sure we save during shutdown
//        Runtime.getRuntime().addShutdownHook(
//                new Thread( new Runnable() {
//                    public void run() {
//                        save( prefsCopy, name );
//                    }
//                }));
//
//        // save once in a while
//        // TODO only shutdown ?
//        new Thread() {
//            @SuppressWarnings({"OverlyBroadCatchBlock"})
//            public void run() {
//                //noinspection InfiniteLoopStatement
//                while ( true ) {
//                    try {
//                        Thread.sleep( 10000 );
//                        save( prefsCopy, name );
//                    } catch ( Exception exp ) {
//                        Log.warning( "PrefSaveThread cought " + exp );
//                    }
//                }
//            }
//        }.start();
//
//        return prefs;
//    }
//
//    private static String getPath(String name) {
//        String path = FileUtils.getHomeDir() + "/." + name + "/preferences.xml";
//        return path;
//    }
//
//    public static synchronized <T extends Persistable> void save( T prefs, String name ) {
//
//        if ( prefs == null || !prefs.isDirty() ) {
//            return;
//        }
//
//        String path = getPath( name );
//
//        new File( path ).getParentFile().mkdirs();
//
//        String xml = xstream.toXML( prefs );
//
//        Writer fw = null;
//
//        try
//        {
//            fw = new FileWriter( path );
//            fw.write( xml );
//        }
//        catch ( IOException e ) {
//            Log.severe( "could not save preferences for " + e);
//        }
//        finally {
//            if ( fw != null ) {
//                //noinspection EmptyCatchBlock
//                try {
//                    fw.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//
//
//        prefs.setClean();
//    }
//
//
////    private static <T> T read( Class<T> clas, final String path ) {
//      private static <T> T read( T exa, final String path ) {
//        char[] buff = new char[ 500000 ];
//        int    res;
//        Reader f = null;
//
//        try
//        {
//            f = new FileReader( path );
//
//            res = f.read( buff );
//        }
//        catch ( IOException e ) {
//            if ( f != null ) {
//                try { f.close(); } catch ( Exception ex ) { }
//            }
//
//            throw Log.log( new Error( e ));
//        }
//        finally {
//            try { f.close(); } catch ( Exception e ) { }
//        }
//
//        return (T)xstream.fromXML( new String( buff ).substring( 0, res ) );
//    }
//}
//
