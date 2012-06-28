//package org.openCage.util.prefs;
//
//import org.openCage.util.logging.Log;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
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
//public class Prefs {
//
//    private static Prefs theStore;
//
//    static String name = null ;
//    public static final String PROJECTS = "-projects-";
//
//    public static void setName( String nme ) {
//        name = nme;
//    }
//
//    private static synchronized Prefs get() {
//        if ( theStore != null ) {
//            return theStore;
//        }
//
//        if ( name == null ) {
//            Log.severe( "prefs file must have a name" );
//            throw new Error( "name not set" );
//        }
//
//        theStore = PrefPersi.load( name );
//        return theStore;
//
//    }
//
//    public static void setDirty() {
//        get().dirty = true;
//    }
//
//    Prefs() {
//        GlobalItem item = new GlobalItem();
//        globalItems.put(PROJECTS, item);
//    //    item.addPossible( "-default-");
//        item.set("-default-");
//    }
//
//    transient boolean dirty = false;
//
//    private Map<String,PrefItem> globalItems  = new HashMap<String, PrefItem>();
//    private Map<String,PrefItem> projectItems = new HashMap<String, PrefItem>();
//
//    public static String getCurrentProject() {
//        return (String)get().globalItems.get(PROJECTS).get();
//    }
//
//    public static void setProject( String proj ) {
//        get().setProj( proj );
//    }
//
//
//    public static PrefItem getProjectItem(String key) {
//        if ( ! get().projectItems.containsKey( key )) {
//            PrefItem item = new ProjectItem();
//            get().projectItems.put( key, item);
//
//            return item;
//        }
//
//        return get().projectItems.get( key );
//    }
//
////    public static TextFieldOld getTextField( PrefItem item) {
////        return new TextFieldOld( item );
////    }
//
//    public static PrefItem getItem(String key, boolean global) {
//        if ( global ) {
//            return getGlobalItem( key );
//        }
//        return getProjectItem( key );
//    }
//
//    private static PrefItem getGlobalItem(String key) {
//        if ( ! get().globalItems.containsKey( key )) {
//            PrefItem item = new GlobalItem();
//            get().globalItems.put( key, item);
//
//            return item;
//        }
//
//        return get().globalItems.get( key );
//    }
//
//    private void setProj(String proj) {
//
//        globalItems.get( PROJECTS ).set( proj );
//
//        for ( PrefItem item : projectItems.values() ) {
//            ((ProjectItem)item).projectChanged( proj );
//        }
//    }
//
//    public static PrefItem<ArrayList<String>> getProjectItemLists(String key, ArrayList<String> ini ) {
//        if ( ! get().projectItems.containsKey( key )) {
//            PrefItem<ArrayList<String>> item = new ProjectItemLists();
//            get().projectItems.put( key, item);
//        }
//
//        PrefItem<ArrayList<String>> item = get().projectItems.get( key );
//
//        if ( ini != null ) {
//            item.setInitials( ini );
//        }
//
//        return item;
//
//    }
//}
