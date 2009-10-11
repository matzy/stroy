package org.openCage.lang;

//package org.openCage.util.lang;
//
//import org.openCage.lang.protocol.Lazy;
//import junit.framework.TestCase;
//import org.openCage.utils.func.F0;
//import org.openCage.utils.lang.Ref;
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
//public class LazyTest extends TestCase {
//
//    public void testCalledLazy() {
//
//        final Ref<Integer> count = new Ref<Integer>( 0 );
//
//        Lazy<String> ll = new Lazy<String>( new F0<String>() {
//            public String c() {
//                count.o = count.o + 1;
//                return "foo";
//            }
//        } );
//
//        assertEquals( 0, count.o.intValue() );
//
//    }
//
//    public void testCalledOnce() {
//        final Ref<Integer> count = new Ref<Integer>( 0 );
//
//        Lazy<String> ll = new Lazy<String>( new F0<String>() {
//            public String c() {
//                count.o = count.o + 1;
//                return "foo";
//            }
//        } );
//
//        ll.get();
//        assertEquals( 1, count.o.intValue() );
//
//        ll.get();
//        assertEquals( 1, count.o.intValue() );
//
//    }
//
//    public void testVal() {
//        final Ref<Integer> count = new Ref<Integer>( 0 );
//
//        Lazy<String> ll = new Lazy<String>( new F0<String>() {
//            public String c() {
//                count.o = count.o + 1;
//                return "foo";
//            }
//        } );
//
//        assertEquals( "foo", ll.get() );
//    }
//
//    public void test1CalledLazy() {
//
//        final Ref<Integer> count = new Ref<Integer>( 0 );
//
//        Lazy1<String, Integer> ll = new Lazy1<String, Integer>( new F1<String, Integer>() {
//            public String c( Integer i) {
//                count.o = count.o + 1;
//                return "" + i;
//            }
//        } );
//
//        assertEquals( 0, count.o.intValue() );
//
//    }
//
//    public void test1CalledOnce() {
//        final Ref<Integer> count = new Ref<Integer>( 0 );
//
//        Lazy1<String, Integer> ll = new Lazy1<String, Integer>( new F1<String, Integer>() {
//            public String c( Integer i) {
//                count.o = count.o + 1;
//                return "" + i;
//            }
//        } );
//
//        assertEquals( 0, count.o.intValue() );
//
//        ll.get( 1 );
//        assertEquals( 1, count.o.intValue() );
//
//        ll.get( 2 );
//        assertEquals( 1, count.o.intValue() );
//
//    }
//
//    public void test1Val() {
//        final Ref<Integer> count = new Ref<Integer>( 0 );
//
//        Lazy1<String, Integer> ll = new Lazy1<String, Integer>( new F1<String, Integer>() {
//            public String c( Integer i) {
//                count.o = count.o + 1;
//                return "" + i;
//            }
//        } );
//
//        assertEquals( "123", ll.get(123) );
//        assertEquals( "123", ll.get(456) );
//    }
//
//    public void test1MethodReuse() {
//        final Ref<Integer> count = new Ref<Integer>( 0 );
//        final F1<String,Integer> meth = new F1<String, Integer>() {
//            public String c( Integer i) {
//                count.o = count.o + 1;
//                return "" + i;
//            }};
//
//
//        Lazy1<String, Integer> l1 = new Lazy1<String, Integer>( meth );
//        Lazy1<String, Integer> l2 = new Lazy1<String, Integer>( meth );
//
//        assertEquals( "123", l1.get(123) );
//        assertEquals( "456", l2.get(456) );
//    }
//
//    public void testNotNull() {
//        final Ref<Integer> count = new Ref<Integer>( 0 );
//        F1<String,Integer> meth = new F1<String, Integer>() {
//            public String c( Integer i) {
//                count.o = count.o + 1;
//                return "" + i;
//            }};
//
//
//        new Lazy1<String, Integer>( meth );
//
//        try {
//            new Lazy1<String, Integer>( null );
//        } catch ( IllegalArgumentException exp )  {
//            // expected
//        }
//
//        if ( 1 < 2 )  {
//            meth = null;
//        }
//
//        try {
//            new Lazy1<String, Integer>( meth );
//        } catch ( IllegalArgumentException exp )  {
//            // expected
//        }
//
//        new Lazy1<String, Integer>( meth );
//
//    }
//}
