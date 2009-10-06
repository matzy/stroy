//package org.openCage.property.protocol;
//
//import org.openCage.utils.persistence.Persistable;
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
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//public class PropStoreImpl implements PropStore, Persistable {
//
//    private Map<String, Prop> store = new HashMap<String, Prop>();
//    private boolean           dirty = false;
//
//
//    public Prop get( String key ) {
//        return store.get( key );
//    }
//
//    public void init( String key, Prop val ) {
//        if ( store.get( key  ) != null ) {
//            // exists already
//            // TODO check typ
//            return;
//        }
//
//        // new prop
//
//        store.put( key, val );
//
//        // notify me if it changes in the future
//        val.addListener( new PropChangeListener() {
//            public void propChanged( Object txt ) {
//                dirty = true;
//            }
//        } );
//
//        // needs to be saved
//        dirty = true;
//    }
//
//    public boolean isDirty() {
//        return dirty;
//    }
//
//    public void setClean() {
//        dirty = false;
//    }
//}
