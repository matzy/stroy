//package org.openCage.util.prefs;
//
//import javax.swing.*;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.util.Arrays;
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
//public class ComboBox extends JComboBox {
//
//    private final PrefItem<String> item;
//
//    public ComboBox( String key, boolean global  ) {
//        item = Prefs.getItem( key, global);
//
//
//        for ( String name : Arrays.asList("foo", "duh" ) /*item.getPossibles() */) {
//            super.addItem( name );
//        }
//
//        setSelectedItem( item.get() );
//
//        addActionListener( new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                item.set( (String)getSelectedItem() );
//            }
//        });
//
//        final ComboBox me = this;
//
//        item.addListener( new PreferencesChangeListener<String>() {
//            public void changed(String txt) {
//                me.setSelectedItem( txt );
//            }
//        });
//
//
//    }
//
//    public void setSelectedItem(Object object) {
//        super.setSelectedItem(object);
//
//        if ( object instanceof String ) {
//            item.set( (String)object);
//        }
//    }
//}
