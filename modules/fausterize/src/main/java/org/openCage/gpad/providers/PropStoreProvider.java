//package org.openCage.gpad.providers;
//
//import com.google.inject.Inject;
//import com.google.inject.Provider;
//import org.openCage.gpad.Constants;
//import org.openCage.io.SingletonApp;
//import org.openCage.io.fspath.FSPathBuilder;
//import org.openCage.lang.BackgroundExecutor;
//import org.openCage.property.PersistingPropStore;
//import org.openCage.property.PropStore;
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
// * Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
// * All Rights Reserved.
// *
// * Contributor(s):
// ***** END LICENSE BLOCK *****/
//
//public class PropStoreProvider implements Provider<PropStore> {
//    private SingletonApp singleApp;
//    private BackgroundExecutor executor;
//
//    @Inject
//    public PropStoreProvider( SingletonApp singleApp, BackgroundExecutor executor ) {
//        this.singleApp = singleApp;
//        this.executor = executor;
//    }
//
//    @Override
//    public PropStore get(){
//        return new PersistingPropStore(
//                executor,
//                FSPathBuilder.getPreferences().add( Constants.FAUSTERIZE, "prefs.xml").toFile(),
//                null,
//                singleApp );
//    }
//}
