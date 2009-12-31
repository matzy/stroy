//package org.openCage.util.app;
//
//import org.openCage.util.www.wikidot.WikiDotGen;
//
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Collection;
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
//public class Change {
//
//    public final int num;
//    public final String title;
//    public final String typ;
//
//    public String comment;
//    public String user;
//    public String version;
//    public List<String> tags = new ArrayList<String>();
//    public Integer original;
//    public List<Integer> refs = new ArrayList<Integer>();
//    public List<String> froms = new ArrayList<String>();
//    public List<String> solvers = new ArrayList<String>();
//
//    public Change( int num, String typ, String title ) {
//        this.num = num;
//        this.typ = typ;
//        this.title = title;
//    }
//
//    public Change user( String user ) {
//        this.user = user;
//        return this;
//    }
//
//    public Change implemented( String version ) {
//        this.version = version;
//        return this;
//    }
//
//    public Change tag( String tag ) {
//        tags.add( tag );
//        return this;
//    }
//
//    public Change from( String tag ) {
//        froms.add( tag );
//        return this;
//    }
//
//    public Change duplicate( int dup ) {
//        original = dup;
//        return this;
//    }
//
//    public Change ref( int other ) {
//        refs.add( other );
//        return this;
//    }
//
//    public Change comment( String comment ) {
//        this.comment = comment;
//        return this;
//    }
//
//
//    public void printWikiAncor() {
//        System.out.println(WikiDotGen.ancor( num ) );
//        System.out.println( "++++ " + fullTitle()  );
//
//        if ( version != null ) {
//            System.out.print( WikiDotGen.link( version, version));
//        } else {
//            System.out.print("open");
//        }
//
//        for ( String from : froms ) {
//            System.out.print( " " + WikiDotGen.link( from, from ));
//        }
//
//        for ( String from : solvers ) {
//            System.out.print( " " + WikiDotGen.link( from, from ));
//        }
//
//        for ( String tag : tags ) {
//            System.out.print( " " + WikiDotGen.link( tag, "" + tag ));
//        }
//
//        for ( Integer ref : refs ) {
//            System.out.print( " " + WikiDotGen.link( ref, "" + ref ));
//        }
//
//        System.out.println("");
//
//        if ( comment != null ) {
//            System.out.println( comment );
//        }
//    }
//
//    public void printWikiVersionAncor() {
//        if ( version == null ) {
//            throw new IllegalArgumentException( "null version no ancor" );
//        }
//        System.out.println(WikiDotGen.ancor( version ) );
//        System.out.println("+++ " + version );
//
//    }
//
//    public void printWikiLink() {
//        System.out.println( WikiDotGen.link( num, fullTitle() ));
//    }
//
//    private String fullTitle() {
//        return "(" + num + ") (" + typ + ") " + title;
//    }
//
//
//    public Collection<? extends String> getFroms() {
////        if ( froms.size() == 0 ) {
////            froms.add( "Stephan" );
////        }
//        return froms;
//    }
//
//    public boolean isFrom(String from) {
//        return getFroms().contains( from );
//    }
//
//
//    public boolean hasTag(String tag) {
//        return tags.contains( tag );
//    }
//
//    public Change solve( String person ) {
//        solvers.add( person);
//
//        return this;
//    }
//
//    public boolean isHelped(String person) {
//        return getHelpers().contains( person );
//    }
//
//    public Collection<? extends String> getHelpers() {
////        if ( solvers.size() == 0 && version != null ) {
////            solvers.add( "Stephan" );
////        }
//        return solvers;
//    }
//}
