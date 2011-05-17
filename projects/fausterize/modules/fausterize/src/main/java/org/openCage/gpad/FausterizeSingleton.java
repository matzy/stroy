//package org.openCage.gpad;
//
//import com.google.inject.Provider;
//import org.openCage.io.fspath.FSPathBuilder;
//import org.openCage.io.FriendlySingletonApp;
//import org.openCage.io.SingletonApp;
//
///***** BEGIN LICENSE BLOCK *****
////* Version: MPL 1.1
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
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//
//public class FausterizeSingleton implements Provider<SingletonApp > {
//
////    @Inject
////    private Application app;
//
//    @Override
//    public SingletonApp get() {
//        return new FriendlySingletonApp( FSPathBuilder.getPreferences().add( "fausterize"/*app.gettName()*/, "application.runs").toFile());
//    }
//}
