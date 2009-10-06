//package org.openCage.util.iterator;
//
//import java.util.Iterator;
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
//public class T3It<A, B,C> implements Iterable<T3<A, B,C>>, Iterator<T3<A,B,C>> {
//
//    private final Iterator<A> it0;
//    private final Iterator<B> it1;
//    private final Iterator<C> it2;
//
//    public T3It( final Iterable<A> s, final Iterable<B> t, final Iterable<C> c) {
//        it0 = s.iterator();
//        it1 = t.iterator();
//        it2 = c.iterator();
//    }
//
//    public Iterator<T3<A,B,C>> iterator() {
//        return this;
//    }
//
//    public boolean hasNext() {
//        return it0.hasNext() && it1.hasNext() && it2.hasNext();
//    }
//
//    public T3<A,B,C> next() {
//        return new T3<A,B,C>( it0.next(), it1.next(), it2.next());
//    }
//
//    public void remove() {
//        throw new Error( "not impl" );
//    }
//}
