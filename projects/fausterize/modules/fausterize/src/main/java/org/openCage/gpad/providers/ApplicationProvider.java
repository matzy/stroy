//package org.openCage.gpad.providers;
//
//import com.google.inject.Inject;
//import com.google.inject.Provider;
//
//import com.google.inject.name.Named;
//import org.openCage.application.protocol.Application;
//import org.openCage.application.protocol.ApplicationFromConfig;
//import org.openCage.localization.Localize;
//
//import static org.openCage.gpad.Constants.FAUSTERIZE;
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
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//
//public class ApplicationProvider implements Provider<Application > {
//
//	@Inject private ApplicationFromConfig fromConfig;
//    @Inject @Named(FAUSTERIZE) Localize localize;
//
//	@Override
//    public Application get() {
//
//		return fromConfig.get( getClass().getResource( "FausterizeApp.xml" ),
//				               getClass().getResource( "faust.png" ),
//                               localize.localize( "org.openCage.fausterize.description" ));
//	}
//}
