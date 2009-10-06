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
//public class UsingMixedProps {
//
//    private final Prop<String> foo;
//    private final Prop<Double> pi;
//
//    @Inject
//    public UsingMixedProps( @Named("foo") final Provider<Prop<String>> foo,
//                            @Named("pi") final Provider<Prop<Double>> pi) {
//        this.foo = foo.get();
//        this.pi = pi.get();
//    }
//
//    public Prop<String> getFoo() {
//        return foo;
//    }
//
//    public Prop<Double> getPi() {
//        return pi;
//    }
//}
