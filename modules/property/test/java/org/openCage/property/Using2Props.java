//package org.openCage.property;
//
//import org.openCage.property.protocol.Prop;
//import com.google.inject.Inject;
//import com.google.inject.Provider;
//import com.google.inject.name.Named;
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
//public class Using2Props {
//
//    private final Prop<String> foo;
//    private final Prop<String> woo;
//
//    @Inject
//    public Using2Props( @Named("foo") final Provider<Prop<String>> foo,
//                        @Named("woo") final Provider<Prop<String>> woo) {
//        this.foo = foo.get();
//        this.woo = woo.get();
//    }
//
//    public Prop<String> getFoo() {
//        return foo;
//    }
//
//    public Prop<String> getWoo() {
//        return woo;
//    }
//}
