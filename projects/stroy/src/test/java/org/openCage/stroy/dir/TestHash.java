//package org.openCage.stroy.dir;
//
//import org.openCage.lang.functions.VF1;
//import org.openCage.lindwurm.FileUtils;
//
//import java.util.Map;
//import java.util.List;
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.io.File;
//
///***** BEGIN LICENSE BLOCK *****
// * BSD License (2 clause)
// * Copyright (c) 2006 - 2012, Stephan Pfab
// * All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *     * Redistributions of source code must retain the above copyright
// *       notice, this list of conditions and the following disclaimer.
// *     * Redistributions in binary form must reproduce the above copyright
// *       notice, this list of conditions and the following disclaimer in the
// *       documentation and/or other materials provided with the distribution.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// * DISCLAIMED. IN NO EVENT SHALL Stephan Pfab BE LIABLE FOR ANY
// * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//***** END LICENSE BLOCK *****/
//
//
//@SuppressWarnings( {"HardCodedStringLiteral"} )
//public class TestHash {
//
//    static private Map<Integer, List<String>> hashes = new HashMap<Integer, List<String>>();
//    static private int dup = 0;
//
//    public static void main(String[] args) {
//
//        fill( new File( "/Users/spfab/Documents/aaf-game/spfab_Cortex_A_intg"));
//
//
//
//    }
//
//    private static void fill(File file) {
//
//        if ( file.getName().equals( ".svn" ) ||
//                file.getName().equals( ".class" ) ||
//                file.getName().equals( ".DS_Store" ) ||
//                file.getName().equals( ".copyarea.db" ) ||
//                file.getName().equals( ".copyarea.dat" )) {
//            return;
//        }
//
//
//        if ( file.isDirectory() ) {
//            for ( File child : file.listFiles() ) {
//                fill( child );
//            }
//        } else {
//
//            if ( ! FileUtils.getExtension( file ).equals( "java" )) {
//                return;
//            }
//
//            FileUtils.withIterator( file, new VF1<Iterable<String>>() {
//                public void call( Iterable<String> iterable ) {
//                    for ( String line : iterable ) {
//                        if ( !hashes.containsKey( line.hashCode())) {
//                            hashes.put( line.hashCode(), new ArrayList<String>());
//                        }
//
//                        List<String> before  = hashes.get( line.hashCode() );
//
//                        boolean found = false;
//                        for ( String ll : before ) {
//                            if ( ll.equals(line )) {
//                                found = true;
//                                break;
//                            }
//                        }
//
//                        if ( !found ) {
//                            before.add( line );
//
//                            if ( before.size() > 1 ) {
//                                dup++;
//                                System.out.println("-- " + dup + " of " + hashes.size() );
//                                System.out.println("   " + line.hashCode());
//                                for ( String dd : before ) {
//                                    System.out.println("   " + dd );
//                                }
//                            }
//                        }
//
//                    }
//                }
//            } );
//
//        }
//    }
//}
