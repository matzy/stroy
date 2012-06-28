//package org.openCage.stroy.locale;
//
//import org.openCage.util.prefs.PreferenceItem;
//import org.openCage.util.prefs.PreferencesChangeListener;
//import org.openCage.util.prefs.ListSelection;
//
//import javax.swing.*;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
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
//
//public class LocalizedComboBox extends JComboBox {
//
//    private final PreferenceItem<ListSelection<String>> item;
//    //@Named(value = "wfd")
//    //private Selection1 item1;
//    private final Map<String,String>                    reverse=  new HashMap<String, String>();
//
//    public LocalizedComboBox( final String key ) {
//
//        setModel( new DefaultComboBoxModel());
//
//        item = PListSelectionString.get( key );
//
//        for ( String elem : item.get().getList() ) {
//            String loc = Message.get( elem );
//            reverse.put( loc, elem );
//            ((DefaultComboBoxModel)getModel()).addElement( loc );
//        }
//
//        setSelectedItem( Message.get( item.get().getSelection() ));
//
//        addActionListener( new ActionListener() {
//            public void actionPerformed( ActionEvent actionEvent) {
//                item.get().setSelection(reverse.get( getSelectedItem() ));
//                item.setDirty();
//            }
//        });
//
//
//        final JComboBox me = this;
//
//        item.addListener( new PreferencesChangeListener<ListSelection<String>>() {
//            public void changed( ListSelection<String> lsel ) {
//                if ( lsel.getSelection().equals( reverse.get(me.getSelectedItem() ))) {
//                    return;
//                }
//
//                me.setSelectedItem( Message.get(lsel.getSelection() ));
//            }
//        });
//
//    }
//
////    public LocalizedComboBox( final String key, ListSelection<String> ini ) {
////
////        setModel( new DefaultComboBoxModel());
////
////        item = PListSelectionString.getOrCreate( key, ini );
////
////        for ( String elem : item.get().list ) {
////            String loc = Message.get( elem );
////            reverse.put( loc, elem );
////            ((DefaultComboBoxModel)getModel()).addElement( loc );
////        }
////
////        setSelectedItem( Message.get( item.get().getSelection() ));
////
////        addActionListener( new ActionListener() {
////            public void actionPerformed( ActionEvent actionEvent) {
////                item.get().setSelection(reverse.get( getSelectedItem() ));
////                item.setDirty();
////            }
////        });
////
////
////        final JComboBox me = this;
////
////        item.addListener( new PreferencesChangeListener<ListSelection<String>>() {
////            public void changed( ListSelection<String> lsel ) {
////                if ( lsel.getSelection().equals( reverse.get(me.getSelectedItem() ))) {
////                    return;
////                }
////
////                me.setSelectedItem( Message.get(lsel.getSelection() ));
////            }
////        });
////    }
//
////    public PreferenceItem<ListSelection<String>> getItem() {
////        return item;
////    }
//
//
//
//}
