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
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//public class PrefPersi {
//
//    private static final XStream xstream = new XStream( new DomDriver());
//
//    static Prefs load( final String name ) {
//
//        String path = getPath(name);
//
//        // make sure path exits
//        new File( path ).getParentFile().mkdirs();
//
//        Prefs prefs;
//
//        if ( !new File( path ).exists() ) {
//            prefs = new Prefs();
//            //theStore.initialize();
//        } else {
//            prefs = read( path );
//        }
//
//
//        final Prefs prefsCopy = prefs;
//
//        // make sure we save during shutdown
//        Runtime.getRuntime().addShutdownHook(
//                new Thread( new Runnable() {
//                    public void run() {
//                        save( prefsCopy );
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
//                        save( prefsCopy );
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
//        String path = FileUtils.getHomeDir() + "/." + name + "/prefs.xml";
//        return path;
//    }
//
//    public static synchronized void save( Prefs prefs ) {
//
//        if ( prefs == null || !prefs.dirty ) {
//            return;
//        }
//
//        String path = getPath( prefs.name );
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
//            Log.severe( "could not save namedPresets " + e);
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
//        prefs.dirty = false;
//    }
//
//
//    private static Prefs read( final String path ) {
//        char[] buff = new char[ 16384 ];
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
//        return (Prefs)xstream.fromXML( new String( buff ).substring( 0, res ) );
//    }
//}
