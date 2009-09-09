//package org.openCage.stroy.ui.popup;
//
//import net.java.dev.designgridlayout.DesignGridLayout;
//
//import javax.swing.*;
//import java.awt.*;
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
//public class OneDiffUI extends JFrame {
//    private final JTextField cmdField = new JTextField();
//    private final JButton typeButton = new JButton( "for all ..." );
//    private final JButton thisButton = new JButton( "for this file" );
//    private final JButton cmdButton = new JButton( ".." );
//
//    public OneDiffUI() {
//        setTitle( "Open With" );
//
//        JPanel top = new JPanel();
//        DesignGridLayout layout = new DesignGridLayout( top );
//        top.setLayout( layout );
//
//        layout.row().label( "command" ).add( cmdField, 5).add( cmdButton);
//        layout.row().label( "remember" ).add( typeButton ).add( thisButton);
//
//        setLayout( new BorderLayout());
//        add(top, BorderLayout.CENTER);
//
//        pack();
//    }
//}
