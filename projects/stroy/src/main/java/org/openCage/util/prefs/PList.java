//package org.openCage.util.prefs;
//
//import javax.swing.*;
//import java.util.List;
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
//public class PList extends JList {
//
//    private final PreferenceItem<List<String>> item;
//
//    public PList( final String key ) {
//
//        setModel( new DefaultListModel());
//
//        item = PreferenceStringList.create( key );
//
//        for ( String elem : item.get() ) {
//            ((DefaultListModel)getModel()).addElement( elem );
//        }
//
//    }
//
//    public PList( final String key, List<String> ini ) {
//
//        setModel( new DefaultListModel());
//
//        item = PreferenceStringList.create( key, ini );
//
//        for ( String elem : item.get() ) {
//            ((DefaultListModel)getModel()).addElement( elem );
//        }
//
//    }
//
//    public PreferenceItem<List<String>> getItem() {
//        return item;
//    }
//}
