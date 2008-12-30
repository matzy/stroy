//package org.openCage.utils.persistence;
//
//import org.openCage.util.logging.Log;
//import org.openCage.utils.lang.Saver;
//
//import java.util.Map;
//import java.util.HashMap;
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
//* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//
//public class Preferences implements Persistable {
//
//    // static
//
//    private static Preferences thePref;
//    private static String      name;
//
//    public static void setDirty() {
//        get().dirty = true;
//    }
//
//
//    public static void add( String key, PropOld item ) {
//        get().map.put( key, item );
//        setDirty();
//    }
//
//    private static synchronized Preferences get() {
//        if ( thePref == null ) {
//            Log.info( "preferences initialized" );
//            thePref = new PersistenceImpl<Preferences>( new Saver() ).load( new Preferences(), name);
//        }
//
//        return thePref;
//    }
//
//    /**
//     * enable inmemory testing only
//     */
//    public static synchronized void initForTest() {
//        if ( thePref != null ) {
//            return;
////            throw new IllegalStateException( "can't init an exisiting prefs" );
//        }
//        Log.severe( "test preferences initialized" );
//        thePref = new Preferences();
//    }
//
//    public static synchronized void clearAfterTest() {
//        thePref = null;
//    }
//
//    public static PropOld getItem(String key) {
//        return get().map.get( key );
//    }
//
//    public static void setName(String nme ) {
//        name = nme;
//    }
//
//    // object
//
//    private boolean dirty;
//    private Map<String, PropOld> map = new HashMap<String, PropOld>();
//
//
//    public boolean isDirty() {
//        return dirty;
//    }
//
//    public void setClean() {
//        dirty = false;
//    }
//
//}
