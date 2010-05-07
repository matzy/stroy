//package org.openCage.gpad;
//
//import org.openCage.withResource.impl.WithImpl;
//import org.openCage.withResource.protocol.FileLineIterable;
//
//import java.io.File;
//import java.util.*;
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
///**
// * encode to words from novel faust
// */
//@Deprecated
//public class FaustWordNum implements TextEncoder<Character>{
//    List<String>[] num2line;
//    Set<String> knownLines = new HashSet();
//    Map<String,Integer> line2num = new HashMap<String,Integer>();
//
//    public FaustWordNum() {
//        num2line = new List[256];
//        for ( int i = 0; i < 256; ++i ) {
//            num2line[i] = new ArrayList<String>();
//        }
//
//        File ff = new File( getClass().getResource( "faust.txt" ).getPath());
//
//        int idx = 0;
//        FileLineIterable it = new WithImpl().getLineIteratorCloseInFinal( ff );
//        try {
//            for ( String str : it ) {
//                String[] words = str.split(" ");
//                for ( String word : words ) {
//                    if ( word.contains(":") || word.contains("(") || word.trim().equals( "" )) {
//                        continue;
//                    }
//
//                    if ( knownLines.contains(word )) {
//                        continue;
//                    }
//
//                    ++idx;
//                    knownLines.add( word );
//                    num2line[idx % 256].add(word);
//                    line2num.put( word, idx % 256 );
//                }
//            }
//        } finally {
//            it.close();
//        }
//    }
//
//    public String encode( Character ch ) {
//        List<String> posi =  num2line[ch]; // TODO limit 255 for now
//        return posi.get(((int)(Math.random() * posi.size())));
//    }
//
//    public Character decode( String line ) {
//        return (char)(line2num.get( line ).intValue());
//    }
//}
