//package org.openCage.util.prefs;
//
//import org.openCage.comphy.*;
//import org.openCage.comphy.Readable;
//import org.openCage.lang.listeners.Listeners;
//import org.openCage.lang.listeners.VoidListenerControl;
//import org.openCage.lang.listeners.VoidListeners;
//import org.openCage.util.logging.Log;
//
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.openCage.comphy.DereaderUtil.deread;
//
///***** BEGIN LICENSE BLOCK *****
// * Version: MPL 1.1
// *
// * The contents of this file are subject to the Mozilla Public License Version
// * 1.1 (the "License"); you may not use this file except in compliance with
// * the License. You may obtain a copy of the License at
// * http://www.mozilla.org/MPL/
// *
// * Software distributed under the License is distributed on an "AS IS" basis,
// * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
// * for the specific language governing rights and limitations under the
// * License.
// *
// * The Original Code is stroy code.
// *
// * The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
// * Portions created by Stephan Pfab are Copyright (C) 2006 - 2009.
// * All Rights Reserved.
// *
// * Contributor(s):
// ***** END LICENSE BLOCK *****/
//
///**
// * A list class with a marker for a selected element
// */
//public class ListSelectionProperty<T extends Property> implements Property {
//
//    protected ListProperty<T>  list;
//    protected T                selection;
//    private   VoidListeners    listeners = new VoidListeners();
//    private   Dereadalizer<Property> valueDereader;
//
////    public ListSelectionProperty(T sel, List<T> list) {
////        this.list = new ListProperty<T>("elements",null);
////        setSelection( sel );
////    }
//
//
////    public ListSelectionProperty( String sel, String ... entries) {
////        selection = sel;
////        list = new ListProperty<StringProperty>( "elems", new StringPropertyDereader() );
////
////        for ( String elem : entries ) {
////            list.add( new StringProperty(elem));
////        }
////
////        if ( !Arrays.asList(entries).contains(sel)) {
////            throw new IllegalArgumentException( "selection not in list" );
////        }
////    }
//
//    protected  ListSelectionProperty() {}
//
//    public T getSelection() {
//        return selection;
//    }
//
//    /**
//     * set the selected element of the list
//     * @param selection A valid element of the list
//     */
//    public void setSelection( T selection ) {
//        if ( !list.contains( selection ) ) {
//            Log.warning( "selection outside list: ignored" );
//            throw new IllegalArgumentException( "selection outside list"  );
//        }
//
//        this.selection =  selection;
//        listeners.shout();
//    }
//
//
//    public List<T> getList() {
//        return Collections.unmodifiableList( list );
//    }
//
//    @Override
//    public VoidListenerControl getListenerControl() {
//        return listeners;
//    }
//
//    @Override
//    public Readable toReadable() {
//        RMap rmap = new RMap();
//        rmap.put("list", list.toReadable() );
//        rmap.put("selection", selection.toReadable() );
//        return rmap;
//    }
//
//    public static class Dereader<T extends Property> extends DereadalizerMap<ListSelectionProperty<T>>  {
//
//        private final Dereadalizer<T> valueDereader;
//
//        public Dereader(Dereadalizer<T> valueDereader) {
//            this.valueDereader = valueDereader;
//        }
//
//        @Override
//        public ListSelectionProperty<T> fromMap(RMap map) {
//            ListSelectionProperty<T> ret = new ListSelectionProperty<T>();
//            ret.list = deread( new ListPropertyDereader<T>( valueDereader), map.get("list"));
//            ret.selection = deread( valueDereader, map.get("selection"));
//
//            return ret;
//        }
//    }
//}
